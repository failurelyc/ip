package retupmoc;

import java.time.format.DateTimeParseException;

import retupmoc.command.Command;
import retupmoc.command.CommandParser;
import retupmoc.storage.ListFile;
import retupmoc.storage.TaskList;
import retupmoc.tasks.Deadline;
import retupmoc.tasks.Event;
import retupmoc.tasks.Task;
import retupmoc.tasks.ToDo;
import retupmoc.ui.Ui;

/**
 * The main class of this application.
 */
public class Retupmoc {

    private final TaskList list;
    private final ListFile file;
    private final Ui ui;
    private final CommandParser parser;

    /**
     * Constructs a new Retupmoc which initialises the other components of this application.
     *
     * @param fileLocation The path to the saved data
     */
    public Retupmoc(String fileLocation) {
        parser = new CommandParser();
        file = new ListFile(fileLocation);
        list = TaskList.deserialize(file.readList());
        ui = new Ui();
    }

    /**
     * Processes user input and runs the user command if valid.
     *
     * @param input the user input
     * @return the chatbot output
     */
    public String run(String input) {

        try {
            Command command = parser.parse(input);
            return runCommand(command);
        } catch (RetupmocException e) {
            return ui.printErrorMessage(e);
        }

    }

    /**
     * Runs the parsed command.
     *
     * @param command The parsed command
     * @return the chatbot output
     * @throws RetupmocException If an exception occurs when executing the command
     */
    private String runCommand(Command command) throws RetupmocException {
        return switch (command.commandType()) {
        case GREET:
            yield ui.printGreeting();
        case LIST:
            yield ui.displayList(list);
        case MARK:
            assert !command.parameters.isEmpty();
            yield markTaskDone(Integer.parseInt(command.parameters.get(0)));
        case UNMARK:
            assert !command.parameters.isEmpty();
            yield markTaskNotDone(Integer.parseInt(command.parameters.get(0)));
        case BYE:
            yield ui.printGoodbye();
        case TODO:
            assert !command.parameters.isEmpty();
            yield addTask(new ToDo(command.parameters.get(0)));
        case DEADLINE:
            try {
                yield addTask(new Deadline(command.parameters().get(0), command.parameters().get(1)));
            } catch (DateTimeParseException e) {
                throw new RetupmocException("Date format should be " + Deadline.INPUT_FORMAT + ". Time is optional");
            }
        case EVENT:
            assert command.parameters.size() >= 3;
            try {
                yield addTask(
                        new Event(
                                command.parameters().get(0),
                                command.parameters().get(1),
                                command.parameters().get(2)
                        )
                );
            } catch (DateTimeParseException e) {
                throw new RetupmocException("Date format should be " + Deadline.INPUT_FORMAT + ". Time is optional");
            }
        case DELETE:
            assert !command.parameters.isEmpty();
            yield removeTask(Integer.parseInt(command.parameters().get(0)));
        case FIND:
            assert !command.parameters.isEmpty();
            yield ui.displayList(list.findTasks(command.parameters().get(0)));
        default:
            throw new RetupmocException("Unknown command: " + command.commandType());
        };
    }

    /**
     * Removes the task from the list and returns the chatbot output.
     *
     * @param taskNo The index of the task in the list
     * @return the chatbot output
     * @throws RetupmocException If the task is not found
     */
    private String removeTask(int taskNo) throws RetupmocException {
        try {
            Task task = list.removeTask(taskNo);
            file.writeList(list.serialize());
            return ui.printTaskRemovalSuccess(task) + "\n" + ui.printNoOfTask(list);
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    /**
     * Marks a task as done and returns the chatbot output.
     *
     * @param taskNo The index of the task in the list
     * @return the chatbot output
     * @throws RetupmocException If the task is not found
     */
    private String markTaskDone(int taskNo) throws RetupmocException {
        Task task = list.findTask(taskNo);
        task.markAsDone();
        file.writeList(list.serialize());
        return ui.printMarkTaskDone(task);
    }

    /**
     * Marks a task as not done and returns the chatbot output.
     *
     * @param taskNo The index of the task in the list
     * @return the chatbot output
     * @throws RetupmocException If the task is not found
     */
    private String markTaskNotDone(int taskNo) throws RetupmocException {
        Task task = list.findTask(taskNo);
        task.markAsNotDone();
        file.writeList(list.serialize());
        return ui.printMarkTaskNotDone(task);
    }

    /**
     * Adds the task to the list and returns the chatbot output.
     *
     * @param task The task to be added
     * @return the chatbot output
     */
    private String addTask(Task task) {
        list.addTask(task);
        file.writeList(list.serialize());
        return ui.printAddTaskSuccess(task) + "\n" + ui.printNoOfTask(list);
    }

}
