package studentDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ActiveTestController implements Initializable {

	@FXML
	private Label testTitleLbl;

	@FXML
	private JFXTextArea instTxt;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private Label timeLbl;

	@FXML
	private JFXButton downloadBtn;

	@FXML
	private JFXButton uploadBtn;

	@FXML
	private JFXButton finishBtn;

	private VBox vbox = new VBox();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		vbox.setSpacing(20);
		addQuestionToTest();
		addQuestionToTest();
		addQuestionToTest();
	}

	/**
	 * this function adds question to the active test
	 *
	 */
	public void addQuestionToTest() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Question.fxml"));
			Node question;
			question = loader.load();
			QuestionController controller = loader.getController();
			vbox.getChildren().add(question);
			scrollPane.setContent(vbox);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}