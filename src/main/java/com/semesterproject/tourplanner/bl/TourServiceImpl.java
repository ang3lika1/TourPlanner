package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.dl.TourDAO;
import com.semesterproject.tourplanner.models.Tour;

public class TourServiceImpl implements TourService{
    private static TourDAO tourDAO;

    public TourServiceImpl() {
        this.tourDAO = tourDAO.getInstance();
    }

    @Override
    public Tour createTour(String name, String description, String start, String destination, String transport_type, int distance, int time, String route_information) {
        Tour tour = new Tour(name, description, start, destination, transport_type, distance, time, route_information);
        var tourDB = tourDAO.create(tour);

        return tourDB;
    }
}
