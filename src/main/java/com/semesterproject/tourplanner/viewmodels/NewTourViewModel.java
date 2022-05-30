package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.bl.ExceptionHelper;
import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.bl.MapException;
import com.semesterproject.tourplanner.bl.MapQuest;
import com.semesterproject.tourplanner.bl.TourServiceImpl;
import com.semesterproject.tourplanner.models.Tour;

public class NewTourViewModel {
    private static TourServiceImpl tourServiceImpl;
    private static Tour newTour;
    private ExceptionHelper exceptionHelper;
    private static final LoggerWrapper logger = LoggerFactory.getLogger(NewTourViewModel.class);


    public NewTourViewModel() {
        this.exceptionHelper = new ExceptionHelper();
        tourServiceImpl = new TourServiceImpl();
    }


    public void addTour(Tour tour) throws Exception {
        //try {
            Tour tourDB = tourServiceImpl.createTour(tour);
            NewTour.getInstance().setCreateTour(tourDB);
       /* }catch (SQLException e){
            this.exceptionHelper = new ExceptionHelper(e);
            this.exceptionHelper = exceptionHelper.handleException(e);
            System.out.println(exceptionHelper.getMessage());
            throw exceptionHelper;
        }*/
    }

    public Boolean isUnique(String tourname) {
        return tourServiceImpl.isUnique(tourname);
    }

    public Boolean validMap(String start){
        try {
            return MapQuest.testLocation(start);
        } catch (MapException e) {
            logger.warn(e.getMessage());
            return false;
        }
    }

    public static Tour getNewTour() {
        return newTour;
    }

    public static void setNewTour(Tour newTour) {
        NewTourViewModel.newTour = newTour;
    }
}
