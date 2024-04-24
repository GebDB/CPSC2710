package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.TimerApplication;
import edu.au.cpsc.module7.data.ColorsIO;
import edu.au.cpsc.module7.data.TimeIO;
import edu.au.cpsc.module7.model.TimerModel;
import edu.au.cpsc.module7.model.TimerUIModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Menu;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
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
    @FXML
    public ColorPicker backgroundPicker;
    @FXML
    public ColorPicker ringColorPicker;
    @FXML
    public BorderPane background;
    @FXML
    public Circle ring;
    @FXML
    public ColorPicker innerCircleColorPicker;
    //-----------------------Timer Setup-------------------------//
    private TimerModel timerModel;
    private Timer timer;
    private Color[] colors = new Color[3];
    /**
     * Timer Functionality Here.
     */
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
                        reset.setOpacity(1.0);
                        edit.setOpacity(1.0);
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
     * Initialize the Timer Model and timer, bind the timeText to the TimerUI Model. Initialize colors.
     */
    @FXML
    protected void initialize() throws IOException, ClassNotFoundException {
        timerModel = new TimerModel();
        timeText.textProperty().bind(TimerUIModel.getInstance().timeProperty());
        timer = new Timer();
        String time = TimeIO.load();
        TimerUIModel.getInstance().setTime(time);

        loadColors();
        backgroundPicker.setValue(colors[0]);
        ringColorPicker.setValue(colors[1]);
        innerCircleColorPicker.setValue(colors[2]);
        String colorHex = ColorsIO.toHexString(colors[0]);
        background.setStyle("-fx-background-color: " + colorHex + ";");
        ring.setStroke(colors[1]);
        ring.setFill(colors[2]);

    }
    @FXML
    protected void onResetClick() throws IOException, ClassNotFoundException {
        if (!start.isSelected()) {
            reset.arm();
            TimerUIModel.getInstance().setTime(TimeIO.load());
        }
        else {
            reset.disarm();
        }
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
                reset.setOpacity(0.5);
                edit.setOpacity(0.5);
                scheduleTimer();
            } else {
                start.setText("Start");
                reset.setOpacity(1.0);
                edit.setOpacity(1.0);
                timer.cancel();  // Cancel the current timer
                timer = new Timer();  // Re-instantiate the timer for future use
            }
        }
        // If time is equal to 00:00:00, do not start the timer.
        else {
            reset.setOpacity(1.0);
            edit.setOpacity(1.0);
            start.setSelected(false);
        }
    }

    /**
     * Open a new edit timer window.
     */
    @FXML
    protected void onEditClick() {
        if (!start.isSelected()) {
            edit.arm();
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
        else {
            edit.disarm();
        }
    }

    /**
     * Adjusts and saves background color.
     */
    @FXML
    protected void backgroundPicked() {
        Color color = backgroundPicker.getValue();
        colors[0] = color;
        ColorsIO.save(colors);
        String colorHex = ColorsIO.toHexString(colors[0]);
        background.setStyle("-fx-background-color: " + colorHex + ";");
    }
    /**
     * Adjusts and saves ring color.
     */
    @FXML
    protected void ringColorPicked() {
        Color color = ringColorPicker.getValue();
        colors[1] = color;
        ColorsIO.save(colors);
        ring.setStroke(color);
    }
    /**
     * Adjusts and saves inner circle color.
     */
    @FXML
    protected void innerCircleColorPicked() {
        Color color = innerCircleColorPicker.getValue();
        colors[2] = color;
        ColorsIO.save(colors);
        ring.setFill(color);
    }
    /**
     * Loads saved colors, saves a new file if load is null.
     */
    private void loadColors() throws IOException, ClassNotFoundException {
        if (ColorsIO.load() == null) {
            colors[0] = Color.WHITE;
            colors[1] = Color.RED;
            colors[2] = Color.WHITE;
            ColorsIO.save(colors);
        }
        else {
            colors = ColorsIO.load();
        }
    }
}