package edu.au.cpsc.module3;

import java.io.IOException;
import java.util.List;

public class Airport {
    private String ident;
    private String type;
    private String name;
    private Integer elevationft;
    private String continent;
    private String country;
    private String region;
    private String municipality;
    private String gpsCode;
    private String iataCode;
    private String localCode;
    private double[] coordinates = new double[2];

    public String getIdent() {
        return ident;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Integer getElevationft() {
        return elevationft;
    }

    public String getContinent() {
        return continent;
    }

    public String getCountry() {
        return country;
    }

    public String getRegion() {
        return region;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getGpsCode() {
        return gpsCode;
    }

    public String getIataCode() {
        return iataCode;
    }

    public String getLocalCode() {
        return localCode;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setElevationft(Integer elevationft) {
        this.elevationft = elevationft;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setGpsCode(String gpsCode) {
        this.gpsCode = gpsCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

}
