package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import client.ClientController;
import common.Teacher;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class QuestionFormUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private JFXButton backBtn;

	@FXML
	private JFXButton saveBtn;

	@FXML
	private AnchorPane insideContentAnchor;

	@FXML
	private AnchorPane questionAnchor;

	@FXML
	private Label chooseAnswersLbl11;

	@FXML
	private JFXComboBox<String> fieldCBox;

	@FXML
	private Label newQuestionFormLbl;

	@FXML
	private Label chooseAnswersLbl1;

	@FXML
	private JFXTextArea questionContentTxt;

	@FXML
	private AnchorPane questionsTxtAnchor;

	@FXML
	private Label chooseAnswersLbl;

	@FXML
	private JFXRadioButton answer1Btn;

	@FXML
	private JFXRadioButton answer2Btn;

	@FXML
	private JFXRadioButton answer3Btn;

	@FXML
	private JFXRadioButton answer4Btn;

	@FXML
	private Label correctAnswer3Lbl;

	@FXML
	private Label correctAnswer2Lbl;

	@FXML
	private Label correctAnswer1Lbl;

	@FXML
	private Label correctAnswer4Lbl;

	@FXML
	private JFXTextArea answer4Txt;

	@FXML
	private JFXTextArea answer3Txt;

	@FXML
	private JFXTextArea answer2Txt;

	@FXML
	private JFXTextArea answer1Txt;

	public AnchorPane getContentPaneAnchor() {
		return contentPaneAnchor;
	}

	public AnchorPane getQuestionsTxtAnchor() {
		return questionsTxtAnchor;
	}

	public AnchorPane getInsideContentAnchor() {
		return insideContentAnchor;
	}

	public AnchorPane getQuestionAnchor() {
		return questionAnchor;
	}

	public int getCorrectAnswer() {
		return correctAnswer = 0;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	private int correctAnswer;
	// toggle group for allowing one choice of radio button
	final ToggleGroup group = new ToggleGroup();
	private String author;
	ObservableList fields = FXCollections.observableArrayList();

	@FXML
	void clickBack() {
		Node page = null;
		try {
			page = FXMLLoader.load(getClass().getResource(Navigator.QUESTION_BANK.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, page);
	}

	/**
	 * the method handles sending a query to DB for editing question and adding a
	 * new question
	 */
	@FXML
	void clickSave() throws IOException {
		String teacherId = null;
		// Get data from UI
		if (ClientController.getRoleFrame().equals("Teacher"))
			teacherId = ClientController.getActiveUser().getSSN();

		// Set answers
		ArrayList<JFXTextArea> answers = getAnswerTextFields();
		String questionContent = getQuestionContentTxt().getText();
		String answer1 = answers.get(0).getText();
		String answer2 = answers.get(1).getText();
		String answer3 = answers.get(2).getText();
		String answer4 = answers.get(3).getText();

		// Query to add question to dataBase
		if (getNewQuestionFormLbl().getText().toString().equals("New Question Form")) {
			// Send query only if fields aren't empty
			if (correctAnswer != 0 && !questionContent.isEmpty() && !fieldCBox.getValue().toString().isEmpty()
					&& !answer1.isEmpty() && !answer2.isEmpty() && !answer3.isEmpty() && !answer4.isEmpty()) {
				// Author,questionContent,correctAnswer,field,answer1,answer2,answer3,answer4
				String queryAddQuestion = "ADD_QUESTION-" + teacherId + "," + questionContent + ","
						+ correctAnswer + "," + fieldCBox.getValue().toString() + "," + answer1 + "," + answer2 + ","
						+ answer2 + "," + answer4;
				ClientController.accept(queryAddQuestion);
				// check if question added correctly
				if (ClientController.isQuestionAdded()) {
					// Show popup window
					String toShow = "Question ID: ";
					toShow = toShow.concat(ClientController.getNewQuestionId());
					JFXButton okayBtn = new JFXButton("Okay");
					okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
						try {
							GeneralUIMethods.loadPage(contentPaneAnchor,
									FXMLLoader.load(getClass().getResource(Navigator.QUESTION_BANK.getVal())));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					});
					new PopUp(PopUp.TYPE.SUCCESS, "Question saved", toShow, contentPaneAnchor, Arrays.asList(okayBtn),
							null);
				}
				// handle empty fields
			} else {
				ArrayList<Node> nodes = new ArrayList<Node>();
				nodes.add(fieldCBox);
				nodes.add(questionContentTxt);
				nodes.add(answer1Txt);
				nodes.add(answer2Txt);
				nodes.add(answer3Txt);
				nodes.add(answer4Txt);
				if (!answer1Btn.isSelected() && !answer2Btn.isSelected() && !answer3Btn.isSelected() && !answer4Btn.isSelected()) {	
					nodes.add(answer1Btn);
					nodes.add(answer2Btn);
					nodes.add(answer3Btn);
					nodes.add(answer4Btn);
				}
				if (GeneralUIMethods.checkEmptyFields(nodes))
					new PopUp(PopUp.TYPE.LOGIN, "Error", "Some fields are missing", contentPaneAnchor, null, null);
			}
		}
		// query for editing question
		else {
			String[] questionID = getNewQuestionFormLbl().getText().toString().split(" ");
			String queryEditQuestion = "EDIT_QUESTION-" + questionID[2] + "," + teacherId + ","
					+ getQuestionContentTxt().getText() + "," + correctAnswer + ","
					+ fieldCBox.getPromptText().toString() + "," + answer1 + "," + answer2 + "," + answer3 + ","
					+ answer4;
			if (correctAnswer != 0 && !getQuestionContentTxt().getText().isEmpty()
					&& !fieldCBox.getPromptText().toString().isEmpty() && !answer1.isEmpty() && !answer2.isEmpty()
					&& !answer3.isEmpty() && !answer4.isEmpty()) { // send query onlt if all fields are not empty
				ClientController.accept(queryEditQuestion);
				boolean answerEdit = ClientController.isQuestionEdited();
				// check if answer edited correctly in DB
				if (answerEdit) {
					// Show popup window
					String toShow = "Question ID: ";
					toShow = toShow.concat(questionID[2]);
					JFXButton okayBtn = new JFXButton("Okay");
					okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
						try {
							GeneralUIMethods.loadPage(contentPaneAnchor,
									FXMLLoader.load(getClass().getResource(Navigator.QUESTION_BANK.getVal())));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					});
					new PopUp(PopUp.TYPE.SUCCESS, "Question edited", toShow, contentPaneAnchor, Arrays.asList(okayBtn), null);
				}
			} else
				new PopUp(PopUp.TYPE.ERROR, "Question not edited", "Some fields are missing!", contentPaneAnchor,
						null, null);
		}
		correctAnswer = 0;
	}

	/**
	 * this method initializes the toggle group for radio button and other data
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String[] fieldsSplit = null;
		if (ClientController.getRoleFrame().equals("Teacher")) {
			fieldsSplit = ((Teacher) ClientController.getActiveUser()).getFields().split("~");
			for (String oneField : fieldsSplit)
				fields.add(oneField);
			getFieldCBox().setItems(fields);
		}
		answer1Btn.setToggleGroup(group);
		answer2Btn.setToggleGroup(group);
		answer3Btn.setToggleGroup(group);
		answer4Btn.setToggleGroup(group);
		setToggleGroupNotVisible();
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			// Observable to view changes in radio button
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				if (group.getSelectedToggle() != null) {
					JFXRadioButton button = (JFXRadioButton) group.getSelectedToggle();
					setToggleGroupNotVisible();
					if (button.equals(answer1Btn)) {
						correctAnswer1Lbl.setVisible(true);
						correctAnswer = 1;
					}
					if (button.equals(answer2Btn)) {
						correctAnswer2Lbl.setVisible(true);
						correctAnswer = 2;
					}
					if (button.equals(answer3Btn)) {
						correctAnswer3Lbl.setVisible(true);
						correctAnswer = 3;
					}
					if (button.equals(answer4Btn)) {
						correctAnswer4Lbl.setVisible(true);
						correctAnswer = 4;
					}

				}

			}

		});
	}

	/**
	 * this method sets the correct answers label to be not visible
	 */
	public void setToggleGroupNotVisible() {
		correctAnswer1Lbl.setVisible(false);
		correctAnswer2Lbl.setVisible(false);
		correctAnswer3Lbl.setVisible(false);
		correctAnswer4Lbl.setVisible(false);
	}

	public Label getNewQuestionFormLbl() {
		return newQuestionFormLbl;
	}

	public JFXTextArea getQuestionContentTxt() {
		return questionContentTxt;
	}

	public ArrayList<JFXTextArea> getAnswerTextFields() {
		ArrayList<JFXTextArea> answers = new ArrayList<JFXTextArea>();
		answers.add(answer1Txt);
		answers.add(answer2Txt);
		answers.add(answer3Txt);
		answers.add(answer4Txt);
		return answers;
	}

	public ArrayList<JFXRadioButton> getAnswerBtns() {
		ArrayList<JFXRadioButton> answersBtns = new ArrayList<JFXRadioButton>();
		answersBtns.add(answer1Btn);
		answersBtns.add(answer2Btn);
		answersBtns.add(answer3Btn);
		answersBtns.add(answer4Btn);
		return answersBtns;
	}

	public JFXButton getSaveBtn() {
		return saveBtn;
	}

	public JFXComboBox<?> getFieldCBox() {
		return fieldCBox;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}