package com.semesterproject.tourplanner.bl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.semesterproject.tourplanner.models.Tour;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class JSONHelper {

    private static JsonObject getJsonObj(String json){
        return new Gson().fromJson(json, JsonObject.class);
    }

    public static String getStringFromJson(String json, String gets){
        return getJsonObj(json).get("route").getAsJsonObject().get(gets).getAsString();
    }

    public static int getIntFromJson(String json, String gets){
        return getJsonObj(json).get("route").getAsJsonObject().get(gets).getAsInt();
    }

    public static double getDoubleFromJson(String json, String gets){
        return getJsonObj(json).get("route").getAsJsonObject().get(gets).getAsDouble();
    }


    public static void getJsonFromObj(Object obj , FileWriter writer){
        new Gson().toJson(obj, writer);
    }

    public static ArrayList<Tour> getToursFromJson(FileReader reader){
        return new Gson().fromJson(reader, new TypeToken<ArrayList<Tour>>() {}.getType());
    }

}
