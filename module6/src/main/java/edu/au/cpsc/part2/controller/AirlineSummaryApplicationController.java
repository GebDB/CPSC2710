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

    public void initialize() {
        airlineTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        airlineTableViewController.onFlightSelectionChanged(
                event -> showFlight(event.getSelectedFlight()));
        AirlineDetailModel uiModel = airlineDetailViewController.getModel();
        updateButton.disableProperty().bind(uiModel.modifiedProperty().not());
        updateButton.textProperty().bind(Bindings.when(uiModel.newProperty()).then("Add").otherwise("Update"));
        updateMenuItem.disableProperty().bind(uiModel.modifiedProperty().not());
        updateMenuItem.textProperty().bind(Bindings.when(uiModel.newProperty()).then("Add").otherwise("Update"));
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
    }

    @FXML
    protected void updateButtonAction() {
        if (airlineDetailViewController.getModel().isNew()) {
            addFlight();
        }
        else {
            updateFlight();
        }

    }
    private void addFlight() {
        ScheduledFlight flight = new ScheduledFlight("", "", null, "", null, null);
        if (!airlineDetailViewController.updateFlight(flight)) {
            return;
        }
        Db.getDatabase().addScheduledFlight(flight);
        Db.saveDatabase();
        airlineTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        airlineTableViewController.select(flight);
    }
    private void updateFlight() {
        ScheduledFlight flight = getFlightBeingEdited();
        if(!airlineDetailViewController.updateFlight(flight)){
            return;
        }
        Db.getDatabase().updateScheduledFlight(flight);
        Db.saveDatabase();
        airlineTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        airlineTableViewController.select(getFlightBeingEdited());
    }

    private ScheduledFlight getFlightBeingEdited() {
        return airlineTableViewController.getFlight();
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
            Db.getDatabase().removeScheduledFlight(getFlightBeingEdited());
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