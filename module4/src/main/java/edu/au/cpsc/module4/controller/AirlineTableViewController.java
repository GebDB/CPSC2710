package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.data.Db;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;

import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AirlineTableViewController  {

    @FXML
    private TableView<ScheduledFlight> scheduledFlightTableView;

    @FXML
    private TableColumn<ScheduledFlight, String> fdColumn, departAirportColumn, departTimeColumn,
                        arrivalAirportColumn, arrivalTimeColumn, daysOfWeekColumn;

    public void initialize() {
        fdColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("Flight Designator"));
        departAirportColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("Departure Airport"));
        departTimeColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("Departure Time"));
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("Arrival Airport"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("Arrival Time"));
        departTimeColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("Departure Time"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("Days of Week"));
        scheduledFlightTableView.getSelectionModel().selectedItemProperty().addListener(c -> tableSelectionChanged());
    }

    public void showFlights(List<ScheduledFlight> flights) {
        SortedList<ScheduledFlight> sortedList = new SortedList<>(FXCollections.observableList(flights));
        scheduledFlightTableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(scheduledFlightTableView.comparatorProperty());
        scheduledFlightTableView.refresh();
    }

    public void onFlightSelectionChanged(EventHandler<FlightTableEvent> handler) {
        scheduledFlightTableView.addEventHandler(FlightTableEvent.FLIGHT_SELECTED, handler);
    }

    private void tableSelectionChanged() {
        ScheduledFlight selectedFlight = scheduledFlightTableView.getSelectionModel().getSelectedItem();
        FlightTableEvent event = new FlightTableEvent(FlightTableEvent.FLIGHT_SELECTED,
                selectedFlight);
        scheduledFlightTableView.fireEvent(event);
    }

    public void select(ScheduledFlight flight) {
        scheduledFlightTableView.getSelectionModel().select(flight);
    }

    public static class FlightTableEvent extends Event {

        public static final EventType<FlightTableEvent> ANY = new EventType<>(Event.ANY, "ANY");

        public static final EventType<FlightTableEvent> FLIGHT_SELECTED = new EventType<>(ANY, "FLIGHT_SELECTED");

        private ScheduledFlight selectedFlight;

        public FlightTableEvent(EventType<? extends Event> eventType, ScheduledFlight selectedFlight) {
            super(eventType);
            this.selectedFlight = selectedFlight;
        }

        public ScheduledFlight getSelectedFlight() {
            return selectedFlight;
        }

    }

}