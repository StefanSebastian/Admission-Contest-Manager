package Interface.Charts;

import Domain.DepartmentCandidates;
import Interface.InterfaceReports.ReportsViewController;
import Utils.Observer;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.stage.Stage;

/**
 * Created by Sebi on 02-Jan-17.
 */
public class ReportBarChart implements Observer {
    //view controller for reports - gives report data
    private ReportsViewController reportsViewController;

    //data used in bar chart
    private XYChart.Series dataSeries;

    //pie chart reference
    private BarChart chart;
    private CategoryAxis xAxis;
    private NumberAxis yAxis;

    //scene reference
    private Scene scene;

    public ReportBarChart(ReportsViewController reportsViewController){
        this.reportsViewController = reportsViewController;

        //create a new window
        Stage stage = new Stage();
        stage.setTitle("Report chart");
        scene = new Scene(new Group());
        stage.setWidth(500);
        stage.setHeight(500);

        createChart();
        setData();

        chart.getData().add(dataSeries);

        //show window
        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }

    /*
    Creates a new chart
     */
    private void createChart(){
        //create chart
        xAxis = new CategoryAxis();
        xAxis.setLabel("Departments");
        yAxis = new NumberAxis();
        yAxis.setLabel("Number of candidates");
        chart = new BarChart(xAxis, yAxis);
    }

    /*
   Updates the data displayed in pie chart
    */
    private void setData() {
        dataSeries = new XYChart.Series();
        //get data
        for (DepartmentCandidates departmentCandidates : reportsViewController.getCurrentReportAsList()){
            dataSeries.getData().add(new XYChart.Data(departmentCandidates.getDepartment().getName(),
                    departmentCandidates.getCandidateList().size()));
        }
    }

    @Override
    public void update() {
        setData();
        createChart();
        chart.getData().add(dataSeries);
        ((Group) scene.getRoot()).getChildren().clear();
        ((Group) scene.getRoot()).getChildren().add(chart);
    }

    @Override
    public void pushUpdate(Object o) {

    }
}
