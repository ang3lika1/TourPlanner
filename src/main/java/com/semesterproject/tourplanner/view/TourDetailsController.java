package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.viewmodels.TourDetailsViewModel;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;


public class TourDetailsController {
    @FXML
    public TextField nameTextField;
    @FXML
    public ImageView mapImg;
    @FXML
    public TextField descriptionTextField;
    @FXML
    private final TourDetailsViewModel tourDetailsViewModel;
    public TextField distanceField;
    public TextField timeField;
    public TextField startTextField;
    public TextField destinationTextField;
    public TextField transptypeTextField;
    public AnchorPane anchorPaneDetails;
    public Button editButton;

   /* @FXML
    private TableView<TourLog> tourLogListItems;
    @FXML
    private TableColumn<TourLog, LocalDate> date;
    @FXML
    private TableColumn<TourLog, Integer> totalTime;
    @FXML
    private TableColumn<TourLog, Integer> distance;
    @FXML
    private TableColumn<TourLog, String> comment;
    @FXML
    private TableColumn<TourLog, String> difficulty;
    @FXML
    private TableColumn<TourLog, String> rating;
    private ObservableList<TourLog> tourlogs  = FXCollections.observableArrayList();*/

    private Button saveButton;


    public TourDetailsController(TourDetailsViewModel tourDetailsViewModel) {
        this.tourDetailsViewModel = tourDetailsViewModel;
    }

    public TourDetailsViewModel getTourDetailsViewModel() {
        return tourDetailsViewModel;
    }

    @FXML
    void initialize() {
        nameTextField.textProperty().bindBidirectional(tourDetailsViewModel.nameProperty());
        //nameTextField.setEditable(false);
        descriptionTextField.textProperty().bindBidirectional(tourDetailsViewModel.descriptionProperty());
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

        //Bindings.bindContent(tourlogs, tourDetailsViewModel.ListProperty());
        /*date.setCellValueFactory(new PropertyValueFactory<TourLog, LocalDate>("date"));
        totalTime.setCellValueFactory(new PropertyValueFactory<TourLog, Integer>("totalTime"));
        distance.setCellValueFactory(new PropertyValueFactory<TourLog, Integer>("distance"));
        comment.setCellValueFactory(new PropertyValueFactory<TourLog, String>("comment"));
        difficulty.setCellValueFactory(new PropertyValueFactory<TourLog, String>("difficulty"));
        rating.setCellValueFactory(new PropertyValueFactory<TourLog, String>("rating"));
        tourLogListItems.setItems(tourlogs);
        tourLogListItems.getSelectionModel().selectedItemProperty().addListener(tourDetailsViewModel.getChangeListener());*/
    }

   /* @FXML
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

    @FXML
    public void onButtonRemoveLog(ActionEvent actionEvent) {
        tourDetailsViewModel.deleteTourLog(tourLogListItems.getSelectionModel().getSelectedItem());
    }

    @FXML
    public void onButtonEditLog(ActionEvent actionEvent) throws IOException {
        Parent parent = DependencyInjection.load("EditLog.fxml", Locale.ENGLISH);  // Locale.GERMANY, Locale.ENGLISH
        Stage stage = new Stage();
        Scene dialogScene = new Scene(parent, 600, 400);
        stage.setScene(dialogScene);
        stage.showAndWait();


        tourDetailsViewModel.editTourLog(tourLogListItems.getSelectionModel().getSelectedItem());
        //tourDetailsViewModel.addNewTourLog();
        Button deleteButton = new Button("delete");
        deleteButton.setOnAction(this::onButtonRemoveLog);

        tourLogListItems.getSelectionModel().selectLast();
    }*/

    public void editTour(ActionEvent actionEvent) throws IOException {
        nameTextField.setEditable(true);
        transptypeTextField.setEditable(true);
        descriptionTextField.setEditable(true);
        distanceField.setEditable(true);
        timeField.setEditable(true);

        saveButton = new Button("save");
        saveButton.setLayoutX(500);
        saveButton.setLayoutY(400);
        saveButton.setOnAction(e->updateTour());
        anchorPaneDetails.getChildren().add(saveButton);
    }

    private void updateTour() {
        tourDetailsViewModel.updateTourModel();

        nameTextField.setEditable(false);
        transptypeTextField.setEditable(false);
        descriptionTextField.setEditable(false);
        distanceField.setEditable(false);
        timeField.setEditable(false);
        anchorPaneDetails.getChildren().remove(saveButton);
    }
}
