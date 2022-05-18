package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.DependencyInjection;
import com.semesterproject.tourplanner.bl.MapException;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;
import com.semesterproject.tourplanner.viewmodels.TourDetailsViewModel;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;


public class TourDetailsController {
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
    private TableView<TourLog> tourLogListItems;
    @FXML
    private TableColumn<TourLog, LocalDate> date;
    @FXML
    private TableColumn<TourLog, Integer> totalTime;
    @FXML
    private TableColumn<TourLog, String> comment;
    @FXML
    private TableColumn<TourLog, String> difficulty;
    @FXML
    private TableColumn<TourLog, String> rating;
    private ObservableList<TourLog> tourlogs  = FXCollections.observableArrayList();;


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

        Bindings.bindContent(tourlogs, tourDetailsViewModel.ListProperty());
        date.setCellValueFactory(new PropertyValueFactory<TourLog, LocalDate>("date"));
        totalTime.setCellValueFactory(new PropertyValueFactory<TourLog, Integer>("totalTime"));
        comment.setCellValueFactory(new PropertyValueFactory<TourLog, String>("comment"));
        difficulty.setCellValueFactory(new PropertyValueFactory<TourLog, String>("difficulty"));
        rating.setCellValueFactory(new PropertyValueFactory<TourLog, String>("rating"));
        tourLogListItems.setItems(tourlogs);
        tourLogListItems.getSelectionModel().selectedItemProperty().addListener(tourDetailsViewModel.getChangeListener());
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

        tourLogListItems.getSelectionModel().selectLast();
    }

    public void onButtonRemoveLog(ActionEvent actionEvent) {
        tourDetailsViewModel.deleteTourLog(tourLogListItems.getSelectionModel().getSelectedItem());
    }
}
