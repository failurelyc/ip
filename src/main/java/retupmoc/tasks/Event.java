package retupmoc.tasks;

import java.time.LocalDateTime;

/**
 * This class represents a Deadline task which stores an additional start and end date.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    /**
     * Constructs a new Event task with the provided description, start and end dates
     *
     * @param description The description
     * @param start The start date as a String
     * @param end The end date as a String
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, START_DATE_FORMATTER);
        this.end = LocalDateTime.parse(end, END_DATE_FORMATTER);
    }

    /**
     * Constructs a new Event task with the provided description, start and end dates
     *
     * @param description The description
     * @param isDone True if the task is marked as done, false otherwise
     * @param start The start date as a String
     * @param end The end date as a String
     */
    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = LocalDateTime.parse(start, START_DATE_FORMATTER);
        this.end = LocalDateTime.parse(end, END_DATE_FORMATTER);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(PRINT_DATE_FORMATTER)
                + " to: " + this.end.format(PRINT_DATE_FORMATTER) + ")";
    }

    @Override
    public String serialize() {
        return "Event," + super.description + "," + super.isDone + ","
                + this.start.format(START_DATE_FORMATTER) + "," + this.end.format(END_DATE_FORMATTER);
    }

}
