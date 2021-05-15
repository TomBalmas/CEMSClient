package studentDashboard;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QuestionController {

    @FXML
    private Label questionNumLbl;

    @FXML
    private JFXTextArea contantTxt;

    @FXML
    private Label pointsLbl;

    
	@FXML
    private JFXRadioButton answer1Btn;

    @FXML
    private JFXRadioButton answer2Btn;

    @FXML
    private JFXRadioButton answer3Btn;

    @FXML
    private JFXRadioButton answer4Btn;

    @FXML
    private JFXTextArea studentNotesTxt;
    public Label getQuestionNumLbl() {
		return questionNumLbl;
	}

	public JFXTextArea getContantTxt() {
		return contantTxt;
	}

	public Label getPointsLbl() {
		return pointsLbl;
	}

	public JFXRadioButton getAnswer1Btn() {
		return answer1Btn;
	}

	public JFXRadioButton getAnswer2Btn() {
		return answer2Btn;
	}

	public JFXRadioButton getAnswer3Btn() {
		return answer3Btn;
	}

	public JFXRadioButton getAnswer4Btn() {
		return answer4Btn;
	}

	public JFXTextArea getStudentNotesTxt() {
		return studentNotesTxt;
	}


}
