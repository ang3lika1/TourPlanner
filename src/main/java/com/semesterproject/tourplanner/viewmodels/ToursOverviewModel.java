package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.bl.TourServiceImpl;
import com.semesterproject.tourplanner.dl.TourDAO;
import com.semesterproject.tourplanner.models.Tour;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ToursOverviewModel {
    private static TourServiceImpl tourServiceImpl;

    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<Tour> observableMediaItems = FXCollections.observableArrayList();

    public ToursOverviewModel()
    {
        tourServiceImpl = new TourServiceImpl();
        setTours(TourDAO.getInstance().getAll());
    }

    public ObservableList<Tour> getObservableTours() {
        return observableMediaItems;
    }


    public ChangeListener<Tour> getChangeListener() {
        return (observableValue, oldValue, newValue) -> notifyListeners(newValue);
    }

    public void addSelectionChangedListener(SelectionChangedListener listener) {
        listeners.add(listener);
    }

    public void removeSelectionChangedListener(SelectionChangedListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners(Tour newValue) {
        for ( var listener : listeners ) {
            listener.changeSelection(newValue);
        }
    }

    public void setTours(List<Tour> tourItems) {
        observableMediaItems.clear();
        observableMediaItems.addAll(tourItems);
    }

    public void addNewTour() {
        //var tour = DAL.getInstance().tourDao().create();
        //var tour = new Tour("name", "url");
        //Tour tour = new Tour("Heimweg", "on my way", "FH", "Engerthstraße", "Bus", 8, 25, "route_information");
        //var tourDB = TestDAO.create(tour);
        Tour tourDB;
        tourDB = tourServiceImpl.createTour("Heimweg", "on my way", "FH", "Engerthstraße", "Bus", 8, 25, "route_information");
        observableMediaItems.add(tourDB);
    }

    public void deleteTour(Tour tour) {
        //DAL.getInstance().tourDao().delete(mediaItem);
        observableMediaItems.remove(tour);
    }
}
