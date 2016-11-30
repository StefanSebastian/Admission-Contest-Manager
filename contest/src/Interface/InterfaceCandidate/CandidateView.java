package Interface.InterfaceCandidate;

import Controller.ControllerCandidate;
import Domain.Candidate;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

/**
 * Created by Sebi on 15-Nov-16.
 */
public class CandidateView {
    /*
    Widgets used
     */
    private BorderPane background;

    private Label labelId;
    private Label labelName;
    private Label labelTelephone;
    private Label labelAddress;

    TextField textId;
    TextField textName;
    TextField textTelephone;
    TextField textAddress;

    private Button buttonSave;
    private Button buttonUpdate;
    private Button buttonDelete;
    private Button buttonClearAll;

    TableView<Candidate> tableCandidate;
    private TableColumn<Candidate, Integer> idColumn;
    private TableColumn<Candidate, String> nameColumn;

    /*
    Data source
     */
    private ObservableList<Candidate> model;

    /*
    Controller
     */
    private CandidateViewController candidateController;

    /*
    Constructor - gets reference to data model and entity controller
     */
    public CandidateView(ObservableList<Candidate> model, ControllerCandidate controller){
        this.model = model;
        candidateController = new CandidateViewController(controller, this);
        initComponents();
    }

    /*
    Initializes the components
     */
    private void initComponents(){
        background = new BorderPane();
        background.setLeft(createLeftPane());
        background.setCenter(createCenterPane());
    }

    /*
    Returns the parent view
     */
    public BorderPane getView(){
        return background;
    }

    /*
    Creates the candidates table
     */
    private Pane createLeftPane(){
        tableCandidate = new TableView<>();

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableCandidate.getColumns().add(idColumn);

        nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCandidate.getColumns().add(nameColumn);

        tableCandidate.setItems(model);

        /*
        When the selected items change
         */
        tableCandidate.getSelectionModel().getSelectedIndices().addListener(candidateController.handlerSelectionListener());
        return new HBox(tableCandidate);
    }

    /*
    Creates widgets and buttons for crud operations
     */
    private Pane createCenterPane(){
        labelId = new Label("Id: ");
        labelName = new Label("Nume: ");
        labelTelephone = new Label("Telefon: ");
        labelAddress = new Label("Adresa: ");
        textId = new TextField();
        textName = new TextField();
        textTelephone = new TextField();
        textAddress = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(labelId, 0, 0);
        gridPane.add(textId, 1, 0);
        gridPane.add(labelName, 0, 1);
        gridPane.add(textName, 1, 1);
        gridPane.add(labelTelephone, 0, 2);
        gridPane.add(textTelephone, 1, 2);
        gridPane.add(labelAddress, 0, 3);
        gridPane.add(textAddress, 1, 3);
        gridPane.setVgap(10);

        buttonSave = new Button("Save");
        buttonSave.setOnAction(candidateController.addButtonHandler());
        buttonDelete = new Button("Delete");
        buttonDelete.setOnAction(candidateController.deleteButtonHandler());
        buttonUpdate = new Button("Update");
        buttonUpdate.setOnAction(candidateController.updateButtonHandler());
        buttonClearAll = new Button("Clear all");
        buttonClearAll.setOnAction(candidateController.clearAllButtonHandler());
        HBox buttonBox = new HBox(buttonSave, buttonDelete, buttonUpdate, buttonClearAll);
        buttonBox.setSpacing(10);

        VBox centerBox = new VBox(gridPane, buttonBox);
        centerBox.setSpacing(10);
        centerBox.setPadding(new Insets(20));
        return centerBox;
    }
}
