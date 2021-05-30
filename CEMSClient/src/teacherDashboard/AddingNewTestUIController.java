package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import common.Test;
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
import javafx.scene.layout.VBox;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class AddingNewTestUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

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
	private JFXComboBox<?> selectFieldComboBox;

	@FXML
	private JFXComboBox<?> selectFieldComboBox1;

	@FXML
	private JFXTextField titleTxt;

	@FXML
	private JFXTextField durationTxt;

	@FXML
	private JFXTextArea teacherInstructionsTxtArea;

	@FXML
	private JFXTextArea studentInstructionsTxtArea1;

	@FXML
	private AnchorPane testAnchor;

	@FXML
	private ScrollPane testScrollPane;

	@FXML
	private Label headTitleLbl;

	@FXML
	private JFXButton backBtn1;

	@FXML
	private JFXButton backBtn2;

	@FXML
	private JFXButton backBtn3;

	@FXML
	private JFXButton finishBtn;

	@FXML
	private JFXButton previewTestBtn;

	@FXML
	private JFXButton continueWithParametersBtn;
	private Node QuestionForm;
	private QuestionFormUIController questionFormUIController;
	private String testTitle, duration, course, studentInst, teacherInst, field;
	private Node testBank;
	private Set<Question> pickedQuestions;

	ObservableList fields = FXCollections.observableArrayList();
	ObservableList courses = FXCollections.observableArrayList();

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
	 * initialize a combo box with the relevant fields
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		pickedQuestions = new HashSet<>();
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			String[] fieldsSplit = teacher.getFields().split("~");
			for (String oneField : fieldsSplit)
				fields.add(oneField);

		}
		selectFieldComboBox.setItems(fields);
		selectFieldComboBox.setOnAction(event -> {
			questionTable.getItems().clear();
			ClientController.accept("QUESTION_BANK-" + selectFieldComboBox.getValue());
			ArrayList<Question> questions = ClientController.getQuestions();
			idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
			textCol.setCellValueFactory(new PropertyValueFactory<>("text"));
			authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
			viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
			selectCol.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
			ClientController.accept("GET_COURSES_BY_FIELD-" + selectFieldComboBox.getValue());
			courses.clear();
			for (Course course : ClientController.getCourses())
				courses.add(course.getCourseName());
			selectFieldComboBox1.setItems(courses);
			for (Question q : questions) {
				QuestionRow qr = new QuestionRow(q);
				questionTable.getItems().add(qr);

				EventHandler<ActionEvent> btnEventHandler = new EventHandler<ActionEvent>() { // delete form table and DB
					@Override
					public void handle(ActionEvent event) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.QUESTION_FORM.getVal()));
						try {
							QuestionForm = loader.load();
						} catch (IOException e) {
							e.printStackTrace();
						}
						JFXButton buttonText = (JFXButton) event.getSource();
						questionFormUIController = loader.getController();
						questionFormUIController.getNewQuestionFormLbl().setText(
								buttonText.getText() + "ing question " + qr.getID() + " by " + qr.getAuthor());
						questionFormUIController.getQuestionContentTxt().setText(q.getQuestionText());
						questionFormUIController.getAnswerBtns().get(q.getCorrectAnswer()).setSelected(true);
						// blankQuestionFormUIController.getFieldCBox().getSelectionModel().select(q.getField());
						// //---TODO:fix
						// (q.getField().toString());
						for (int j = 0; j < 4; j++)
							questionFormUIController.getAnswerTextFields().get(j)
									.setText(q.getAnswers().get(j));
						GeneralUIMethods.loadPage(contentPaneAnchor, QuestionForm);
					}
				};

				qr.getViewBtn().setOnAction(e -> {
					btnEventHandler.handle(e);
					{
						questionFormUIController.getQuestionContentTxt().setEditable(false);
						for (int p = 0; p < 4; p++) {
							questionFormUIController.getAnswerTextFields().get(p).setEditable(false);
							questionFormUIController.getAnswerBtns().get(p).setDisable(true);
							questionFormUIController.getSaveBtn().setVisible(false);
						}
					}
					;
				});
				if (pickedQuestions.size() == 0)
					previewTestBtn.setDisable(true);
				if (pickedQuestions.contains(q))
					qr.getCheckBox().setSelected(true);
				qr.getCheckBox().setOnAction(eventCheck -> {
					if (qr.getCheckBox().isSelected()) {
						pickedQuestions.add(q);
						previewTestBtn.setDisable(false);
					} else {
						pickedQuestions.remove(q);
						if (pickedQuestions.size() == 0)
							previewTestBtn.setDisable(true);
					}
					System.out.print("[");
					for (Question qe : pickedQuestions) {
						System.out.print(qe.getID());
						System.out.print(",");
					}
					System.out.print("]\n");
				});
			}
			pickedQuestions.clear();
		});

	}

	/**
	 * clicking back will go back to the test bank screen
	 * 
	 * @param event
	 */
	@FXML
	void clickBack1(MouseEvent event) {
		try {
			testBank = FXMLLoader.load(getClass().getResource(Navigator.TEST_BANK.getVal()));
			contentPaneAnchor.getChildren().setAll(testBank);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void clickBack2(MouseEvent event) {
		backBtn1.setVisible(true);
		backBtn2.setVisible(false);
		continueWithParametersBtn.setVisible(true);
		previewTestBtn.setVisible(false);
		parametersVBox.setVisible(true);
		questionTable.setVisible(false);
		headTitleLbl.setText("Set parameters");
	}

	@FXML
	void clickBack3(MouseEvent event) {
		backBtn2.setVisible(true);
		backBtn3.setVisible(false);
		previewTestBtn.setVisible(true);
		finishBtn.setVisible(false);
		questionTable.setVisible(true);
		headTitleLbl.setText("Choose questions to add to the test");
		testAnchor.setVisible(false);
	}

	/**
	 * clicking continue will move to blank test form only if at least one question
	 * was chosen.
	 * 
	 * @param event
	 */
	@FXML
	void clickFinish(MouseEvent event) {
		try {
			StringBuilder sb = new StringBuilder(); // changing the set to and array like : 12~1~5~5
			for (Question q : pickedQuestions) {
				sb.append(q.getID());
				sb.append("~");
			}
			sb.deleteCharAt(sb.length() - 1);
			System.out.println(sb.toString());
			ClientController.accept("ADD_TEST-" + ClientController.getActiveUser().getName() + "," + testTitle + ","
					+ course + "," + duration + "," + 100 / pickedQuestions.size() + "," + studentInst + ","
					+ teacherInst + "," + sb.toString() + "," + field);

			testBank = FXMLLoader.load(getClass().getResource(Navigator.TEST_BANK.getVal()));
			contentPaneAnchor.getChildren().setAll(testBank);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * testTitle, duration, course, studentInst, teacherInst
	 */
	@FXML
	void clickContinueWithParameters(MouseEvent event) {
		testTitle = titleTxt.getText();
		duration = durationTxt.getText();
		field = selectFieldComboBox.getValue().toString();
		course = selectFieldComboBox1.getValue().toString();
		studentInst = (studentInstructionsTxtArea1.getText() == null) ? "null" : studentInstructionsTxtArea1.getText();
		teacherInst = (teacherInstructionsTxtArea.getText() == null) ? "null" : teacherInstructionsTxtArea.getText();
		backBtn1.setVisible(false);
		backBtn2.setVisible(true);
		continueWithParametersBtn.setVisible(false);
		previewTestBtn.setVisible(true);
		parametersVBox.setVisible(false);
		questionTable.setVisible(true);
		parametersVBox.setVisible(false);
		headTitleLbl.setText("Choose questions to add to the test");
	}

	@FXML
	void clickPreviewTest(MouseEvent event) {
		backBtn2.setVisible(false);
		backBtn3.setVisible(true);
		previewTestBtn.setVisible(false);
		finishBtn.setVisible(true);
		questionTable.setVisible(false);
		headTitleLbl.setText("Preview the test");
		testAnchor.setVisible(true);
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
			Region test = loader.load();
			test.prefWidthProperty().bind(testScrollPane.widthProperty());
			test.prefHeightProperty().bind(testScrollPane.heightProperty());
			TestFormController controller = loader.getController();
//			controller.getScrollPane().setLayoutX(testScrollPane.layoutXProperty().doubleValue());
//			controller.getScrollPane().setLayoutY(testScrollPane.layoutYProperty().doubleValue());
			controller.getScrollPane().prefHeightProperty().bind(testScrollPane.heightProperty());
			controller.getScrollPane().prefWidthProperty().bind(testScrollPane.widthProperty());
			controller.getScrollPane().setTranslateX(10);
			controller.getScrollPane().setTranslateY(11);
			controller.getEditBtn().setVisible(false);
			controller.addTitleAndInstructionsToTest(testTitle, teacherInst, studentInst);
			int i = 1;
			for (Question q : pickedQuestions) {
				controller.addQuestionToTestForm(q, i, 100 / pickedQuestions.size()); // adding questions to preview
				i++;
			}
			GeneralUIMethods.loadPage(testAnchor, test);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// -------------need to implement an if statement that will block passage if no
		// questions were selected--------------
	}

}