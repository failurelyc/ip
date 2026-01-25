public abstract class Task {

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

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String serialize();

    public static Task deserialize(String s) {
        String[] parameters = s.split(",", -1);
        if (parameters.length < 1)
            throw new IllegalArgumentException();
        String taskType = parameters[0];
        String description = parameters[1];
        boolean isDone = Boolean.parseBoolean(parameters[2]);
        switch (taskType) {
        case "ToDo":
            return new ToDo(description, isDone);
        case "Deadline":
            return new Deadline(description, isDone, parameters[3]);
        case "Event":
            return new Event(description, isDone, parameters[3], parameters[4]);
        default:
            throw new IllegalArgumentException("Not a valid task");
        }
    }

}
