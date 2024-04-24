package edu.au.cpsc.module7.model;

import java.util.Timer;
import java.util.TimerTask;

public class TimerModel {
    private String timeString;
    private int hours, minutes, seconds;

    public TimerModel() {
        timeString = "00:00:00";
    }

    private String getTimeString() {
        return timeString;
    }
    public void setTimeString(String timeString) {
        this.timeString = timeString;
        parseTimeString(timeString);
    }
    private void parseTimeString(String timeString) {
        String[] parts = timeString.split(":");
        hours = Integer.parseInt(parts[0]);
        minutes = Integer.parseInt(parts[1]);
        seconds = Integer.parseInt(parts[2]);
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

}
