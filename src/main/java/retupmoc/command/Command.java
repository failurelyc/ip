package retupmoc.command;

import java.util.List;

/**
 * A parsed user command.
 */
public class Command {

    public final CommandType commandType;
    public final List<String> parameters;

    /**
     * Creates a new Command with the specified type of command and the parameters.
     *
     * @param commandType the type of command
     * @param parameters the parameters for this command as a list of Strings
     */
    public Command(CommandType commandType, List<String> parameters) {
        this.commandType = commandType;
        this.parameters = parameters;
    }

}
