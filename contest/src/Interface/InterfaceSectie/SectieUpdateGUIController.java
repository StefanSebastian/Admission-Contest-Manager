package Interface.InterfaceSectie;

import Controller.ControllerSectie;
import Domain.Sectie;
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
public class SectieUpdateGUIController {
    @FXML
    private TextField idText;
    @FXML
    private TextField numeText;
    @FXML
    private TextField nrLocuriText;
    @FXML
    private Button updateButton;
    @FXML
    private Button closeButton;

    private ControllerSectie controllerSectie;
    private Stage stage;
    Sectie sectieSelectata;

    public SectieUpdateGUIController(){

    }

    public void initialize(ControllerSectie controller, Stage stage, Sectie sectieSelectata){
        this.controllerSectie = controller;
        this.stage = stage;
        this.sectieSelectata = sectieSelectata;
        idText.setText(sectieSelectata.getId().toString());
        numeText.setText(sectieSelectata.getNume());
        nrLocuriText.setText(sectieSelectata.getNrLocuri().toString());
    }

    @FXML
    public void closeButtonHandler(){
        stage.close();
    }

    @FXML
    public void updateButtonHandler(){
        try{
            controllerSectie.update(sectieSelectata.getId().toString(), idText.getText(), numeText.getText(), nrLocuriText.getText());
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
