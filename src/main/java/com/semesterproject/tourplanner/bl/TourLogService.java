package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.models.TourLog;

public interface TourLogService {
    TourLog createTourLog(TourLog tourLog);
    void removeTour(TourLog tourLog);
}
