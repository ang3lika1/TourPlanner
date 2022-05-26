package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;

public class MainWindowViewModel {
    private SearchBarViewModel searchBarViewModel;
    private ToursOverviewModel toursOverviewModel;
    private TourDetailsViewModel tourDetailsViewModel;

    public MainWindowViewModel(SearchBarViewModel searchBarViewModel, ToursOverviewModel toursOverviewModel, TourDetailsViewModel tourDetailsViewModel) {
        this.searchBarViewModel = searchBarViewModel;
        this.toursOverviewModel = toursOverviewModel;
        this.tourDetailsViewModel = tourDetailsViewModel;

       // this.searchBarViewModel.addSearchListener(searchString -> searchTours(searchString));

        //this.toursOverviewModel.(selectedTour));

        this.toursOverviewModel.addSelectionChangedListener(this::selectTour);
    }

    private void selectTour(Tour selectedTour){
        tourDetailsViewModel.setTourModel(selectedTour);
        tourDetailsViewModel.setTourMap(selectedTour);
    }


    /*private void searchTours(String searchString){
        var tours :List<Tour> = BL.getInstance().findMatchingTours(searchString);
        toursOverviewModel.setTours(tours);
    }*/

}
