package Interface.InterfaceMainWindow;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 * Created by Sebi on 30-Nov-16.
 */
public class MainWindowController {
    /*
    Widgets used
     */
    @FXML
    AnchorPane candidatesPane;
    @FXML
    AnchorPane departmentsPane;
    @FXML
    AnchorPane optionsPane;
    @FXML
    AnchorPane reportsPane;

    public MainWindowController() {}

    public void initialize(Parent departmentsParent, Parent candidatesParent, Parent optionParent, Parent reportsParent){
        this.candidatesPane.getChildren().setAll(candidatesParent);
        this.departmentsPane.getChildren().setAll(departmentsParent);
        this.optionsPane.getChildren().setAll(optionParent);
        this.reportsPane.getChildren().setAll(reportsParent);
    }
}
