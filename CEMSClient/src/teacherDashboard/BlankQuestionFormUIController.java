package teacherDashboard;

import java.awt.event.MouseEvent;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import util.Navigator;

public class BlankQuestionFormUIController {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private Label newQuestionFormLbl;

	@FXML
	private JFXTextArea questionContentTxt;

	@FXML
	private Label chooseAnswersLbl;

	@FXML
	private JFXButton backBtn;

	@FXML
	private JFXButton saveBtn;

	@FXML
	private JFXRadioButton answer1Btn;

	@FXML
	private JFXTextArea answer1Txt;

	@FXML
	private JFXRadioButton answer2Btn;

	@FXML
	private JFXTextArea answer2Txt;

	@FXML
	private JFXRadioButton answer3Btn;

	@FXML
	private JFXTextArea answer3Txt;

	@FXML
	private JFXRadioButton answer4Btn;

	@FXML
	private JFXTextArea answer4Txt;
	
	private Node questionBank;

	
    @FXML
    void setCorrectAnswer(MouseEvent event) {
    	
    }

	@FXML
	void clickBack() {
		try {
			questionBank = FXMLLoader.load(getClass().getResource(Navigator.QUESTION_BANK.getVal()));
			contentPaneAnchor.getChildren().setAll(questionBank);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
