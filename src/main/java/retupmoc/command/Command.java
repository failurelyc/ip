package retupmoc.command;

import java.util.List;

/**
 * A record/data class representing a parsed user command.
 */
public record Command(CommandType commandType, List<String> parameters) {}
