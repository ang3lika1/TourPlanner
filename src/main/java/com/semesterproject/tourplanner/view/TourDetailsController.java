package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.viewmodels.TourDetailsViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class TourDetailsController {
    @FXML
    public TextField nameTextField;
    @FXML
    public ImageView mapImg;

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
        mapImg.imageProperty().bindBidirectional(tourDetailsViewModel.mapImage());
    }
}
