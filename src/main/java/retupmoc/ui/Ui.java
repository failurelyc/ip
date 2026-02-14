package retupmoc.ui;

import retupmoc.JavanMynaException;
import retupmoc.storage.TaskList;
import retupmoc.tasks.Task;

/**
 * This class generates the text to be displayed by the chatbot.
 * ChatGPT was used to add personality to the Strings
 */
public class Ui {

    /**
     * Returns the greeting. Should be called at application startup.
     */
    public String printGreeting() {
        return "Hey there! I'm Javan Myna, your trusty task assistant. 😊\nWhat can I help you with today?";
    }

    /**
     * Returns the goodbye message. Should be called before the application exits.
     */
    public String printGoodbye() {
        return "Goodbye for now! I'll be here when you need me again. ✌️";
    }

    /**
     * Returns every Task in the TaskList.
     * @param list the TaskList
     */
    public String displayList(TaskList list) {
        return "Here’s what’s on your to-do list right now:\n" + list;
    }

    /**
     * Returns the error message. Should be called if an Exception is thrown.
     * @param e The exception
     */
    public String printErrorMessage(JavanMynaException e) {
        return "Oops! Something went wrong: " + e.getMessage() + " 😬";
    }

    /**
     * Returns the message after a task is successfully added.
     * @param task The task that was added
     */
    public String printAddTaskSuccess(Task task) {
        return "Awesome! I've added this task to your list: 🎉\n" + task;
    }

    /**
     * Prints the message after a task is successfully removed.
     * @param task The task that was removed
     */
    public String printTaskRemovalSuccess(Task task) {
        return "Task removed! I’ve crossed this off your list: ✅\n" + task;
    }

    /**
     * Prints the message after a task is successfully marked as done.
     * @param task The task that was marked as done
     */
    public String printMarkTaskDone(Task task) {
        return "Yay, you did it! 🎯 This task is now marked as done:\n" + task;
    }

    /**
     * Prints the message after a task is successfully marked as not done.
     * @param task The task that was marked as not done
     */
    public String printMarkTaskNotDone(Task task) {
        return "No worries! This task is back on the to-do list: 🔄\n" + task;
    }

    /**
     * Prints the number of tasks in the task list.
     *
     * @param list the TaskList
     */
    public String printNoOfTask(TaskList list) {
        return "You currently have " + list.getNoOfTasks()
                + " task" + (list.getNoOfTasks() != 1 ? "s" : "") + " in your list!";
    }
}
