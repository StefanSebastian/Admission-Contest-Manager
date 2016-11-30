package Interface.InterfaceOption;

import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Controller.ControllerOption;
import Domain.Candidate;
import Utils.Observer;
import Validator.ControllerException;
import Validator.InvalidSelectionException;
import Validator.RepositoryException;
import Validator.ValidatorException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

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
    Data source
     */
    private ObservableList<Candidate> candidateObservableList;

    /*
    Widgets used
     */
    @FXML
    TableView<Candidate> candidateTableView;
    @FXML
    TableColumn idColumn;
    @FXML
    TableColumn nameColumn;
    @FXML
    ComboBox comboBoxCandidateCRUD;
    @FXML
    ComboBox comboBoxDepartmentCRUD;
    @FXML
    ComboBox comboBoxDepartmentDisplay;
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

    /*
    Initializes gui components
     */
    public void initialize(ControllerOption controllerOption, ControllerCandidate controllerCandidate,
                           ControllerDepartment controllerDepartment){
        this.controllerCandidate = controllerCandidate;
        this.controllerDepartment = controllerDepartment;
        this.controllerOption = controllerOption;

        controllerDepartment.addObserver(this);
        controllerCandidate.addObserver(this);
        populateComboBoxes();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        candidateObservableList = FXCollections.observableArrayList();
        candidateTableView.setItems(candidateObservableList);
        candidateTableView.getSelectionModel().getSelectedIndices().addListener(tableSelectionListener());
    }

    /*
    Updates id textfield and comboboxes
     */
    public ListChangeListener<Integer> tableSelectionListener(){
        return c -> {
            if (candidateTableView.getSelectionModel().getSelectedIndex() != -1){
                //gets the selected candidate
                Candidate candidate = candidateTableView.getSelectionModel().getSelectedItem();

                //gets the selected department
                String selectedDepartment = comboBoxDepartmentDisplay.getSelectionModel().getSelectedItem().toString();
                String[] fields = selectedDepartment.split(" ");
                String idDepartment = fields[0];

                //to implement controllerOption id getter
            }
        };
    }

    /*
    Populates combo boxes
     */
    public void populateComboBoxes(){
        comboBoxCandidateCRUD.getItems().clear();
        controllerCandidate.getAll().forEach(x -> comboBoxCandidateCRUD.getItems().add(x.getId() + " " + x.getName()));

        comboBoxDepartmentCRUD.getItems().clear();
        controllerDepartment.getAll().forEach(x -> comboBoxDepartmentCRUD.getItems().add(x.getId() + " " + x.getName()));

        comboBoxDepartmentDisplay.getItems().clear();
        controllerDepartment.getAll().forEach(x -> comboBoxDepartmentDisplay.getItems().add(x.getId() + " " + x.getName()));
    }

    @Override
    public void update() {
        populateComboBoxes(); //updates combo boxes
        if (comboBoxDepartmentDisplay.getSelectionModel().getSelectedIndex() != -1) {
            displayCandidatesHandler(); //updates table view
        }
    }

    @FXML
    public void displayCandidatesHandler(){
        String selectedDepartment = comboBoxDepartmentDisplay.getSelectionModel().getSelectedItem().toString();
        String[] fields = selectedDepartment.split(" ");
        String idDepartment = fields[0];

        //gets the candidates for the given department
        List<Integer> candidateIds = controllerOption.candidateIdsForDepartment(Integer.parseInt(idDepartment));
        List<Candidate> candidates = new ArrayList<>();
        try {
            for (Integer id : candidateIds){
                candidates.add(controllerCandidate.getById(id.toString()));
            }
        } catch (ControllerException exc){
            exc.printStackTrace();
        }
        candidateObservableList.setAll(candidates);
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
