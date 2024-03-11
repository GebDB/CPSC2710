package module1;
import java.time.LocalDate;

public class SeatReservation {
    private String flightDesignator;
    private java.time.LocalDate flightDate;
    private String firstName;
    private String lastName;

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
    public java.time.LocalDate getFlightDate() {
        return flightDate;
    }
    public void setFlightDate(java.time.LocalDate date) {
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
    public String toString() {
        String result = "SeatReservation{flightDesignator=";

        if (flightDesignator == null) {
            String fd = "null";
            result = result + fd;
        }
        else {
            result = result + flightDesignator;
        }

        String ld ="null";
        if (flightDate != null) {
            ld = flightDate.toString();
        }
        result = result + ",flightDate=" + ld + ",firstName=";

        if (firstName == null) {
            String fn = "null";
            result = result + fn;
        }
        else {
            result = result + firstName;
        }

        result = result + ",lastName=";
        if (lastName == null) {
            String ln = "null";
            result = result + ln;
        }
        else {
            result = result + lastName;
        }
        result = result + "}";

        return result;
    }
}
