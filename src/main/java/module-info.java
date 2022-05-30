module com.semesterproject.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.gson;
    requires java.desktop;
    requires ini4j;
    requires org.apache.logging.log4j;
    requires layout;
    requires kernel;
    requires io;
    requires org.controlsfx.controls;

    opens com.semesterproject.tourplanner;
    exports com.semesterproject.tourplanner;
    opens com.semesterproject.tourplanner.view to javafx.fxml, org.controlsfx.controls, javafx.graphics;
    opens com.semesterproject.tourplanner.models to javafx.base, org.controlsfx.controls;
}