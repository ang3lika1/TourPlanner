package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.bl.TourLogServiceImpl;
import com.semesterproject.tourplanner.bl.TourServiceImpl;
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
    private static TourServiceImpl tourServiceImpl;
    private Tour tour;
    private volatile boolean isInitValue = false;

    public Tour getTour() {
        return tour;
    }

    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final ObjectProperty<javafx.scene.image.Image> mapImg = new SimpleObjectProperty<>();
    private final DoubleProperty distance = new SimpleDoubleProperty();
    private final IntegerProperty time = new SimpleIntegerProperty();
    private final StringProperty start = new SimpleStringProperty();
    private final StringProperty destination = new SimpleStringProperty();
    private final StringProperty transtype = new SimpleStringProperty();
    private TourLogViewModel tourLogViewModel;
   // final ListProperty<TourLog> tourlogs = new SimpleListProperty<>(FXCollections.observableArrayList());

   // private List<TourDetailsViewModel.SelectionChangedListener> listeners = new ArrayList<>();
    //private ObservableList<TourLog> observableTourLogs = FXCollections.observableArrayList();

    public TourDetailsViewModel() {
        tourLogServiceImpl = new TourLogServiceImpl();
        tourServiceImpl = new TourServiceImpl();
        name.addListener( (arg, oldVal, newVal)->updateTourModel());
    }
    /*public interface SelectionChangedListener {
        void changeLogSelection(TourLog tourlog);
    }

    public ObservableList<TourLog> getObservableTourLogs() {
        return observableTourLogs;
    }
    /*public ChangeListener<TourLog> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }
    private void notifyListeners(TourLog newValue) {
        for ( var listener : listeners ) {
            listener.changeLogSelection(newValue);
        }
    }*/

    /*public List<TourDetailsViewModel.SelectionChangedListener> getListeners() {
        return listeners;
    }
    public void addSelectionChangedListener(TourDetailsViewModel.SelectionChangedListener listener) {
        listeners.add(listener);
    }*/

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
    public StringProperty descriptionProperty() {
        return description;
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
    /*public ObservableList<TourLog> ListProperty(){
        return tourlogs;
    }*/

    public void setTourLogViewModel(TourLogViewModel tourLogViewModel) {
        this.tourLogViewModel = tourLogViewModel;
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
        start.setValue( tourModel.getStart() );
        destination.setValue( tourModel.getDestination() );
        transtype.setValue( tourModel.getTransport_type() );
        distance.setValue(tourModel.getDistance());
        time.setValue(tourModel.getTime());
        //set observableTourLogs with data from database
        tourLogViewModel.setParentViewModel(this);
        tourLogViewModel.setTourModel(tour);
        tourLogViewModel.setTourLogs(tourLogServiceImpl.getAll(tour));
        //tourlogs is returned as Listproperty
        //tourlogs.setValue(observableTourLogs);
        isInitValue = false;
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

    public void updateTourModel() {
        if( !isInitValue ) {
            //SET name=?, description=?, transport_type=?, distance=?, time=?
            tour.setName(name.get());
            tour.setDescription(description.get());
            tour.setTransport_type(transtype.get());
            tour.setDistance(distance.get());
            tour.setTime(time.get());

            tourServiceImpl.updateTour(tour);
            //DAL.getInstance().tourDao().update(mediaItemModel, Arrays.asList(mediaItemModel.getId(), name.get(), distance.get(), plannedTime.get()));
        }
    }

    /*public void setTourLogs(ArrayList<TourLog> logItems) {
        observableTourLogs.clear();
        observableTourLogs.addAll(logItems);
        tour.setLog(logItems);
    }

    public void addNewTourLog() throws IOException {
        TourLog tourLog = NewTourLog.getInstance().getCreateTourLog();

        if(!NewTourLog.getInstance().isCancelled()) {
            tourLog.setTourId(tour.getId());
            TourLog tourLogDB = tourLogServiceImpl.createTourLog(tourLog);
            observableTourLogs.add(tourLogDB);
        }
    }

    public void editTourLog(TourLog tourLog) throws IOException {
        //TourLog tourLog = NewTourLog.getInstance().getCreateTourLog();

        if(!NewTourLog.getInstance().isCancelled()) {
            //tourLog.setTourId(tour.getId());
            observableTourLogs.remove(tourLog);
            TourLog tourLogDB = tourLogServiceImpl.updateTourLog(tourLog);
            observableTourLogs.add(tourLogDB);
        }
    }

    public void deleteTourLog(TourLog tourLog) {
        tourLogServiceImpl.removeTourLog(tourLog);
        observableTourLogs.remove(tourLog);
    }*/

}
