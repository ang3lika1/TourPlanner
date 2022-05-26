package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.dl.TourDAO;
import com.semesterproject.tourplanner.dl.TourLogDAO;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TourServiceImpl implements TourService{
    private static TourDAO tourDAO;
    private static TourLogServiceImpl tourLogServiceImpl;
    private static final LoggerWrapper logger = LoggerFactory.getLogger(TourServiceImpl.class);

    public TourServiceImpl() {
        tourDAO = TourDAO.getInstance();
        tourLogServiceImpl = new TourLogServiceImpl();
    }

    public static ArrayList<Tour> getAllTours() {
        return tourDAO.getAll();
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
    public Tour updateTour(Tour tour){
        var tourDB = tourDAO.update(tour);
        //tourDB.setId(tourDAO.getID(tourDB));
        logger.info("Tour with ID: " + tourDB.getId() + " updated!");
        return tourDB;
    }

    @Override
    public void removeTour(Tour tour){
        tourLogServiceImpl.removeAllTourLogs(tour);
        int deletedTour = tour.getId();
        tourDAO.delete(tour);
        //FileHelper.delFile(getMapImgPath(tour.getName()));
        logger.info("Tour with ID: " + deletedTour + " deleted!");
    }

    public void removeTourImg(Tour tour){
        Path filePath = Paths.get(getMapImgPath(tour.getName()));
        FileHelper.delFile(filePath);
    }


    public static String getMapImgPath(String name){
        //logger.info("map created at: " + ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + name + ".jpg");
        return ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + name + ".jpg";
    }

}
