package retupmoc.tasks;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

/**
 * This class represent an abstract Task. All types of tasks should extend this class.
 */
public abstract class Task {

    public static final String INPUT_FORMAT = "dd/MM/yyyy HHmm";
    protected static final DateTimeFormatter startDateFormatter = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();
    protected static final DateTimeFormatter endDateFormatter = new DateTimeFormatterBuilder()
            .appendPattern("dd/MM/yyyy")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
            .toFormatter();
    protected static final DateTimeFormatter printDateFormatter = DateTimeFormatter.ofPattern("d MMM yyyy K.mma");

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon representing whether the task is done.
     *
     * @return X if the task is done, and a space character otherwise
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of this Task.
     *
     * @return the String representation of this Task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a String representation of this Task.
     *
     * @return the String representation of this Task
     */
    public abstract String serialize();

    /**
     * Constructs a task based on its String representation.
     * The String representation can be representing an Object of any subtype of Task.
     *
     * @param s the String representation of this Task
     * @return the Task Object of the correct type.
     */
    public static Task deserialize(String s) {
        String[] parameters = s.split(",", -1);
        if (parameters.length < 1)
            throw new IllegalArgumentException();
        String taskType = parameters[0];
        String description = parameters[1];
        boolean isDone = Boolean.parseBoolean(parameters[2]);
        return switch (taskType) {
            case "ToDo" -> new ToDo(description, isDone);
            case "Deadline" -> new Deadline(description, isDone, parameters[3]);
            case "Event" -> new Event(description, isDone, parameters[3], parameters[4]);
            default -> throw new IllegalArgumentException("Not a valid task");
        };
    }

}
