package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.viewmodels.NewTour;
import com.semesterproject.tourplanner.viewmodels.NewTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationMessage;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import static org.controlsfx.validation.Validator.*;

public class NewTourController {
    @FXML
    public Button cancelButton;
    @FXML
    public TextField transtype;
    @FXML
    public TextField description;
    @FXML
    private TextField tourname;
    @FXML
    private TextField start;
    @FXML
    private TextField destination;
    @FXML
    private Button createButton;

    private final NewTourViewModel newTourViewModel;
    private ValidationSupport support;

    public NewTourController(NewTourViewModel newTourViewModel) {
        this.newTourViewModel = newTourViewModel;
    }


    public NewTourViewModel getNewTourViewModel() {
        return newTourViewModel;
    }

    @FXML
    void initialize() {
        this.support = new ValidationSupport();
        createButton.disableProperty().bind(support.invalidProperty());
        support.registerValidator(tourname, Validator.createEmptyValidator("Text is required"));
        //support.registerValidator(tourname,false, Validator.createEmptyValidator("Text is required", Severity.WARNING));
        support.registerValidator(start, Validator.createEmptyValidator("Text is required", Severity.WARNING));
        support.registerValidator(destination, Validator.createEmptyValidator("Text is required", Severity.WARNING));

        ListView<ValidationMessage> listView = new ListView<>();
        support.validationResultProperty().addListener((o, oldValue, newValue) ->
                listView.getItems().setAll(newValue.getMessages()));
    }

    public void submit(ActionEvent actionEvent) {
        Tour tour = new Tour(tourname.getText(),description.getText(), start.getText(), destination.getText(), transtype.getText());

        NewTour.getInstance().setCreateTour(tour);
        NewTour.getInstance().setCancelled(false);

        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void cancel(ActionEvent actionEvent) {
        NewTour.getInstance().setCancelled(true);

        Node source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
