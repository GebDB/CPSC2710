package edu.au.cpsc.module7.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Timer;

public class TimerUIModel {
    private static final TimerUIModel instance = new TimerUIModel();
    private final StringProperty timeProperty;
    private TimerUIModel(){
        timeProperty = new SimpleStringProperty();
        this.setTime("00:00:00");
    }
    public static TimerUIModel getInstance() {
        return instance;
    }
    public void setTime(String time) {
        timeProperty.set(time);
    }
    public String getTime() {
        return timeProperty.get();
    }
    public StringProperty timeProperty() {return timeProperty; }

}
