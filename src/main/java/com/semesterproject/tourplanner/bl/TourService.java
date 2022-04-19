package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.models.Tour;

public interface TourService {
    Tour createTour(String name, String description, String start, String destination, String transport_type, int distance, int time, String route_information);
}
