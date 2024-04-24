package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.TimerApplication;
import edu.au.cpsc.module7.model.TimerModel;
import edu.au.cpsc.module7.model.TimerUIModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private Timer timer;

    private void scheduleTimer() {
        timerModel.setTimeString(timeText.getText());
        TimerTask task = new TimerTask() {
            public void run() {
                // Decrement seconds
                timerModel.setSeconds(timerModel.getSeconds() - 1);

                // Check if seconds have gone below zero
                if (timerModel.getSeconds() < 0) {
                    if (timerModel.getMinutes() > 0 || timerModel.getHours() > 0) {
                        timerModel.setSeconds(59);
                        timerModel.setMinutes(timerModel.getMinutes() - 1);
                    }

                    // Check if minutes have gone below zero
                    if (timerModel.getMinutes() < 0) {
                        if (timerModel.getHours() > 0) {
                            timerModel.setMinutes(59);
                            timerModel.setHours(timerModel.getHours() - 1);
                        } else {
                            // Set minutes to zero if hours is zero and minutes is negative.
                            timerModel.setMinutes(0);
                        }
                    }
                }
                // Check if all time components have reached zero and stop the timer
                if (timerModel.getHours() == 0 && timerModel.getMinutes() == 0 && timerModel.getSeconds() == 0) {
                    Platform.runLater(() -> {
                        start.setSelected(false);
                        start.setText("Start");
                    });
                    this.cancel();
                    timer.cancel();
                }
                String formattedTime = String.format("%02d", timerModel.getHours()) + ":"
                        + String.format("%02d", timerModel.getMinutes())
                        + ":" + String.format("%02d", timerModel.getSeconds());
                Platform.runLater(() -> TimerUIModel.getInstance().setTime(formattedTime));
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }


    //---------------------------UI METHODS---------------------------//

    /**
     * Initialize the Timer Model and timer, bind the timeText to the TimerUI Model
     */
    @FXML
    protected void initialize() {
        timerModel = new TimerModel();
        timeText.textProperty().bind(TimerUIModel.getInstance().timeProperty());
        timer = new Timer();
    }

    /**
     * Starts the TimeText countdown.
     */
    @FXML
    protected void onStartClick() {
        // Start Timer
        if (!timeText.getText().equals("00:00:00"))
        {
            if (start.isSelected()) {
                start.setText("Pause");
                scheduleTimer();
            } else {
                start.setText("Start");
                timer.cancel();  // Cancel the current timer
                timer = new Timer();  // Re-instantiate the timer for future use
            }
        }
        // If time is equal to 00:00:00, do not start the timer.
        else {
            start.setSelected(false);
        }
    }

    /**
     * Open a new edit timer window.
     */
    @FXML
    protected void onEditClick() {
        try {
            FXMLLoader loader = new FXMLLoader(TimerApplication.class.getResource("edit-timer.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Edit Timer");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}