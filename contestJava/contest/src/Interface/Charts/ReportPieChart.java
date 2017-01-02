package Interface.Charts;

import Domain.DepartmentCandidates;
import Interface.InterfaceOption.OptionsViewController;
import Interface.InterfaceReports.ReportsViewController;
import Utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class ReportPieChart implements Observer{
    //view controller for reports - gives report data
    private ReportsViewController reportsViewController;

    //data used in pie chart
    private ObservableList<PieChart.Data> pieChartData;

    //pie chart reference
    private PieChart chart;

    //scene reference
    private Scene scene;

    public ReportPieChart(ReportsViewController reportsViewController){
        this.reportsViewController = reportsViewController;

        //create a new window
        Stage stage = new Stage();
        stage.setTitle("Report chart");
        scene = new Scene(new Group());
        stage.setWidth(500);
        stage.setHeight(500);

        setData();

        //create chart
        chart = new PieChart(pieChartData);
        chart.setTitle("Departments");

        //display window
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

    /*
    Updates the data displayed in pie chart
     */
    private void setData(){
        //set data
        List<DepartmentCandidates> report = reportsViewController.getCurrentReportAsList();
        pieChartData = FXCollections.observableArrayList();
        for (DepartmentCandidates departmentCandidates : report){
            pieChartData.add(new PieChart.Data(departmentCandidates.getDepartment().getName(),
                    departmentCandidates.getCandidateList().size()));
        }
    }

    /*
    When report changes
     */
    @Override
    public void update() {
        setData();
        chart = new PieChart(pieChartData);
        ((Group) scene.getRoot()).getChildren().clear();
        ((Group) scene.getRoot()).getChildren().add(chart);
    }

    @Override
    public void pushUpdate(Object o) {

    }
}
