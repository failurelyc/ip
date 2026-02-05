package retupmoc.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import retupmoc.Retupmoc;

/**
 * A GUI for ChatBot using FXML.
 */
public class Main extends Application {

    private final Retupmoc chatbot = new Retupmoc("./saved_data");

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
