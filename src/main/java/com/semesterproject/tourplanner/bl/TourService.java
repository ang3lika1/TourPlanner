package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.models.Tour;

import java.util.List;

public interface TourService {
    Tour createTour(Tour tour) throws MapException;
    Tour updateTour(Tour tour);
    void removeTour(Tour tour);
}
