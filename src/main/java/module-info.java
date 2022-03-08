module com.semesterproject.tourplanner {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.semesterproject.tourplanner to javafx.fxml;
    exports com.semesterproject.tourplanner;
    exports com.semesterproject.tourplanner.injection;
    opens com.semesterproject.tourplanner.injection to javafx.fxml;
}