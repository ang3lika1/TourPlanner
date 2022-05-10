package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.dl.TourLogDAO;
import com.semesterproject.tourplanner.models.TourLog;

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
    public void removeTour(TourLog tourLog) {

    }
}
