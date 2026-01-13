import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Retupmoc {

    private static final Scanner s = new Scanner(System.in);
    private static final List<String> list = new ArrayList<>();

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
        switch (input.toLowerCase()) {
            case "list":
                displayList();
                break;
            case "bye":
                printGoodbye();
                printHorizontalLine();
                System.exit(0);
            default:
                addToList(input);
        }
    }

    private static void addToList(String task) {
        list.add(task);
        System.out.println("added: " + task);
    }

    private static void displayList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

}
