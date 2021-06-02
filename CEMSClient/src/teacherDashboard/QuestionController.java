package teacherDashboard;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class QuestionController {

    @FXML
    private AnchorPane questionAnchor;
    
	@FXML
	private Label questionNumLbl;

	@FXML
	private JFXTextArea contantTxt;

	@FXML
	private Label pointsLbl;
	
    @FXML
    private VBox questionsVBox;

	@FXML
	private JFXRadioButton answer1Btn;

	@FXML
	private JFXRadioButton answer2Btn;

	@FXML
	private JFXRadioButton answer3Btn;

	@FXML
	private JFXRadioButton answer4Btn;

	@FXML
	private JFXTextArea teacherNotesTxt;

	@FXML
	private JFXTextArea studentNotesTxt;
	
	final ToggleGroup group = new ToggleGroup();

	public ToggleGroup getGroup() {
		return group;
	}
	
	public AnchorPane getQuestionAnchor() {
		return questionAnchor;
	}

	public Label getQuestionNumLbl() {
		return questionNumLbl;
	}

	public JFXTextArea getContantTxt() {
		return contantTxt;
	}

	public Label getPointsLbl() {
		return pointsLbl;
	}
	
	public VBox getQuestionsVBox() {
		return questionsVBox;
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

	public JFXTextArea getTeacherNotesTxt() {
		return teacherNotesTxt;
	}

	public JFXTextArea getStudentNotesTxt() {
		return studentNotesTxt;
	}
}