package retupmoc.command;

import java.util.List;

import org.junit.jupiter.api.Test;

import retupmoc.JavanMynaException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommandParserTest {

    @Test
    public void parseParamsForFindTask_invalidNumber_exceptionThrown() {

        CommandParser parser = new CommandParser();
        String input = "Mark hi";
        String input2 = "Unmark hi";
        String input3 = "Delete hi";

        JavanMynaException exception = assertThrows(JavanMynaException.class, () -> parser.parse(input));
        JavanMynaException exception2 = assertThrows(JavanMynaException.class, () -> parser.parse(input2));
        JavanMynaException exception3 = assertThrows(JavanMynaException.class, () -> parser.parse(input3));
        assertEquals("Invalid task number", exception.getMessage());
        assertEquals("Invalid task number", exception2.getMessage());
        assertEquals("Invalid task number", exception3.getMessage());

    }

    @Test
    public void parseParamsForFindTask_validNumber_correctParams() throws JavanMynaException {

        CommandParser parser = new CommandParser();
        String input = "Mark 1";
        String input2 = "Unmark 2";
        String input3 = "Delete 3 blah blah blah";

        assertEquals(List.of("0"), parser.parse(input).parameters());
        assertEquals(List.of("1"), parser.parse(input2).parameters());
        assertEquals(List.of("2"), parser.parse(input3).parameters());

    }

    @Test
    public void parseParamsForToDoTask_noDescription_exceptionThrown() {

        CommandParser parser = new CommandParser();
        String input = "todo";
        String input2 = "todo ";

        JavanMynaException exception = assertThrows(JavanMynaException.class, () -> parser.parse(input));
        JavanMynaException exception2 = assertThrows(JavanMynaException.class, () -> parser.parse(input2));
        assertEquals("The description of a Task cannot be empty", exception.getMessage());
        assertEquals("The description of a Task cannot be empty", exception2.getMessage());

    }

    @Test
    public void parseParamsForToDoTask_haveDescription_correctParams() throws JavanMynaException {

        CommandParser parser = new CommandParser();
        String input = "todo abc";
        String input2 = "tOdO todo";
        String input3 = "todo abc def 12345";
        String input4 = "todo 12345 abc def ";
        String input5 = "todo abc /by 01/01/2025";

        assertEquals(List.of("abc"), parser.parse(input).parameters());
        assertEquals(List.of("todo"), parser.parse(input2).parameters());
        assertEquals(List.of("abc def 12345"), parser.parse(input3).parameters());
        assertEquals(List.of("12345 abc def"), parser.parse(input4).parameters());
        assertEquals(List.of("abc /by 01/01/2025"), parser.parse(input5).parameters());

    }

    @Test
    public void parseParamsForDeadlineTask_noDescription_exceptionThrown() {

        CommandParser parser = new CommandParser();
        String input = "deadline";
        String input2 = "deadline ";
        String input3 = "deadline /by 01/01/2026 1300";

        JavanMynaException exception = assertThrows(JavanMynaException.class, () -> parser.parse(input));
        JavanMynaException exception2 = assertThrows(JavanMynaException.class, () -> parser.parse(input2));
        JavanMynaException exception3 = assertThrows(JavanMynaException.class, () -> parser.parse(input3));
        assertEquals("The description of a Task cannot be empty", exception.getMessage());
        assertEquals("The description of a Task cannot be empty", exception2.getMessage());
        assertEquals("The description of a Task cannot be empty", exception3.getMessage());

    }

    @Test
    public void parseParamsForDeadlineTask_haveDescription_correctParams() throws JavanMynaException {

        CommandParser parser = new CommandParser();
        String input = "deadline abc /by 01/01/2025";
        String input2 = "dEaDlInE deadline /by today ";
        String input3 = "deadline abc def 12345";
        String input4 = "deadline 12345 abc def /by today 1pm";
        String input5 = "deadline abc /by 01/01/2025 1300";

        assertEquals(List.of("abc", "01/01/2025"), parser.parse(input).parameters());
        assertEquals(List.of("deadline", "today"), parser.parse(input2).parameters());
        assertEquals(List.of("abc def 12345", ""), parser.parse(input3).parameters());
        assertEquals(List.of("12345 abc def", "today 1pm"), parser.parse(input4).parameters());
        assertEquals(List.of("abc", "01/01/2025 1300"), parser.parse(input5).parameters());

    }

    @Test
    public void parseParamsForEventTask_noDescription_exceptionThrown() {

        CommandParser parser = new CommandParser();
        String input = "event";
        String input2 = "event ";
        String input3 = "event /from 01/01/2026 /to 02/01/2026";

        JavanMynaException exception = assertThrows(JavanMynaException.class, () -> parser.parse(input));
        JavanMynaException exception2 = assertThrows(JavanMynaException.class, () -> parser.parse(input2));
        JavanMynaException exception3 = assertThrows(JavanMynaException.class, () -> parser.parse(input3));
        assertEquals("The description of a Task cannot be empty", exception.getMessage());
        assertEquals("The description of a Task cannot be empty", exception2.getMessage());
        assertEquals("The description of a Task cannot be empty", exception3.getMessage());

    }

    @Test
    public void parseParamsForEventTask_haveDescription_correctParams() throws JavanMynaException {

        CommandParser parser = new CommandParser();
        String input = "event abc /from 01/01/2025 /to 02/01/2025";
        String input2 = "eVeNt event /from 01/01/2025";
        String input3 = "event abc def 12345";
        String input4 = "event 12345 abc def /to 02/01/2025";
        String input5 = "event abc /from 01/01/2025 1300 /to 02/01/2025 0000";

        assertEquals(List.of("abc", "01/01/2025", "02/01/2025"), parser.parse(input).parameters());
        assertEquals(List.of("event", "01/01/2025", ""), parser.parse(input2).parameters());
        assertEquals(List.of("abc def 12345", "", ""), parser.parse(input3).parameters());
        assertEquals(List.of("12345 abc def", "", "02/01/2025"), parser.parse(input4).parameters());
        assertEquals(List.of("abc", "01/01/2025 1300", "02/01/2025 0000"), parser.parse(input5).parameters());

    }

}
