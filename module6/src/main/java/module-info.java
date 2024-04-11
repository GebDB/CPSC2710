module edu.au.cpsc {
        requires javafx.controls;
        requires javafx.fxml;

        opens edu.au.cpsc.part1 to javafx.fxml;
        opens edu.au.cpsc.part2 to javafx.fxml;

        exports edu.au.cpsc.part1;
        exports edu.au.cpsc.part2;

        exports edu.au.cpsc.part2.data;
        opens edu.au.cpsc.part2.data to javafx.fxml;
        exports edu.au.cpsc.part2.model;
        opens edu.au.cpsc.part2.model to javafx.fxml;
        exports edu.au.cpsc.part2.controller;
        opens edu.au.cpsc.part2.controller to javafx.fxml;
        exports edu.au.cpsc.part2.uimodel;
        opens edu.au.cpsc.part2.uimodel to javafx.fxml;
}