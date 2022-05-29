package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.viewmodels.NewTourViewModel;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewTourControllerTest {
    NewTourViewModel newTourViewModel;
    NewTourController newTourController;
    private TextField tourname=new TextField("new");
    private TextField start=new TextField("wien");
    private TextField destination=new TextField("bern");
    private Button createButton = new Button();

    public NewTourControllerTest(NewTourViewModel newTourViewModel, NewTourController newTourController) {
        this.newTourViewModel = newTourViewModel;
        this.newTourController = newTourController;
    }

    @Test
    void initialize() {
        newTourController.initialize();
    }

    @Test
    void submit() {
    }
}