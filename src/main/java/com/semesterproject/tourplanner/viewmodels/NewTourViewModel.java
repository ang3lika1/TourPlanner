package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.bl.TourServiceImpl;
import com.semesterproject.tourplanner.models.Tour;

public class NewTourViewModel {
    private static TourServiceImpl tourServiceImpl;
    private static Tour newTour;


    public static TourServiceImpl getTourServiceImpl() {
        return tourServiceImpl;
    }

    public static void setTourServiceImpl(TourServiceImpl tourServiceImpl) {
        NewTourViewModel.tourServiceImpl = tourServiceImpl;
    }

    public static Tour getNewTour() {
        return newTour;
    }

    public static void setNewTour(Tour newTour) {
        NewTourViewModel.newTour = newTour;
    }
}
