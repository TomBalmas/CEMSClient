package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import client.ClientController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.GeneralUIMethods;
import util.Navigator;

public class TeacherDashboardUIController implements Initializable {

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

	@FXML
	private StackPane popUpWindow;

	private Node testBank;
	private Node questionBank;
	private Node activeTests;
	private Node login;
	private Node checkTests;
	private Node scheduledTests;

	/**
	 * clicking test bank will open the test bank page.
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void testBankClicked(MouseEvent event) throws IOException {
		testBank = FXMLLoader.load(getClass().getResource(Navigator.TEST_BANK.getVal()));
		contentPaneAnchor.getChildren().clear();
		GeneralUIMethods.loadPage(contentPaneAnchor, testBank);

		GeneralUIMethods.setMenuStyle(testBankBtn, menuVBox);
	}

	/**
	 * clicking question bank will open the question bank page.
	 *
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void questionBankClicked(MouseEvent event) throws IOException {
		questionBank = FXMLLoader.load(getClass().getResource(Navigator.QUESTION_BANK.getVal()));
		GeneralUIMethods.loadPage(contentPaneAnchor, questionBank);
		GeneralUIMethods.setMenuStyle(questionBankBtn, menuVBox);
	}

	/**
	 * clicking view active test will open the active test page.
	 *
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void viewActiveTestClicked(MouseEvent event) throws IOException {
		activeTests = FXMLLoader.load(getClass().getResource(Navigator.VIEW_ACTIVE_TESTS.getVal()));
		GeneralUIMethods.loadPage(contentPaneAnchor, activeTests);
		GeneralUIMethods.setMenuStyle(viewActiveTestsBtn, menuVBox);
	}

	/**
	 * clicking check tests will go to the check tests screen
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void checkTestsClicked(MouseEvent event) throws IOException {
		checkTests = FXMLLoader.load(getClass().getResource(Navigator.CHECK_TESTS.getVal()));
		GeneralUIMethods.loadPage(contentPaneAnchor, checkTests);
		GeneralUIMethods.setMenuStyle(checkTestsBtn, menuVBox);

	}

	@FXML
	void testReportsClicked(MouseEvent event) throws IOException {
		checkTests = FXMLLoader.load(getClass().getResource(Navigator.VIEW_REPORTS.getVal()));
		GeneralUIMethods.loadPage(contentPaneAnchor, checkTests);
		GeneralUIMethods.setMenuStyle(testReportsBtn, menuVBox);

	}

	@FXML
	void scheduledTestsClicked(MouseEvent event) throws IOException {
		scheduledTests = FXMLLoader.load(getClass().getResource(Navigator.SCHEDULED_TESTS.getVal()));
		GeneralUIMethods.loadPage(contentPaneAnchor, scheduledTests);
		GeneralUIMethods.setMenuStyle(scheduledTestsBtn, menuVBox);
	}

	/**
	 * clicking sign out will go back to the login screen
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void signOutClicked(MouseEvent event) throws IOException {
		login = FXMLLoader.load(getClass().getResource(Navigator.LOGIN.getVal()));
		GeneralUIMethods.signOut(contentPaneAnchor, anchorLogin, menuVBox, login);
		GeneralUIMethods.closeConnection();
	}

	/**
	 * initializes all the FXML files for easier access.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GeneralUIMethods.setPopupPane(popUpWindow);
		GeneralUIMethods.setSideBar(menuVBox);
		popUpWindow.toBack();
	}
}
