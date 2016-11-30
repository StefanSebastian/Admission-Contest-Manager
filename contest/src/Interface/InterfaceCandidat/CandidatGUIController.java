package Interface.InterfaceCandidat;

import Controller.ControllerCandidat;
import Domain.Candidat;
import Interface.InterfaceCandidat.CandidatView;
import Validator.ControllerException;
import Validator.RepositoryException;
import Validator.ValidatorException;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;


/**
 * Created by Sebi on 15-Nov-16.
 */
public class CandidatGUIController {
    private ControllerCandidat controller;
    private CandidatView candidatView;

    /*
    Constructor
     */
    public CandidatGUIController(ControllerCandidat controller, CandidatView candidatView){
        this.candidatView = candidatView;
        this.controller = controller;
    }

    /*
    Add button handler
     */
    EventHandler<ActionEvent> addButtonHandler(){
        return event -> {
            try {
                controller.save(candidatView.idText.getText(),
                        candidatView.numeText.getText(),
                        candidatView.telefonText.getText(),
                        candidatView.adresaText.getText());
            }catch (ValidatorException | RepositoryException | ControllerException exc){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning dialog");
                alert.setHeaderText("Invalid operation");
                alert.setContentText(exc.getMessage());
                alert.showAndWait();
            }
        };
    }

    /*
    Delete button handler
     */
    EventHandler<ActionEvent> deleteButtonHandler(){
        return event -> {
            try {
                Candidat candidat = candidatView.tabelCandidat.getSelectionModel().getSelectedItem();
                if (candidat == null){
                    throw new NullPointerException();
                }
                controller.delete(candidat.getId().toString());
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
                alert.setContentText("Trebuie sa selectati un candidat");
                alert.showAndWait();
            }
        };
    }

    /*
    Clear all button handler
     */
    EventHandler<ActionEvent> clearAllButtonHandler(){
        return event -> {
            candidatView.adresaText.setText("");
            candidatView.idText.setText("");
            candidatView.numeText.setText("");
            candidatView.telefonText.setText("");
        };
    }

    /*
    Update button handler
     */
    EventHandler<ActionEvent> updateButtonHandler(){
        return event -> {
          try{
              //gets the initial values
              Candidat candidat = candidatView.tabelCandidat.getSelectionModel().getSelectedItem();
              if (candidat == null) {
                  throw new NullPointerException();
              }

              //updates if possible
              controller.update(candidat.getId().toString(), candidatView.idText.getText(),
                      candidatView.numeText.getText(),
                      candidatView.telefonText.getText(),
                      candidatView.adresaText.getText());
          } catch (NullPointerException exc){
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Warning dialog");
              alert.setHeaderText("Invalid operation");
              alert.setContentText("Trebuie sa selectati un candidat");
              alert.showAndWait();
          } catch (ValidatorException | RepositoryException | ControllerException exc){
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Warning dialog");
              alert.setHeaderText("Invalid operation");
              alert.setContentText(exc.getMessage());
              alert.showAndWait();
          }
        };
    }

    /*
    Updates text fields
     */
    public ListChangeListener<Integer> handlerSelectionListener(){
        return c -> {
            if (candidatView.tabelCandidat.getSelectionModel().getSelectedIndex() != -1) {
                Candidat candidat = candidatView.tabelCandidat.getSelectionModel().getSelectedItem();
                candidatView.idText.setText(candidat.getId().toString());
                candidatView.numeText.setText(candidat.getNume());
                candidatView.adresaText.setText(candidat.getAdresa());
                candidatView.telefonText.setText(candidat.getTelefon());
            }
        };
    }
}
