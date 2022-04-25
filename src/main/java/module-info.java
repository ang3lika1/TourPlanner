module com.semesterproject.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.semesterproject.tourplanner to javafx.fxml;
    exports com.semesterproject.tourplanner;
    opens com.semesterproject.tourplanner.view to javafx.fxml;
}