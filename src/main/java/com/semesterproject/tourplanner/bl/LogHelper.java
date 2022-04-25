package com.semesterproject.tourplanner.bl;

import java.util.logging.Logger;
//import org.apache.log4j.*;

//macht noch nix
public class LogHelper {
    public static void error(String msg, String name){
        //getLog(name).error(msg);
    }

    public static void error(Exception e){
        e.printStackTrace();
        //getLog(e.getClass().getPackage().getName() + e.getClass().getName()).error(e.getMessage());
    }

    private static Logger getLog(String name){
        return Logger.getLogger(name); // Instanziert den Logger
    }

}
