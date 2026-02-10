package retupmoc.command;

import retupmoc.tasks.Task;

/**
 * This enum class represents a command type.
 */
public enum CommandType {
    GREET("greet"),
    LIST("list"),
    MARK("mark <task number>"),
    UNMARK("unmark <task number>"),
    BYE("bye"),
    TODO("todo <task description>"),
    DEADLINE("deadline <task description> /by <" + Task.INPUT_FORMAT + ">"),
    EVENT("event <task description> /from <" + Task.INPUT_FORMAT + "> /to <" + Task.INPUT_FORMAT + ">"),
    DELETE("delete <task number>"),
    FIND("find <task number>"),
    HELP("help <command>");

    private final String format;

    CommandType(String description) {
        this.format = description;
    }

    public String getFormat() {
        return this.format;
    }

}
