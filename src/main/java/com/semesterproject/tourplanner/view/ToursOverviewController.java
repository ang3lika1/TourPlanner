package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.DependencyInjection;
import com.semesterproject.tourplanner.bl.MapException;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.viewmodels.ToursOverviewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class ToursOverviewController {
    @FXML
    public ListView<Tour> tourListItem;

    private final ToursOverviewModel toursOverviewModel;

    public ToursOverviewController(ToursOverviewModel toursOverviewModel) {
        this.toursOverviewModel = toursOverviewModel;
    }


        public ToursOverviewModel getToursOverviewViewModel() {
            return toursOverviewModel;
        }

        @FXML
        void initialize() {
            tourListItem.setItems(toursOverviewModel.getObservableTours());
            tourListItem.getSelectionModel().selectedItemProperty().addListener(toursOverviewModel.getChangeListener());
        }

        @FXML
        public void onButtonAdd(ActionEvent actionEvent) throws MapException, IOException {
            Parent parent = DependencyInjection.load("CreateTour.fxml", Locale.ENGLISH);  // Locale.GERMANY, Locale.ENGLISH
            Stage stage = new Stage();
            Scene dialogScene = new Scene(parent, 300, 200);
            stage.setScene(dialogScene);
            stage.showAndWait();


            toursOverviewModel.addNewTour();
            tourListItem.getSelectionModel().selectLast();
        }

        public void onButtonRemove(ActionEvent actionEvent) {
            toursOverviewModel.deleteTour(tourListItem.getSelectionModel().getSelectedItem());
        }

        /*@FXML
        private Label createLabel;
        @FXML
        private TextField tourname;
        @FXML
        private Button createButton;

        String name;
        public void submit(ActionEvent event){
            name = tourname.getText();
            System.out.println(name);
        }*/

    }

