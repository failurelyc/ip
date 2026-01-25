import java.time.LocalDateTime;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, endDateFormatter);
    }

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = LocalDateTime.parse(by, endDateFormatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(printDateFormatter) + ")";
    }

    @Override
    public String serialize() {
        return "Deadline," + super.description + "," + super.isDone + "," + this.by.format(endDateFormatter);
    }

}
