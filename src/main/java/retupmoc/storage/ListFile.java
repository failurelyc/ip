package retupmoc.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A representation of a text file that stores a list of items.
 * This class handles the writing and reading of the file.
 */
public class ListFile {

    public final Path fileLocation;

    /**
     * Creates a ListFile with the specified file path.
     *
     * @param fileLocation the file path
     */
    public ListFile(String fileLocation) {
        this.fileLocation = Paths.get(fileLocation);
    }

    /**
     * Writes the entire list into the file.
     * The list should contain String representation of Objects rather
     * than the Object itself.
     *
     * @param serialized A list of String representation of Objects
     * @return True if the operation succeeds, false otherwise
     */
    public boolean writeList(List<String> serialized) {
        try {
            Files.write(fileLocation, serialized, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Reads the entire list from the file.
     *
     * @return The list of String representation of Objects
     */
    public List<String> readList() {
        try {
            return Files.readAllLines(fileLocation, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return List.of();
        }
    }

}
