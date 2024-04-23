package edu.au.cpsc.module7.controller;

import edu.au.cpsc.module7.data.TimeIO;
import edu.au.cpsc.module7.model.TimerUIModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Timer;

public class EditController {

    @FXML
    private TextField editTimerField;
    @FXML
    private Text formatText;
    @FXML
    private Button set;
    @FXML
    private Button cancel;

    @FXML
    public void initialize() {
        var validity = Bindings.createStringBinding(() -> {
            String text = editTimerField.getText();
            if (text.matches("\\d{2}:[0-5][0-9]:[0-5][0-9]")) {
                formatText.setFill(Color.GREEN);
                return "Valid Format";
            } else {
                formatText.setFill(Color.RED);
                return "Format must be HH:MM:SS with minutes and seconds between 00 and 59";
            }
        }, editTimerField.textProperty());
        formatText.textProperty().bind(validity);
    }
    @FXML
    protected void onCancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    protected void onSet() throws IOException {
        if (formatText.getText().equals("Valid Format")) {
            TimeIO.save(editTimerField.getText());
            TimerUIModel.getInstance().setTime(editTimerField.getText());
            this.onCancel();
        }
    }



}
