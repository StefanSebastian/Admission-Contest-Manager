import Controller.ControllerCandidat;
import Controller.ControllerSectie;
import Domain.Candidat;
import Domain.CandidatDataModel;
import Domain.Sectie;
import Interface.InterfaceCandidat.CandidatView;
import Interface.InterfaceSectie.SectieGUIController;
import Repository.IRepository;
import Repository.RepositoryCandidatFromFile;
import Repository.RepositorySectieSerializable;
import Validator.ValidatorCandidat;
import Validator.ValidatorSectie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
        IRepository<Sectie, Integer> repositorySectie = new RepositorySectieSerializable("src/Files/Sectii", new ValidatorSectie());
        IRepository<Candidat, Integer> repositoryCandidat = new RepositoryCandidatFromFile("src/Files/Candidati", new ValidatorCandidat());
        ControllerSectie controllerSectie = new ControllerSectie(repositorySectie);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("Interface/InterfaceSectie/SectieView.fxml"));
        BorderPane borderPane = loader.load();

        SectieGUIController sectieViewController = loader.getController();
        sectieViewController.initialize(controllerSectie, borderPane);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sectie CRUD");
        primaryStage.show();
    }

   /* @Override
    public void start(Stage primaryStage) throws Exception {
        ValidatorCandidat validatorCandidat = new ValidatorCandidat();
        IRepository<Candidat, Integer> repositoryCandidat = new RepositoryCandidatFromFile("src/Files/Candidati", validatorCandidat);
        ValidatorSectie validatorSectie = new ValidatorSectie();
        IRepository<Sectie, Integer> repositorySectie = new RepositorySectieSerializable("src/Files/Sectii", validatorSectie);
        ControllerCandidat controllerCandidat = new ControllerCandidat(repositoryCandidat);
        ControllerSectie controllerSectie = new ControllerSectie(repositorySectie);

        CandidatDataModel candidatDataModel = new CandidatDataModel(repositoryCandidat);
        CandidatView candidatView = new CandidatView(candidatDataModel.getModel(), controllerCandidat);
        Parent parent = candidatView.getView();
        Scene scene = new Scene(parent, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Candidat manager");
        primaryStage.show();
    }*/
}
