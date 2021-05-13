package studentDashboard;

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
import util.GeneralUIMethods;
import util.Navigator;

public class StudentDashboardUIController implements Initializable {

	@FXML
	private VBox menuVBox;

	@FXML
	private JFXToggleButton darkModeToggleBtn;

	@FXML
	private ImageView logoImg;

	@FXML
	private Label studentDashboardLbl;

	@FXML
	private JFXButton gradesBtn;

	@FXML
	private JFXButton takeTestBtn;

	@FXML
	private JFXButton signOutBtn;

	@FXML
	private AnchorPane anchorLogin;

	@FXML
	private AnchorPane contentPaneAnchor;
	
	@FXML
	private StackPane popUpWindow;

	private Node grades;
	private Node takeTest;
	private Node login;

	/**
	 * clicking grades will open the grades page.
	 * 
	 * @param event
	 */
	@FXML
	void gradesPage(MouseEvent event) {
		GeneralUIMethods.loadPage(contentPaneAnchor, grades);
		GeneralUIMethods.setMenuStyle(gradesBtn, menuVBox);
	}

	@FXML
	void takeTestPage(MouseEvent event) {
		GeneralUIMethods.loadPage(contentPaneAnchor, takeTest);
		GeneralUIMethods.setMenuStyle(takeTestBtn, menuVBox);
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
	 *initializes all the FXML files for easier access.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		GeneralUIMethods.setPopupStackPane(popUpWindow);
		try {
			grades = FXMLLoader.load(getClass().getResource(Navigator.GRADES.getVal()));
			takeTest = FXMLLoader.load(getClass().getResource(Navigator.STUDENT_TAKE_TEST.getVal()));
			login = FXMLLoader.load(getClass().getResource(Navigator.LOGIN.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
