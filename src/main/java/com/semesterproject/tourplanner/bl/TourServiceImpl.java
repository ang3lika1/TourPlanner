package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.dl.TourDAO;
import com.semesterproject.tourplanner.models.Tour;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

public class TourServiceImpl implements TourService{
    private static TourDAO tourDAO;
    private static TourLogServiceImpl tourLogServiceImpl;
    private static final LoggerWrapper logger = LoggerFactory.getLogger(TourServiceImpl.class);
    private ExceptionHelper exceptionHelper;

    public TourServiceImpl() {
        tourDAO = TourDAO.getInstance();
        tourLogServiceImpl = new TourLogServiceImpl();
    }

    @Override
    public ArrayList<Tour> getAllTours() {
        return tourDAO.getAll();
    }

    @Override
    public Tour createTour(Tour tour) throws Exception {
        if(tourDAO.checkUnique(tour.getName())) {
            try {
                MapQuest mapquest = new MapQuest(tour);
                tour.setMap(mapquest);
                tour.setDistance(mapquest.getCalculatedDistance());
                tour.setTime(mapquest.getCalculatedTime());
            }catch (MapException e){
                logger.fatal(e.getMessage());
                throw e;
            }

            Tour tourDB;
            tourDB = tourDAO.create(tour);

            logger.info("new Tour with ID: " + tourDB.getId() + " created!");
            return tourDB;
        }else{
            throw new Exception("Tour with this name already exists.");
        }
    }

    @Override
    public Tour createImportedTour(Tour tour) throws Exception {
        if(tourDAO.checkUnique(tour.getName())) {
            try {
                MapQuest mapquest = new MapQuest(tour);
                tour.setMap(mapquest);
            }catch (MapException e){
                logger.fatal(e.getMessage());
                throw e;
            }

            Tour tourDB;
            tourDB = tourDAO.create(tour);

            logger.info("new Tour with ID: " + tourDB.getId() + " created!");
            return tourDB;
        }else{
            throw new Exception("Tour with this name already exists.");
        }
    }

    public Boolean isUnique(String tourname){
        try {
            return tourDAO.checkUnique(tourname);
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return null;
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
