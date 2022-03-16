package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.models.Tour;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;

public class TourDetailsViewModel {
    private Tour tour;
    private volatile boolean isInitValue = false;

    private final StringProperty name = new SimpleStringProperty();
    private final DoubleProperty distance = new SimpleDoubleProperty();
    private final StringProperty plannedTime = new SimpleStringProperty();

    public TourDetailsViewModel() {
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public double getDistance() {
        return distance.get();
    }

    public DoubleProperty distanceProperty() {
        return distance;
    }

    public String getPlannedTime() {
        return plannedTime.get();
    }

    public StringProperty plannedTimeProperty() {
        return plannedTime;
    }

    public void setTourModel(Tour tourModel) {
        isInitValue = true;
        if( tourModel ==null ) {
            // select the first in the list
            name.set("");
            return;
        }
        System.out.println("setTourModel name=" + tourModel.getTitle());
        this.tour = tourModel;
        name.setValue( tourModel.getTitle() );
        isInitValue = false;
    }

    private void updateTourModel() {
        //if( !isInitValue )
            //DAL.getInstance().tourDao().update(mediaItemModel, Arrays.asList(mediaItemModel.getId(), name.get(), distance.get(), plannedTime.get()));
    }
}
