package retupmoc.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @Test
    public void testContainsDescription() {

        Task task = new ToDo("abc");
        Task task2 = new ToDo("abc def 12345");
        Task task3 = new ToDo("Abc def 12345");
        Task task4 = new ToDo("12345 abc def");
        Task task5 = new ToDo("ABC");

        String description = "abc";
        String description2 = "DEF";
        String description3 = "H";
        String description4 = "def 123";
        String description5 = "45 abc";

        assertTrue(task.containsDescription(description));
        assertTrue(task2.containsDescription(description));
        assertTrue(task3.containsDescription(description));
        assertTrue(task4.containsDescription(description));
        assertTrue(task5.containsDescription(description));

        assertFalse(task.containsDescription(description2));
        assertTrue(task2.containsDescription(description2));
        assertTrue(task3.containsDescription(description2));
        assertTrue(task4.containsDescription(description2));
        assertFalse(task5.containsDescription(description2));

        assertFalse(task.containsDescription(description3));
        assertFalse(task2.containsDescription(description3));
        assertFalse(task3.containsDescription(description3));
        assertFalse(task4.containsDescription(description3));
        assertFalse(task5.containsDescription(description3));

        assertFalse(task.containsDescription(description4));
        assertTrue(task2.containsDescription(description4));
        assertTrue(task3.containsDescription(description4));
        assertFalse(task4.containsDescription(description4));
        assertFalse(task5.containsDescription(description4));

        assertFalse(task.containsDescription(description5));
        assertFalse(task2.containsDescription(description5));
        assertFalse(task3.containsDescription(description5));
        assertTrue(task4.containsDescription(description5));
        assertFalse(task5.containsDescription(description5));

    }

    @Test
    public void testDeserializeToDoTask() {

        String line = "ToDo,abc,true";
        String line2 = "ToDo,abc,false";
        String line3 = "ToDo,abc def 12345,false";
        String line4 = "ToDo,12345 abc def,true";
        String line5 = "ToDo,,true";

        Task task = Task.deserialize(line);
        Task task2 = Task.deserialize(line2);
        Task task3 = Task.deserialize(line3);
        Task task4 = Task.deserialize(line4);
        Task task5 = Task.deserialize(line5);

        assertInstanceOf(ToDo.class, task);
        assertEquals("abc", task.description);
        assertTrue(task.isDone);

        assertInstanceOf(ToDo.class, task2);
        assertEquals("abc", task2.description);
        assertFalse(task2.isDone);

        assertInstanceOf(ToDo.class, task3);
        assertEquals("abc def 12345", task3.description);
        assertFalse(task3.isDone);

        assertInstanceOf(ToDo.class, task4);
        assertEquals("12345 abc def", task4.description);
        assertTrue(task4.isDone);

        assertInstanceOf(ToDo.class, task5);
        assertEquals("", task5.description);
        assertTrue(task5.isDone);

    }

    @Test
    public void testSerializeToDoTask() {

        String line = "ToDo,abc,true";
        String line2 = "ToDo,abc,false";
        String line3 = "ToDo,abc def 12345,false";
        String line4 = "ToDo,12345 abc def,true";
        String line5 = "ToDo,,true";

        Task task = Task.deserialize(line);
        Task task2 = Task.deserialize(line2);
        Task task3 = Task.deserialize(line3);
        Task task4 = Task.deserialize(line4);
        Task task5 = Task.deserialize(line5);

        assertEquals(line, task.serialize());
        assertEquals(line2, task2.serialize());
        assertEquals(line3, task3.serialize());
        assertEquals(line4, task4.serialize());
        assertEquals(line5, task5.serialize());

    }

    @Test
    public void testDeserializeDeadlineTask() {

        String line = "Deadline,abc,true,01/12/2025 1300";
        String line2 = "Deadline,abc,false,02/01/2024 1200";
        String line3 = "Deadline,abc def 12345,false,03/02/2023 1100";
        String line4 = "Deadline,12345 abc def,true,04/03/2022 1000";
        String line5 = "Deadline,,true,05/04/2021 0900";

        Task task = Task.deserialize(line);
        Task task2 = Task.deserialize(line2);
        Task task3 = Task.deserialize(line3);
        Task task4 = Task.deserialize(line4);
        Task task5 = Task.deserialize(line5);

        assertInstanceOf(Deadline.class, task);
        assertEquals("abc", task.description);
        assertTrue(task.isDone);
        assertEquals(LocalDateTime.of(2025,12,1,13,0), ((Deadline) task).by);

        assertInstanceOf(Deadline.class, task2);
        assertEquals("abc", task2.description);
        assertFalse(task2.isDone);
        assertEquals(LocalDateTime.of(2024,1,2,12,0), ((Deadline) task2).by);

        assertInstanceOf(Deadline.class, task3);
        assertEquals("abc def 12345", task3.description);
        assertFalse(task3.isDone);
        assertEquals(LocalDateTime.of(2023,2,3,11,0), ((Deadline) task3).by);

        assertInstanceOf(Deadline.class, task4);
        assertEquals("12345 abc def", task4.description);
        assertTrue(task4.isDone);
        assertEquals(LocalDateTime.of(2022,3,4,10,0), ((Deadline) task4).by);

        assertInstanceOf(Deadline.class, task5);
        assertEquals("", task5.description);
        assertTrue(task5.isDone);
        assertEquals(LocalDateTime.of(2021,4,5,9,0), ((Deadline) task5).by);

    }

    @Test
    public void testSerializeDeadlineTask() {

        String line = "Deadline,abc,true,01/12/2025 1300";
        String line2 = "Deadline,abc,false,02/01/2024 1200";
        String line3 = "Deadline,abc def 12345,false,03/02/2023 1100";
        String line4 = "Deadline,12345 abc def,true,04/03/2022 1000";
        String line5 = "Deadline,,true,05/04/2021 0900";

        Task task = Task.deserialize(line);
        Task task2 = Task.deserialize(line2);
        Task task3 = Task.deserialize(line3);
        Task task4 = Task.deserialize(line4);
        Task task5 = Task.deserialize(line5);

        assertEquals(line, task.serialize());
        assertEquals(line2, task2.serialize());
        assertEquals(line3, task3.serialize());
        assertEquals(line4, task4.serialize());
        assertEquals(line5, task5.serialize());

    }

    @Test
    public void testDeserializeEventTask() {

        String line = "Event,abc,true,01/12/2025 1300,16/12/2025 2200";
        String line2 = "Event,abc,false,02/01/2024 1200,17/01/2024 2100";
        String line3 = "Event,abc def 12345,false,03/02/2023 1100,18/02/2023 2000";
        String line4 = "Event,12345 abc def,true,04/03/2022 1000,19/03/2022 1900";
        String line5 = "Event,,true,05/04/2021 0900,20/04/2021 1800";

        Task task = Task.deserialize(line);
        Task task2 = Task.deserialize(line2);
        Task task3 = Task.deserialize(line3);
        Task task4 = Task.deserialize(line4);
        Task task5 = Task.deserialize(line5);

        assertInstanceOf(Event.class, task);
        assertEquals("abc", task.description);
        assertTrue(task.isDone);
        assertEquals(LocalDateTime.of(2025,12,1,13,0), ((Event) task).start);
        assertEquals(LocalDateTime.of(2025,12,16,22,0), ((Event) task).end);

        assertInstanceOf(Event.class, task2);
        assertEquals("abc", task2.description);
        assertFalse(task2.isDone);
        assertEquals(LocalDateTime.of(2024,1,2,12,0), ((Event) task2).start);
        assertEquals(LocalDateTime.of(2024,1,17,21,0), ((Event) task2).end);

        assertInstanceOf(Event.class, task3);
        assertEquals("abc def 12345", task3.description);
        assertFalse(task3.isDone);
        assertEquals(LocalDateTime.of(2023,2,3,11,0), ((Event) task3).start);
        assertEquals(LocalDateTime.of(2023,2,18,20,0), ((Event) task3).end);

        assertInstanceOf(Event.class, task4);
        assertEquals("12345 abc def", task4.description);
        assertTrue(task4.isDone);
        assertEquals(LocalDateTime.of(2022,3,4,10,0), ((Event) task4).start);
        assertEquals(LocalDateTime.of(2022,3,19,19,0), ((Event) task4).end);

        assertInstanceOf(Event.class, task5);
        assertEquals("", task5.description);
        assertTrue(task5.isDone);
        assertEquals(LocalDateTime.of(2021,4,5,9,0), ((Event) task5).start);
        assertEquals(LocalDateTime.of(2021,4,20,18,0), ((Event) task5).end);

    }

    @Test
    public void testSerializeEventTask() {

        String line = "Event,abc,true,01/12/2025 1300,16/12/2025 2200";
        String line2 = "Event,abc,false,02/01/2024 1200,17/01/2024 2100";
        String line3 = "Event,abc def 12345,false,03/02/2023 1100,18/02/2023 2000";
        String line4 = "Event,12345 abc def,true,04/03/2022 1000,19/03/2022 1900";
        String line5 = "Event,,true,05/04/2021 0900,20/04/2021 1800";

        Task task = Task.deserialize(line);
        Task task2 = Task.deserialize(line2);
        Task task3 = Task.deserialize(line3);
        Task task4 = Task.deserialize(line4);
        Task task5 = Task.deserialize(line5);

        assertEquals(line, task.serialize());
        assertEquals(line2, task2.serialize());
        assertEquals(line3, task3.serialize());
        assertEquals(line4, task4.serialize());
        assertEquals(line5, task5.serialize());

    }

}
