package loginScreen;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.GeneralUIMethods;
import util.Navigator;

public class LoginUIController {

	@FXML
	private JFXTextField usernameTxt;

	@FXML
	private JFXPasswordField passwordTxt;

	@FXML
	private JFXButton loginBtn;

	@FXML
	private Label welcomeLbl;

	@FXML
	private VBox menuVBox;

	@FXML
	private AnchorPane anchorLogin;

	private Node dashBoard;
	private String TEACHER = "t";// ---------need to receive the string from DB---------
	private String STUDENT = "s";// ---------need to receive the string from DB---------
	private String PRINCIPLE = "p";// ---------need to receive the string from DB---------

	private static final int menuMovementRightToLeft = -1280 + 283 - 1;
	
	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.LOGIN.getVal()));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setTitle("CEMS");
		stage.setResizable(false);
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * clicking login with a correct user name and password will open the relevant
	 * menu according to the user's permissions.
	 * 
	 * @param event
	 */
	@FXML
	void clickLogin(Event event) {
		ClientController.accept("LOGIN-" + usernameTxt.getText() + ", " + passwordTxt.getText());
		
		if (!GeneralUIMethods.validateCode(usernameTxt, TEACHER) && !GeneralUIMethods.validateCode(usernameTxt, STUDENT)
				&& !GeneralUIMethods.validateCode(usernameTxt, PRINCIPLE))
			return;
		/*------------------------------------------------------------------------------------------------
		 * need to add here a test: which user entered the system
		 * moveItem's lambda expression will change according to the user's permissions
		 * using switch case: 1- teacher, 2- student, 3- principle
		 * ------------------------------------------------------------------------------------------------*/
		GeneralUIMethods.moveItem(menuVBox, menuMovementRightToLeft, 1, (e) -> {
			try {
				if (GeneralUIMethods.validateCode(usernameTxt, TEACHER)) {
					dashBoard = FXMLLoader.load(getClass().getResource(Navigator.TEACHER_DASHBOARD.getVal()));
				} else if (GeneralUIMethods.validateCode(usernameTxt, PRINCIPLE)) {
					dashBoard = FXMLLoader.load(getClass().getResource(Navigator.PRINCIPLE_DASHBOARD.getVal()));
				} else if (GeneralUIMethods.validateCode(usernameTxt, STUDENT)) {
					dashBoard = FXMLLoader.load(getClass().getResource(Navigator.STUDENT_DASHBOARD.getVal()));
				}
				anchorLogin.getChildren().setAll(dashBoard);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		GeneralUIMethods.moveItem(usernameTxt, 0, 0.55, (e) -> {
			usernameTxt.setVisible(false);
		});
		GeneralUIMethods.moveItem(passwordTxt, 0, 0.55, (e) -> {
			passwordTxt.setVisible(false);
		});
		GeneralUIMethods.moveItem(welcomeLbl, 0, 0.55, (e) -> {
			welcomeLbl.setVisible(false);
		});
		GeneralUIMethods.moveItem(loginBtn, 0, 0.55, (e) -> {
			loginBtn.setVisible(false);
		});
	}

}
