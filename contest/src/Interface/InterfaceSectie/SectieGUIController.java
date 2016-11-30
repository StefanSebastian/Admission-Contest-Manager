package Interface.InterfaceSectie;

import Controller.ControllerSectie;
import Domain.Sectie;
import Utils.Observable;
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
public class SectieGUIController implements Observer<Sectie> {
    /*
    Widgets used
     */
    @FXML
    private TableView<Sectie> tableSectii;
    @FXML
    private TableColumn<Sectie, String> numeColumn;
    @FXML
    private TableColumn<Sectie, Integer> nrLocuriColumn;
    @FXML
    private TableColumn<Sectie, Integer> idColumn;
    @FXML
    private TextField textId;
    @FXML
    private TextField textNume;
    @FXML
    private TextField textNrLocuri;
    @FXML
    private Button buttonAdd;
    @FXML
    private Button buttonDelete;
    @FXML
    private Button buttonUpdate;

    //entity controller
    private ControllerSectie controller;

    //data source
    private ObservableList<Sectie> observableList;

    //parent pane
    private Pane parentPane;

    /*
    Constructor
     */
    public SectieGUIController(){}

    /*
    Initializes the view-controller with an entity controller and the parent pane
    sets table cell value factories
    adds a table selection listener
     */
    public void initialize(ControllerSectie controller, Pane parentPane){
        this.parentPane = parentPane;
        this.controller = controller;
        observableList = FXCollections.observableArrayList(controller.getAll());
        tableSectii.setItems(observableList);
        controller.addObserver(this);

        numeColumn.setCellValueFactory(new PropertyValueFactory<Sectie, String>("nume"));
        nrLocuriColumn.setCellValueFactory(new PropertyValueFactory<Sectie, Integer>("nrLocuri"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Sectie, Integer>("id"));
        tableSectii.getSelectionModel().getSelectedIndices().addListener(tableSelectionListener());
    }

    /*
    Listener for table selection
    Updates text fields according to currently selected item
     */
    public ListChangeListener<Integer> tableSelectionListener(){
        return c -> {
            if (tableSectii.getSelectionModel().getSelectedIndex() != -1){
                Sectie sectie = tableSectii.getSelectionModel().getSelectedItem();
                textId.setText(sectie.getId().toString());
                textNume.setText(sectie.getNume());
                textNrLocuri.setText(sectie.getNrLocuri().toString());
            }
        };
    }

    /*
    Observer method implementation
    For every change in data we update the displayed table list
     */
    @Override
    public void update(Observable<Sectie> e) {
        observableList.setAll(controller.getAll());
    }


    /*
    Add button handler
    Creats an add window
     */
    @FXML
    public void addButtonHandler() throws IOException{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(SectieGUIController.class.getResource("AddView.fxml"));
        AnchorPane anchorPane = loader.load();

        SectieAddGUIController controllerAdd = loader.getController();
        controllerAdd.initialize(controller, stage);

        Scene addScene = new Scene(anchorPane);
        stage.setScene(addScene);
        stage.setTitle("Adauga o sectie");
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
            Sectie sectie = tableSectii.getSelectionModel().getSelectedItem();
            if (sectie == null){
                throw new NullPointerException();
            }

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SectieGUIController.class.getResource("UpdateView.fxml"));
            AnchorPane anchorPane = loader.load();

            SectieUpdateGUIController controllerUpdate = loader.getController();
            controllerUpdate.initialize(controller, stage, sectie);

            Scene addScene = new Scene(anchorPane);
            stage.setScene(addScene);
            stage.setTitle("Modifica o sectie");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(parentPane.getScene().getWindow());
            stage.show();
        } catch (NullPointerException exc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText("Trebuie sa selectati o sectie");
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
            Sectie sectie = tableSectii.getSelectionModel().getSelectedItem();
            if (sectie == null){
                throw new NullPointerException();
            }
            controller.delete(sectie.getId().toString());
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
            alert.setContentText("Trebuie sa selectati o sectie");
            alert.showAndWait();
        }
    }
}
