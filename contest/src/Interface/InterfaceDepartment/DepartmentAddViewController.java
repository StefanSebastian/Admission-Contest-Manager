package Interface.InterfaceDepartment;

import Controller.ControllerDepartment;
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
public class DepartmentAddViewController {
    /*
    Widgets used
     */
    @FXML
    private TextField textId;
    @FXML
    private TextField textName;
    @FXML
    private TextField textNrPlaces;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonClose;

    //entity controller
    private ControllerDepartment controllerDepartment;

    //current stage
    private Stage stage;

    /*
    Controller
     */
    public DepartmentAddViewController(){

    }

    /*
    Initializes the view-controller with an entity controller and the current stage
     */
    public void initialize(ControllerDepartment controller, Stage stage){
        this.controllerDepartment = controller;
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
            controllerDepartment.save(textId.getText(), textName.getText(), textNrPlaces.getText());
        } catch (ValidatorException | RepositoryException | ControllerException exc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        }
    }
}
