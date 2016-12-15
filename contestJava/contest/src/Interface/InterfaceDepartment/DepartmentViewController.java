package Interface.InterfaceDepartment;

import Controller.ControllerDepartment;
import Domain.Department;
import Utils.Observer;
import Validator.ControllerException;
import Validator.RepositoryException;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Sebi on 25-Nov-16.
 */
public class DepartmentViewController implements Observer<Department> {
    /*
    Widgets used
     */
    @FXML
    private TableView<Department> tableDepartments;
    @FXML
    private TableColumn<Department, String> nameColumn;
    @FXML
    private TableColumn<Department, Integer> nrPlacesColumn;
    @FXML
    private TableColumn<Department, Integer> idColumn;
    @FXML
    private TextField textId;
    @FXML
    private TextField textName;
    @FXML
    private TextField textNrPlaces;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonUpdate;
    @FXML
    private TextField textFilterName;
    @FXML
    private TextField textFilterNrPlaces;

    //entity controller
    private ControllerDepartment controller;

    //data source
    private ObservableList<Department> observableList;

    //parent pane
    private Pane parentPane;

    /*
    Constructor
     */
    public DepartmentViewController(){}

    /*
    Initializes the view-controller with an entity controller and the parent pane
    sets table cell value factories
    adds a table selection listener
     */
    public void initialize(ControllerDepartment controller, Pane parentPane){
        this.parentPane = parentPane;
        this.controller = controller;
        observableList = FXCollections.observableArrayList(controller.getAll());
        tableDepartments.setItems(observableList);
        controller.addObserver(this);

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nrPlacesColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfPlaces"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableDepartments.getSelectionModel().getSelectedIndices().addListener(tableSelectionListener());
    }

    /*
    Listener for table selection
    Updates text fields according to currently selected item
     */
    public ListChangeListener<Integer> tableSelectionListener(){
        return c -> {
            if (tableDepartments.getSelectionModel().getSelectedIndex() != -1){
                Department department = tableDepartments.getSelectionModel().getSelectedItem();
                textId.setText(department.getId().toString());
                textName.setText(department.getName());
                textNrPlaces.setText(department.getNumberOfPlaces().toString());
            } else {
                clearTextFields();
            }
        };
    }

    /*
    Observer method implementation
    For every change in data we update the displayed table list
     */
    @Override
    public void update() {
        observableList.setAll(controller.getAll());
        textFilterNrPlaces.setText("");
        textFilterName.setText("");
    }

    @Override
    public void pushUpdate(Department department) {
        //ignores push updates
    }


    /*
    Add button handler
    Creates an add window
     */
    @FXML
    public void addButtonHandler() throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(DepartmentViewController.class.getResource("AddView.fxml"));
        AnchorPane anchorPane = loader.load();

        DepartmentAddViewController controllerAdd = loader.getController();
        controllerAdd.initialize(controller, stage);

        Scene addScene = new Scene(anchorPane);
        stage.setScene(addScene);
        stage.setTitle("Add departments");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
    }

    /*
    Update button handler
    Creates an update window for the currently selected item
    Throws an exception if no item is selected
     */
    @FXML
    public void updateButtonHandler() throws IOException{
        try {
            Department department = tableDepartments.getSelectionModel().getSelectedItem();
            if (department == null){
                throw new NullPointerException();
            }

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(DepartmentViewController.class.getResource("UpdateView.fxml"));
            AnchorPane anchorPane = loader.load();

            DepartmentUpdateViewController controllerUpdate = loader.getController();
            controllerUpdate.initialize(controller, stage, department);

            Scene addScene = new Scene(anchorPane);
            stage.setScene(addScene);
            stage.setTitle("Update the department");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(parentPane.getScene().getWindow());
            stage.show();
        } catch (NullPointerException exc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText("You must select a department");
            alert.showAndWait();
        }
    }

    /*
    Delete button handler
    Tries to delete currently selected item
    Throws an exception if no item is selected
     */
    @FXML
    public void deleteButtonHandler() throws IOException {

        try {
            Department department = tableDepartments.getSelectionModel().getSelectedItem();
            if (department == null){
                throw new NullPointerException();
            }
            controller.delete(department.getId().toString());
            clearTextFields();
        } catch (RepositoryException | ControllerException exc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText(exc.getMessage());
            alert.showAndWait();
        } catch (NullPointerException exc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText("You must select a department");
            alert.showAndWait();
        }
    }

    /*
    Clears the values in text fields
     */
    private void clearTextFields(){
        textId.setText("");
        textName.setText("");
        textNrPlaces.setText("");
    }

    /*
    Filters departments by name
     */
    @FXML
    public void filterNameHandler(){
        String name = textFilterName.getText();
        if (name.equals("")){
            observableList.setAll(controller.getAll());
        } else {
            observableList.setAll(controller.filterDepartmentsByName(name));
        }
    }

    /*
    Filters departments by number of places
     */
    @FXML
    public void filterNumberPlacesHandler(){
        if (textFilterNrPlaces.getText().equals("")){
            observableList.setAll(controller.getAll());
        } else {
            Tooltip tooltip = new Tooltip();
            textFilterNrPlaces.setTooltip(tooltip);
            try {
                Integer nrPlaces = Integer.parseInt(textFilterNrPlaces.getText());
                observableList.setAll(controller.filterDepartmentsByNumberOfPlaces(nrPlaces));
                tooltip.setText("Filters the departments that have a number of places greater than the given one");
            } catch(NumberFormatException exc){
                tooltip.setText("You must insert a valid number");
            }
        }
    }
}
