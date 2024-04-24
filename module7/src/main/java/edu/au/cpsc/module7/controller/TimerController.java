package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.TimerApplication;
import edu.au.cpsc.module7.data.TimeIO;
import edu.au.cpsc.module7.model.TimerModel;
import edu.au.cpsc.module7.model.TimerUIModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class TimerController {
    //-------------------------FXML--------------------------//
    @FXML
    public Menu start2;
    @FXML
    public Menu reset2;
    @FXML
    public Menu edit2;
    @FXML
    public ToggleButton start;
    @FXML
    public Button reset;
    @FXML
    public Button edit;
    @FXML
    public Text timeText;
    //-----------------------Timer Setup-------------------------//
    private TimerModel timerModel;
    private boolean started;
    private Timer timer;
    private final TimerTask task = new TimerTask() {
        public void run() {
            if (timerModel.getSeconds() >= 0) {
                timerModel.setSeconds(timerModel.getSeconds() - 1);
            }
            else {
                if (timerModel.getHours() > 0 || timerModel.getMinutes() > 0) {
                    timerModel.setSeconds(59);
                    if (timerModel.getMinutes() >= 0) {
                        timerModel.setMinutes(timerModel.getMinutes() - 1);
                    }
                    else if (timerModel.getHours() > 0) {
                        timerModel.setHours(timerModel.getHours() - 1);
                    }
                }
            }
            String formattedTime = String.format("%02d", timerModel.getHours()) + ":"
                    + String.format("%02d", timerModel.getMinutes())
                    + ":" + String.format("%02d", timerModel.getSeconds());
            TimerUIModel.getInstance().setTime(formattedTime);
        }
    };

    //---------------------------METHODS---------------------------//

    /**
     * Initialize the Timer Model and timer, bind the timeText to the TimerUI Model
     */
    @FXML
    protected void initialize() {
        timerModel = new TimerModel();
        timeText.textProperty().bind(TimerUIModel.getInstance().timeProperty());
        started = false;
        timer = new Timer();
    }

    /**
     * Starts the TimeText countdown.
     */
    @FXML
    protected void onStartClick() {
        int hours, minutes, seconds;
        hours = timerModel.getHours();
        minutes = timerModel.getMinutes();
        seconds = timerModel.getSeconds();

        if (start.isSelected() && !started) {
            start.setText("Pause");
            timerModel.setTimeString(timeText.getText());
            started = true;
            timer.scheduleAtFixedRate(task, 1000, 1000);
        }
        else if (!start.isSelected() && started) {
            start.setText("Start");
            timerModel.setTi
        }
    }
    @FXML
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