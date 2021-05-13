package teacherDashboard;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CheckTestsUIController {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private Label checkTestLbl;

    @FXML
    private JFXTreeTableView<?> testTbl;

    @FXML
    private Label serachLbl;

    @FXML
    private JFXTextArea searchTxt;

    @FXML
    private Label serachLbl1;

    @FXML
    private Label serachLbl2;

    @FXML
    private JFXTextArea testCodeTxt;

    @FXML
    private JFXButton submitBtn;

    @FXML
    void clickSubmit(MouseEvent event) {

    }

}
