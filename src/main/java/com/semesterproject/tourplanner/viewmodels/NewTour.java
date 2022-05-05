package com.semesterproject.tourplanner.viewmodels;

import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.view.ControllerFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewTour {
    private Tour createTour;

    private static NewTour instance = new NewTour();

    public static NewTour getInstance() {
        return instance;
    }

    private boolean isCancelled;

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Tour getCreateTour() {
        return createTour;
    }

    public void setCreateTour(Tour createTour) {
        this.createTour = createTour;
    }
    /*static String tourname;
    public static String display(String title, String message){
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle(title);
        //VBox dialogVbox = new VBox(20);
        //dialogVbox.getChildren().add(new Text("Enter new Tour:"));
        Label label = new Label();
        label.setText(message);
        TextField tournameTF = new TextField("name");
        Button createButton = new Button("create");

        createButton.setOnAction(e-> {
            tourname = tournameTF.getText();
            dialog.close();
        });


        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().addAll(label, tournameTF, createButton);

        Scene dialogScene = new Scene(dialogVbox, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();

        return tourname;
    }*/
}
