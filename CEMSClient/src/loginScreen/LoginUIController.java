package loginScreen;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

	/**
	 * upon clicking login with a correct user name and password
	 * 
	 * @param event
	 */
	@FXML
	void clickLogin(MouseEvent event) {
		moveItem(menuVBox, -900 + 283 - 1, 1, null);

		moveItem(usernameTxt, 0, 0.55, (e)->{usernameTxt.setVisible(false);});
		moveItem(passwordTxt, 0, 0.55, (e)->{passwordTxt.setVisible(false);});
		moveItem(welcomeLbl, 0, 0.55, (e)->{welcomeLbl.setVisible(false);});
		moveItem(loginBtn, 0, 0.55, (e)->{loginBtn.setVisible(false);});

	}

	/**
	 * moves an element on the screen to location x
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
