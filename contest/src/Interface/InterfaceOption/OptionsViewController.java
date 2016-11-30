package Interface.InterfaceOption;

import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Controller.ControllerOption;
import Domain.Candidate;
import Domain.Department;
import Utils.Observer;
import Validator.ControllerException;
import Validator.InvalidSelectionException;
import Validator.RepositoryException;
import Validator.ValidatorException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class OptionsViewController implements Observer {
    /*
    Entity controllers
     */
    private ControllerCandidate controllerCandidate;
    private ControllerDepartment controllerDepartment;
    private ControllerOption controllerOption;

    /*
    Widgets used
     */
    @FXML
    ComboBox comboBoxCandidateCRUD;
    @FXML
    ComboBox comboBoxDepartmentCRUD;
    @FXML
    Button buttonAdd;
    @FXML
    Button buttonUpdate;
    @FXML
    Button buttonDelete;
    @FXML
    TextField textId;

    /*
    Constructor
     */
    public OptionsViewController(){}

    public void initialize(ControllerOption controllerOption, ControllerCandidate controllerCandidate,
                           ControllerDepartment controllerDepartment){
        this.controllerCandidate = controllerCandidate;
        this.controllerDepartment = controllerDepartment;
        this.controllerOption = controllerOption;

        controllerDepartment.addObserver(this);
        controllerCandidate.addObserver(this);
        populateComboBoxes();
    }

    public void populateComboBoxes(){
        comboBoxCandidateCRUD.getItems().clear();
        controllerCandidate.getAll().forEach(x -> comboBoxCandidateCRUD.getItems().add(x.getId() + " " + x.getName()));

        comboBoxDepartmentCRUD.getItems().clear();
        controllerDepartment.getAll().forEach(x -> comboBoxDepartmentCRUD.getItems().add(x.getId() + " " + x.getName()));
    }

    @Override
    public void update() {
        populateComboBoxes();
    }

    @FXML
    public void addButtonHandler(){
        try {
            if (comboBoxDepartmentCRUD.getSelectionModel().getSelectedItem() == null){
                throw new InvalidSelectionException("You must select a department");
            }
            if (comboBoxCandidateCRUD.getSelectionModel().getSelectedItem() == null){
                throw new InvalidSelectionException("You must select a candidate");
            }
            String selectedCandidate = comboBoxCandidateCRUD.getSelectionModel().getSelectedItem().toString();
            String[] fields = selectedCandidate.split(" ");
            String idCandidate = fields[0];

            String selectedDepartment = comboBoxDepartmentCRUD.getSelectionModel().getSelectedItem().toString();
            fields = selectedDepartment.split(" ");
            String idDepartment = fields[0];

            controllerOption.save(textId.getText(), idCandidate, idDepartment);

        } catch (InvalidSelectionException | ControllerException | RepositoryException | ValidatorException exc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        }
    }
}
