package edu.au.cpsc.module3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class AirportApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        List<Airport> x = Airport.readAll();
        FXMLLoader fxmlLoader = new FXMLLoader(AirportApplication.class.getResource("airportapp-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}