package com.semesterproject.tourplanner;

import com.semesterproject.tourplanner.injection.DependencyInjection;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class Application extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = DependencyInjection.load("com/semesterproject/tourplanner/MainWindow.fxml", Locale.GERMAN );  // Locale.GERMANY, Locale.ENGLISH

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Tour Planner");
        primaryStage.show();
    }
}
