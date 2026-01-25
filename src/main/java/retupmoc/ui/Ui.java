package retupmoc.ui;

import retupmoc.RetupmocException;
import retupmoc.storage.TaskList;
import retupmoc.tasks.Task;

/**
 * This class displays the UI of the application.
 */
public class Ui {

    /**
     * Prints the greeting. Should be called at application startup.
     */
    public void printGreeting() {
        System.out.println(" Hello! I'm Retupmoc");
        System.out.println(" What can I do for you?");
    }

    /**
     * Prints a horizontal line. Should be called after each message displayed.
     */
    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message. Should be called before the application exits.
     */
    public void printGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    /**
     * Prints every Task in the TaskList.
     * @param list the TaskList
     */
    public void displayList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    /**
     * Prints the error message. Should be called if an Exception is thrown.
     * @param e The exception
     */
    public void printErrorMessage(RetupmocException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints the message after a task is successfully added.
     * @param task The task that was added
     */
    public void printAddTaskSuccess(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    /**
     * Prints the message after a task is successfully removed.
     * @param task The task that was added
     */
    public void printTaskRemovalSuccess(Task task) {
        System.out.println("Noted: I've removed this task:");
        System.out.println(task);
    }

    /**
     * Prints the message after a task is successfully marked as done.
     * @param task The task that was added
     */
    public void printMarkTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    /**
     * Prints the message after a task is successfully marked as not done.
     * @param task The task that was added
     */
    public void printMarkTaskNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    /**
     * Prints number of tasks in the task list.
     *
     * @param list the TaskList
     */
    public void printNoOfTask(TaskList list) {
        System.out.println("Now you have " + list.getNoOfTasks() + " tasks in the list.");
    }

}
