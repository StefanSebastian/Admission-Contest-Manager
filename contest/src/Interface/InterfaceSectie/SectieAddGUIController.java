package Interface.InterfaceSectie;

import Controller.ControllerSectie;
import Validator.ControllerException;
import Validator.RepositoryException;
import Validator.ValidatorException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Sebi on 25-Nov-16.
 */
public class SectieAddGUIController {
    /*
    Widgets used
     */
    @FXML
    private TextField idText;
    @FXML
    private TextField numeText;
    @FXML
    private TextField nrLocuriText;
    @FXML
    private Button addButton;
    @FXML
    private Button closeButton;

    //entity controller
    private ControllerSectie controllerSectie;

    //current stage
    private Stage stage;

    /*
    Controller
     */
    public SectieAddGUIController(){

    }

    /*
    Initializes the view-controller with an entity controller and the current stage
     */
    public void initialize(ControllerSectie controller, Stage stage){
        this.controllerSectie = controller;
        this.stage = stage;
    }

    /*
    Close button handler
    Closes the current stage
     */
    @FXML
    public void closeButtonHandler(){
        stage.close();
    }

    /*
    Add button handler
    Tries to save in memory the entity from text fields
     */
    @FXML
    public void addButtonHandler() {
        try {
            controllerSectie.save(idText.getText(), numeText.getText(), nrLocuriText.getText());
        } catch (ValidatorException | RepositoryException | ControllerException exc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        }
    }
}
