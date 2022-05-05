module com.semesterproject.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires java.desktop;
    requires ini4j;
    requires commons.logging;
    requires javafx.swing;


    opens com.semesterproject.tourplanner to javafx.fxml;
    exports com.semesterproject.tourplanner;
    opens com.semesterproject.tourplanner.view to javafx.fxml;
}