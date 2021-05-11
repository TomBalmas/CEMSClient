package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
	private Node login;


	@FXML
	void activeTestRequestClicked(MouseEvent event) {

	}

	@FXML
	void createReportClicked(MouseEvent event) {
		GeneralUIMethods.loadPage(contentPaneAnchor, createReport);
		GeneralUIMethods.setMenuStyle(createReportBtn, menuVBox);
	}

	@FXML
	void questionBankClicked(MouseEvent event) {
		List<JFXButton> l = new ArrayList<JFXButton>();
		l.add(new JFXButton("Okay"));
		l.add(new JFXButton("Cancel"));
		util.popUp.showMaterialDialog(error, contentPaneAnchor, l, "Test", "test");
	}

	@FXML
	void testBankClicked(MouseEvent event) {

	}

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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
