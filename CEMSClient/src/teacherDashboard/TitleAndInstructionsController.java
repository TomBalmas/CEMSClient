package teacherDashboard;

import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * Class for the title and instructions used in the test form
 *
 */
public class TitleAndInstructionsController {

	@FXML
	private Label testTitleLbl;

	@FXML
	private Label instructionsLbl;

	@FXML
	private JFXTextArea instructionsTxtArea;

	public Label getTestTitleLbl() {
		return testTitleLbl;
	}

	public Label getInstructionsLbl() {
		return instructionsLbl;
	}

	public JFXTextArea getInstructionsTxtArea() {
		return instructionsTxtArea;
	}

}
