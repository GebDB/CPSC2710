package edu.au.cpsc.launcher;

import edu.au.cpsc.miscstyle.Part1Application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.application.Application.launch;

public class LauncherApplication extends Application {

        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(LauncherApplication.class.getResource("launcher-app.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.getStylesheets().add(getClass().getResource("style/main.css").toExternalForm());
            stage.setTitle("Part 2");
            stage.setScene(scene);
            stage.show();
        }

        public static void main(String[] args) {
            launch();
        }
}

