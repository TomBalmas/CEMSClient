package teacherDashboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LockTestController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private Label testNameLbl;

	@FXML
	private JFXTextField codeTxt;

	@FXML
	private JFXButton confirmTestLock;
	
	public AnchorPane getContentPaneAnchor() {
		return contentPaneAnchor;
	}

	public Label getTestNameLbl() {
		return testNameLbl;
	}


	public JFXTextField getCodeTxt() {
		return codeTxt;
	}


	public JFXButton getConfirmTestLock() {
		return confirmTestLock;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}