package com.semesterproject.tourplanner.dl;

import com.semesterproject.tourplanner.models.Tour;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TourDAO implements DAO<Tour>{
    static Connection connection = Database.getInstance().getConnection();
    static PreparedStatement insert = null;
    static PreparedStatement select = null;
    private static TourDAO instance = null;

    static{
        TourDAO.instance = new TourDAO();
    }

    public static TourDAO getInstance() {
        return instance;
    }

    @Override
    public Optional<Tour> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Tour> getAll() {
        List<Tour> allTours= new ArrayList<>();
        try {
            select = connection.prepareStatement("SELECT * FROM tour");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet result = select.executeQuery();
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String description = result.getString(3);
                String start = result.getString(4);
                String destination = result.getString(5);
                String transportType = result.getString(6);
                int distance = result.getInt(7);
                int time = result.getInt(8);
                String routeInfo = result.getString(9);

                Tour tour = new Tour(name, description, start, destination, transportType, distance, time, routeInfo);
                allTours.add(tour);
            }
            return allTours;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Tour create(Tour tour) {
        try{
            insert = connection.prepareStatement("""
                INSERT INTO tour(name, description, start, destination, transport_type, distance, time, route_information)
                VALUES(?,?,?,?,?,?,?,?)
                """);
            insert.setString(1, tour.getName());
            insert.setString(2, tour.getDescription());
            insert.setString(3, tour.getStart());
            insert.setString(4, tour.getDestination());
            insert.setString(5, tour.getTransport_type());
            insert.setInt(6, tour.getDistance());
            insert.setInt(7, tour.getTime());
            insert.setString(8, tour.getRoute_information());
            insert.execute();

            return tour;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Tour tour, List<?> params) {

    }

    @Override
    public void delete(Tour tour) {

    }
}
