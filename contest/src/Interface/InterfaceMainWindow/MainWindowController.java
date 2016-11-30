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

    public MainWindowController() {}

    public void initialize(Parent departmentsParent, Parent candidatesParent){
        this.candidatesPane.getChildren().setAll(candidatesParent);
        this.departmentsPane.getChildren().setAll(departmentsParent);
    }
}
