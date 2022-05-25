package com.semesterproject.tourplanner.models;

import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
class TourTest {
    private static final LoggerWrapper logger = LoggerFactory.getLogger(TourTest.class);
    private Tour tour;
    private javafx.scene.image.Image img;
    private JFXPanel panel = new JFXPanel();

    @BeforeEach
    void setUp() {
        tour = new Tour("testName", "testDescription", "Wien", "Linz", "testTransType",230,60, "testInfo",0);
        img = new Image("C:\\Users\\User1\\Documents\\alles_Semester_4\\SWEN2\\TourPlanner\\TourPlannerMap\\testName.jpg");
    }

    @Test
    void setDistanceInvalid() {
        try{
            tour.setDistance(-100);
            fail("negative distance in setDistance() should trigger an exception");
        }catch (IllegalArgumentException e){
            logger.info("properly caught negative value in setDistance() : " + e);
        }catch (Exception e){
            fail("wrong exception thrown for setDistance() with negative value");
        }
    }

    @Test
    void setDistance() {
        tour.setDistance(350);
        int expResult = 350;
        assertEquals(expResult, tour.getDistance());
    }

    @Test
    void getImage(){
        Image expResult = img;
        assertEquals(expResult, tour.getImage());
    }

    @Test
    void getLog() {
    }

    @Test
    void setLog() {
    }

    @Test
    void testToString() {
        String expResult = "testName";
        assertEquals(expResult, tour.toString());
    }
}