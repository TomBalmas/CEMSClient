package teacherDashboard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Navigator;

/**
 * @author Toosick22
 *
 */
/**
 * @author Toosick22
 *
 */
public class TeacherDashboardUIController {

	@FXML
	private VBox menuVBox;

	@FXML
	private JFXToggleButton darkModeToggleBtn;

	@FXML
	private ImageView logoImg;

	@FXML
	private Label teacherDashboardLbl;

	@FXML
	private JFXButton testBankBtn;

	@FXML
	private JFXButton questionBankBtn;

	@FXML
	private JFXButton viewActiveTestsBtn;

	@FXML
	private JFXButton scheduledTestsBtn;

	@FXML
	private JFXButton testReportsBtn;

	@FXML
	private JFXButton checkTestsBtn;

	@FXML
	private JFXButton signOutBtn;

	@FXML
	private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane anchorLogin;

	private Node testBank;

	/**
	 * clicking test bank will open the test bank page.
	 * 
	 * @param event
	 */
	@FXML
    void clickTestBank(MouseEvent event) {
		/*------------------------------------------------------------------------------------------------
		 * need to add query that will show the tests of the teacher's teaching fields only.
		 * ------------------------------------------------------------------------------------------------*/
		try {
			testBank = FXMLLoader.load(getClass().getResource(Navigator.TESTBANK.getVal()));
			contentPaneAnchor.getChildren().setAll(testBank);
			setMenuStyle(testBankBtn);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**clicking a button on the menu will paint the background
	 * and remove the color from all other buttons on the menu.
	 * 
	 * @param b
	 */
	private void setMenuStyle(JFXButton b) {
		testBankBtn.setStyle("");
		questionBankBtn.setStyle("");
		viewActiveTestsBtn.setStyle("");
		scheduledTestsBtn.setStyle("");
		testReportsBtn.setStyle("");
		checkTestsBtn.setStyle("");
		b.setStyle("-fx-background-color:#00ADB5;");
	}
}
