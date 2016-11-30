import Controller.ControllerDepartment;
import Domain.Candidate;
import Domain.Department;
import Interface.InterfaceDepartment.DepartmentViewController;
import Repository.IRepository;
import Repository.RepositoryCandidateFromFile;
import Repository.RepositoryDepartmentSerializable;
import Validator.ValidatorCandidate;
import Validator.ValidatorDepartment;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
        IRepository<Department, Integer> repositorySectie = new RepositoryDepartmentSerializable("src/Files/Departments",
                                                                                                new ValidatorDepartment());
        IRepository<Candidate, Integer> repositoryCandidat = new RepositoryCandidateFromFile("src/Files/Candidates",
                                                                                                new ValidatorCandidate());
        ControllerDepartment controllerDepartment = new ControllerDepartment(repositorySectie);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Interface/InterfaceDepartment/DepartmentView.fxml"));
        BorderPane borderPane = loader.load();

        DepartmentViewController sectieViewController = loader.getController();
        sectieViewController.initialize(controllerDepartment, borderPane);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Department CRUD");
        primaryStage.show();
    }

  /*  @Override
    public void start(Stage primaryStage) throws Exception {
        ValidatorCandidate validatorCandidat = new ValidatorCandidate();
        IRepository<Candidate, Integer> repositoryCandidat = new RepositoryCandidateFromFile("src/Files/Candidates", validatorCandidat);
        ValidatorDepartment validatorSectie = new ValidatorDepartment();
        IRepository<Department, Integer> repositorySectie = new RepositoryDepartmentSerializable("src/Files/Departments", validatorSectie);
        ControllerCandidate controllerCandidat = new ControllerCandidate(repositoryCandidat);
        ControllerDepartment controllerSectie = new ControllerDepartment(repositorySectie);

        CandidateDataModel candidateDataModel = new CandidateDataModel(repositoryCandidat);
        CandidateView candidatView = new CandidateView(candidateDataModel.getModel(), controllerCandidat);
        Parent parent = candidatView.getView();
        Scene scene = new Scene(parent, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Candidate manager");
        primaryStage.show();
    } */
}
