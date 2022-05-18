package com.semesterproject.tourplanner.models;

import com.semesterproject.tourplanner.bl.MapQuest;
import com.semesterproject.tourplanner.bl.TourServiceImpl;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class Tour {
    private String name;
    private String description;
    private String start;
    private String destination;
    private String transport_type;
    private double distance;
    private int time;
    private String route_information;
    private MapQuest map;
    private int id;

    private ArrayList<TourLog> log;

    public Tour(String name, String description, String start, String destination, String transport_type, String route_information) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.destination = destination;
        this.transport_type = transport_type;
        this.route_information = route_information;
        this.log = new ArrayList<>();
    }

    public Tour(String name, String description, String start, String destination, String transport_type, int distance, int time, String route_information) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.destination = destination;
        this.transport_type = transport_type;
        this.distance = distance;
        this.time = time;
        this.route_information = route_information;
    }

    public Tour(String name, String description, String start, String destination, String transport_type, double distance, int time, String route_information, int id) {
        this.name = name;
        this.description = description;
        this.start = start;
        this.destination = destination;
        this.transport_type = transport_type;
        this.distance = distance;
        this.time = time;
        this.route_information = route_information;
        this.id = id;
    }

    public Image getImage() {
        String filename = TourServiceImpl.getMapImgPath(name);
        Image image = null;
        try {
            InputStream stream = new FileInputStream(filename);
            image = new Image(stream);
        } catch (IOException e) {

        }
        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MapQuest getMap() {
        return map;
    }

    public void setMap(MapQuest map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTransport_type() {
        return transport_type;
    }

    public void setTransport_type(String transport_type) {
        this.transport_type = transport_type;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getRoute_information() {
        return route_information;
    }

    public void setRoute_information(String route_information) {
        this.route_information = route_information;
    }

    public TourLog getLog(int id){
        AtomicReference<TourLog> returnLog = new AtomicReference<>();
        this.log.forEach(s -> {
            if(s.getId() == id){
                returnLog.set(s);
            }
        });
        return returnLog.get();
    }

    public ArrayList<TourLog> getLog() {
        return log;
    }

    public void setLog(ArrayList<TourLog> log) {
        this.log = log;
    }

    @Override
    public String toString() {
        return name;
    }
}
