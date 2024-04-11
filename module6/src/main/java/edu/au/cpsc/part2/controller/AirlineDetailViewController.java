package edu.au.cpsc.part2.controller;

import edu.au.cpsc.part2.model.ScheduledFlight;
import edu.au.cpsc.part2.uimodel.AirlineDetailModel;
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

    private AirlineDetailModel model;
   public void initialize() {
        model = new AirlineDetailModel();
        fdField.textProperty().bindBidirectional(model.fdProperty());
        departureAirportField.textProperty().bindBidirectional(model.departureAirportProperty());
        departureTimeField.textProperty().bindBidirectional(model.departureTimeProperty());
        arrivalAirportField.textProperty().bindBidirectional(model.arrivalAirportProperty());
        arrivalTimeField.textProperty().bindBidirectional(model.arrivalTimeProperty());
        u.selectedProperty().bindBidirectional(model.uProperty());
        m.selectedProperty().bindBidirectional(model.mProperty());
        t.selectedProperty().bindBidirectional(model.tProperty());
        w.selectedProperty().bindBidirectional(model.wProperty());
        r.selectedProperty().bindBidirectional(model.rProperty());
        f.selectedProperty().bindBidirectional(model.fProperty());
        s.selectedProperty().bindBidirectional(model.sProperty());
    }
    public AirlineDetailModel getModel() { return model;}
    public void showFlight(ScheduledFlight flight) {
        if (flight == null) {
            model.setFD("");
            model.setDepartureAirport("");
            model.setDepartureTime("");
            model.setArrivalAirport("");
            model.setArrivalTime("");
            model.setU(false);
            model.setM(false);
            model.setT(false);
            model.setW(false);
            model.setR(false);
            model.setF(false);
            model.setS(false);
            model.setModified(false);
            model.setNew(true);
            return;
        }
        model.setFD(flight.getFlightDesignator());
        model.setDepartureAirport(flight.getDepartureAirportIdent());

        LocalTime departureTime = flight.getDepartureTime();
        if (departureTime != null) {
            model.setDepartureTime(departureTime.toString());
        } else {
            model.setDepartureTime("");
        }

        model.setArrivalAirport(flight.getArrivalAirportIdent());

        LocalTime arrivalTime = flight.getArrivalTime();
        if (arrivalTime != null) {
            model.setArrivalTime(arrivalTime.toString());
        } else {
            model.setArrivalTime("");
        }

        HashSet<String> daysOfWeek = flight.getDaysOfWeek();
        if (daysOfWeek != null) {
            for (String day : daysOfWeek) {
                switch (day.toLowerCase()) {
                    case "u":
                        model.setU(true);
                        break;
                    case "m":
                        model.setM(true);
                        break;
                    case "t":
                        model.setT(true);
                        break;
                    case "w":
                        model.setW(true);
                        break;
                    case "r":
                        model.setR(true);
                        break;
                    case "f":
                        model.setF(true);
                        break;
                    case "s":
                        model.setS(true);
                        break;
                    default:
                        break;
                }
            }
        }
        model.setModified(false);
        model.setNew(false);
    }

    public boolean updateFlight(ScheduledFlight flight) {
        flight.setFlightDesignator(model.getFD());

        DateTimeFormatter converter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime time = LocalTime.parse(model.getDepartureTime(),converter);
        flight.setDepartureTime(time);

        flight.setDepartureAirportIdent(model.getDepartureAirport());

        time = LocalTime.parse(model.getArrivalTime(),converter);
        flight.setArrivalTime(time);

        flight.setArrivalAirportIdent(model.getArrivalAirport());

        HashSet<String> daysOfWeek = new HashSet<>();
        if (model.getU()) {
            daysOfWeek.add("U");
        }
        if (model.getM()) {
            daysOfWeek.add("M");
        }
        if (model.getT()) {
            daysOfWeek.add("T");
        }
        if (model.getW()) {
            daysOfWeek.add("W");
        }
        if (model.getR()) {
            daysOfWeek.add("R");
        }
        if (model.getF()) {
            daysOfWeek.add("F");
        }
        if (model.getS()) {
            daysOfWeek.add("S");
        }
        flight.setDaysOfWeek(daysOfWeek);
        return true;
    }

}