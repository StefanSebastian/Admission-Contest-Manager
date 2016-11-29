package Interface.InterfaceSectie;

import Controller.ControllerSectie;
import Domain.Candidat;
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

    private ControllerSectie controller;
    private ObservableList<Sectie> observableList;
    private Pane parrentPane;

    public SectieGUIController(){

    }

    @FXML
    public void initialize(ControllerSectie controller, Pane parrentPane){
        this.parrentPane = parrentPane;
        this.controller = controller;
        observableList = FXCollections.observableArrayList(controller.getAll());
        tableSectii.setItems(observableList);
        controller.addObserver(this);

        numeColumn.setCellValueFactory(new PropertyValueFactory<Sectie, String>("nume"));
        nrLocuriColumn.setCellValueFactory(new PropertyValueFactory<Sectie, Integer>("nrLocuri"));
        idColumn.setCellValueFactory(new PropertyValueFactory<Sectie, Integer>("id"));
        tableSectii.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> c) {
                tableSelectionHandler();
            }
        });
    }

    public void tableSelectionHandler(){
        if (tableSectii.getSelectionModel().getSelectedIndex() != -1){
            Sectie sectie = tableSectii.getSelectionModel().getSelectedItem();
            textId.setText(sectie.getId().toString());
            textNume.setText(sectie.getNume());
            textNrLocuri.setText(sectie.getNrLocuri().toString());
        }
    }

    @Override
    public void update(Observable<Sectie> e) {
        observableList.setAll(controller.getAll());
    }


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
            stage.initOwner(parrentPane.getScene().getWindow());
            stage.show();
        } catch (NullPointerException exc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText("Trebuie sa selectati o sectie");
            alert.showAndWait();
        }
    }

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
