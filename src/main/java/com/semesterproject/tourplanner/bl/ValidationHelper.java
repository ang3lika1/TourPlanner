package com.semesterproject.tourplanner.bl;

import java.sql.SQLIntegrityConstraintViolationException;

public class ValidationHelper {
    private Exception exception;
    private String message;
    public ValidationHelper() {
    }
    /*public void handleException(Exception e){
        if (e.equals(SQLIntegrityConstraintViolationException)) {
            this.message = "Tour with this name already exists.";
        } else {
            throw new IllegalStateException("Unexpected value: " + e);
        }
    }*/

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
