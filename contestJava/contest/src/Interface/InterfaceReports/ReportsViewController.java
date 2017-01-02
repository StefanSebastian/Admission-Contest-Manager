package Interface.InterfaceReports;

import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Controller.ControllerOption;
import Domain.Candidate;
import Domain.Department;
import Domain.DepartmentCandidates;
import Utils.NumberCheck;
import Utils.Observer;
import Validator.ControllerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class ReportsViewController implements Observer {
    /*
    Entity controllers
     */
    private ControllerCandidate controllerCandidate;
    private ControllerDepartment controllerDepartment;
    private ControllerOption controllerOption;

    /*
    Data source
     */
    private ObservableList<Candidate> candidateList;
    private ObservableList<Department> departmentList;

    /*
    Widgets used
     */
    @FXML
    TableView<Department> departmentTableView;
    @FXML
    TableColumn<Department, Integer> idDepartmentColumn;
    @FXML
    TableColumn<Department, String> nameDepartmentColumn;
    @FXML
    TableColumn<Department, Integer> placesDepartmentColumn;

    @FXML
    TableView<Candidate> candidateTableView;
    @FXML
    TableColumn<Candidate, Integer> idCandidateColumn;
    @FXML
    TableColumn<Candidate, String> nameCandidateColumn;
    @FXML
    TableColumn<Candidate, String> telephoneCandidateColumn;
    @FXML
    TableColumn<Candidate, String> addressCandidateColumn;

    @FXML
    Label numberOfCandidatesLabel;
    @FXML
    TextField filterTextField;

    /*
    Constructor
     */
    public ReportsViewController(){}

    /*
    Initialize gui components
     */
    public void initialize(ControllerOption controllerOption, ControllerDepartment controllerDepartment,
                           ControllerCandidate controllerCandidate){
        this.controllerCandidate = controllerCandidate;
        this.controllerDepartment = controllerDepartment;
        this.controllerOption = controllerOption;

        this.controllerCandidate.addObserver(this);
        this.controllerDepartment.addObserver(this);
        this.controllerOption.addObserver(this);

        //tables
        idCandidateColumn.setCellValueFactory(new PropertyValueFactory<Candidate, Integer>("id"));
        nameCandidateColumn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("name"));
        telephoneCandidateColumn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("telephone"));
        addressCandidateColumn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("address"));

        idDepartmentColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
        nameDepartmentColumn.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        placesDepartmentColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("numberOfPlaces"));

        candidateList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();

        candidateTableView.setItems(candidateList);
        departmentTableView.setItems(departmentList);

        setDepartmentData(((Integer)controllerDepartment.size()).toString());//initializes with all departments
    }

    //sets the top x departments in departments table
    public void setDepartmentData(String x){
        List<Department> topDepartments;

        if (!NumberCheck.isNumber(x)){ //not a number, sets all data
            topDepartments = controllerOption.topXDepartments(controllerDepartment.getAll().size());
        } else {
            topDepartments = controllerOption.topXDepartments(Integer.parseInt(x));
        }

        departmentList.setAll(topDepartments);
    }

    /*
    Handles candidate display
    if there is no selected department then no candidates are displayed
    otherwise only the candidates with the option corresponding to the selected department are displayed
     */
    @FXML
    public void displayCandidatesHandler(){
        if (departmentTableView.getSelectionModel().getSelectedItem() == null){//no selected department
            List<Candidate> candidates = new ArrayList<>();
            candidateList.setAll(candidates);
            numberOfCandidatesLabel.setText("0");
        } else {
            Department selectedDepartment = departmentTableView.getSelectionModel().getSelectedItem();
            candidateList.setAll(controllerOption.getCandidatesForDepartment(selectedDepartment.getId()));
            Integer numberOfCandidates = candidateList.size();
            numberOfCandidatesLabel.setText(numberOfCandidates.toString());
        }
    }

    /*
    Whenever the text in top candidates text fields changes we update the department table
     */
    @FXML
    public void displayDepartmentsHandler(){
        String x = filterTextField.getText();
        setDepartmentData(x);
    }

    @Override
    public void update() {
        displayDepartmentsHandler();
        displayCandidatesHandler();
    }

    @Override
    public void pushUpdate(Object o) {

    }
}
