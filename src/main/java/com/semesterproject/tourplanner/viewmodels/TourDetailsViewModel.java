package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.models.Tour;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TourDetailsViewModel {
    private Tour tour;
    private volatile boolean isInitValue = false;

    private final StringProperty name = new SimpleStringProperty();
    private final ObjectProperty<javafx.scene.image.Image> mapImg = new SimpleObjectProperty<>();
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

    public ObjectProperty<Image> mapImage(){
        return mapImg;
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
        System.out.println("setTourModel name=" + tourModel.getName());
        this.tour = tourModel;
        name.setValue( tourModel.getName() );
        isInitValue = false;
    }

    public void setTourMap(Tour tourModel) {
        isInitValue = true;
        if( tourModel ==null ) {
            // select the first in the list
           //mapImg.setValue("");
            return;
        }

        this.tour = tourModel;
        mapImg.setValue(tourModel.getImage());
        isInitValue = false;
    }

    private void updateTourModel() {
        //if( !isInitValue )
            //DAL.getInstance().tourDao().update(mediaItemModel, Arrays.asList(mediaItemModel.getId(), name.get(), distance.get(), plannedTime.get()));
    }


}
