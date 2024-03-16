package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.geometry.Pos.TOP_RIGHT;

public class SeatReservationApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        SeatReservation seatres = new SeatReservation();

        //BorderPane
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 320, 240);

        //Center GridPane
        GridPane center = new GridPane();
        //FlyingWithInfant
        Label flyingWithBabyLabel = new Label("Flying with baby:");
        CheckBox flyingWithBabyInput = new CheckBox();
        boolean isFlyingWithBaby = seatres.isFlyingWithInfant();
        center.add(flyingWithBabyLabel, 0, 0);
        center.add(flyingWithBabyInput, 1, 0);
        //FlightDesignator
        Label flightDesignatorLabel = new Label("Flight Designator:");
        TextField flightDesignatorInput = new TextField("xxxxx");
        center.add(flightDesignatorLabel, 0, 1);
        center.add(flightDesignatorInput, 1, 1);
        //FirstName
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameInput = new TextField("John");
        center.add(firstNameLabel, 0, 2);
        center.add(firstNameInput, 1, 2);
        //LastName
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameInput = new TextField("Doe");
        center.add(lastNameLabel, 0, 3);
        center.add(lastNameInput, 1, 3);
        //FlightDate
        Label flightDateLabel = new Label("Flight Date:");
        DatePicker flightDatePicker = new DatePicker();
        center.add(flightDateLabel, 0, 4);
        center.add(flightDatePicker, 1, 4);
        //NumberOfPassengers
        Label passengerTotalLabel = new Label("Number of Passengers:");
        TextField passengerTotalInput = new TextField("1");
        passengerTotalInput.setEditable(false);
        center.add(passengerTotalLabel, 0, 5);
        center.add(passengerTotalInput, 1, 5);

        //Bottom HBox
        HBox bottom = new HBox();
        Button button1 = new Button("Cancel");
        Button button2 = new Button("Save");
        bottom.getChildren().addAll(button1, button2);
        bottom.setAlignment(Pos.TOP_RIGHT);

        //BorderPane
        borderPane.setCenter(center);
        borderPane.setBottom(bottom);

        stage.setTitle("Seat Reservation Editor");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}