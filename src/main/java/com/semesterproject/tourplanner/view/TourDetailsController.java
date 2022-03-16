package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.viewmodels.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TourDetailsController {
    @FXML
    public TextField nameTextField;

    private final TourDetailsViewModel tourDetailsViewModel;

    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    public TourDetailsViewModel getTourDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
    }
}
