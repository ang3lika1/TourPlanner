package com.semesterproject.tourplanner.view;

import com.semesterproject.tourplanner.bl.Logging.LoggerFactory;
import com.semesterproject.tourplanner.bl.Logging.LoggerWrapper;
import com.semesterproject.tourplanner.bl.TourServiceImpl;
import com.semesterproject.tourplanner.models.Tour;
import com.semesterproject.tourplanner.viewmodels.NewTour;
import com.semesterproject.tourplanner.viewmodels.NewTourViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.validation.*;

public class NewTourController {
    @FXML
    public Button cancelButton;
    @FXML
    public TextField transtype;
    @FXML
    public TextField description;
    public AnchorPane createAnchorPane;
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
    private static final LoggerWrapper logger = LoggerFactory.getLogger(NewTourController.class);

    public NewTourController(NewTourViewModel newTourViewModel) {
        this.newTourViewModel = newTourViewModel;
        TourServiceImpl tourServiceImpl = new TourServiceImpl();
    }


    @FXML
    void initialize() {
        this.support = new ValidationSupport();
        createButton.disableProperty().bind(support.invalidProperty());
        Validator<String> emptyField = Validator.createEmptyValidator("Text required");
        Validator<String> uniqueName = Validator.createPredicateValidator(
                newTourViewModel::isUnique
                ,"Tour with this name already exists");
        Validator<String> validLocation = Validator.createPredicateValidator(
                newTourViewModel::validMap
                ,"invalid location");
        support.registerValidator(tourname, Validator.combine(emptyField, uniqueName));
        support.registerValidator(start, Validator.combine(emptyField, validLocation));
        support.registerValidator(destination, Validator.combine(emptyField, validLocation));
    }

    public void submit(ActionEvent actionEvent){
        Tour tour = new Tour(tourname.getText(),description.getText(), start.getText(), destination.getText(), transtype.getText());

        NewTour.getInstance().setCancelled(false);
        try {
            newTourViewModel.addTour(tour);
        } catch (Exception e) {
            logger.warn(e.getMessage());
        }

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
