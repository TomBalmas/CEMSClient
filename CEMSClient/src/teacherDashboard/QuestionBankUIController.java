package teacherDashboard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Navigator;

public class QuestionBankUIController {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private JFXButton addAnewQuestionBtn;

	@FXML
	private Label questionBankLbl;
	private Node blankQuestionForm;
	@FXML
	private JFXTreeTableView<?> questionBankTable;

	/**
	 * clicking add a new question will go to blank question for page.
	 * 
	 * @param event
	 */
	@FXML

	void clickAddAnewQuestion(MouseEvent event) {
		try {
			blankQuestionForm = FXMLLoader.load(getClass().getResource(Navigator.BLANK_QUESTION_FORM.getVal()));
			contentPaneAnchor.getChildren().setAll(blankQuestionForm);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public JFXButton getAddAnewQuestionBtn() {
		return addAnewQuestionBtn;
	}

}
