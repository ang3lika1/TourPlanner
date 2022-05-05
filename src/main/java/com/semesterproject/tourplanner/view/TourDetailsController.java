package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.viewmodels.TourDetailsViewModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;


public class TourDetailsController {
    //public TableView<Tour> details;
    //public TableColumn<Tour, String> startCol, destinationCol, transtypeCol;
    //public TableColumn<Tour, Double> distanceCol, timeCol;
    @FXML
    public TextField nameTextField;
    @FXML
    public ImageView mapImg;
    @FXML
    public TextField descriptionTextField;
    @FXML
    public TextField informationTextField;
    @FXML
    private final TourDetailsViewModel tourDetailsViewModel;
    public TextField distanceField;
    public TextField timeField;
    public TextField startTextField;
    public TextField destinationTextField;
    public TextField transptypeTextField;

    //public TableColumn<Parameter, Double> distanceCol;



    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    public TourDetailsViewModel getTourDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
        descriptionTextField.textProperty().bindBidirectional(tourDetailsViewModel.descriptionProperty());
        informationTextField.textProperty().bindBidirectional(tourDetailsViewModel.informationProperty());
        startTextField.textProperty().bindBidirectional(tourDetailsViewModel.startProperty());
        destinationTextField.textProperty().bindBidirectional(tourDetailsViewModel.destinationProperty());
        transptypeTextField.textProperty().bindBidirectional(tourDetailsViewModel.transtypeProperty());
        distanceField.textProperty().bind(Bindings.createStringBinding(
                () -> Double.toString(tourDetailsViewModel.distanceProperty().get()),
                tourDetailsViewModel.distanceProperty()));
        timeField.textProperty().bind(Bindings.createStringBinding(
                () -> Integer.toString(tourDetailsViewModel.timeProperty().get()),
                tourDetailsViewModel.timeProperty()));
        mapImg.imageProperty().bindBidirectional(tourDetailsViewModel.mapImage());
    }
}
