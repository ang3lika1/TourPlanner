package com.semesterproject.tourplanner.dl;

import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;

import java.util.List;
import java.util.Optional;

public interface DAOLog<T> {
    Optional<T> get(int id);

    List<T> getAll(int id);

    T create(TourLog tourLog);

    T update(TourLog tourLog);

    void delete(TourLog tourLog);

    void deleteAll(Tour tour);
}
