package retupmoc.command;

import java.util.Arrays;
import java.util.List;

import retupmoc.RetupmocException;

/**
 * A parser that parses raw user input into the command type and parameters.
 */
public class CommandParser {

    /**
     * Parses the raw user input into a command.
     *
     * @param input the user input
     * @return the parsed command
     * @throws RetupmocException if the user input is invalid
     */
    public Command parse(String input) throws RetupmocException {
        String[] tokens = input.trim().split("\\s+");
        String commandTypeString = tokens[0];
        try {
            CommandType commandType = CommandType.valueOf(commandTypeString.toUpperCase());
            return switch (commandType) {
            case MARK, UNMARK, DELETE:
                yield new Command(commandType, parseParamsForFindTask(tokens));
            case TODO, FIND:
                yield new Command(commandType, parseParamsForToDoTask(tokens));
            case DEADLINE:
                yield new Command(commandType, parseParamsForDeadlineTask(tokens));
            case EVENT:
                yield new Command(commandType, parseParamsForEventTask(tokens));
            case HELP:
                yield new Command(commandType, parseHelp(tokens));
            default:
                yield new Command(commandType, List.of(tokens));

            };
        } catch (IllegalArgumentException e) {
            throw new RetupmocException("Unknown command: " + commandTypeString);
        }
    }

    private static List<String> parseHelp(String ... tokens) {
        try {
            return List.of(tokens[1].toUpperCase());
        } catch (IndexOutOfBoundsException e) {
            return List.of("HELP");
        }
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
