package Interface.InterfaceReports;

import Controller.ControllerCandidate;
import Controller.ControllerDepartment;
import Controller.ControllerOption;
import Domain.Candidate;
import Domain.Department;
import Domain.DepartmentCandidates;
import Utils.FileSaver;
import Utils.NumberCheck;
import Utils.Observer;
import Utils.ReportConverter;
import Validator.ControllerException;
import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class ReportsViewController implements Observer {
    /*
    Primary stage
     */
    private Stage primaryStage;

    /*
    Entity controllers
     */
    private ControllerCandidate controllerCandidate;
    private ControllerDepartment controllerDepartment;
    private ControllerOption controllerOption;

    /*
    Data source
     */
    private ObservableList<Candidate> candidateList;
    private ObservableList<Department> departmentList;

    /*
    Widgets used
     */
    @FXML
    TableView<Department> departmentTableView;
    @FXML
    TableColumn<Department, Integer> idDepartmentColumn;
    @FXML
    TableColumn<Department, String> nameDepartmentColumn;
    @FXML
    TableColumn<Department, Integer> placesDepartmentColumn;

    @FXML
    TableView<Candidate> candidateTableView;
    @FXML
    TableColumn<Candidate, Integer> idCandidateColumn;
    @FXML
    TableColumn<Candidate, String> nameCandidateColumn;
    @FXML
    TableColumn<Candidate, String> telephoneCandidateColumn;
    @FXML
    TableColumn<Candidate, String> addressCandidateColumn;

    @FXML
    Label numberOfCandidatesLabel;
    @FXML
    TextField filterTextField;
    @FXML
    Slider departmentSlider;

    @FXML
    Button saveReportsButton;
    @FXML
    ComboBox saveTypeCombobox;

    @FXML
    Button pieChartButton;
    @FXML
    Button barChartButton;

    /*
    Constructor
     */
    public ReportsViewController(){}

    /*
    Initialize gui components
     */
    public void initialize(ControllerOption controllerOption, ControllerDepartment controllerDepartment,
                           ControllerCandidate controllerCandidate, Stage primaryStage){
        this.controllerCandidate = controllerCandidate;
        this.controllerDepartment = controllerDepartment;
        this.controllerOption = controllerOption;

        this.controllerCandidate.addObserver(this);
        this.controllerDepartment.addObserver(this);
        this.controllerOption.addObserver(this);

        //stage
        this.primaryStage = primaryStage;

        //tables
        idCandidateColumn.setCellValueFactory(new PropertyValueFactory<Candidate, Integer>("id"));
        nameCandidateColumn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("name"));
        telephoneCandidateColumn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("telephone"));
        addressCandidateColumn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("address"));

        idDepartmentColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("id"));
        nameDepartmentColumn.setCellValueFactory(new PropertyValueFactory<Department, String>("name"));
        placesDepartmentColumn.setCellValueFactory(new PropertyValueFactory<Department, Integer>("numberOfPlaces"));

        candidateList = FXCollections.observableArrayList();
        departmentList = FXCollections.observableArrayList();

        candidateTableView.setItems(candidateList);
        departmentTableView.setItems(departmentList);

        updateDepartmentSliderValues();
        initializeSaveCombobox();

        setDepartmentData(((Integer)controllerDepartment.size()).toString());//initializes with all departments
    }

    /*
    Updates the possible values for the department slider
     */
    public void updateDepartmentSliderValues(){
        departmentSlider.setMin(0);
        departmentSlider.setMax(controllerDepartment.size());
    }

    //sets the top x departments in departments table
    public void setDepartmentData(String x){
        List<Department> topDepartments;

        if (!NumberCheck.isNumber(x)){ //not a number, sets all data
            topDepartments = controllerOption.topXDepartments(controllerDepartment.getAll().size());
        } else {
            topDepartments = controllerOption.topXDepartments(Integer.parseInt(x));
        }

        departmentList.setAll(topDepartments);
    }

    /*
    Handles candidate display
    if there is no selected department then no candidates are displayed
    otherwise only the candidates with the option corresponding to the selected department are displayed
     */
    @FXML
    public void displayCandidatesHandler(){
        if (departmentTableView.getSelectionModel().getSelectedItem() == null){//no selected department
            List<Candidate> candidates = new ArrayList<>();
            candidateList.setAll(candidates);
            numberOfCandidatesLabel.setText("0");
        } else {
            Department selectedDepartment = departmentTableView.getSelectionModel().getSelectedItem();
            candidateList.setAll(controllerOption.getCandidatesForDepartment(selectedDepartment.getId()));
            Integer numberOfCandidates = candidateList.size();
            numberOfCandidatesLabel.setText(numberOfCandidates.toString());
        }
    }

    /*
    Whenever the text in top candidates text fields changes we update the department table
     */
    @FXML
    public void displayDepartmentsHandler(){
        String x = filterTextField.getText();
        setDepartmentData(x);
    }

    /*
    Handles slider value
     */
    @FXML
    public void departmentSliderHandler(){
        Integer sliderValue = ((Double)departmentSlider.getValue()).intValue();
        filterTextField.setText(sliderValue.toString());
        displayDepartmentsHandler();
    }

    @Override
    public void update() {
        displayDepartmentsHandler();
        displayCandidatesHandler();
        updateDepartmentSliderValues();
    }

    //ignores push updates
    @Override
    public void pushUpdate(Object o) {

    }

    // -------------- Report saving --------------
    /*
    Returns the currently displayed information
     */
    public List<DepartmentCandidates> getCurrentReportAsList(){
        List<DepartmentCandidates> report = new ArrayList<>();
        for (Department d : departmentList){
            report.add(new DepartmentCandidates(d, controllerOption.getCandidatesForDepartment(d.getId())));
        }
        return report;
    }

    private File chooseFileOfExtension(String... extension){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the file you wish to save to");

        //set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(extension[0], extension[1]);
        fileChooser.getExtensionFilters().add(extFilter);

        return fileChooser.showSaveDialog(primaryStage);
    }

    /*
    Save as button pressed
     */
    @FXML
    public void saveReportButtonHandler(){
        if (saveTypeCombobox.getSelectionModel().getSelectedItem() == null){//no format selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning dialog");
            alert.setHeaderText("Invalid operation");
            alert.setContentText("Select a format");
            alert.showAndWait();
            return;
        }

        String format = saveTypeCombobox.getSelectionModel().getSelectedItem().toString();
        File file = null;
        String data = "";
        if (format.equals("Txt")){
            file = chooseFileOfExtension("Text Files", "*.txt");
            data = ReportConverter.convertText(getCurrentReportAsList());
        }
        if (format.equals("HTML")){
            file = chooseFileOfExtension("HTML Files", "*.html");
            data = ReportConverter.convertHTML(getCurrentReportAsList());
        }
        if (file == null){ //no file chosen
            return;
        }
        FileSaver.save(file.getAbsolutePath(), data);
    }

    /*
    Initializes values in combo box
     */
    private void initializeSaveCombobox(){
        ObservableList<String> fileTypes = FXCollections.observableArrayList("Txt", "HTML");
        saveTypeCombobox.setItems(fileTypes);
    }

    //--------------------------------------------

    //-------------Charts-------------------------
    /*
    Creates a pie chart of report data
     */
    @FXML
    public void pieChartButtonHandler(){
        //create a new window
        Stage stage = new Stage();
        stage.setTitle("Report chart");
        Scene scene = new Scene(new Group());
        stage.setWidth(500);
        stage.setHeight(500);

        //set data
        List<DepartmentCandidates> report = getCurrentReportAsList();
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (DepartmentCandidates departmentCandidates : report){
            pieChartData.add(new PieChart.Data(departmentCandidates.getDepartment().getName(),
                    departmentCandidates.getCandidateList().size()));
        }

        //create chart
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Departments");

        //display window
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

    /*
   Creates a bar chart of report data
    */
    @SuppressWarnings("unchecked")
    @FXML
    public void barChartButtonHandler(){
        //create a new window
        Stage stage = new Stage();
        stage.setTitle("Report chart");
        Scene scene = new Scene(new Group());
        stage.setWidth(500);
        stage.setHeight(500);

        //create chart
        CategoryAxis xAxis = new CategoryAxis();;
        xAxis.setLabel("Departments");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Number of candidates");
        yAxis.setTickUnit(1.0);
        BarChart barChart = new BarChart(xAxis, yAxis);

        List<DepartmentCandidates> report = getCurrentReportAsList();

        XYChart.Series dataSeries = new XYChart.Series();

        //get data
        for (DepartmentCandidates departmentCandidates : report){
            dataSeries.getData().add(new XYChart.Data(departmentCandidates.getDepartment().getName(),
                    departmentCandidates.getCandidateList().size()));
        }
        barChart.getData().add(dataSeries);

        //show window
        ((Group) scene.getRoot()).getChildren().add(barChart);
        stage.setScene(scene);
        stage.show();
    }

    //--------------------------------------------
}
