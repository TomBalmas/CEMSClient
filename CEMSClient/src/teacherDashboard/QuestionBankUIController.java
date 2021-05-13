package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class QuestionBankUIController {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private JFXButton addAnewQuestionBtn;

	@FXML
	private Label questionBankLbl;

	@FXML
	private JFXTreeTableView<?> questionBankTable;

	private Node blankQuestionForm;

	/**
	 * clicking add a new question will go to blank question for page.
	 * 
	 * @param event
	 */
	@FXML

	void clickAddAnewQuestion(MouseEvent event) {
		try {
			blankQuestionForm = FXMLLoader.load(getClass().getResource(Navigator.BLANK_QUESTION_FORM.getVal()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, blankQuestionForm);
	}

	public JFXButton getAddAnewQuestionBtn() {
		return addAnewQuestionBtn;
	}

}