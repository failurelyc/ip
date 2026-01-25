package retupmoc.storage;

import retupmoc.RetupmocException;
import retupmoc.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private final ArrayList<Task> list;

    private TaskList() {
        this.list = new ArrayList<>();
    }

    private TaskList(List<Task> taskList) {
        this();
        list.addAll(taskList);
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task findTask(int taskNo) throws RetupmocException {
        try {
            return list.get(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    public Task removeTask(int taskNo) throws RetupmocException {
        try {
            return list.remove(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    public void markTaskDone(int taskNo) throws RetupmocException {
        Task task = findTask(taskNo);
        task.markAsDone();
    }

    public void markTaskNotDone(int taskNo) throws RetupmocException {
        Task task = findTask(taskNo);
        task.markAsNotDone();
    }

    public List<String> serialize() {
        return list.stream().map(Task::serialize).toList();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            s.append((i + 1)).append(". ").append(list.get(i));
            if (i < list.size() - 1) {
                s.append("\n");
            }
        }
        return s.toString();
    }

    public int getNoOfTasks() {
        return list.size();
    }

    public static TaskList deserialize(List<String> serialized) {
        return new TaskList(serialized.stream().map(Task::deserialize).toList());
    }

}
