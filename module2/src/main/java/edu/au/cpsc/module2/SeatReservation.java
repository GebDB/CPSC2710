package module2.src.main.java.edu.au.cpsc.module2;
import java.time.LocalDate;

public class SeatReservation {
    private String flightDesignator;
    private LocalDate flightDate;
    private String firstName;
    private String lastName;
    private int numberOfBags;
    private boolean flyingWithInfant;

    public String getFlightDesignator() {
        return flightDesignator;
    }
    public void setFlightDesignator(String flightDesignator) {

        if (flightDesignator == null) {
            throw new IllegalArgumentException("flight designator cannot be null");
        }
        this.flightDesignator = flightDesignator;

        if (this.flightDesignator.length() < 4 || this.flightDesignator.length() > 6) {
            throw new IllegalArgumentException("Character length is not in the bounds of 3 < x < 7");
        }
    }
    public LocalDate getFlightDate() {
        return flightDate;
    }
    public void setFlightDate(LocalDate date) {
        flightDate = date;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String fn) {
        firstName = fn;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String ln) {
        lastName = ln;
    }
    public int getNumberOfBags() { return numberOfBags; }
    public void setNumberOfBags(int bagsIn) { numberOfBags = bagsIn; }
    public boolean isFlyingWithInfant() { return flyingWithInfant; };
    public void makeFlyingWithInfant() { flyingWithInfant = true; }
    public void makeNotFlyingWithInfant() { flyingWithInfant = false; }

    public String toString() {
        String result = "SeatReservation{flightDesignator=";
        String fd = "null";
        if (flightDesignator != null) {
            fd = flightDesignator;
        }
        result = result + fd;


        String ld ="null";
        if (flightDate != null) {
            ld = flightDate.toString();
        }
        result = result + ",flightDate=" + ld + ",firstName=";

        String fn = "null";
        if (firstName != null) {
            fn = firstName;
        }
        result = result + fn + ",lastName=";

        String ln = "null";
        if (lastName != null) {
            ln = lastName;
        }
        result = result + ln + ",numberOfBags=" + numberOfBags + ",flyingWithInfant=" + flyingWithInfant + "}";

        return result;
    }
}
