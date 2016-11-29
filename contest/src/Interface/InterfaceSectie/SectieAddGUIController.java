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

    private ControllerSectie controllerSectie;
    private Stage stage;

    public SectieAddGUIController(){

    }

    public void initialize(ControllerSectie controller, Stage stage){
        this.controllerSectie = controller;
        this.stage = stage;
    }

    @FXML
    public void closeButtonHandler(){
        stage.close();
    }

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
