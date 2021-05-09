package teacherDashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class teacherDashboardUIController {

    @FXML
    private VBox menuVBox;

    @FXML
    private JFXToggleButton darkModeToggleBtn;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label teacherDashboardLbl;

    @FXML
    private JFXButton setDateForTestsBtn;

    @FXML
    private JFXButton viewActiveTestsBtn;

    @FXML
    private JFXButton testBankBtn;

    @FXML
    private JFXButton questionBankBtn;

    @FXML
    private JFXButton testReportsBtn;

    @FXML
    private JFXButton scheduledTestsBtn;

    @FXML
    private JFXButton signOutBtn;

}
