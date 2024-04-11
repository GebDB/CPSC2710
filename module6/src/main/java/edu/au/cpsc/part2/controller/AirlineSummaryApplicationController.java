package edu.au.cpsc.part2.controller;
import edu.au.cpsc.part2.uimodel.AirlineDetailModel;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ReadOnlyBooleanProperty;
import edu.au.cpsc.part2.data.Db;
import edu.au.cpsc.part2.model.ScheduledFlight;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.time.LocalTime;
import java.util.HashSet;

public class AirlineSummaryApplicationController {
    @FXML
    public Button deleteButton;
    @FXML
    public MenuItem deleteMenuItem;
    @FXML
    private AirlineTableViewController airlineTableViewController;

    @FXML
    private AirlineDetailViewController airlineDetailViewController;

    @FXML
    private Button updateButton;

    @FXML
    private MenuItem updateMenuItem;
    @FXML
    private Button newButton;
    @FXML
    private MenuItem newMenuItem;

    private ScheduledFlight flightBeingEdited;


    public void initialize() {
        airlineTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        airlineTableViewController.onFlightSelectionChanged(
                event -> showFlight(event.getSelectedFlight()));
        AirlineDetailModel uiModel = airlineDetailViewController.getModel();
        updateButton.disableProperty().bind(uiModel.modifiedProperty().not());
        updateMenuItem.disableProperty().bind(uiModel.modifiedProperty().not());
        newButton.disableProperty().bind(uiModel.modifiedProperty()
                .or(uiModel.newProperty()));
        newMenuItem.disableProperty().bind(uiModel.modifiedProperty()
                .or(uiModel.newProperty()));
        deleteButton.disableProperty().bind(uiModel.modifiedProperty()
                .or(uiModel.newProperty()));
        deleteMenuItem.disableProperty().bind(uiModel.modifiedProperty()
                .or(uiModel.newProperty()));

        showFlight(null);
    }

    private void showFlight(ScheduledFlight flight) {
        airlineDetailViewController.showFlight(flight);
        LocalTime time = null;
        HashSet<String> days = null;
        flightBeingEdited = flight == null ? new ScheduledFlight("", "",
                time, "", time, days) : flight;
        String buttonLabel = airlineDetailViewController.getModel().isNew() ? "Add" : "Update";
        updateButton.setText(buttonLabel);
        updateMenuItem.setText(buttonLabel);
    }

    @FXML
    protected void updateButtonAction() {
        airlineDetailViewController.updateFlight(flightBeingEdited);
        if (airlineDetailViewController.getModel().isNew()) {
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
        if (airlineDetailViewController.getModel().isNew()) {
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