package edu.au.cpsc.module2;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.io.IOException;

public class SeatReservationApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        SeatReservation seatReservation = new SeatReservation();

        //BorderPane
        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane, 320, 240);

        //Center GridPane
        GridPane center = new GridPane();
        //Center labels
        Label flightDesignatorLabel = new Label("Flight designator:");
        Label flightDateLabel = new Label("Flight date:");
        Label firstNameLabel = new Label("First name:");
        Label lastNameLabel = new Label("Last name:");
        Label numberOfBagsLabel = new Label("Bags:");
        Label flyingWithBabyLabel = new Label("Flying with baby?");
        Label passengerTotalLabel = new Label("Number of passengers:");
        //Center inputs
        TextField passengerTotalInput = new TextField("1");
        passengerTotalInput.setEditable(false);

        TextField firstNameInput = new TextField();
        TextField lastNameInput = new TextField();
        TextField flightDesignatorInput = new TextField();
        TextField numberOfBagsInput = new TextField();
        DatePicker flightDatePicker = new DatePicker();
        CheckBox flyingWithBabyInput = new CheckBox();
        //Add UI
        addCenterGridPaneUI(center, passengerTotalLabel, passengerTotalInput, flightDateLabel, flightDatePicker,
                lastNameLabel, lastNameInput, firstNameLabel, firstNameInput, flightDesignatorLabel,
                flightDesignatorInput, flyingWithBabyLabel, flyingWithBabyInput, numberOfBagsLabel, numberOfBagsInput);

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
        updateUI(firstNameInput, seatReservation, lastNameInput, flightDatePicker, flightDesignatorInput,
                flyingWithBabyInput, numberOfBagsInput, passengerTotalInput);
        cancelInput(button1);
        saveInput(button2, firstNameInput, seatReservation, lastNameInput, flightDatePicker, flightDesignatorInput,
                flyingWithBabyInput, numberOfBagsInput);
        stage.show();

    }

    // Updates the UI based on the data from the seatReservation class.
    private static void updateUI(TextField firstNameInput, SeatReservation seatReservation, TextField lastNameInput,
                                 DatePicker flightDatePicker, TextField flightDesignatorInput, CheckBox flyingWithBabyInput,
                                 TextField numberOfBagsInput, TextField passengerTotalInput) {

        firstNameInput.setText(seatReservation.getFirstName());
        lastNameInput.setText(seatReservation.getLastName());
        flightDatePicker.setValue(seatReservation.getFlightDate());
        flightDesignatorInput.setText(seatReservation.getFlightDesignator());
        numberOfBagsInput.setText("" + seatReservation.getNumberOfBags());
        flyingWithBabyInput.setSelected(seatReservation.isFlyingWithInfant());
        flyingWithBabyInput.addEventHandler(ActionEvent.ACTION, event -> {

            if (!seatReservation.isFlyingWithInfant()) {
                passengerTotalInput.setText("2");
                seatReservation.makeFlyingWithInfant();
            }
            else {
                passengerTotalInput.setText("1");
                seatReservation.makeNotFlyingWithInfant();
            }
        });
    }

    // Closes the application based on an event and prints a message to console.
    public static void cancelInput(Button button1) {
        button1.addEventHandler(ActionEvent.ACTION, event -> {
           System.out.println("Cancel clicked");
           Platform.exit();
        });
    }

    /* Sets the SeatReservation class variables to the user-inputs provided.
     * Closes the program if no exceptions are caught.
     */
    public static void saveInput(Button button2, TextField firstNameInput, SeatReservation seatReservation, TextField lastNameInput,
                                 DatePicker flightDatePicker, TextField flightDesignatorInput, CheckBox flyingWithBabyInput,
                                 TextField numberOfBagsInput) {
        button2.addEventHandler(ActionEvent.ACTION, event -> {
            seatReservation.setFirstName(firstNameInput.getText());
            seatReservation.setLastName(lastNameInput.getText());
            seatReservation.setFlightDate(flightDatePicker.getValue());

            try {
                seatReservation.setFlightDesignator(flightDesignatorInput.getText());
            }
            catch (IllegalArgumentException e) {
                System.out.println("Flight designator code must be between 3 and 7 characters.");
                return;
            }
            catch (NullPointerException e) {
                System.out.println("Flight designator cannot be null/empty.");
                return;
            }

            if (flyingWithBabyInput.isSelected()) {
                seatReservation.makeFlyingWithInfant();
            }
            else {
                seatReservation.makeNotFlyingWithInfant();
            }

            try {
                seatReservation.setNumberOfBags(Integer.parseInt(numberOfBagsInput.getText()));
            }
            catch (NumberFormatException e) {
                System.out.println("Enter a integer number for the number of bags in number form (e.g. \"2\").");
                return;
            }
            System.out.println(seatReservation.toString());
            Platform.exit();
        });
    }
    // Adds all center UI to the grid panel.
    private static void addCenterGridPaneUI(GridPane center, Label passengerTotalLabel, TextField passengerTotalInput,
                                            Label flightDateLabel, DatePicker flightDatePicker, Label lastNameLabel,
                                            TextField lastNameInput, Label firstNameLabel, TextField firstNameInput,
                                            Label flightDesignatorLabel, TextField flightDesignatorInput,
                                            Label flyingWithBabyLabel, CheckBox flyingWithBabyInput, Label numberOfBagsLabel,
                                            TextField numberOfBagsInput ) {
        center.setPadding(new Insets(5.0));
        center.setVgap(5.0);
        center.setHgap(5.0);
        center.add(flightDesignatorLabel, 0, 0);
        center.add(flightDesignatorInput, 1, 0);
        center.add(flightDateLabel, 0, 1);
        center.add(flightDatePicker, 1, 1);
        center.add(firstNameLabel, 0, 2);
        center.add(firstNameInput, 1, 2);
        center.add(lastNameLabel, 0, 3);
        center.add(lastNameInput, 1, 3);
        center.add(numberOfBagsLabel, 0, 4);
        center.add(numberOfBagsInput, 1, 4);
        center.add(flyingWithBabyLabel, 0, 5);
        center.add(flyingWithBabyInput, 1, 5);
        center.add(passengerTotalLabel, 0, 6);
        center.add(passengerTotalInput, 1, 6);
    }

    public static void main(String[] args) {
        launch();
    }
}