public class Event extends Task {

    protected String start;
    protected String end;

    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public Event(String description, boolean isDone, String start, String end) {
        super(description, isDone);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start +
                " to: " + this.end + ")";
    }

    @Override
    public String serialize() {
        return "Event," + super.description + "," + super.isDone + "," + this.start + "," + this.end;
    }

}
