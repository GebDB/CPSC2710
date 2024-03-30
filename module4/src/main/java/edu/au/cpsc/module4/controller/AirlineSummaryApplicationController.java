package edu.au.cpsc.module4.controller;

import edu.au.cpsc.module4.data.Db;
import edu.au.cpsc.module4.model.ScheduledFlight;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.time.LocalTime;
import java.util.HashSet;

public class AirlineSummaryApplicationController {
    @FXML
    private AirlineTableViewController airlineTableViewController;

    @FXML
    private AirlineDetailViewController airlineDetailViewController;

    @FXML
    private Button updateButton;

    @FXML
    private MenuItem updateMenuItem;


    private ScheduledFlight flightBeingEdited;


    private boolean flightBeingEditedIsNew;

    public void initialize() {
        airlineTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        airlineTableViewController.onFlightSelectionChanged(
                event -> showFlight(event.getSelectedFlight()));
        showFlight(null);
    }

    private void showFlight(ScheduledFlight flight) {
        airlineDetailViewController.showFlight(flight);
        LocalTime time = null;
        HashSet<String> days = null;
        flightBeingEdited = flight == null ? new ScheduledFlight("", "",
                time, "", time, days) : flight;
        flightBeingEditedIsNew = flight == null;
        String buttonLabel = flightBeingEditedIsNew ? "Add" : "Update";
        updateButton.setText(buttonLabel);
        updateMenuItem.setText(buttonLabel);
    }

    @FXML
    protected void updateButtonAction() {
        airlineDetailViewController.updateFlight(flightBeingEdited);
        if (flightBeingEditedIsNew) {
            Db.getDatabase().addScheduledFlight(flightBeingEdited);
        } else {
            Db.getDatabase().updateScheduledFlight(flightBeingEdited);
        }
        Db.saveDatabase();
        airlineTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        airlineTableViewController.select(flightBeingEdited);
    }

    @FXML
    protected void newButtonAction() {
        airlineTableViewController.select(null);
    }

    @FXML
    protected void deleteButtonAction() {
        if (flightBeingEditedIsNew) {
            airlineTableViewController.select(null);
        } else {
            Db.getDatabase().removeScheduledFlight(flightBeingEdited);
            Db.saveDatabase();
            airlineTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        }
    }

    @FXML
    protected void updateMenuAction() {
        updateButtonAction();
    }

    @FXML
    protected void newMenuAction() {
        newButtonAction();
    }

    @FXML
    protected void deleteMenuAction() {
        deleteButtonAction();
    }

    @FXML
    protected void closeMenuAction() {
        Platform.exit();
    }


}