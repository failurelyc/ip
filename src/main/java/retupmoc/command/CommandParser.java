package retupmoc.command;

import retupmoc.RetupmocException;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandParser {

    private final Scanner s;

    public CommandParser() {
        s = new Scanner(System.in);
    }

    public String getUserInput() {
        return s.nextLine();
    }

    public Command parse(String input) throws RetupmocException {
        String[] tokens = input.trim().split("\\s+");
        String commandType = tokens[0].toLowerCase();
        return switch (commandType) {
            case "mark", "unmark", "delete":
                yield new Command(commandType, parseParamsForFindTask(tokens));
            case "todo":
                yield new Command(commandType, parseParamsForToDoTask(tokens));
            case "deadline":
                yield new Command(commandType, parseParamsForDeadlineTask(tokens));
            case "event":
                yield new Command(commandType, parseParamsForEventTask(tokens));
            default:
                yield new Command(commandType, List.of());

        };
    }

    private static List<String> parseParamsForFindTask(String[] tokens) throws RetupmocException {
        try {
            return List.of(String.valueOf(Integer.parseInt(tokens[1]) - 1));
        } catch (NumberFormatException e) {
            throw new RetupmocException("Invalid task number");
        }
    }

    private static List<String> parseParamsForToDoTask(String[] tokens) throws RetupmocException {
        String description = String.join(" ", Arrays.stream(tokens).skip(1).toList());
        return List.of(description);
    }

    private static List<String> parseParamsForDeadlineTask(String[] tokens) throws RetupmocException {
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
        if (description.isEmpty())
            throw new RetupmocException("The description of a Task cannot be empty");
        else {
            return List.of(description, by);
        }

    }

    private static List<String> parseParamsForEventTask(String[] tokens) throws RetupmocException {
        String description = String
                .join(
                        " ",
                        Arrays.stream(tokens)
                                .takeWhile(word -> !"/from".equals(word))
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
        if (description.isEmpty())
            throw new RetupmocException("The description of a Task cannot be empty");
        else {
            return List.of(description, start, end);
        }
    }

}
