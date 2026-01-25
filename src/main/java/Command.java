
import java.util.List;

public class Command {

    public final String commandType;
    public final List<String> parameters;

    public Command(String commandType, List<String> parameters) {
        this.commandType = commandType;
        this.parameters = parameters;
    }

}
