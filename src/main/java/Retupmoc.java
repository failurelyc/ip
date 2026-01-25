
import java.time.format.DateTimeParseException;

public class Retupmoc {

    private final TaskList list;
    private final ListFile file;
    private final Ui ui;
    private final CommandParser parser;

    public Retupmoc(String fileLocation) {
        parser = new CommandParser();
        file = new ListFile(fileLocation);
        list = TaskList.deserialize(file.readList());
        ui = new Ui();
    }

    public void run() {
        ui.printHorizontalLine();
        ui.printGreeting();
        ui.printHorizontalLine();

        while (true) {
            String input = parser.getUserInput();
            ui.printHorizontalLine();
            try {
                Command command = parser.parse(input);
                runCommand(command);
            } catch (RetupmocException e) {
                ui.printErrorMessage(e);
            }
            ui.printHorizontalLine();
        }
    }

    private void runCommand(Command command) throws RetupmocException {
        switch (command.commandType) {
            case "list":
                ui.displayList(list);
                break;
            case "mark":
                markTaskDone(Integer.parseInt(command.parameters.get(0)));
                break;
            case "unmark":
                markTaskNotDone(Integer.parseInt(command.parameters.get(0)));
                break;
            case "bye":
                ui.printGoodbye();
                ui.printHorizontalLine();
                System.exit(0);
            case "todo":
                addTask(new ToDo(command.parameters.get(0)));
                break;
            case "deadline":
                try {
                    addTask(new Deadline(command.parameters.get(0), command.parameters.get(1)));
                } catch (DateTimeParseException e) {
                    throw new RetupmocException("Date format should be " + Deadline.INPUT_FORMAT + ". Time is optional");
                }
                break;
            case "event":
                try {
                    addTask(
                            new Event(
                                    command.parameters.get(0),
                                    command.parameters.get(1),
                                    command.parameters.get(2)
                            )
                    );
                } catch (DateTimeParseException e) {
                    throw new RetupmocException("Date format should be " + Deadline.INPUT_FORMAT + ". Time is optional");
                }
                break;
            case "delete":
                removeTask(Integer.parseInt(command.parameters.get(0)));
                break;
            default:
                throw new RetupmocException("Unknown command: " + command.commandType);
        }
    }

    private void removeTask(int taskNo) throws RetupmocException {
        try {
            Task task = list.removeTask(taskNo);
            file.writeList(list.serialize());
            ui.printTaskRemovalSuccess(task);
            ui.printNoOfTask(list);
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    private void markTaskDone(int taskNo) throws RetupmocException {
        Task task = list.findTask(taskNo);
        task.markAsDone();
        file.writeList(list.serialize());
        ui.printMarkTaskDone(task);
    }

    private void markTaskNotDone(int taskNo) throws RetupmocException {
        Task task = list.findTask(taskNo);
        task.markAsNotDone();
        file.writeList(list.serialize());
        ui.printMarkTaskNotDone(task);
    }

    private void addTask(Task task) {
        list.addTask(task);
        file.writeList(list.serialize());
        ui.printAddTaskSuccess(task);
        ui.printNoOfTask(list);
    }

    public static void main(String[] args) {
        Retupmoc retupmoc = new Retupmoc("./saved_data");
        retupmoc.run();
    }
}
