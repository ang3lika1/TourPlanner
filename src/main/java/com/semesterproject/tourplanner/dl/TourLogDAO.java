package com.semesterproject.tourplanner.dl;

import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.models.TourLog;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourLogDAO implements DAOLog{
    static Connection connection = Database.getInstance().getConnection();
    static PreparedStatement insert = null;
    static PreparedStatement select = null;
    static PreparedStatement delete = null;
    private static TourLogDAO instance = null;
    private static final LoggerWrapper logger = LoggerFactory.getLogger(TourLogDAO.class);

    static{
        TourLogDAO.instance = new TourLogDAO();
    }
    public static TourLogDAO getInstance() {
        return instance;
    }

    @Override
    public Optional get(int id) {
        return Optional.empty();
    }

    @Override
    public ArrayList<TourLog> getAll(int tourId) {
        ArrayList<TourLog> allTourLogs= new ArrayList<>();
        try {
            select = null;
            select = connection.prepareStatement("SELECT * FROM tourlog WHERE tour_id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            select.setInt(1, tourId);
            ResultSet result = select.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                int tour_id = result.getInt(2);
                LocalDate date = result.getDate(3).toLocalDate();
                String comment = result.getString(4);
                String difficulty = result.getString(5);
                int total_time = result.getInt(6);
                int rating = result.getInt(7);
                int distance = result.getInt(8);

                TourLog tourLog  = new TourLog(id, tour_id, date, comment, difficulty, total_time, rating, distance);
                allTourLogs.add(tourLog);
            }
            return allTourLogs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TourLog create(TourLog tourLog) {
        try{
            insert = connection.prepareStatement("""
                INSERT INTO tourlog(tour_id, date, comment, difficulty, total_time, rating, distance)
                VALUES(?,?,?,?,?,?,?) RETURNING id
                """);
            insert.setInt(1, tourLog.getTourId());
            insert.setDate(2,Date.valueOf(tourLog.getDate()));
            insert.setString(3,tourLog.getComment());
            insert.setString(4,tourLog.getDifficulty());
            insert.setInt(5,tourLog.getTotalTime());
            insert.setInt(6,tourLog.getRating());
            insert.setInt(7,tourLog.getDistance());
            ResultSet result = insert.executeQuery();

            if (result.next()) {
                int id = result.getInt(1);
                tourLog.setId(id);
                logger.info("id returned: "+id);
            }
            return tourLog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(TourLog tourLog) {
        try{
            delete = null;
            delete = connection.prepareStatement("""
                DELETE FROM tourlog WHERE id = ?
                """);
            delete.setInt(1, tourLog.getId());
            delete.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll(Tour tour) {
        try{
            delete = null;
            delete = connection.prepareStatement("""
                DELETE FROM tourlog WHERE tour_id = ?
                """);
            delete.setInt(1, tour.getId());
            delete.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Object o, List params) {

    }
}
