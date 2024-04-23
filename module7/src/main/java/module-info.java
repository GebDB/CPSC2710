module edu.au.cpsc.module7 {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.au.cpsc.module7 to javafx.fxml;
    exports edu.au.cpsc.module7;
    exports edu.au.cpsc.module7.controller;
    exports edu.au.cpsc.module7.model;
    exports edu.au.cpsc.module7.data;
    opens edu.au.cpsc.module7.controller to javafx.fxml;

}