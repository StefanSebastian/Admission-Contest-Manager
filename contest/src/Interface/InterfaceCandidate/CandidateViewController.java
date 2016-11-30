package Interface.InterfaceCandidate;

import Controller.ControllerCandidate;
import Domain.Candidate;
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
public class CandidateViewController {
    //entity controller
    private ControllerCandidate controller;

    //entity view
    private CandidateView candidateView;

    /*
    Constructor
     */
    public CandidateViewController(ControllerCandidate controller, CandidateView candidateView){
        this.candidateView = candidateView;
        this.controller = controller;
    }

    /*
    Add button handler
     */
    EventHandler<ActionEvent> addButtonHandler(){
        return event -> {
            try {
                controller.save(candidateView.textId.getText(),
                        candidateView.textName.getText(),
                        candidateView.textTelephone.getText(),
                        candidateView.textAddress.getText());
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
                Candidate candidate = candidateView.tableCandidate.getSelectionModel().getSelectedItem();
                if (candidate == null){
                    throw new NullPointerException();
                }
                controller.delete(candidate.getId().toString());
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
                alert.setContentText("You must select a candidate");
                alert.showAndWait();
            }
        };
    }

    /*
    Clear all button handler
     */
    EventHandler<ActionEvent> clearAllButtonHandler(){
        return event -> {
            candidateView.textAddress.setText("");
            candidateView.textId.setText("");
            candidateView.textName.setText("");
            candidateView.textTelephone.setText("");
        };
    }

    /*
    Update button handler
     */
    EventHandler<ActionEvent> updateButtonHandler(){
        return event -> {
          try{
              //gets the initial values
              Candidate candidate = candidateView.tableCandidate.getSelectionModel().getSelectedItem();
              if (candidate == null) {
                  throw new NullPointerException();
              }

              //updates if possible
              controller.update(candidate.getId().toString(), candidateView.textId.getText(),
                      candidateView.textName.getText(),
                      candidateView.textTelephone.getText(),
                      candidateView.textAddress.getText());
          } catch (NullPointerException exc){
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Warning dialog");
              alert.setHeaderText("Invalid operation");
              alert.setContentText("You must select a candidate");
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
            if (candidateView.tableCandidate.getSelectionModel().getSelectedIndex() != -1) {
                Candidate candidate = candidateView.tableCandidate.getSelectionModel().getSelectedItem();
                candidateView.textId.setText(candidate.getId().toString());
                candidateView.textName.setText(candidate.getName());
                candidateView.textAddress.setText(candidate.getAddress());
                candidateView.textTelephone.setText(candidate.getTelephone());
            }
        };
    }
}
