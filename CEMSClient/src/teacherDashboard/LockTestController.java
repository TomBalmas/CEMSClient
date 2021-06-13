package teacherDashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class LockTestController {

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

}