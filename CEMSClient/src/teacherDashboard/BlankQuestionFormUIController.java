package teacherDashboard;

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

	// need to add a method that allows to select only one answer at a time
	private void setCorrectAnswer(JFXButton b) {
		b.setStyle("-fx-background-color:#00ADB5;");
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
