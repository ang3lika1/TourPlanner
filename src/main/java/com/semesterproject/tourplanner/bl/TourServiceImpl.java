package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.dl.TourDAO;
import com.semesterproject.tourplanner.models.Tour;

public class TourServiceImpl implements TourService{
    private static TourDAO tourDAO;

    public TourServiceImpl() {
        tourDAO = TourDAO.getInstance();
    }

    @Override
    public Tour createTour(Tour tour) throws MapException {
        MapQuest mapquest = new MapQuest(tour);
        tour.setMap(mapquest);
        tour.setDistance(mapquest.getCalculatedDistance());
        tour.setTime(mapquest.getCalculatedTime());

        var tourDB = tourDAO.create(tour);
        tourDB.setId(tourDAO.getID(tourDB));
        return tourDB;
    }

    @Override
    public void removeTour(Tour tour){
        tourDAO.delete(tour);
    }


    public static String getMapImgPath(String name){
        System.out.println(ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + name + ".jpg");
        return ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + name + ".jpg";
    }
}
