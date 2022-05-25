package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.dl.TourLogDAO;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;

import java.util.ArrayList;
import java.util.List;

public class TourLogServiceImpl implements TourLogService{
    private static TourLogDAO tourLogDAO;
    private static final LoggerWrapper logger = LoggerFactory.getLogger(TourServiceImpl.class);

    public TourLogServiceImpl() {
        tourLogDAO = TourLogDAO.getInstance();
    }

    @Override
    public TourLog createTourLog(TourLog tourLog) {

        var tourLogDB = tourLogDAO.create(tourLog);
        logger.info("new TourLog with ID: " + tourLogDB.getId() + " created!");
        return tourLogDB;
    }

    @Override
    public void removeTourLog(TourLog tourLog) {
        tourLogDAO.delete(tourLog);
    }

    @Override
    public void removeAllTourLogs(Tour tour) {
        tourLogDAO.deleteAll(tour);
    }

    @Override
    public ArrayList<TourLog> getAll(Tour tour) {
        return tourLogDAO.getAll(tour.getId());
    }


}
