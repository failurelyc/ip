
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class Retupmoc {

    private static final Scanner s = new Scanner(System.in);
    private final TaskList list;
    private final ListFile file;

    public Retupmoc(String fileLocation) {
        file = new ListFile(fileLocation);
        list = TaskList.deserialize(file.readList());
    }

    public void run() {
        printHorizontalLine();
        printGreeting();
        printHorizontalLine();

        while (true) {
            String input = getUserInput();
            printHorizontalLine();
            try {
                processUserInput(input);
            } catch (RetupmocException e) {
                System.out.println(e.getMessage());
            }
            printHorizontalLine();
        }
    }

    private void printGreeting() {
        System.out.println(" Hello! I'm Retupmoc");
        System.out.println(" What can I do for you?");
    }

    private void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private void printGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private String getUserInput() {
        return s.nextLine();
    }

    private void processUserInput(String input) throws RetupmocException {
        String[] tokens = input.trim().split("\\s+");
        switch (tokens[0].toLowerCase()) {
            case "list":
                displayList();
                break;
            case "mark":
                markTaskDone(convertInputToTaskNo(tokens));
                break;
            case "unmark":
                markTaskNotDone(convertInputToTaskNo(tokens));
                break;
            case "bye":
                printGoodbye();
                printHorizontalLine();
                System.exit(0);
            case "todo":
                addToDo(tokens);
                break;
            case "deadline":
                addDeadline(tokens);
                break;
            case "event":
                addEvent(tokens);
                break;
            case "delete":
                removeTask(convertInputToTaskNo(tokens));
                break;
            default:
                throw new RetupmocException("Unknown command: " + tokens[0]);
        }
    }

    private void displayList() {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    private int convertInputToTaskNo(String[] input) throws RetupmocException {
        try {
            return Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new RetupmocException("Invalid task number: " + input[1]);
        }
    }

    private void removeTask(int taskNo) throws RetupmocException {
        try {
            Task task = list.removeTask(taskNo);
            file.writeList(list.serialize());
            System.out.println("Noted: I've removed this task:");
            System.out.println(task);
            System.out.println("Now you have " + list.getNoOfTasks() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    private void markTaskDone(int taskNo) throws RetupmocException {
        Task task = list.findTask(taskNo);
        task.markAsDone();
        file.writeList(list.serialize());
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    private void markTaskNotDone(int taskNo) throws RetupmocException {
        Task task = list.findTask(taskNo);
        task.markAsNotDone();
        file.writeList(list.serialize());
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    private void addToDo(String[] input) throws RetupmocException {
        String description = String.join(" ", Arrays.stream(input).skip(1).toList());
        if (description.isEmpty())
            throw new RetupmocException("The description of a Task cannot be empty");
        addTask(new ToDo(description));
    }

    private void addDeadline(String[] input) throws RetupmocException {
        String description = String.join(" ", Arrays.stream(input).takeWhile(word -> !"/by".equals(word)).skip(1).toList());
        String by = String.join(" ", Arrays.stream(input).dropWhile(word -> !"/by".equals(word)).skip(1).toList());
        if (description.isEmpty())
            throw new RetupmocException("The description of a Task cannot be empty");
        try {
            addTask(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            throw new RetupmocException("Date format should be " + Deadline.INPUT_FORMAT + ". Time is optional");
        }
    }

    private void addEvent(String[] input) throws RetupmocException {
        String description = String.join(" ", Arrays.stream(input).takeWhile(word -> !"/from".equals(word)).skip(1).toList());
        String start = String.join(" ", Arrays.stream(input).dropWhile(word -> !"/from".equals(word)).skip(1).takeWhile(word -> !"/to".equals(word)).toList());
        String end = String.join(" ", Arrays.stream(input).dropWhile(word -> !"/to".equals(word)).skip(1).toList());
        if (description.isEmpty())
            throw new RetupmocException("The description of a Task cannot be empty");
        try {
            addTask(new Event(description, start, end));
        } catch (DateTimeParseException e) {
            throw new RetupmocException("Date format should be " + Deadline.INPUT_FORMAT + ". Time is optional");
        }
    }

    private void addTask(Task task) {
        list.addTask(task);
        file.writeList(list.serialize());
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.getNoOfTasks() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Retupmoc retupmoc = new Retupmoc("./saved_data");
        retupmoc.run();
    }
}
