package teacherDashboard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.GeneralUIMethods;
import util.Navigator;

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
	private Node questionBank;
	private Node login;
	private int menuMovementLeftToRight = 1280 - 283 + 1;

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
			testBank = FXMLLoader.load(getClass().getResource(Navigator.TEST_BANK.getVal()));
			contentPaneAnchor.getChildren().setAll(testBank);
			setMenuStyle(testBankBtn);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * clicking question bank will open the question bank page.
	 *
	 * @param event
	 */
	@FXML
	void clickQuestionBank(MouseEvent event) {
		try {
			questionBank = FXMLLoader.load(getClass().getResource(Navigator.QUESTION_BANK.getVal()));
			contentPaneAnchor.getChildren().setAll(questionBank);
			setMenuStyle(questionBankBtn);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * clicking sign out will go back to the login screen
	 * 
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	void clickSignOut(MouseEvent event) throws IOException {	// this method is not finished!
		
		login = FXMLLoader.load(getClass().getResource(Navigator.LOGIN.getVal()));
		anchorLogin.getChildren().setAll(login);
		
		GeneralUIMethods.moveItem(menuVBox, menuMovementLeftToRight, 1, (e) ->{
			darkModeToggleBtn.setVisible(false);
			teacherDashboardLbl.setVisible(false);
			questionBankBtn.setVisible(false);
			testBankBtn.setVisible(false);
			viewActiveTestsBtn.setVisible(false);
			scheduledTestsBtn.setVisible(false);
			testReportsBtn.setVisible(false);
			checkTestsBtn.setVisible(false);
			signOutBtn.setVisible(false);
		});
				
		
		/*
		GeneralUIMethods.moveItem(usernameTxt, 0, 0.55, (e) -> {
			usernameTxt.setVisible(true);
		});
		GeneralUIMethods.moveItem(passwordTxt, 0, 0.55, (e) -> {
			passwordTxt.setVisible(true);
		});
		GeneralUIMethods.moveItem(welcomeLbl, 0, 0.55, (e) -> {
			welcomeLbl.setVisible(true);
		});
		GeneralUIMethods.moveItem(loginBtn, 0, 0.55, (e) -> {
			loginBtn.setVisible(true);
		});*/
	}

	/**
	 * clicking a button on the menu will paint the background and remove the color
	 * from all other buttons on the menu.
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
