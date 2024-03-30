package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalTime;
import java.util.HashSet;

public class AirlineDetailViewController {
    @FXML
    private static TextField fdField;
    @FXML
    private static TextField departureAirportField;
    @FXML
    private static TextField departureTimeField;
    @FXML
    private static TextField arrivalAirportField;
    @FXML
    private static TextField arrivalTimeField;
    @FXML
    private static TextField daysOfWeekField;
    public static void showFlight(ScheduledFlight flight) {
        if (flight == null) {
            fdField.clear();
            departureAirportField.clear();
            departureTimeField.clear();
            arrivalAirportField.clear();
            arrivalTimeField.clear();
            daysOfWeekField.clear();
            return;
        }
        fdField.setText(flight.getFlightDesignator());
        departureAirportField.setText(flight.getDepartureAirportIdent());
        departureTimeField.setText(flight.getDepartureTime().toString());
        arrivalAirportField.setText(flight.getArrivalAirportIdent());
        arrivalTimeField.setText(flight.getArrivalTime().toString());
        daysOfWeekField.setText(flight.getDaysOfWeek().toString());


    }

    public void updateFlight(ScheduledFlight flight) {
        flight.setFlightDesignator(fdField.getText());
        //flight.setDepartureTime(departureTimeField.getText().LocalTime.parse);
        flight.setDepartureAirportIdent(departureAirportField.getText());
        //flight.setArrivalTime(arrivalTimeField.getText().LocalTime.parse);
        //flight.setDaysOfWeek(daysOfWeekField.getText());
        flight.setArrivalAirportIdent(arrivalAirportField.getText());
    }

}