package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

public class AirlineDetailViewController {
    @FXML
    private TextField fdField;
    @FXML
    private TextField departureAirportField;
    @FXML
    private TextField departureTimeField;
    @FXML
    private TextField arrivalAirportField;
    @FXML
    private TextField arrivalTimeField;
    @FXML
    private ToggleButton u,m,t,w,r,f,s;
    public void showFlight(ScheduledFlight flight) {
        if (flight == null) {
            fdField.clear();
            departureAirportField.clear();
            departureTimeField.clear();
            arrivalAirportField.clear();
            arrivalTimeField.clear();
            u.setSelected(false);
            m.setSelected(false);
            t.setSelected(false);
            w.setSelected(false);
            r.setSelected(false);
            f.setSelected(false);
            s.setSelected(false);
            return;
        }
        fdField.setText(flight.getFlightDesignator());
        departureAirportField.setText(flight.getDepartureAirportIdent());

        LocalTime departureTime = flight.getDepartureTime();
        if (departureTime != null) {
            departureTimeField.setText(departureTime.toString());
        } else {
            departureTimeField.setText("");
        }

        arrivalAirportField.setText(flight.getArrivalAirportIdent());

        LocalTime arrivalTime = flight.getArrivalTime();
        if (arrivalTime != null) {
            arrivalTimeField.setText(arrivalTime.toString());
        } else {
            arrivalTimeField.setText("");
        }

        HashSet<String> daysOfWeek = flight.getDaysOfWeek();
        if (daysOfWeek != null) {
            for (String day : daysOfWeek) {
                switch (day.toLowerCase()) {
                    case "u":
                        u.setSelected(true);
                        break;
                    case "m":
                        m.setSelected(true);
                        break;
                    case "t":
                        t.setSelected(true);
                        break;
                    case "w":
                        w.setSelected(true);
                        break;
                    case "r":
                        r.setSelected(true);
                        break;
                    case "f":
                        f.setSelected(true);
                        break;
                    case "s":
                        s.setSelected(true);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void updateFlight(ScheduledFlight flight) {
        flight.setFlightDesignator(fdField.getText());

        DateTimeFormatter converter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(departureTimeField.getText(),converter);
        flight.setDepartureTime(time);

        flight.setDepartureAirportIdent(departureAirportField.getText());

        time = LocalTime.parse(arrivalTimeField.getText(),converter);
        flight.setArrivalTime(time);

        HashSet<String> daysOfWeek = new HashSet<>();
        if (u.isSelected()) {
            daysOfWeek.add("U");
        }
        if (m.isSelected()) {
            daysOfWeek.add("M");
        }
        if (t.isSelected()) {
            daysOfWeek.add("T");
        }
        if (w.isSelected()) {
            daysOfWeek.add("W");
        }
        if (r.isSelected()) {
            daysOfWeek.add("R");
        }
        if (f.isSelected()) {
            daysOfWeek.add("F");
        }
        if (s.isSelected()) {
            daysOfWeek.add("S");
        }
        flight.setDaysOfWeek(daysOfWeek);

        flight.setArrivalAirportIdent(arrivalAirportField.getText());
    }

}