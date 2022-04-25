package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.dl.TourDAO;
import com.semesterproject.tourplanner.models.Tour;

public class TourServiceImpl implements TourService{
    private static TourDAO tourDAO;

    public TourServiceImpl() {
        tourDAO = TourDAO.getInstance();
    }

    @Override
    public Tour createTour(String name, String description, String start, String destination, String transport_type, int distance, int time, String route_information) throws MapException {
        Tour tour = new Tour(name, description, start, destination, transport_type, distance, time, route_information);
        tour.setMap(new MapQuest(tour));
        var tourDB = tourDAO.create(tour);

        return tourDB;
    }


    public static String getMapImgPath(String name){
        System.out.println(ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + name + ".jpg");
        return ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + name + ".jpg";
    }
}
