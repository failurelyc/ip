package retupmoc.storage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ListFile {

    public final Path fileLocation;

    public ListFile(String fileLocation) {
        this.fileLocation = Paths.get(fileLocation);
    }

    public boolean writeList(List<String> serialized) {
        try {
            Files.write(fileLocation, serialized, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public List<String> readList() {
        try {
            return Files.readAllLines(fileLocation, StandardCharsets.UTF_8);
        } catch (IOException e) {
            return List.of();
        }
    }

}
