package edu.au.cpsc.module3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;

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
    protected void webViewAction() {
        if (!identTextfield.getText().isEmpty()) {

        }
        String url = "https://www.windy.com/?";
        WebEngine webEngine = webView.getEngine();
        webEngine.load(url);
    }
}