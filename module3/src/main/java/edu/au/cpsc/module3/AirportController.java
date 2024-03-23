package edu.au.cpsc.module3;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.scene.web.WebEngine;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class AirportController {
    public TextField municipalityTextfield;
    @FXML
    public TextField regionTextfield;
    @FXML
    public TextField countryTextfield;
    @FXML
    public TextField elevationTextfield;
    @FXML
    public TextField nameTextfield;
    @FXML
    public TextField typeTextfield;
    @FXML
    private WebView webView;
    @FXML
    private TextField identTextfield;
    @FXML
    private TextField iataCodeTextfield;
    @FXML
    private TextField localCodeTextfield;
    @FXML
    private Button searchButton; // Declared for functionality.

    // Updates webView and calls updateFields().
    @FXML
    protected void webViewAction() throws IOException {
        String searchTerm;
        Airport airport = new Airport();
        boolean found = false;

        // Check if the fields are empty and find the correct airport object.
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
                return; // End method if the ident cannot be found.
            }
        }
        else if (!iataCodeTextfield.getText().isEmpty()) {
            searchTerm = iataCodeTextfield.getText();
            List<Airport> airports = Airport.readAll();
            for (Airport tempAirport : airports) {

                if (tempAirport.getIataCode().equalsIgnoreCase(searchTerm)) {
                    airport = tempAirport;
                    found = true;
                    break;
                }
            }
            if (!found) {
                return; // End method if the iata code cannot be found.
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
                return; // End method if the local code cannot be found.
            }
        }
        else {
            return; // End method if all textfields are empty.
        }

        //Set the url and load the url into the webEngine. Then call updateFields().
        Double[] coordinates = airport.getCoordinates();
        DecimalFormat df = new DecimalFormat("#.###");
        String coordinate1 = df.format(coordinates[0]);
        String coordinate2 = df.format(coordinates[1]);
        String url = "https://www.windy.com/?" + coordinate2 + "," + coordinate1 + ",12" ;
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);
        updateFields(airport);
    }
    //Updates the UI fields after webViewAction is successfully called and executed.
    private void updateFields(Airport airport) {
        municipalityTextfield.setText(airport.getMunicipality());
        regionTextfield.setText(airport.getRegion());
        countryTextfield.setText(airport.getCountry());
        elevationTextfield.setText(airport.getElevationft().toString());
        nameTextfield.setText(airport.getName());
        typeTextfield.setText(airport.getType());
        identTextfield.setText(airport.getIdent());
        iataCodeTextfield.setText(airport.getIataCode());
        localCodeTextfield.setText(airport.getLocalCode());
    }

}