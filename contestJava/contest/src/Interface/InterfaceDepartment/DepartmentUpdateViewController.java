package Interface.InterfaceDepartment;

import Controller.ControllerDepartment;
import Domain.Department;
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
public class DepartmentUpdateViewController {
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
    private Button buttonUpdate;
    @FXML
    private Button buttonClose;

    //entity controller
    private ControllerDepartment controllerDepartment;

    //current stage
    private Stage stage;

    //selected entity
    private Department selectedDepartment;

    /*
    Constructor
     */
    public DepartmentUpdateViewController(){

    }

    /*
    Initializes the view-controller with entity controller, current stage and the selected entity
     */
    public void initialize(ControllerDepartment controller, Stage stage, Department selectedDepartment){
        this.controllerDepartment = controller;
        this.stage = stage;
        this.selectedDepartment = selectedDepartment;
        textId.setText(selectedDepartment.getId().toString());
        textName.setText(selectedDepartment.getName());
        textNrPlaces.setText(selectedDepartment.getNumberOfPlaces().toString());
    }

    /*
    Close button handler
    closes current stage
     */
    @FXML
    public void closeButtonHandler(){
        stage.close();
    }

    /*
    Update button handler
    tries to update the selected entity
     */
    @FXML
    public void updateButtonHandler(){
        try{
            controllerDepartment.update(selectedDepartment.getId().toString(), textId.getText(), textName.getText(), textNrPlaces.getText());
            stage.close();
        } catch (ControllerException | RepositoryException | ValidatorException exc) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        }

    }
}
