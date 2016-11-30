import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Controller.ControllerOption;
import Domain.Candidate;
import Domain.CandidateDataModel;
import Domain.Department;
import Domain.Option;
import Interface.InterfaceMainWindow.InterfaceMain;
import Repository.IRepository;
import Repository.RepositoryCandidateFromFile;
import Repository.RepositoryDepartmentSerializable;
import Repository.RepositoryOptionXML;
import Validator.ValidatorCandidate;
import Validator.ValidatorDepartment;
import Validator.ValidatorOption;
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
        IRepository<Option, Integer> repositoryOption =
                new RepositoryOptionXML("src/Files/Options", new ValidatorOption(),
                        repositoryCandidate, repositoryDepartment);
        //controllers
        ControllerCandidate controllerCandidate = new ControllerCandidate(repositoryCandidate);
        ControllerDepartment controllerDepartment = new ControllerDepartment(repositoryDepartment);
        ControllerOption controllerOption = new ControllerOption(repositoryOption);

        //data models
        CandidateDataModel candidateDataModel = new CandidateDataModel(repositoryCandidate);

        //gui
        InterfaceMain interfaceMain = new InterfaceMain(primaryStage, controllerCandidate,
                candidateDataModel, controllerDepartment, controllerOption);
    }
}
