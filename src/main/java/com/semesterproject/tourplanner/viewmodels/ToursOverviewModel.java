package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.bl.MapException;
import com.semesterproject.tourplanner.bl.TourLogServiceImpl;
import com.semesterproject.tourplanner.bl.TourServiceImpl;
import com.semesterproject.tourplanner.models.Tour;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ToursOverviewModel {
    private static TourServiceImpl tourServiceImpl;
    private static TourLogServiceImpl tourLogServiceImpl;

    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<Tour> observableTours = FXCollections.observableArrayList();
    private ArrayList<Tour> allTours;

    public ToursOverviewModel()
    {
        tourServiceImpl = new TourServiceImpl();
        tourLogServiceImpl = new TourLogServiceImpl();
        //setTours(TourDAO.getInstance().getAll());
        setTours(tourServiceImpl.getAllTours());
    }

    public ObservableList<Tour> getObservableTours() {
        return observableTours;
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

    public void setTours(ArrayList<Tour> tourItems) {
        observableTours.clear();
        for (Tour tour:tourItems) {
            tour.setLog(tourLogServiceImpl.getAll(tour));
        }
        observableTours.addAll(tourItems);
        allTours=tourItems;
    }



    public void addNewTour() throws MapException, IOException {
        Tour t = NewTour.getInstance().getCreateTour();

        if(!NewTour.getInstance().isCancelled()) {
            Tour tourDB = tourServiceImpl.createTour(t);
            observableTours.add(tourDB);
        }
    }

    public void deleteTour(Tour tour) {
        tourServiceImpl.removeTour(tour);
        observableTours.remove(tour);
        tourServiceImpl.removeTourImg(tour);
    }

    public ArrayList<Tour> getAllTours() {
        return allTours;
    }
}
