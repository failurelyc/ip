package retupmoc.tasks;

import java.time.LocalDateTime;

public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = LocalDateTime.parse(start, startDateFormatter);
        this.end = LocalDateTime.parse(end, endDateFormatter);
    }

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = LocalDateTime.parse(start, startDateFormatter);
        this.end = LocalDateTime.parse(end, endDateFormatter);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start.format(printDateFormatter) +
                " to: " + this.end.format(printDateFormatter) + ")";
    }

    @Override
    public String serialize() {
        return "Event," + super.description + "," + super.isDone + ","
                + this.start.format(startDateFormatter) + "," + this.end.format(endDateFormatter);
    }

}
