package teacherDashboard;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;

public class RequestTimeExtensionUIController {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private Label studentsTestLbl;

    @FXML
    private JFXTextArea reasonForRequestTxt;

    @FXML
    private JFXButton senfForApprovalBtn;
    
    AnchorPane selectedTest;

	public AnchorPane getSelectedTest() {
		return selectedTest;
	}

	public void setSelectedTest(AnchorPane selectedTest) {
		this.selectedTest = selectedTest;
	}

	/**
	 * this method shows the popup that the request for time extension is approved
	 * need to connect to active test screen
	 */
	@FXML
	void clicksenfForApproval(MouseEvent event) {
		List<JFXButton> l = new ArrayList<JFXButton>();
		l.add(new JFXButton("Okay"));
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupPane(), contentPaneAnchor, GeneralUIMethods.getSideBar(),
				l, "Request sent for principles approval", "");
	}
}
