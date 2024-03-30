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
    private TableView<ScheduledFlight> airlineTableView;

  //  @FXML
  //  private TableColumn<ScheduledFlight, String> fdColumn, departAirportColumn, departTimeColumn,
                     //   arrivalAirportColumn, arrivalTimeColumn, daysOfWeekColumn;'
    @FXML
    private TableColumn<ScheduledFlight, String> fdColumn, departAirportColumn, arrivalAirportColumn;
    @FXML
    private TableColumn<ScheduledFlight, LocalTime> departTimeColumn, arrivalTimeColumn;
    @FXML
    private TableColumn<ScheduledFlight, HashSet<String>> daysOfWeekColumn;

    public void initialize() {
        fdColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("FlightDesignator"));
        departAirportColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("DepartureAirportIdent"));
        departTimeColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,LocalTime>("DepartureTime"));
        arrivalAirportColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,String>("ArrivalAirportIdent"));
        arrivalTimeColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight,LocalTime>("ArrivalTime"));
        daysOfWeekColumn.setCellValueFactory(new PropertyValueFactory<ScheduledFlight, HashSet<String>>("DaysOfWeek"));
        airlineTableView.getSelectionModel().selectedItemProperty().addListener(c -> tableSelectionChanged());
    }

    public void showFlights(List<ScheduledFlight> flights) {
        SortedList<ScheduledFlight> sortedList = new SortedList<>(FXCollections.observableList(flights));
        airlineTableView.setItems(sortedList);
        sortedList.comparatorProperty().bind(airlineTableView.comparatorProperty());
        airlineTableView.refresh();
    }

    public void onFlightSelectionChanged(EventHandler<FlightTableEvent> handler) {
        airlineTableView.addEventHandler(FlightTableEvent.FLIGHT_SELECTED, handler);
    }

    private void tableSelectionChanged() {
        ScheduledFlight selectedFlight = airlineTableView.getSelectionModel().getSelectedItem();
        FlightTableEvent event = new FlightTableEvent(FlightTableEvent.FLIGHT_SELECTED,
                selectedFlight);
        airlineTableView.fireEvent(event);
    }

    public void select(ScheduledFlight flight) {
        airlineTableView.getSelectionModel().select(flight);
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