import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Retupmoc {

    private static final Scanner s = new Scanner(System.in);
    private static final List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
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

    private static void printGreeting() {
        System.out.println(" Hello! I'm Retupmoc");
        System.out.println(" What can I do for you?");
    }

    private static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    private static void printGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    private static String getUserInput() {
        return s.nextLine();
    }

    private static void processUserInput(String input) throws RetupmocException {
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

    private static void displayList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private static int convertInputToTaskNo(String[] input) throws RetupmocException {
        try {
            return Integer.parseInt(input[1]) - 1;
        } catch (NumberFormatException e) {
            throw new RetupmocException("Invalid task number: " + input[1]);
        }
    }

    private static Task findTask(int taskNo) throws RetupmocException {
        try {
            return list.get(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    private static void removeTask(int taskNo) throws RetupmocException {
        try {
            Task task = list.remove(taskNo);
            System.out.println("Noted: I've removed this task:");
            System.out.println(task);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    private static void markTaskDone(int taskNo) throws RetupmocException {
        Task task = findTask(taskNo);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    private static void markTaskNotDone(int taskNo) throws RetupmocException {
        Task task = findTask(taskNo);
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    private static void addToDo(String[] input) throws RetupmocException {
        String description = String.join(" ", Arrays.stream(input).skip(1).toList());
        if (description.isEmpty())
            throw new RetupmocException("The description of a Task cannot be empty");
        addTask(new ToDo(description));
    }

    private static void addDeadline(String[] input) throws RetupmocException {
        String description = String.join(" ", Arrays.stream(input).takeWhile(word -> !"/by".equals(word)).skip(1).toList());
        String by = String.join(" ", Arrays.stream(input).dropWhile(word -> !"/by".equals(word)).skip(1).toList());
        if (description.isEmpty())
            throw new RetupmocException("The description of a Task cannot be empty");
        addTask(new Deadline(description, by));
    }

    private static void addEvent(String[] input) throws RetupmocException {
        String description = String.join(" ", Arrays.stream(input).takeWhile(word -> !"/from".equals(word)).skip(1).toList());
        String start = String.join(" ", Arrays.stream(input).dropWhile(word -> !"/from".equals(word)).skip(1).takeWhile(word -> !"/to".equals(word)).toList());
        String end = String.join(" ", Arrays.stream(input).dropWhile(word -> !"/to".equals(word)).skip(1).toList());
        if (description.isEmpty())
            throw new RetupmocException("The description of a Task cannot be empty");
        addTask(new Event(description, start, end));
    }

    private static void addTask(Task task) {
        list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

}
