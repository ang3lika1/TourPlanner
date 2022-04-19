package com.semesterproject.tourplanner.dl;


import com.semesterproject.tourplanner.models.Tour;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {

    Optional<T> get(int id);

    List<T> getAll();

    T create(Tour tour);

    void update(T t, List<?> params);

    void delete(T t);
}
