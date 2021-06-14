package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.Course;
import common.Question;
import common.Teacher;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class AddingNewTestUIController implements Initializable {

	private String isEditingTest = null;
	private static int Screen = 0;
	private Node QuestionForm, testBank;
	private QuestionFormUIController questionFormUIController;
	private String testTitle, duration, course, studentInst, teacherInst, field;
	private Set<Question> pickedQuestions, savedPickedQuestion;
	boolean flag = false;
	ArrayList<Question> questions;
	ObservableList<String> fields = FXCollections.observableArrayList();
	ObservableList<String> courses = FXCollections.observableArrayList();
	ObservableList<String> durationList = FXCollections.observableArrayList();
	
    @FXML
    private AnchorPane contentPaneAnchor;
    
    @FXML
    private StackPane popUpWindow;

    @FXML
    private AnchorPane setParametersAnchor;

    @FXML
    private AnchorPane insidesetParametersAnchor;

    @FXML
    private AnchorPane parameterTitleAnchor;

    @FXML
    private AnchorPane insideparameterTitleAnchor;

    @FXML
    private TableView<QuestionRow> questionTable;

    @FXML
    private TableColumn<?, ?> selectCol;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> authorCol;

    @FXML
    private TableColumn<?, ?> textCol;

    @FXML
    private TableColumn<?, ?> viewCol;

    @FXML
    private VBox parametersVBox;

    @FXML
    private JFXComboBox<String> selectFieldCBox;

    @FXML
    private JFXComboBox<String> selectCourseCBox;

    @FXML
    private JFXTextField titleTxt;

    @FXML
    private JFXComboBox<String> durationCbox;

    @FXML
    private JFXTextArea teacherInstructionsTxtArea;

    @FXML
    private JFXTextArea studentInstructionsTxtArea;

    @FXML
    private AnchorPane testAnchor;

    @FXML
    private ScrollPane testScrollPane;

    @FXML
    private Label headTitleLbl;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton finishBtn;

    @FXML
    private JFXButton previewTestBtn;

    @FXML
    private JFXButton continueWithParametersBtn;

    @FXML
    private AnchorPane questionAnchor;

    @FXML
    private ScrollPane questionScrollPane;

    @FXML
    private AnchorPane testAnchor2;

    @FXML
    private JFXButton backToPageBtn;
    
    @FXML
    void backToPageBtnClicked(MouseEvent event) {
		questionAnchor.setVisible(false);
		questionAnchor.toBack();
		backBtn.setVisible(true);
		previewTestBtn.setVisible(true);
    }

	public TableView<QuestionRow> getQuestionTable() {
		return questionTable;
	}

	public JFXComboBox<String> getSelectFieldCBox() {
		return selectFieldCBox;
	}

	public JFXComboBox<String> getSelectCourseCBox() {
		return selectCourseCBox;
	}

	public JFXTextField getTitleTxt() {
		return titleTxt;
	}

	public JFXTextArea getTeacherInstructionsTxtArea() {
		return teacherInstructionsTxtArea;
	}

	public Set<Question> getPickedQuestions() {
		return pickedQuestions;
	}

	public void setPickedQuestions(Set<Question> pickedQuestions) {
		this.pickedQuestions = pickedQuestions;
	}

	public JFXTextArea getStudentInstructionsTxtArea() {
		return studentInstructionsTxtArea;
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	public void setEditingTest(String isEditingTest) {
		this.isEditingTest = isEditingTest;
	}

	public JFXComboBox<String> getDurationCbox() {
		return durationCbox;
	}

	public void setDurationCbox(JFXComboBox<String> durationCbox) {
		this.durationCbox = durationCbox;
	}

	// QuestionRow for table
	public class QuestionRow {
		private String id;
		private String author;
		private String text;
		private JFXButton viewBtn;
		private CheckBox checkBox;

		public QuestionRow(Question question) {
			id = question.getID();
			author = question.getAuthorID();
			text = question.getQuestionText();
			viewBtn = new JFXButton();
			viewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
			checkBox = new CheckBox();
		}

		public JFXButton getViewBtn() {
			return viewBtn;
		}

		public CheckBox getCheckBox() {
			return checkBox;
		}

		public String getID() {
			return id;
		}

		public String getAuthor() {
			return author;
		}

		public String getText() {
			return text;
		}

	}

	/**
	 * Initialize a combo box with the relevant fields
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Initialize duration combo box
		for (int i = 30; i <= 180; i += 5)
			durationList.add(String.valueOf(i));
		durationCbox.setItems(durationList);
		durationCbox.getSelectionModel().select(6);
		pickedQuestions = new HashSet<>();
		savedPickedQuestion = new HashSet<>();
		// Add fields to the combo box
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			String[] fieldsSplit = teacher.getFields().split("~");
			for (String oneField : fieldsSplit)
				fields.add(oneField);
		}

		// Get courses by the field
		selectFieldCBox.setItems(fields);
		selectFieldCBox.setOnAction(event -> {
			questionTable.getItems().clear();
			ClientController.accept("QUESTION_BANK-" + selectFieldCBox.getValue());
			questions = ClientController.getQuestions();
			idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
			textCol.setCellValueFactory(new PropertyValueFactory<>("text"));
			authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
			viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
			selectCol.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
			ClientController.accept("GET_COURSES_BY_FIELD-" + selectFieldCBox.getValue());
			courses.clear();
			if (ClientController.getCourses() == null || ClientController.getCourses().isEmpty())
				return;
			// Add courses to the combo box
			for (Course course : ClientController.getCourses())
				courses.add(course.getName());
			selectCourseCBox.setItems(courses);
			// Set the rows of the table
			if (ClientController.getQuestions() == null) {
				new PopUp(PopUp.TYPE.ERROR, "Error", "There are no questions in this field. Please create a question or change field.", contentPaneAnchor, null, null);
				continueWithParametersBtn.setDisable(true);
				return;
			}
			else
				continueWithParametersBtn.setDisable(false);
			for (Question q : questions) {
				QuestionRow qr = new QuestionRow(q);
				questionTable.getItems().add(qr);
				if (pickedQuestions.contains(q))
					flag = true;
				
				// View button
				qr.getViewBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						FXMLLoader questionFormPageLoader = new FXMLLoader(
								getClass().getResource(Navigator.QUESTION_FORM.getVal()));
						try {
							QuestionForm = questionFormPageLoader.load();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						questionAnchor.setVisible(true);
						questionAnchor.toFront();
						backBtn.setVisible(false);
						previewTestBtn.setVisible(false);
						questionFormUIController = questionFormPageLoader.getController();
						questionFormUIController.getQuestionContentTxt().setText(q.getQuestionText());
						questionFormUIController.getAnswerBtns().get(q.getCorrectAnswer() - 1).setSelected(true);
						questionFormUIController.getNewQuestionFormLbl()
								.setText("Viewing question " + qr.getID() + " by " + qr.getAuthor());
						questionFormUIController.getQuestionContentTxt().setEditable(false);
						questionFormUIController.getFieldCBox().setDisable(true);
						for (int p = 0; p < 4; p++) {
							questionFormUIController.getAnswerTextFields().get(p).setText(q.getAnswers().get(p));
							questionFormUIController.getAnswerTextFields().get(p).setEditable(false);
							questionFormUIController.getAnswerBtns().get(p).setDisable(true);
							questionFormUIController.getSaveBtn().setVisible(false);
						}
						AnchorPane.setTopAnchor(questionFormUIController.getInsideContentAnchor(), 0.0);
						AnchorPane.setBottomAnchor(questionFormUIController.getInsideContentAnchor(), 0.0);
						AnchorPane.setLeftAnchor(questionFormUIController.getInsideContentAnchor(), 0.0);
						AnchorPane.setRightAnchor(questionFormUIController.getInsideContentAnchor(), 0.0);
						GeneralUIMethods.loadPage(testAnchor2, QuestionForm);
					}
				});
				// Set the picked questions string
				if (pickedQuestions.size() == 0 && savedPickedQuestion.size() == 0)
					previewTestBtn.setDisable(true);
				if (pickedQuestions.contains(q))
					qr.getCheckBox().setSelected(true);
				qr.getCheckBox().setOnAction(eventCheck -> {
					if (qr.getCheckBox().isSelected()) {
						pickedQuestions.add(q);
						savedPickedQuestion.add(q);
						previewTestBtn.setDisable(false);
					} else {
						pickedQuestions.remove(q);
						savedPickedQuestion.remove(q);
						if (pickedQuestions.size() == 0 && savedPickedQuestion.size() == 0)
							previewTestBtn.setDisable(true);
					}
				});
			}
			if (flag) {
				savedPickedQuestion.addAll(pickedQuestions);
				pickedQuestions.clear();
			}
		});
	}

	/**
	 * Clicking back will go back to the relevant screen
	 * 
	 * @param event
	 */
	@FXML
	void clickBack(MouseEvent event) {
		switch (Screen) {
		case 0:
			try {
				testBank = FXMLLoader.load(getClass().getResource(Navigator.TEST_BANK.getVal()));
				contentPaneAnchor.getChildren().setAll(testBank);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			continueWithParametersBtn.setVisible(true);
			previewTestBtn.setVisible(false);
			parametersVBox.setVisible(true);
			questionTable.setVisible(false);
			headTitleLbl.setText("Set parameters");
			break;
		case 2:
			previewTestBtn.setVisible(true);
			finishBtn.setVisible(false);
			questionTable.setVisible(true);
			headTitleLbl.setText("Choose questions to add to the test");
			testAnchor.setVisible(false);
			break;
		}
		if (--Screen == -1)
			Screen = 0;
	}


	/**
	 * Set parameters and move to the questions screen
	 * @param event
	 */
	@FXML
	void clickContinueWithParameters(MouseEvent event) {
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(titleTxt);
		nodes.add(durationCbox);
		nodes.add(selectFieldCBox);
		nodes.add(selectCourseCBox);
		nodes.add(teacherInstructionsTxtArea);
		nodes.add(studentInstructionsTxtArea);
		if (GeneralUIMethods.checkEmptyFields(nodes))
			new PopUp(PopUp.TYPE.LOGIN, "Error", "Some fields are missing", contentPaneAnchor, null, null);
		else {
			Screen++;
			testTitle = titleTxt.getText();
			duration = durationCbox.getValue().toString();
			field = selectFieldCBox.getValue().toString();
			course = selectCourseCBox.getValue().toString();
			studentInst = (studentInstructionsTxtArea.getText() == null) ? "null"
					: studentInstructionsTxtArea.getText();
			teacherInst = (teacherInstructionsTxtArea.getText() == null) ? "null"
					: teacherInstructionsTxtArea.getText();
			continueWithParametersBtn.setVisible(false);
			previewTestBtn.setVisible(true);
			parametersVBox.setVisible(false);
			questionTable.setVisible(true);
			parametersVBox.setVisible(false);
			headTitleLbl.setText("Choose questions to add to the test");
		}
		// If editing disable the possibility to change field and course
	}

	
	/**
	 * Preview the test
	 * @param event
	 */
	@FXML
	void clickPreviewTest(MouseEvent event) {
		Screen++;
		previewTestBtn.setVisible(false);
		finishBtn.setVisible(true);
		questionTable.setVisible(false);
		headTitleLbl.setText("Preview the test");
		testAnchor.setVisible(true);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
			Region test = loader.load();
			test.prefWidthProperty().bind(testScrollPane.widthProperty());
			test.prefHeightProperty().bind(testScrollPane.heightProperty().add(28));
			TestFormController controller = loader.getController();
			controller.getScrollPane().prefHeightProperty().bind(testScrollPane.heightProperty().add(20));
			controller.getScrollPane().prefWidthProperty().bind(testScrollPane.widthProperty());
			controller.getScrollPane().setTranslateX(10);
			controller.getScrollPane().setTranslateY(11);
			controller.addTitleAndInstructionsToTest(testTitle, teacherInst, studentInst);
			int i = 1;
			if (savedPickedQuestion != null)
				// Adding questions to preview
				for (Question q : savedPickedQuestion) {
					controller.addQuestionToTestForm(q, i, 100 / savedPickedQuestion.size());
					if (controller.getQuestionsToggleGroup().size() > 0) {
						controller.getQuestionsToggleGroup().get(i - 1).getToggles().forEach(toggle -> {
						    Node node = (Node) toggle;
						    node.setDisable(true);
						});
						controller.getQuestionsToggleGroup().get(i - 1).getToggles().get(q.getCorrectAnswer()-1)
								.setSelected(true);
					}
					i++;
				}
			else if (pickedQuestions != null) // Adding questions to preview
				for (Question q : pickedQuestions) {
					controller.addQuestionToTestForm(q, i, 100 / pickedQuestions.size());
					if (controller.getQuestionsToggleGroup().size() > 0) {
						controller.getQuestionsToggleGroup().get(i - 1).getToggles().forEach(toggle -> {
						    Node node = (Node) toggle;
						    node.setDisable(true);
						});
						controller.getQuestionsToggleGroup().get(i - 1).getToggles().get(q.getCorrectAnswer()-1)
								.setSelected(true);
					}
					i++;
				}
			GeneralUIMethods.loadPage(testAnchor, test);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Clicking continue will move to blank test form only if at least one question
	 * was chosen.
	 * 
	 * @param event
	 */
	@FXML
	void clickFinish(MouseEvent event) {
		Screen++;
		StringBuilder sb = new StringBuilder();
		for (Question q : pickedQuestions) {
			sb.append(q.getID());
			sb.append("~");
		}
		if (pickedQuestions.size() == 0)
			for (Question q : savedPickedQuestion) {
				sb.append(q.getID());
				sb.append("~");
			}
		sb.deleteCharAt(sb.length() - 1);
		String popupMsg = "";
		// Edit question query
		if (isEditingTest != null) {
			ClientController.accept("EDIT_TEST-" + isEditingTest + "," + testTitle + "," + duration + ","
					+ 100 / savedPickedQuestion.size() + "," + studentInst + "," + teacherInst + "," + sb.toString());
			popupMsg = "The test (ID: " + isEditingTest + ") has been updated!";
		} else { // Add new test query
			ClientController.accept("ADD_TEST-" + ClientController.getActiveUser().getSSN() + "," + testTitle + ","
					+ course + "," + duration + "," + 100 / pickedQuestions.size() + "," + studentInst + ","
					+ teacherInst + "," + sb.toString() + "," + field);
			popupMsg = "The test " + testTitle + " was added to " + course + " in " + field;
		}
		isEditingTest = null;
		// Show popup window
		JFXButton okayBtn = new JFXButton("Okay");
		okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			try {
				testBank = FXMLLoader.load(getClass().getResource(Navigator.TEST_BANK.getVal()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			GeneralUIMethods.loadPage(contentPaneAnchor, testBank);
		});
		GeneralUIMethods.setPopupPane(popUpWindow);
		new PopUp(PopUp.TYPE.INFORM, "Information", popupMsg, setParametersAnchor, Arrays.asList(okayBtn), null);
		GeneralUIMethods.setPopupPane(ClientController.getTeacherDashboardUIController().getPopUpWindow());
	}

}