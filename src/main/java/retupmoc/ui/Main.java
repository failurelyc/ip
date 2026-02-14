package retupmoc.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import retupmoc.JavanMyna;

/**
 * A GUI for ChatBot using FXML.
 * Reused from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">
 *     JavaFX tutorial part 4 – Using FXML</a>
 */
public class Main extends Application {

    private final JavanMyna chatbot = new JavanMyna("./saved_data");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setChatbot(chatbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
