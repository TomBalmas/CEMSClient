package loginScreen;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import util.Navigator;

public class LoginUIController{

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

	private Node teacherDashboard;

	/**
	 * clicking login with a correct user name and password will open the relevant
	 * menu according to the user's permissions.
	 * 
	 * @param event
	 */
	@FXML
	void clickLogin(MouseEvent event) {
		/*------------------------------------------------------------------------------------------------
		 * need to add here a test: which user entered the system
		 * moveItem's lambda expression will change according to the user's permissions
		 * using switch case: 1- teacher, 2- student, 3- principle
		 * ------------------------------------------------------------------------------------------------*/
		moveItem(menuVBox, -900 + 283 - 1, 1, (e) -> {
			try {
				teacherDashboard = FXMLLoader.load(getClass().getResource(Navigator.TEACHERDASHBOARD.getVal()));
				anchorLogin.getChildren().setAll(teacherDashboard);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});

		moveItem(usernameTxt, 0, 0.55, (e) -> {
			usernameTxt.setVisible(false);
		});
		moveItem(passwordTxt, 0, 0.55, (e) -> {
			passwordTxt.setVisible(false);
		});
		moveItem(welcomeLbl, 0, 0.55, (e) -> {
			welcomeLbl.setVisible(false);
		});
		moveItem(loginBtn, 0, 0.55, (e) -> {
			loginBtn.setVisible(false);
		});
	}

	/**
	 * moves element on the screen "layoutX" pixels in "time" seconds.
	 * 
	 * @param o
	 * @param layoutX
	 * @param time
	 */
	private void moveItem(Object o, double layoutX, double time, EventHandler<ActionEvent> e) {
		TranslateTransition t = new TranslateTransition(Duration.seconds(time), (Node) o);
		t.setToX(layoutX);
		t.play();
		t.setOnFinished(e);
	}

}
