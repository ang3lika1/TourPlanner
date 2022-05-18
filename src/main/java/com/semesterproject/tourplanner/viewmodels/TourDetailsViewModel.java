package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.bl.TourLogServiceImpl;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TourDetailsViewModel {
    private static TourLogServiceImpl tourLogServiceImpl;
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
    final ListProperty<TourLog> tourlogs = new SimpleListProperty<>(FXCollections.observableArrayList());

    private List<TourDetailsViewModel.SelectionChangedListener> listeners = new ArrayList<>();
    private ObservableList<TourLog> observableTourLogs = FXCollections.observableArrayList();

    public TourDetailsViewModel() {
        tourLogServiceImpl = new TourLogServiceImpl();
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }
    public interface SelectionChangedListener {
        void changeLogSelection(TourLog tourlog);
    }

    public ObservableList<TourLog> getObservableTourLogs() {
        return observableTourLogs;
    }
    public ChangeListener<TourLog> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }
    private void notifyListeners(TourLog newValue) {
        for ( var listener : listeners ) {
            listener.changeLogSelection(newValue);
        }
    }

    public List<TourDetailsViewModel.SelectionChangedListener> getListeners() {
        return listeners;
    }
    public void addSelectionChangedListener(TourDetailsViewModel.SelectionChangedListener listener) {
        listeners.add(listener);
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
    public ObservableList<TourLog> ListProperty(){
        return tourlogs;
    }

    public void setTourModel(Tour tourModel) {
        isInitValue = true;
        if( tourModel ==null ) {
            // select the first in the list
            name.set("");
            return;
        }
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
        //set observableTourLogs with data from database
        setTourLogs(tourLogServiceImpl.getAll(tour));
        //tourlogs is returned as Listproperty
        tourlogs.setValue(observableTourLogs);
    }

    public void setTourMap(Tour tourModel) {
        isInitValue = true;
        if( tourModel ==null ) {
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

    public void setTourLogs(List<TourLog> logItems) {
        observableTourLogs.clear();
        observableTourLogs.addAll(logItems);
    }

    public void addNewTourLog() throws IOException {
        TourLog tourLog = NewTourLog.getInstance().getCreateTourLog();

        if(!NewTourLog.getInstance().isCancelled()) {
            tourLog.setTourId(tour.getId());
            TourLog tourLogDB = tourLogServiceImpl.createTourLog(tourLog);
            observableTourLogs.add(tourLogDB);
        }
    }

    public void deleteTourLog(TourLog tourLog) {
        tourLogServiceImpl.removeTourLog(tourLog);
        observableTourLogs.remove(tourLog);
    }

}
