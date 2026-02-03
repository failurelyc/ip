package retupmoc.tasks;

/**
 * This class represents a To Do task which has no additional data.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serialize() {
        return "ToDo," + super.description + "," + super.isDone;
    }

}
