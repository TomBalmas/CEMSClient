package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

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
import teacherDashboard.BankUIController;
import teacherDashboard.QuestionBankUIController;
import util.GeneralUIMethods;
import util.Navigator;

public class PrincipleDashboardUIController implements Initializable {

	@FXML
	private AnchorPane anchorLogin;

	@FXML
	private VBox menuVBox;

	@FXML
	private JFXToggleButton darkModeToggleBtn;

	@FXML
	private ImageView logoImg;

	@FXML
	private Label teacherDashboardLbl;

	@FXML
	private JFXButton activeTestRequestsBtn;

	@FXML
	private JFXButton viewReportsBtn;

	@FXML
	private JFXButton createReportBtn;

	@FXML
	private JFXButton testBankBtn;

	@FXML
	private JFXButton questionBankBtn;

	@FXML
	private JFXButton signOutBtn;

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private StackPane error;

	private Node viewReports;
	private Node createReport;
	private Node activeTests;
	private Node login;
	private Node questionBank;
	private FXMLLoader questionBankLoader;
	private Node testBank;
	private FXMLLoader testBankLoader;

	@FXML
	void activeTestRequestClicked(MouseEvent event) {

		try {
			contentPaneAnchor.getChildren().clear();
			activeTests = FXMLLoader.load(getClass().getResource(Navigator.ACTIVE_TESTS.getVal()));
			contentPaneAnchor.getChildren().addAll(activeTests);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * load create report page
	 *
	 */
	@FXML
	void createReportClicked(MouseEvent event) {
		GeneralUIMethods.loadPage(contentPaneAnchor, createReport);
		GeneralUIMethods.setMenuStyle(createReportBtn, menuVBox);
	}

	/**
	 * load question bank page
	 *
	 */
	@FXML
	void questionBankClicked(MouseEvent event) {
		GeneralUIMethods.loadPage(contentPaneAnchor, questionBank);
		GeneralUIMethods.setMenuStyle(questionBankBtn, menuVBox);
		QuestionBankUIController controller = questionBankLoader.getController();
		controller.getAddAnewQuestionBtn().setVisible(false);
	}

	/**
	 * load test bank page
	 *
	 */
	@FXML
	void testBankClicked(MouseEvent event) {
		GeneralUIMethods.loadPage(contentPaneAnchor, testBank);
		GeneralUIMethods.setMenuStyle(testBankBtn, menuVBox);
		BankUIController controller = testBankLoader.getController();
		controller.getAddNewTestButton().setVisible(false);
	}

	/**
	 * load view reports page
	 *
	 */
	@FXML
	void viewReportsClicked(MouseEvent event) {
		GeneralUIMethods.loadPage(contentPaneAnchor, viewReports);
		GeneralUIMethods.setMenuStyle(viewReportsBtn, menuVBox);
	}

	/**
	 * clicking sign out will go back to the login screen
	 * 
	 * @param event
	 */
	@FXML
	void signOutClicked(MouseEvent event) {
		GeneralUIMethods.signOut(contentPaneAnchor, anchorLogin, menuVBox, login);
	}

	/**
	 * initializes all the FXML files for easier access.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			viewReports = FXMLLoader.load(getClass().getResource(Navigator.VIEW_REPORTS.getVal()));
			createReport = FXMLLoader.load(getClass().getResource(Navigator.CREATE_REPORT.getVal()));
			login = FXMLLoader.load(getClass().getResource(Navigator.LOGIN.getVal()));
			questionBankLoader = new FXMLLoader(getClass().getResource(Navigator.QUESTION_BANK.getVal()));
			questionBank = questionBankLoader.load();
			testBankLoader = new FXMLLoader(getClass().getResource(Navigator.TEST_BANK.getVal()));
			testBank = testBankLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
