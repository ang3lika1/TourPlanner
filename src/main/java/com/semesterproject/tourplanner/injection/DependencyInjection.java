package com.semesterproject.tourplanner.injection;
import com.semesterproject.tourplanner.view.ControllerFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * FXMLLoader with Dependency-Injection
 * based on https://edencoding.com/dependency-injection/
 */
public class DependencyInjection {
    public static Parent load(String location, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    public static FXMLLoader getLoader(String location, Locale locale) {
        return new FXMLLoader(
                DependencyInjection.class.getResource("com/semesterproject/tourplanner/" + location),
                ResourceBundle.getBundle("com.semesterproject.tourplanner." + "gui_strings", locale),
                new JavaFXBuilderFactory(),
                controllerClass-> ControllerFactory.getInstance().create(controllerClass)
        );
    }
    /*public static FXMLLoader getLoader(String location, Locale locale) {
        FXMLLoader fxmlLoader = new FXMLLoader(DependencyInjection.class.getResource("MainWindow.fxml"));
        return fxmlLoader;
    }*/
}
