package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.bl.MapException;
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

    private ObservableList<Tour> observableTours = FXCollections.observableArrayList();

    public ToursOverviewModel()
    {
        tourServiceImpl = new TourServiceImpl();
        setTours(TourDAO.getInstance().getAll());
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

    public void setTours(List<Tour> tourItems) {
        observableTours.clear();
        observableTours.addAll(tourItems);
    }

    public void addNewTour() throws MapException {
        Tour tourDB;
        tourDB = tourServiceImpl.createTour("Daham", "weg", "Heinreichs", "Horn", "Auto", 38, 34, "route_information");
        observableTours.add(tourDB);
        System.out.println(tourDB);
    }

    public void deleteTour(Tour tour) {
        //DAL.getInstance().tourDao().delete(mediaItem);
        observableTours.remove(tour);
    }
}
