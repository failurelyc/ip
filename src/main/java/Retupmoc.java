import java.util.Scanner;

public class Retupmoc {

    private static final Scanner s = new Scanner(System.in);

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
            case "bye":
                printGoodbye();
                printHorizontalLine();
                System.exit(0);
            default:
                System.out.println(input);
        }
    }


}
