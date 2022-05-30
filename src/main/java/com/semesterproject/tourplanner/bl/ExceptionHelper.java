package com.semesterproject.tourplanner.bl;

public class ExceptionHelper extends Exception{

        public ExceptionHelper() {
            super();
        }

    public ExceptionHelper handleException(Throwable cause) {
        if (cause.getMessage().contains("Map"))
            return new ExceptionHelper("invalid start or destination");
        // Other expected exceptions go here

        return new ExceptionHelper(cause);
    }
        public ExceptionHelper(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

        public ExceptionHelper(String message, Throwable cause) {
            super(message, cause);
        }

        public ExceptionHelper(String message) {
            super("Tour with this name already exists.");
        }

        public ExceptionHelper(Throwable cause) {
            super(cause);
        }
    }
