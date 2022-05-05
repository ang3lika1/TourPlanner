package com.semesterproject.tourplanner.bl.Logging;


public class LoggerFactory {
    public static LoggerWrapper getLogger(){
        var logger = new LoggerWrapperImpl();
        logger.initialize();
        return logger;
    }
}
