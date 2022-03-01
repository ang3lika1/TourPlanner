module com.semesterproject.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.semesterproject.tourplanner to javafx.fxml;
    exports com.semesterproject.tourplanner;
}