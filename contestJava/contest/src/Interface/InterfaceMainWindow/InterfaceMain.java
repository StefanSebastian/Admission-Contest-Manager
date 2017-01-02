package Interface.InterfaceMainWindow;

import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Controller.ControllerOption;
import Domain.CandidateDataModel;
import Interface.InterfaceCandidate.CandidateView;
import Interface.InterfaceDepartment.DepartmentViewController;
import Interface.InterfaceOption.OptionsViewController;
import Interface.InterfaceReports.ReportsViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class InterfaceMain {

    /*
    Initializes windows
     */
    public InterfaceMain(Stage primaryStage,
                         ControllerCandidate controllerCandidate, CandidateDataModel candidateDataModel,
                         ControllerDepartment controllerDepartment,
                         ControllerOption controllerOption) throws IOException {

        //gets the parent of department view
        FXMLLoader loaderDepartment = new FXMLLoader(InterfaceMain.class.getResource("../InterfaceDepartment/DepartmentView.fxml"));
        BorderPane departmentParent = loaderDepartment.load();
        DepartmentViewController departmentViewController = loaderDepartment.getController();
        departmentViewController.initialize(controllerDepartment, departmentParent);

        //gets the parent of candidate view
        CandidateView candidateView = new CandidateView(candidateDataModel.getModel(), controllerCandidate);
        BorderPane candidateParent = candidateView.getView();

        //gets the parent of the options view
        FXMLLoader loaderOption = new FXMLLoader(InterfaceMain.class.getResource("../InterfaceOption/OptionsView.fxml"));
        BorderPane optionParent = loaderOption.load();
        OptionsViewController optionsViewController = loaderOption.getController();
        optionsViewController.initialize(controllerOption, controllerCandidate, controllerDepartment);

        //gets the parent of reports view
        FXMLLoader loaderReports = new FXMLLoader(InterfaceMain.class.getResource("../InterfaceReports/ReportsView.fxml"));
        BorderPane reportParent = loaderReports.load();
        ReportsViewController reportsViewController = loaderReports.getController();
        reportsViewController.initialize(controllerOption, controllerDepartment, controllerCandidate);

        //gets the main window view
        FXMLLoader loaderMain = new FXMLLoader(InterfaceMain.class.getResource("MainWindow.fxml"));
        TabPane mainWindowParent = loaderMain.load();
        MainWindowController mainWindowController = loaderMain.getController();
        mainWindowController.initialize(departmentParent, candidateParent, optionParent, reportParent);

        //displays main window
        Scene scene = new Scene(mainWindowParent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admission contest");
        primaryStage.show();
    }

}
