package edu.au.cpsc.part2.model;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashSet;

public class ScheduledFlight implements Serializable {
    private String flightDesignator;
    private String departureAirportIdent;
    private LocalTime departureTime;
    private String arrivalAirportIdent;
    private LocalTime arrivalTime;
    private HashSet<String> daysOfWeek;

    public ScheduledFlight(String flightDesignator, String departureAirportIdent, LocalTime departureTime,
                           String arrivalAirportIdent, LocalTime arrivalTime, HashSet<String> daysOfWeek) {
        this.flightDesignator = flightDesignator;
        this.departureAirportIdent = departureAirportIdent;
        this.departureTime = departureTime;
        this.arrivalAirportIdent = arrivalAirportIdent;
        this.arrivalTime = arrivalTime;
        this.daysOfWeek = daysOfWeek;
    }

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String flightDesignator) {
        if (flightDesignator == null) {
            throw new IllegalArgumentException("Flight designator cannot be null");
        }
        this.flightDesignator = flightDesignator;
    }

    public String getDepartureAirportIdent() {
        return departureAirportIdent;
    }

    public void setDepartureAirportIdent(String departureAirportIdent) {
        if (departureAirportIdent == null) {
            throw new IllegalArgumentException("Departure airport identifier cannot be null");
        }
        this.departureAirportIdent = departureAirportIdent;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        if (departureTime == null) {
            throw new IllegalArgumentException("departure time cannot be null");
        }
        this.departureTime = departureTime;
    }

    public String getArrivalAirportIdent() {
        return arrivalAirportIdent;
    }

    public void setArrivalAirportIdent(String arrivalAirportIdent) {
        if (arrivalAirportIdent == null) {
            throw new IllegalArgumentException("Arrival airport identifier cannot be null");
        }
        this.arrivalAirportIdent = arrivalAirportIdent;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("arrival time cannot be null");
        }
        this.arrivalTime = arrivalTime;
    }

    public HashSet<String> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(HashSet<String> daysOfWeek) {
        if (daysOfWeek == null) {
            throw new IllegalArgumentException("Days of week cannot be null");
        }
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public String toString() {
        return "ScheduledFlight{" +
                "flightDesignator='" + flightDesignator + '\'' +
                ", departureAirportIdent='" + departureAirportIdent + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalAirportIdent='" + arrivalAirportIdent + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", daysOfWeek=" + daysOfWeek +
                '}';
    }
}