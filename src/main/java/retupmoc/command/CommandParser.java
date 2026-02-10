package retupmoc.command;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import retupmoc.RetupmocException;

/**
 * A parser that parses raw user input into the command type and parameters.
 */
public class CommandParser {

    private final Scanner s;

    /**
     * Constructs a new CommandParser with System.in as the input stream.
     */
    public CommandParser() {
        s = new Scanner(System.in);
    }

    /**
     * Obtains the next user input.
     *
     * @return the user input
     */
    public String getUserInput() {
        return s.nextLine();
    }

    /**
     * Parses the raw user input into a command.
     *
     * @param input the user input
     * @return the parsed command
     * @throws RetupmocException if the user input is invalid
     */
    public Command parse(String input) throws RetupmocException {
        String[] tokens = input.trim().split("\\s+");
        String commandType = tokens[0].toLowerCase();
        return switch (commandType) {
            case "mark", "unmark", "delete":
                yield new Command(commandType, parseParamsForFindTask(tokens));
            case "todo", "find":
                yield new Command(commandType, parseParamsForToDoTask(tokens));
            case "deadline":
                yield new Command(commandType, parseParamsForDeadlineTask(tokens));
            case "event":
                yield new Command(commandType, parseParamsForEventTask(tokens));
            default:
                yield new Command(commandType, List.of());

        };
    }

    /**
     * Parses the user input into a command for finding a task.
     *
     * @param tokens each word in the user input
     * @return the parsed command
     * @throws RetupmocException if the task number in the user input is invalid
     */
    private static List<String> parseParamsForFindTask(String ... tokens) throws RetupmocException {
        try {
            return List.of(String.valueOf(Integer.parseInt(tokens[1]) - 1));
        } catch (NumberFormatException e) {
            throw new RetupmocException("Invalid task number");
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("No task specified");
        }
    }

    /**
     * Parses the user input into a command for adding a task.
     *
     * @param tokens each word in the user input
     * @return the parsed command
     */
    private static List<String> parseParamsForToDoTask(String ... tokens) throws RetupmocException {
        String description = String.join(" ", Arrays.stream(tokens).skip(1).toList());
        if (description.isEmpty()) {
            throw new RetupmocException("The description of a Task cannot be empty");
        }
        return List.of(description);
    }

    /**
     * Parses the user input into a command for adding a Deadline task.
     *
     * @param tokens each word in the user input
     * @return the parsed command
     * @throws RetupmocException if the task description in the user input is empty
     */
    private static List<String> parseParamsForDeadlineTask(String ... tokens) throws RetupmocException {
        String description = String
                .join(
                        " ",
                        Arrays.stream(tokens)
                                .takeWhile(word -> !"/by".equals(word))
                                .skip(1)
                                .toList()
                );
        String by = String
                .join(" ",
                        Arrays.stream(tokens)
                                .dropWhile(word -> !"/by".equals(word))
                                .skip(1)
                                .toList()
                );
        if (description.isEmpty()) {
            throw new RetupmocException("The description of a Task cannot be empty");
        } else {
            return List.of(description, by);
        }

    }

    /**
     * Parses the user input into a command for adding an Event task.
     *
     * @param tokens each word in the user input
     * @return the parsed command
     * @throws RetupmocException if the task description in the user input is empty
     */
    private static List<String> parseParamsForEventTask(String ... tokens) throws RetupmocException {
        String description = String
                .join(
                        " ",
                        Arrays.stream(tokens)
                                .takeWhile(word -> !"/from".equals(word) && !"/to".equals(word))
                                .skip(1)
                                .toList()
                );
        String start = String
                .join(
                        " ",
                        Arrays.stream(tokens)
                                .dropWhile(word -> !"/from".equals(word))
                                .skip(1)
                                .takeWhile(word -> !"/to".equals(word))
                                .toList()
                );
        String end = String
                .join(
                        " ",
                        Arrays.stream(tokens)
                                .dropWhile(word -> !"/to".equals(word))
                                .skip(1)
                                .toList()
                );
        if (description.isEmpty()) {
            throw new RetupmocException("The description of a Task cannot be empty");
        } else {
            return List.of(description, start, end);
        }
    }

}
