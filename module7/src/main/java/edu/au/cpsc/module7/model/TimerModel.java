package edu.au.cpsc.module7.model;

import java.util.Timer;

public class TimerModel {
    private String timeString;
    private Timer timer;
    public TimerModel() {
        timeString = "00:00:00";
    }

    public String getTimeString() {
        return timeString;
    }

    public void setTimeString(String timeString) {
        this.timeString = timeString;
    }
}
