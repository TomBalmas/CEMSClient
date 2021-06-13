package loginScreen;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class LoginUIController implements Initializable {
    @FXML
    private AnchorPane anchorLogin;

    @FXML
    private AnchorPane whiteAnchor;

    @FXML
    private Label welcomeLbl;

    @FXML
    private FontAwesomeIconView userIcon;

    @FXML
    private JFXTextField usernameTxt;

    @FXML
    private FontAwesomeIconView lockIcon;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private VBox menuVBox;

    @FXML
    private StackPane popupStackPane;

    
	private Node dashBoard;
    boolean first_active = true;
	private static final int menuMovementRightToLeft = -1280 + 283 - 1;

	public void start(Stage stage) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.LOGIN.getVal()));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add("util/Style.css");
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
		GeneralUIMethods.setPopupPane(popupStackPane);
		GeneralUIMethods.setSideBar(menuVBox);
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(usernameTxt);
		nodes.add(passwordTxt);
		if (GeneralUIMethods.checkEmptyFields(nodes)) {
			new PopUp(PopUp.TYPE.LOGIN, "Error", "Some fields are missing", null, null, null);
			return;
		}
		ClientController.accept("LOGIN-" + usernameTxt.getText() + "," + passwordTxt.getText());
		String role = ClientController.getRoleFrame();
		String error = null;
		if (!role.equals("null") && !role.equals("userAlreadyConnected")) {
			GeneralUIMethods.moveItem(menuVBox, menuMovementRightToLeft, 1, (e) -> {
				try {
					if (role.equals("Teacher")) {
						dashBoard = FXMLLoader.load(getClass().getResource(Navigator.TEACHER_DASHBOARD.getVal()));
					} else if (role.equals("Principle")) {
						dashBoard = FXMLLoader.load(getClass().getResource(Navigator.PRINCIPLE_DASHBOARD.getVal()));
					} else if (role.equals("Student")) {
						dashBoard = FXMLLoader.load(getClass().getResource(Navigator.STUDENT_DASHBOARD.getVal()));
					}
					anchorLogin.getChildren().setAll(dashBoard);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
			GeneralUIMethods.moveItem(usernameTxt, 0, 0.45, (e) -> {
				whiteAnchor.setVisible(false);
			});
		} else {
			if (role.equals("userAlreadyConnected"))
				error = "User is already connected.";
			else
				error = "Wrong username or password.";
			new PopUp(PopUp.TYPE.LOGIN, "Error!", error, null, null, null);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		GeneralUIMethods.setMainAnchorPane(anchorLogin);
	}

}
