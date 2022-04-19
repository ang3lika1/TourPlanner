package com.semesterproject.tourplanner.models;

public class Tour {
    private String name;
    private String description;
    private String start;
    private String destination;
    private String transport_type;
    private int distance;
    private int time;
    private String route_information;

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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
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
}
