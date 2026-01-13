import java.util.ArrayList;
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
            processUserInput(input);
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

    private static void processUserInput(String input) {
        String[] command = input.trim().split("\\s+");
        switch (command[0].toLowerCase()) {
            case "list":
                displayList();
                break;
            case "mark":
                markTaskDone(Integer.parseInt(command[1]) - 1);
                break;
            case "unmark":
                markTaskNotDone(Integer.parseInt(command[1]) - 1);
                break;
            case "bye":
                printGoodbye();
                printHorizontalLine();
                System.exit(0);
            default:
                addToList(input);
        }
    }

    private static void addToList(String description) {
        list.add(new Task(description));
        System.out.println("added: " + description);
    }

    private static void displayList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private static void markTaskDone(int taskNo) {
        Task task = list.get(taskNo);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    private static void markTaskNotDone(int taskNo) {
        Task task = list.get(taskNo);
        task.markAsNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

}
