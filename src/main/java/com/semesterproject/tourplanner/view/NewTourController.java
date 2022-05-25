package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.viewmodels.NewTour;
import com.semesterproject.tourplanner.viewmodels.NewTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTourController {
    @FXML
    public Button cancelButton;
    @FXML
    public TextField transtype;
    @FXML
    public TextField description;
    @FXML
    public TextField routeinformation;
    @FXML
    private TextField tourname;
    @FXML
    private TextField start;
    @FXML
    private TextField destination;
    @FXML
    private Button createButton;

    private final NewTourViewModel newTourViewModel;

    public NewTourController(NewTourViewModel newTourViewModel) {
        this.newTourViewModel = newTourViewModel;
    }


    public NewTourViewModel getNewTourViewModel() {
        return newTourViewModel;
    }

    @FXML
    void initialize() {
    }

    public void submit(ActionEvent actionEvent) {
        Tour tour = new Tour(tourname.getText(),description.getText(), start.getText(), destination.getText(), transtype.getText(), routeinformation.getText());

        NewTour.getInstance().setCreateTour(tour);
        NewTour.getInstance().setCancelled(false);

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
