package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.dl.TourDAO;
import com.semesterproject.tourplanner.dl.TourLogDAO;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;

import java.util.ArrayList;

public class TourServiceImpl implements TourService{
    private static TourDAO tourDAO;
    private static final LoggerWrapper logger = LoggerFactory.getLogger(TourServiceImpl.class);

    public TourServiceImpl() {
        tourDAO = TourDAO.getInstance();
    }

    public static ArrayList<Tour> getAllTours() {
        return (ArrayList<Tour>) tourDAO.getAll();
    }

    @Override
    public Tour createTour(Tour tour) throws MapException {
        MapQuest mapquest = new MapQuest(tour);
        tour.setMap(mapquest);
        tour.setDistance(mapquest.getCalculatedDistance());
        tour.setTime(mapquest.getCalculatedTime());

        var tourDB = tourDAO.create(tour);
        //tourDB.setId(tourDAO.getID(tourDB));
        logger.info("new Tour with ID: " + tourDB.getId() + " created!");
        return tourDB;
    }

    @Override
    public void removeTour(Tour tour){
        int deletedTour = tour.getId();
        tourDAO.delete(tour);
        logger.info("Tour with ID: " + deletedTour + " deleted!");
    }


    public static String getMapImgPath(String name){
        //logger.info("map created at: " + ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + name + ".jpg");
        return ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + name + ".jpg";
    }

}
