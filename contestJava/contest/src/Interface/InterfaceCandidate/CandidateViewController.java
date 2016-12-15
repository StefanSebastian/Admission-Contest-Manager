package Interface.InterfaceCandidate;

import Controller.ControllerCandidate;
import Domain.Candidate;
import Validator.ControllerException;
import Validator.InvalidSelectionException;
import Validator.RepositoryException;
import Validator.ValidatorException;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


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
                //clears text fields after successful operation
                clearTextFields();
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
                    throw new InvalidSelectionException("You must select a candidate");
                }
                controller.delete(candidate.getId().toString());
                //clears the fields
                clearTextFields();
            } catch (RepositoryException | ControllerException | InvalidSelectionException exc){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning dialog");
                alert.setHeaderText("Invalid operation");
                alert.setContentText(exc.getMessage());
                alert.showAndWait();
            }
        };
    }

    /*
    Clear all text fields
     */
    public void clearTextFields(){
        candidateView.textAddress.setText("");
        candidateView.textId.setText("");
        candidateView.textName.setText("");
        candidateView.textTelephone.setText("");
    }

    /*
    Clear all button handler
     */
    EventHandler<ActionEvent> clearAllButtonHandler(){
        return event -> {
            clearTextFields();
            candidateView.tableCandidate.getSelectionModel().clearSelection();
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
                  throw new InvalidSelectionException("You must select a candidate");
              }
              //updates if possible
              controller.update(candidate.getId().toString(), candidateView.textId.getText(),
                      candidateView.textName.getText(),
                      candidateView.textTelephone.getText(),
                      candidateView.textAddress.getText());
              //clears text fields after successful operation
              clearTextFields();
          } catch (ValidatorException | RepositoryException | ControllerException | InvalidSelectionException exc){
              Alert alert = new Alert(Alert.AlertType.WARNING);
              alert.setTitle("Warning dialog");
              alert.setHeaderText("Invalid operation");
              alert.setContentText(exc.getMessage());
              alert.showAndWait();
          }
        };
    }

    /*
    When selection changes
     */
    ListChangeListener<Integer> handlerSelectionListener(){
        return c -> {
            loadTextFields();
        };
    }

    /*
    When a row is clicked
     */
    EventHandler<MouseEvent> rowClickedHandler(){
        return event -> {
          loadTextFields();
        };
    }

    /*
    Filters candidates by name
     */
    EventHandler<KeyEvent> filterNameHandler(){
        return event -> {
            String name = candidateView.textFilterName.getText();
            if (name.equals("")){
                candidateView.model.setAll(controller.getAll());
            } else {
                candidateView.model.setAll(controller.candidatesNameStartsWith(name));
            }
        };
    }

    /*
    Filters candidates by telephone
     */
    EventHandler<KeyEvent> filterTelephoneHandler(){
        return event -> {
            String telephone = candidateView.textFilterTelephone.getText();
            if (telephone.equals("")){
                candidateView.model.setAll(controller.getAll());
            } else {
                candidateView.model.setAll(controller.filterCandidatesByTelephone(telephone));
            }
        };
    }

    /*
    Loads the text fields with the values of the currently selected item
     */
    private void loadTextFields(){
        if (candidateView.tableCandidate.getSelectionModel().getSelectedIndex() != -1) {
            Candidate candidate = candidateView.tableCandidate.getSelectionModel().getSelectedItem();
            candidateView.textId.setText(candidate.getId().toString());
            candidateView.textName.setText(candidate.getName());
            candidateView.textAddress.setText(candidate.getAddress());
            candidateView.textTelephone.setText(candidate.getTelephone());
        } else {
            clearTextFields();
        }
    }
}
