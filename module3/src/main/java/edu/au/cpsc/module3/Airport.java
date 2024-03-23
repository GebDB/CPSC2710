package edu.au.cpsc.module3;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private Double[] coordinates = new Double[2];

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

    public Double[] getCoordinates() {
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

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }
    public static List<Airport> readAll() throws IOException {
        List<Airport> airports = new ArrayList<>();
        try {
            InputStream inputStream = Airport.class.getResourceAsStream("airport-codes.csv");
            if (inputStream == null) {
                throw new IOException("Cannot find airport-codes.csv file");
            }
            Scanner sc = new Scanner(inputStream);
            sc.nextLine();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");
                Airport airport = new Airport();
                airport.setIdent(parts[0]);
                airport.setType(parts[1]);
                airport.setName(parts[2]);
                airport.setElevationft(Integer.parseInt(parts[3]));
                airport.setContinent(parts[4]);
                airport.setCountry(parts[5]);
                airport.setRegion(parts[6]);
                airport.setMunicipality(parts[7]);
                airport.setGpsCode(parts[8]);
                airport.setIataCode(parts[9]);
                airport.setLocalCode(parts[10]);
                Double[] coords = {Double.parseDouble(parts[11]), Double.parseDouble(parts[12])};
                airport.setCoordinates(coords);
                airports.add(airport);
            }
        }catch (Exception e) {
            System.out.println(e);
        }
        return airports;
    }
}
