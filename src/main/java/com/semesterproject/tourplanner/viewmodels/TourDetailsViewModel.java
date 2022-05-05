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
    private final StringProperty description = new SimpleStringProperty();
    private final StringProperty information = new SimpleStringProperty();
    private final ObjectProperty<javafx.scene.image.Image> mapImg = new SimpleObjectProperty<>();
    private final DoubleProperty distance = new SimpleDoubleProperty();
    private final IntegerProperty time = new SimpleIntegerProperty();
    private final StringProperty plannedTime = new SimpleStringProperty();
    private final StringProperty start = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();
    private final StringProperty transtype = new SimpleStringProperty();

    public TourDetailsViewModel() {
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty informationProperty() {
        return information;
    }
    public StringProperty startProperty() {
        return start;
    }
    public StringProperty destinationProperty() {
        return destination;
    }
    public StringProperty transtypeProperty() {
        return transtype;
    }

    public ObjectProperty<Image> mapImage(){
        return mapImg;
    }


    public DoubleProperty distanceProperty() {
        return distance;
    }
    public IntegerProperty timeProperty() {
        return time;
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
        description.setValue( tourModel.getDescription() );
        information.setValue( tourModel.getRoute_information() );
        start.setValue( tourModel.getStart() );
        destination.setValue( tourModel.getDestination() );
        transtype.setValue( tourModel.getTransport_type() );
        distance.setValue(tourModel.getDistance());
        time.setValue(tourModel.getTime());
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
