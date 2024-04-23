package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.TimerApplication;
import edu.au.cpsc.module7.model.TimerModel;
import edu.au.cpsc.module7.model.TimerUIModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class TimerController {
    @FXML
    public Menu start2;
    @FXML
    public Menu reset2;
    @FXML
    public Menu edit2;
    @FXML
    public Button start;
    @FXML
    public Button reset;
    @FXML
    public Button edit;
    @FXML
    public Text timeText;
    private TimerModel timerModel;
    private boolean started;
    private boolean paused;
    @FXML
    protected void initialize() {
        timerModel = new TimerModel();
        timeText.textProperty().bind(TimerUIModel.getInstance().timeProperty());
        started = false;
        paused = true;
    }
    @FXML
    protected void onStartClick() {
        if (!started) {
            timerModel.setTimeString(timeText.getText());
            this.countdown();
        }
        else {
            
        }

    }

    private void countdown() {
    }

    protected void onEditClick() {
        try {
            FXMLLoader loader = new FXMLLoader(TimerApplication.class.getResource("edit-timer.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit TimerModel");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}