package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.models.Tour;

public interface TourService {
    Tour createTour(Tour tour) throws MapException;
    void removeTour(Tour tour);
}
