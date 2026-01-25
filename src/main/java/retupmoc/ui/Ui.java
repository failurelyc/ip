package retupmoc.ui;

import retupmoc.RetupmocException;
import retupmoc.storage.TaskList;
import retupmoc.tasks.Task;

public class Ui {

    public void printGreeting() {
        System.out.println(" Hello! I'm Retupmoc");
        System.out.println(" What can I do for you?");
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void printGoodbye() {
        System.out.println(" Bye. Hope to see you again soon!");
    }

    public void displayList(TaskList list) {
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
    }

    public void printErrorMessage(RetupmocException e) {
        System.out.println(e.getMessage());
    }

    public void printAddTaskSuccess(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
    }

    public void printTaskRemovalSuccess(Task task) {
        System.out.println("Noted: I've removed this task:");
        System.out.println(task);
    }

    public void printMarkTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void printMarkTaskNotDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public void printNoOfTask(TaskList list) {
        System.out.println("Now you have " + list.getNoOfTasks() + " tasks in the list.");
    }

}
