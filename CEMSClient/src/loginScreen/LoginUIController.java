package loginScreen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.GeneralUIMethods;
import util.Navigator;

public class LoginUIController {

    @FXML
    private AnchorPane anchorLogin;

	@FXML
    private StackPane popupStackPane;

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
	private Node dashBoard;
	private String TEACHER = "t";// ---------need to receive the string from DB---------
	private String STUDENT = "s";// ---------need to receive the string from DB---------
	private String PRINCIPLE = "p";// ---------need to receive the string from DB---------

	private static final int menuMovementRightToLeft = -1280 + 283 - 1;

	/**
	 * clicking login with a correct user name and password will open the relevant
	 * menu according to the user's permissions.
	 * 
	 * @param event
	 * @throws InterruptedException 
	 */
	@FXML
	void clickLogin(Event event) throws InterruptedException {
		List<JFXButton> list = new ArrayList<JFXButton>();
		list.add(new JFXButton("Okay"));
		util.PopUp.showMaterialDialog(popupStackPane, null, menuVBox, list, "Question Saved",
				"question Id: " + "blah");
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
				else {

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
