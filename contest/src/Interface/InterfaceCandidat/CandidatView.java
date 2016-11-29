package Interface.InterfaceCandidat;

import Controller.ControllerCandidat;
import Domain.Candidat;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

/**
 * Created by Sebi on 15-Nov-16.
 */
public class CandidatView {
    /*
    Widgets used
     */
    BorderPane background;

    Label idLabel;
    Label numeLabel;
    Label telefonLabel;
    Label adresaLabel;

    TextField idText;
    TextField numeText;
    TextField telefonText;
    TextField adresaText;

    Button saveButton;
    Button updateButton;
    Button deleteButton;
    Button clearAllButton;

    TableView<Candidat> tabelCandidat;
    TableColumn<Candidat, Integer> idColumn;
    TableColumn<Candidat, String> numeColumn;

    /*
    Data source
     */
    private ObservableList<Candidat> model;

    /*
    Controller
     */
    private CandidatGUIController candidatController;

    public CandidatView(ObservableList<Candidat> model, ControllerCandidat controller){
        this.model = model;
        candidatController = new CandidatGUIController(controller, this);
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
        tabelCandidat = new TableView<>();

        idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        tabelCandidat.getColumns().add(idColumn);

        numeColumn = new TableColumn<>("Nume");
        numeColumn.setCellValueFactory(new PropertyValueFactory<>("nume"));
        tabelCandidat.getColumns().add(numeColumn);

        tabelCandidat.setItems(model);

        /*
        When the selected items change
         */
       /* tabelCandidat.getSelectionModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override
            public void onChanged(Change<? extends Integer> c) {
                candidatController.tableSelectedItemChanged();
            }
        }); */
        tabelCandidat.getSelectionModel().getSelectedIndices().addListener(candidatController.handlerSelectionListener());
        return new HBox(tabelCandidat);
    }

    /*
    Creates widgets and buttons for crud operations
     */
    private Pane createCenterPane(){
        idLabel = new Label("Id: ");
        numeLabel = new Label("Nume: ");
        telefonLabel = new Label("Telefon: ");
        adresaLabel = new Label("Adresa: ");
        idText = new TextField();
        numeText = new TextField();
        telefonText = new TextField();
        adresaText = new TextField();

        GridPane gridPane = new GridPane();
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idText, 1, 0);
        gridPane.add(numeLabel, 0, 1);
        gridPane.add(numeText, 1, 1);
        gridPane.add(telefonLabel, 0, 2);
        gridPane.add(telefonText, 1, 2);
        gridPane.add(adresaLabel, 0, 3);
        gridPane.add(adresaText, 1, 3);
        gridPane.setVgap(10);

        saveButton = new Button("Save");
        saveButton.setOnAction(candidatController.addButtonHandler());
        deleteButton = new Button("Delete");
        deleteButton.setOnAction(candidatController.deleteButtonHandler());
        updateButton = new Button("Update");
        updateButton.setOnAction(candidatController.updateButtonHandler());
        clearAllButton = new Button("Clear all");
        clearAllButton.setOnAction(candidatController.clearAllButtonHandler());
        HBox buttonBox = new HBox(saveButton, deleteButton, updateButton, clearAllButton);
        buttonBox.setSpacing(10);

        VBox centerBox = new VBox(gridPane, buttonBox);
        centerBox.setSpacing(10);
        centerBox.setPadding(new Insets(20));
        return centerBox;
    }
}
