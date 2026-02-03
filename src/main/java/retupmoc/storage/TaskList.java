package retupmoc.storage;

import java.util.ArrayList;
import java.util.List;

import retupmoc.RetupmocException;
import retupmoc.tasks.Task;

/**
 * This class represents the task list.
 */
public class TaskList {

    private final ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    private TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the task in the specified list.
     * @param taskList The list of tasks to add to the TaskList
     */
    private TaskList(List<Task> taskList) {
        this();
        list.addAll(taskList);
    }

    /**
     * Adds the task to the list.
     *
     * @param task The task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Returns the task with the specified index.
     * @param taskNo The index
     * @return The task with that index
     * @throws RetupmocException If the index is out of bounds
     */
    public Task findTask(int taskNo) throws RetupmocException {
        try {
            return list.get(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    /**
     * Returns all tasks containing partial description.
     *
     * @param partialDescription The partial description
     * @return A list containing all tasks with that partial description
     */
    public TaskList findTasks(String partialDescription) {
        List<Task> partialList = list
                .stream()
                .filter(task -> task.containsDescription(partialDescription))
                .toList();
        return new TaskList(partialList);
    }

    /**
     * Removes the task from the list.
     *
     * @param taskNo The index of the task in the list
     * @return The removed task
     * @throws RetupmocException If the index is out of bounds
     */
    public Task removeTask(int taskNo) throws RetupmocException {
        try {
            return list.remove(taskNo);
        } catch (IndexOutOfBoundsException e) {
            throw new RetupmocException("Task not found");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param taskNo The index of the task in the list
     * @throws RetupmocException If the index is out of bounds
     */
    public void markTaskDone(int taskNo) throws RetupmocException {
        Task task = findTask(taskNo);
        task.markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param taskNo The index of the task in the list
     * @throws RetupmocException If the index is out of bounds
     */
    public void markTaskNotDone(int taskNo) throws RetupmocException {
        Task task = findTask(taskNo);
        task.markAsNotDone();
    }

    /**
     * Returns a String representation of the task list.
     *
     * @return the String representation of the task list
     */
    public List<String> serialize() {
        return list.stream().map(Task::serialize).toList();
    }

    /**
     * Returns a String representation of the task list.
     *
     * @return the String representation of the task list
     */
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

    /**
     * Get the number of tasks in the TaskList.
     *
     * @return the number of tasks
     */
    public int getNoOfTasks() {
        return list.size();
    }

    /**
     * Converts the String representation of a TaskList to the actual TaskList Object.
     *
     * @param serialized tring representation of a TaskList
     * @return the TaskList Object
     */
    public static TaskList deserialize(List<String> serialized) {
        return new TaskList(serialized.stream().map(Task::deserialize).toList());
    }

}
