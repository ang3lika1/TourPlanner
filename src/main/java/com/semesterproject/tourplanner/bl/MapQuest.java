package com.semesterproject.tourplanner.bl;

import com.semesterproject.tourplanner.models.Tour;


import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapQuest {
    private Tour tour;
    private int calculatedTime, calculatedDistance;
    private String sessionID;

    public MapQuest(Tour tour) throws MapException {
        this.tour = tour;
        try {
            getDirections(tour.getStart(), tour.getDestination());
            Image map = getMap(ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "size"));
            Image mapPdf = getMap(ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "sizePdf"));
            String file = ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + tour.getName() + ".jpg";
            String filePdf = ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "path") + tour.getName() + "_pdf.pdf";
            FileHelper.saveImage(map, "jpg", new File(file));
            FileHelper.saveImage(mapPdf, "pdf", new File(filePdf));
            if (ConfigHelper.getIniInt(ConfigHelper.getConfigIni(), "settings", "openmap") == 1) {
                FileHelper.openDefault(file);
            }
        } catch (IOException e) {
            throw new MapException(e.getMessage());
        }
    }

    private void getDirections(String start, String destination) throws MapException {
        try {
            String json = HTTPHelper.httpGetJsonString("https://www.mapquestapi.com/directions/v2/route?key="+ ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "key")+"&from="+start+"&to="+destination+"&outFormat=json&ambiguities=ignore&routeType=fastest&doReverseGeocode=false&enhancedNarrative=false&avoidTimedConditions=false");
            //System.out.println(json);
            this.calculatedDistance = JSONHelper.getIntFromJson(json, "distance");
            this.calculatedTime = timeInMinutes(JSONHelper.getStringFromJson(json, "formattedTime"));
            System.out.println("time: " + this.calculatedTime);
            System.out.println("distance: " + this.calculatedDistance);
            //set time and distance in db automatically?

            this.sessionID = JSONHelper.getStringFromJson(json, "sessionId");
        }catch (IOException e){
            throw new MapException(e.getMessage());
        }
    }

    private Image getMap(String size) throws MapException {
        try {
            return HTTPHelper.httpGetImage("https://www.mapquestapi.com/staticmap/v5/map?session="+this.sessionID+"&size="+size+"&key="+ConfigHelper.getIniString(ConfigHelper.getConfigIni(), "map", "key"));
        }catch (IOException e){
            throw new MapException(e.getMessage());
        }
    }


    private int timeInMinutes(String time){
        int minutes = 0;
        String[] result = time.split(":");
        minutes += Double.parseDouble(result[0]) * 60;
        minutes += Double.parseDouble(result[1]);
        minutes += Double.parseDouble(result[2]) / 60;
        return minutes;
    }


}


