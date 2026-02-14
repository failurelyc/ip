package retupmoc.ui;

import retupmoc.JavanMynaException;
import retupmoc.storage.TaskList;
import retupmoc.tasks.Task;

/**
 * This class generates the text to be displayed by the chatbot.
 */
public class Ui {

    /**
     * Returns the greeting. Should be called at application startup.
     */
    public String printGreeting() {
        return ("Hello! I'm Javan Myna!\nWhat can I do for you?");
    }

    /**
     * Returns the goodbye message. Should be called before the application exits.
     */
    public String printGoodbye() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns every Task in the TaskList.
     * @param list the TaskList
     */
    public String displayList(TaskList list) {
        return "Here are the tasks in your list:\n" + list;
    }

    /**
     * Returns the error message. Should be called if an Exception is thrown.
     * @param e The exception
     */
    public String printErrorMessage(JavanMynaException e) {
        return e.getMessage();
    }

    /**
     * Returns the message after a task is successfully added.
     * @param task The task that was added
     */
    public String printAddTaskSuccess(Task task) {
        return "Got it. I've added this task:\n" + task;
    }

    /**
     * Prints the message after a task is successfully removed.
     * @param task The task that was added
     */
    public String printTaskRemovalSuccess(Task task) {
        return "Noted: I've removed this task:\n" + task;
    }

    /**
     * Prints the message after a task is successfully marked as done.
     * @param task The task that was added
     */
    public String printMarkTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Prints the message after a task is successfully marked as not done.
     * @param task The task that was added
     */
    public String printMarkTaskNotDone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Prints number of tasks in the task list.
     *
     * @param list the TaskList
     */
    public String printNoOfTask(TaskList list) {
        return "Now you have " + list.getNoOfTasks() + " tasks in the list.";
    }

}
