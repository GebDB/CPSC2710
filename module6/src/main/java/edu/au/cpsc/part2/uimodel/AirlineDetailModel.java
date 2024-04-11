package edu.au.cpsc.part2.uimodel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

public class AirlineDetailModel {
    private final StringProperty fdProperty;
    private final StringProperty departureAirportProperty;
    private final StringProperty departureTimeProperty;
    private final StringProperty arrivalAirportProperty;
    private final StringProperty arrivalTimeProperty;
    private final BooleanProperty uProperty,mProperty,tProperty,wProperty,rProperty,fProperty,sProperty;
    private final BooleanProperty modifiedProperty;
    private final BooleanProperty newProperty;

    public AirlineDetailModel() {
        fdProperty = new SimpleStringProperty();
        departureAirportProperty = new SimpleStringProperty();
        departureTimeProperty = new SimpleStringProperty();
        arrivalAirportProperty = new SimpleStringProperty();
        arrivalTimeProperty = new SimpleStringProperty();
        uProperty = new SimpleBooleanProperty();
        mProperty = new SimpleBooleanProperty();
        tProperty = new SimpleBooleanProperty();
        wProperty = new SimpleBooleanProperty();
        rProperty = new SimpleBooleanProperty();
        fProperty = new SimpleBooleanProperty();
        sProperty = new SimpleBooleanProperty();
        modifiedProperty = new SimpleBooleanProperty();
        newProperty = new SimpleBooleanProperty();

        fdProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        departureAirportProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        departureTimeProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        arrivalAirportProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        arrivalTimeProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        uProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        mProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        tProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        wProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        rProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        fProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
        sProperty.addListener(((observable, oldvalue, newvalue) -> setModified(true)));
    }
    public StringProperty fdProperty() {return fdProperty;}
    public StringProperty departureAirportProperty() {return departureAirportProperty;}
    public StringProperty departureTimeProperty() {return departureTimeProperty;}
    public StringProperty arrivalAirportProperty() {return arrivalAirportProperty;}
    public StringProperty arrivalTimeProperty() {return arrivalTimeProperty;}
    public BooleanProperty uProperty() {return uProperty;}
    public BooleanProperty mProperty() {return mProperty;}
    public BooleanProperty tProperty() {return tProperty;}
    public BooleanProperty wProperty() {return wProperty;}
    public BooleanProperty rProperty() {return rProperty;}
    public BooleanProperty fProperty() {return fProperty;}
    public BooleanProperty sProperty() {return sProperty;}

    public String getFD() { return fdProperty.get();}
    public void setFD(String fd) { fdProperty.set(fd);}
    public String getDepartureAirport() { return departureAirportProperty.get();}
    public void setDepartureAirport(String da) { departureAirportProperty.set(da);}
    public String getDepartureTime() { return departureTimeProperty.get();}
    public void setDepartureTime(String dt) { departureTimeProperty.set(dt);}
    public String getArrivalAirport() { return arrivalAirportProperty.get();}
    public void setArrivalAirport(String aa) { arrivalAirportProperty.set(aa);}
    public String getArrivalTime() { return arrivalTimeProperty.get();}
    public void setArrivalTime(String at) { arrivalTimeProperty.set(at);}
    public boolean getU() { return uProperty.get();}
    public void setU(boolean u) { uProperty.set(u);}
    public boolean getM() { return mProperty.get();}
    public void setM(boolean m) { mProperty.set(m);}
    public boolean getT() { return tProperty.get();}
    public void setT(boolean t) { tProperty.set(t);}
    public boolean getW() { return wProperty.get();}
    public void setW(boolean w) { wProperty.set(w);}
    public boolean getR() { return rProperty.get();}
    public void setR(boolean r) { rProperty.set(r);}
    public boolean getF() { return fProperty.get();}
    public void setF(boolean f) { fProperty.set(f);}
    public boolean getS() { return sProperty.get();}
    public void setS(boolean s) { sProperty.set(s);}


    public BooleanProperty modifiedProperty() { return modifiedProperty; }
    public BooleanProperty newProperty() { return newProperty; }
    public boolean isModified() { return modifiedProperty.get();}
    public void setModified(boolean m) {modifiedProperty.set(m);}
    public boolean isNew() {return newProperty.get();}
    public void setNew(boolean n) {newProperty.set(n);}


}
