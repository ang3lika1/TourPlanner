package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;
import com.semesterproject.tourplanner.viewmodels.NewLogViewModel;
import com.semesterproject.tourplanner.viewmodels.NewTour;
import com.semesterproject.tourplanner.viewmodels.NewTourLog;
import com.semesterproject.tourplanner.viewmodels.NewTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewLogController {
    @FXML
    public TextField difficultyField;
    @FXML
    public Button createButton;
    @FXML
    public TextField ratingField;
    @FXML
    public TextField commentField;
    @FXML
    public TextField timeField;
    @FXML
    public DatePicker dateField;

    private final NewLogViewModel newLogViewModel;

    public NewLogController(NewLogViewModel newLogViewModel) {
        this.newLogViewModel = newLogViewModel;
    }

    @FXML
    void initialize() {
    }

    public void submit(ActionEvent actionEvent) {
        //Tour tour = new Tour(tourname.getText(),description.getText(), start.getText(), destination.getText(), transtype.getText(), 38, 34, routeinformation.getText());
        //TourLog tourLog = new TourLog(tourname.getText(),description.getText(), start.getText(), destination.getText(), transtype.getText(), routeinformation.getText());

        TourLog tourLog = new TourLog(dateField.getValue(),commentField.getText(),difficultyField.getText(),Integer.parseInt(timeField.getText()),ratingField.getText() );
        NewTourLog.getInstance().setCreateTourLog(tourLog);
        NewTourLog.getInstance().setCancelled(false);

        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        NewTour.getInstance().setCancelled(true);

        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
