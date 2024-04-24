package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.TimerApplication;
import edu.au.cpsc.module7.data.ColorsIO;
import edu.au.cpsc.module7.data.TimeIO;
import edu.au.cpsc.module7.data.VolumeIO;
import edu.au.cpsc.module7.model.TimerModel;
import edu.au.cpsc.module7.model.TimerUIModel;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.util.Timer;
import java.util.TimerTask;

public class TimerController {
    //-------------------------FXML--------------------------//
    @FXML
    public MenuItem startMenu;
    @FXML
    public MenuItem resetMenu;
    @FXML
    public MenuItem editMenu;
    @FXML
    public MenuItem closeMenu;
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
    @FXML
    public CheckBox alarm;
    @FXML
    public Slider volumeSlider;
    private Color[] colors = new Color[3];
    //-----------------------Timer Setup-------------------------//
    private TimerModel timerModel;
    private Timer timer;
    private MediaPlayer mediaPlayer;
    private double volume;

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
                        startMenu.setText("Start");
                        reset.setOpacity(1.0);
                        edit.setOpacity(1.0);
                        if (alarm.isSelected()) {
                            mediaPlayer.play();
                            mediaPlayer.seek(mediaPlayer.getStartTime());
                        }
                    });
                    this.cancel();
                    timer = new Timer();
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
    protected void initialize() throws ClassNotFoundException {
        timerModel = new TimerModel();
        timeText.textProperty().bind(TimerUIModel.getInstance().timeProperty());
        timer = new Timer();
        String time = TimeIO.load();
        TimerUIModel.getInstance().setTime(time);

        Media media = new Media(TimerApplication.class.getResource("alarm-sound.mp3").toString());
        mediaPlayer = new MediaPlayer(media);
        volume = VolumeIO.load();
        volumeSlider.setValue(volume);
        mediaPlayer.setVolume(volumeSlider.getValue()/100);
        System.out.println(volume);


        loadColors();
        backgroundPicker.setValue(colors[0]);
        ringColorPicker.setValue(colors[1]);
        innerCircleColorPicker.setValue(colors[2]);
        String colorHex = ColorsIO.toHexString(colors[0]);
        background.setStyle("-fx-background-color: " + colorHex + ";");
        ring.setStroke(colors[1]);
        ring.setFill(colors[2]);

    }
    /**
     * Menu Shortcuts
     */
    @FXML
    protected void startShortcut() {
        start.setSelected(!start.isSelected());
        onStartClick();
    }
    @FXML
    protected void resetShortcut() throws ClassNotFoundException {
        onResetClick();
    }
    @FXML
    protected void editShortcut() {
        onEditClick();
    }
    @FXML
    protected void closeShortcut() {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Resets the timer to the original time saved.
     */
    @FXML
    protected void onResetClick() throws ClassNotFoundException {
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
                startMenu.setText("Pause");
                reset.setOpacity(0.5);
                edit.setOpacity(0.5);
                scheduleTimer();
            } else {
                start.setText("Start");
                startMenu.setText("Start");
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
     * Sets and saves the new volume.
     */
    @FXML
    protected void volumeSet() {
        mediaPlayer.setVolume(volumeSlider.getValue()/100);
        VolumeIO.save(volumeSlider.getValue());
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
    private void loadColors() throws ClassNotFoundException {
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