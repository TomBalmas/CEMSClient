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
import teacherDashboard.TestBankUIController;
import teacherDashboard.QuestionBankUIController;
import util.GeneralUIMethods;
import util.Navigator;

public class PrincipleDashboardUIController implements Initializable {

    @FXML
    private AnchorPane anchorLogin;

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private VBox menuVBox;

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
    private JFXToggleButton darkModeToggleBtn;

    @FXML
    private JFXButton signOutBtn;

    @FXML
    private StackPane popUpWindow;

	private Node viewReports;
	private Node createReport;
	private Node activeTests;
	private Node login;
	private Node questionBank;
	private FXMLLoader questionBankLoader;
	private Node testBank;
	private FXMLLoader testBankLoader;

	/**
	 * load active test page
	 * @throws IOException 
	 *
	 */
	
	@FXML
	void activeTestRequestClicked(MouseEvent event) {

		try {
			contentPaneAnchor.getChildren().clear();
			activeTests = FXMLLoader.load(getClass().getResource(Navigator.ACTIVE_TESTS.getVal()));
			contentPaneAnchor.getChildren().addAll(activeTests);
			GeneralUIMethods.setMenuStyle(activeTestRequestsBtn,menuVBox);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * load create report page
	 * @throws IOException 
	 *
	 */
	@FXML
	void createReportClicked(MouseEvent event) throws IOException {
		createReport = FXMLLoader.load(getClass().getResource(Navigator.CREATE_REPORT.getVal()));
		GeneralUIMethods.loadPage(contentPaneAnchor, createReport);
		GeneralUIMethods.setMenuStyle(createReportBtn, menuVBox);
	}

	/**
	 * load question bank page
	 * @throws IOException 
	 *
	 */
	@FXML
	void questionBankClicked(MouseEvent event) throws IOException {
		questionBankLoader = new FXMLLoader(getClass().getResource(Navigator.QUESTION_BANK.getVal()));
		questionBank = questionBankLoader.load();
		GeneralUIMethods.loadPage(contentPaneAnchor, questionBank);
		GeneralUIMethods.setMenuStyle(questionBankBtn, menuVBox);
		QuestionBankUIController controller = questionBankLoader.getController();
		controller.getAddAnewQuestionBtn().setVisible(false);
	}

	/**
	 * load test bank page
	 * @throws IOException 
	 *
	 */
	@FXML
	void testBankClicked(MouseEvent event) throws IOException {
		testBankLoader = new FXMLLoader(getClass().getResource(Navigator.TEST_BANK.getVal()));
		testBank = testBankLoader.load();
		GeneralUIMethods.loadPage(contentPaneAnchor, testBank);
		GeneralUIMethods.setMenuStyle(testBankBtn, menuVBox);
		TestBankUIController controller = testBankLoader.getController();
		controller.getAddNewTestButton().setVisible(false);
	}

	/**
	 * load view reports page
	 * @throws IOException 
	 *
	 */
	@FXML
	void viewReportsClicked(MouseEvent event) throws IOException {
		viewReports = FXMLLoader.load(getClass().getResource(Navigator.VIEW_REPORTS.getVal()));
		GeneralUIMethods.loadPage(contentPaneAnchor, viewReports);
		GeneralUIMethods.setMenuStyle(viewReportsBtn, menuVBox);
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
	public void initialize(URL location, ResourceBundle resources) {
		GeneralUIMethods.setPopupPane(popUpWindow);
		GeneralUIMethods.setSideBar(menuVBox);
	}

}
