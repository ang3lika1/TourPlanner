package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.GUI.models.Tour;
import com.semesterproject.tourplanner.viewmodels.ToursOverviewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

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

        public void onButtonAdd(ActionEvent actionEvent) {
            toursOverviewModel.addNewTour();
            tourListItem.getSelectionModel().selectLast();
        }

        public void onButtonRemove(ActionEvent actionEvent) {
            toursOverviewModel.deleteTour(tourListItem.getSelectionModel().getSelectedItem());
        }
    }

