import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Domain.Candidate;
import Domain.CandidateDataModel;
import Domain.Department;
import Interface.InterfaceMainWindow.InterfaceMain;
import Repository.IRepository;
import Repository.RepositoryCandidateFromFile;
import Repository.RepositoryDepartmentSerializable;
import Validator.ValidatorCandidate;
import Validator.ValidatorDepartment;
import javafx.application.Application;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

/**
 * Created by Sebi on 10/7/2016.
 */
public class Main extends Application {

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //repositories
        IRepository<Department, Integer> repositoryDepartment =
                new RepositoryDepartmentSerializable("src/Files/Departments", new ValidatorDepartment());
        IRepository<Candidate, Integer> repositoryCandidate =
                new RepositoryCandidateFromFile("src/Files/Candidates", new ValidatorCandidate());

        //controllers
        ControllerCandidate controllerCandidate = new ControllerCandidate(repositoryCandidate);
        ControllerDepartment controllerDepartment = new ControllerDepartment(repositoryDepartment);

        //data models
        CandidateDataModel candidateDataModel = new CandidateDataModel(repositoryCandidate);

        //gui
        InterfaceMain interfaceMain = new InterfaceMain(primaryStage, controllerCandidate,
                candidateDataModel, controllerDepartment);
    }
}
