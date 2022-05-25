package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.DependencyInjection;
import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.bl.MapException;
import com.semesterproject.tourplanner.bl.ReportHelper;
import com.semesterproject.tourplanner.dl.TourDAO;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.viewmodels.ToursOverviewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ToursOverviewController {
    private static final LoggerWrapper logger = LoggerFactory.getLogger(ToursOverviewController.class);
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
            Scene dialogScene = new Scene(parent, 600, 400);
            stage.setScene(dialogScene);
            stage.showAndWait();


            toursOverviewModel.addNewTour();
            Button deleteButton = new Button("delete");
            deleteButton.setOnAction(this::onButtonRemove);

            tourListItem.getSelectionModel().selectLast();
        }

        public void onButtonRemove(ActionEvent actionEvent) {
            toursOverviewModel.deleteTour(tourListItem.getSelectionModel().getSelectedItem());
        }

        public void onButtonCreateTourReport(ActionEvent actionEvent){
            ReportHelper reportHelper = null;
            try {
                reportHelper = new ReportHelper(tourListItem.getSelectionModel().getSelectedItem());
                reportHelper.generatePdf();

                logger.info("report created");
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }

    public void onButtonCreateSummary(ActionEvent actionEvent){
        ReportHelper reportHelper = null;
        try {
            reportHelper = new ReportHelper();
            reportHelper.generateSummary(toursOverviewModel.getAllTours());

            logger.info("summary created");
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    }

