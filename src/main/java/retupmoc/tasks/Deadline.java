package retupmoc.tasks;

import java.time.LocalDateTime;

/**
 * This class represents a Deadline task which stores an additional due date.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructs a new Deadline task with that description and due date.
     *
     * @param description The description of the task
     * @param by The due date as a String
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, END_DATE_FORMATTER);
    }

    /**
     * Constructs a new Deadline task with that description, due date and whether
     * it is marked as done
     *
     * @param description The description of the task
     * @param isDone True if the task is marked as done, false otherwise
     * @param by The due date as a String
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, END_DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(PRINT_DATE_FORMATTER) + ")";
    }

    @Override
    public String serialize() {
        return "Deadline," + super.description + "," + super.isDone + "," + this.by.format(END_DATE_FORMATTER);
    }

}
