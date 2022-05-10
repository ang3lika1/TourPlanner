package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.DependencyInjection;
import com.semesterproject.tourplanner.bl.MapException;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;
import com.semesterproject.tourplanner.viewmodels.TourDetailsViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;


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

    @FXML
    public ListView<TourLog> tourLogListItem;

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

        tourLogListItem.setItems(tourDetailsViewModel.getObservableTourLogs());
        tourLogListItem.getSelectionModel().selectedItemProperty().addListener(tourDetailsViewModel.getChangeListener());
    }

    @FXML
    public void onButtonAddLog(ActionEvent actionEvent) throws MapException, IOException {
        Parent parent = DependencyInjection.load("CreateLog.fxml", Locale.ENGLISH);  // Locale.GERMANY, Locale.ENGLISH
        Stage stage = new Stage();
        Scene dialogScene = new Scene(parent, 600, 400);
        stage.setScene(dialogScene);
        stage.showAndWait();


        tourDetailsViewModel.addNewTourLog();
        Button deleteButton = new Button("delete");
        deleteButton.setOnAction(this::onButtonRemoveLog);

        tourLogListItem.getSelectionModel().selectLast();
    }

    public void onButtonRemoveLog(ActionEvent actionEvent) {
        tourDetailsViewModel.deleteTourLog(tourLogListItem.getSelectionModel().getSelectedItem());
    }
}
