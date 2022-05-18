import com.semesterproject.tourplanner.view.NewTourController;
import com.semesterproject.tourplanner.viewmodels.NewTourViewModel;
import javafx.event.ActionEvent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NewTourControllerTest {
    @Test
    void cancelButtonSetsBoolTrue(){
        NewTourViewModel newTourViewModel = new NewTourViewModel();
        NewTourController newTourController = new NewTourController(newTourViewModel);

        ActionEvent cancel = null;
        newTourController.cancel(cancel);
    }

}