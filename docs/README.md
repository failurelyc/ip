# CS2103T Individual Project User Guide
Javan Myna is a desktop app for managing your tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Javan Myna can get your task management done faster than traditional GUI apps.

# Quick Start
1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `.jar` file from [here](https://github.com/failurelyc/ip/releases)
3. Copy the file to the folder you want to use as the *home folder* for your Javan Myna.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Javan-Myna.jar` command to run the application.
5. Refer to the [Features](#features) below for details of each command.

# Features
* Words enclosed in `<>` are the parameters to be supplied by the user.
* Note that commands are not case sensitive.
* Date format is `dd/MM/yyyy HHmm`. Time is optional (i.e. `dd/MM/yyyy` is acceptable)

## Greet
Greets the user.  
**Format**: `greet`

## List
List all of your tasks.  
**Format**: `list`  
**Example**: 
> Here's what's on your to-do list right now:
> 1. [D][ ] MA2202 Homework 2 (by: 20 Feb 2026 11.59pm)
> 2. [D][X] MA2108 Assignment 2 (by: 22 Feb 2026 11.59pm)
> 3. [E][ ] Chinese New Year (from: 17 Feb 2026 12.00am to 18 Feb 2026 11.59pm)

## Mark
Mark a task as done.  
**Format**: `mark <task number>`  
**Example**:    
* Success: `mark 1`
> Yay, you did it! 🎯 This task is now marked as done:  
> [D][X] MA2202 Homework 2 (by: 20 Feb 2026 11.59pm)
* Failure: `mark -1`
> Oops! Something went wrong: Task not found😬

## Unmark
Mark a task as not done.  
**Format**: `unmark <task number>`  
**Example**: 
* Success: `mark 1`
> No worries! This task is back on the to-do list: 🔄  
> [D][ ] MA2202 Homework 2 (by: 20 Feb 2026 11.59pm)
* Failure: `mark -1`
> Oops! Something went wrong: Task not found😬

## Todo
Add a task with a description.  
**Format**: `todo <task description>`  
**Example**: 
* Success: `todo Update Aiepoissac Bus App README`
> Awesome! I've added this task to your list: 🎉  
> [T][ ] Update Aiepoissac Bus App README  
> You currently have 4 tasks in your list!
* Failure: `todo`
> Oops! Something went wrong: The description of a Task cannot be empty😬

## Deadline
Add a task with a deadline.  
**Format**: `deadline <task description> /by <dd/MM/yyyy HHmm>`  
**Example**:
* Success: `deadline CS2103T Individual Project /by 20/02/2026 2359`
> Awesome! I've added this task to your list: 🎉  
> [D][ ] CS2103T Individual Project (by: 20 Feb 2026 11.59pm)    
> You currently have 5 tasks in your list!
* Failure: `deadline CS2103T Individual Project`
> Oops! Something went wrong: Hmm... that date doesn't look quite right.  
> Please use this format: dd/MM/yyyy HHmm (time is optional)😬

## Event
Add a task with a start and end date.  
**Format**: `event <task description> /from <dd/MM/yyyy HHmm> /to <dd/MM/yyyy HHmm>`  
**Example**:
* Success: `event back to back MA2108 no cheatsheet midterm and MA2202 no cheatsheet midterm /from 04/03/2026 1000 /to 04/03/2026 1400`
> Awesome! I've added this task to your list: 🎉  
> [E][ ] back to back MA2108 no cheatsheet midterm and MA2202 no cheatsheet midterm (from: 4 Mar 2026 10.00am to: 4 Mar 2026 2.00pm)  
> You currently have 6 tasks in your list! 
* Failure: `event CS2103T Individual Project /to 20/02/2026 2359`
> Oops! Something went wrong: Hmm... that date doesn't look quite right.  
> Please use this format: dd/MM/yyyy HHmm (time is optional)😬

## Delete
Deletes a task from the list.  
**Format**: `delete <task number>`  
**Example**:
* Success: `delete 5`
> Task removed! I've crossed this off your list:✔️  
> [D][ ] CS2103T Individual Project (by: 20 Feb 2026 11.59pm) 
> You currently have 5 tasks in your list!
* Failure: `delete -1`
> Oops! Something went wrong: Task not found😬

## Find
Finds a task from the list.  
**Format**: `find <partial description>`  
**Example**: `find MA2202`  
> Here's what's on your to-do list right now:
> 1. [D][ ] MA2202 Homework 2 (by: 20 Feb 2026 11.59pm)  
> 2. [E][ ] back to back MA2108 no cheatsheet midterm and MA2202 no cheatsheet midterm (from: 4 Mar 2026 10.00am to: 4 Mar 2026 2.00pm)

## Help
Get help on a command.  
**Format**: `help <command>`  
**Example**: `help event`  
> Here's how you can use that command:  
> event \<task description\> /from \<dd/MM/yyyy HHmm\> /to \<dd/MM/yyyy HHmm\>  
