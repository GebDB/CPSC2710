package edu.au.cpsc.module3;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Objects;

public class AirportController {
    @FXML
    private WebView webView;
    @FXML
    private TextField identTextfield;
    @FXML
    private TextField iataCodeTextfield;
    @FXML
    private TextField localCodeTextfield;
    @FXML
    private Button searchButton;

    @FXML
    protected void webViewAction() throws IOException {
        String searchTerm;
        Airport airport = new Airport();
        boolean found = false;

        if (!identTextfield.getText().isEmpty()) {
            searchTerm = identTextfield.getText();
            List<Airport> airports = Airport.readAll();
            for (Airport tempAirport : airports) {
                if (tempAirport.getIdent().equalsIgnoreCase(searchTerm)) {
                    airport = tempAirport;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
        }
        else if (!iataCodeTextfield.getText().isEmpty()) {
            searchTerm = iataCodeTextfield.getText();
            List<Airport> airports = Airport.readAll();
            for (Airport tempAirport : airports) {
                System.out.println(tempAirport.getIataCode());
                if (tempAirport.getIataCode().equalsIgnoreCase(searchTerm)) {
                    airport = tempAirport;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
        }
        else if (!localCodeTextfield.getText().isEmpty()) {
            searchTerm = localCodeTextfield.getText();
            List<Airport> airports = Airport.readAll();
            for (Airport tempAirport : airports) {
                if (tempAirport.getLocalCode().equalsIgnoreCase(searchTerm)) {
                    airport = tempAirport;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return;
            }
        }
        else {
            return;
        }
        System.out.println(airport.getIataCode());
        try {
            Double[] coordinates = airport.getCoordinates();
            String url = "https://www.windy.com/?" + coordinates[0].toString() + "," + coordinates[1].toString();
            WebEngine webEngine = webView.getEngine();
            webEngine.load(url);
        }
        catch (Exception e) {

            System.out.println(e);
        }
    }
}