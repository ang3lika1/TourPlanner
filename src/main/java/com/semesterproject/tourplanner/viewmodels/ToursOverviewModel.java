package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.GUI.models.Tour;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ToursOverviewModel {
    public interface SelectionChangedListener {
        void changeSelection(Tour tour);
    }

    private List<SelectionChangedListener> listeners = new ArrayList<>();

    private ObservableList<Tour> observableMediaItems = FXCollections.observableArrayList();

    public ToursOverviewModel()
    {
        //setTours( DAL.getInstance().tourDao().getAll() );
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

    public void setTours(List<Tour> mediaItems) {
        observableMediaItems.clear();
        observableMediaItems.addAll(mediaItems);
    }

    public void addNewTour() {
        //var tour = DAL.getInstance().tourDao().create();
        var tour = new Tour("name", "url");
        observableMediaItems.add(tour);
    }

    public void deleteTour(Tour tour) {
        //DAL.getInstance().tourDao().delete(mediaItem);
        observableMediaItems.remove(tour);
    }
}
