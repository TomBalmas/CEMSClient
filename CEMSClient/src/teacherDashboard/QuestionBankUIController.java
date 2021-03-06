package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.Principle;
import common.Question;
import common.Teacher;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class QuestionBankUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private Label questionBankLbl;

	@FXML
	private JFXTextField searchField;

	@FXML
	private Label startDPlbl;

	@FXML
	private JFXDatePicker startCoursesDP;

	@FXML
	private Label endDPlbl;

	@FXML
	private JFXDatePicker finishCoursesDP;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<questionRow> questionBankTable;

	@FXML
	private TableColumn<?, ?> IDCol;

	@FXML
	private TableColumn<?, ?> authorCol;

	@FXML
	private TableColumn<?, ?> fieldCol;

	@FXML
	private TableColumn<?, ?> contentCol;

	@FXML
	private TableColumn<?, ?> viewCol;

	@FXML
	private TableColumn<?, ?> editCol;

	@FXML
	private TableColumn<?, ?> deleteCol;

	@FXML
	private JFXButton addAnewQuestionBtn;

	private Node blankQuestionForm, QuestionForm;
	private QuestionFormUIController blankQuestionFormUIController;
	private String authorString;
	private final ObservableList<questionRow> dataList = FXCollections.observableArrayList();
	// Lists for Combobox fields. Fields is for adding a new question.
	ObservableList fields = FXCollections.observableArrayList();
	// Field is for viewing specific question
	ObservableList field = FXCollections.observableArrayList();

	public String getAuthorString() {
		return authorString;
	}

	public void setAuthorString(String authorString) {
		this.authorString = authorString;
	}

	/**
	 * Clicking add a new question will go to blank question for page.
	 * 
	 * @param event
	 */
	@FXML
	void clickAddAnewQuestion(MouseEvent event) {
		try {
			blankQuestionForm = FXMLLoader.load(getClass().getResource(Navigator.QUESTION_FORM.getVal()));

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, blankQuestionForm);
	}

	public JFXButton getAddAnewQuestionBtn() {
		return addAnewQuestionBtn;
	}

	/**
	 * Contains all data to be presented in a question row in the table view
	 *
	 */
	public class questionRow {
		private String id;
		private String author;
		private String content;
		private String field;
		private JFXButton ViewBtn;
		private JFXButton DeleteBtn;
		private JFXButton EditBtn;
		private Question question;

		public questionRow(Question question) {
			String authorID = question.getAuthorID();
			ClientController.accept("GET_NAME_BY_ID-" + authorID);
			author = ClientController.getAuthorName();
			content = question.getQuestionText();
			this.question = question;
			id = question.getID();
			field = question.getField();
			this.ViewBtn = new JFXButton();
			this.DeleteBtn = new JFXButton();
			this.EditBtn = new JFXButton();
			DeleteBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TRASH));
			EditBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EDIT));
			ViewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
		}

		public String getID() {
			return id;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public JFXButton getViewBtn() {
			return ViewBtn;
		}

		public void setViewBtn(JFXButton viewBtn) {
			this.ViewBtn = viewBtn;
		}

		public JFXButton getDeleteBtn() {
			return DeleteBtn;
		}

		public void setDeleteBtn(JFXButton deleteBtn) {
			this.DeleteBtn = deleteBtn;
		}

		public JFXButton getEditBtn() {
			return EditBtn;
		}

		public void setEditBtn(JFXButton editBtn) {
			this.EditBtn = editBtn;
		}

		public Question getQuestion() {
			return question;
		}

		public void setQuestion(Question question) {
			this.question = question;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

	}

	/**
	 * Getting data from question table into question bank screen according to
	 * teachers fields
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Question> questions = null;
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();

			// Setting the fields into the combo box
			String[] fieldsSplit = teacher.getFields().split("~");
			for (String oneField : fieldsSplit)
				fields.add(oneField);

			// Calling query for getting teachers field questions
			ClientController.accept("QUESTION_BANK-" + teacher.getFields());
			questions = ClientController.getQuestions();
		} else if (ClientController.getRoleFrame().equals("Principle")) {
			Principle principle = (Principle) ClientController.getActiveUser();
			// Calling query for getting teachers field questions
			ClientController.accept("GET_QUESTIONS_TABLE-");
			questions = ClientController.getQuestions();
			deleteCol.setVisible(false);
			editCol.setVisible(false);
			contentCol.setPrefWidth(375);
			deleteCol.setPrefWidth(0);
			editCol.setPrefWidth(0);
		}

		// Adding PropertyValueFactory for the columns
		PropertyValueFactory IDfactory = new PropertyValueFactory<>("ID");
		PropertyValueFactory fieldfactory = new PropertyValueFactory<>("field");
		PropertyValueFactory authorFactory = new PropertyValueFactory<>("author");
		PropertyValueFactory viewFactory = new PropertyValueFactory<>("ViewBtn");
		PropertyValueFactory contentFactory = new PropertyValueFactory<>("content");

		fieldCol.setCellValueFactory(fieldfactory);
		IDCol.setCellValueFactory(IDfactory);
		authorCol.setCellValueFactory(authorFactory);
		viewCol.setCellValueFactory(viewFactory);
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("DeleteBtn"));
		editCol.setCellValueFactory(new PropertyValueFactory<>("EditBtn"));
		contentCol.setCellValueFactory(contentFactory);

		if (questions != null) {
			for (int i = 0; i < questions.size(); i++) {
				questionRow questionRow = new questionRow(questions.get(i));
				questionBankTable.getItems().add(questionRow);
				dataList.add(questionRow); // Add row to dataList to search field.
				tableViewAnchor.setMouseTransparent(false);
				if (!questionRow.getAuthor().equals(ClientController.getActiveUser().getName())) {
					questionRow.getEditBtn().setDisable(true);
					questionRow.getDeleteBtn().setDisable(true);
				}
				EventHandler<ActionEvent> btnEventHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							FXMLLoader questionFormLoader = new FXMLLoader(
									getClass().getResource(Navigator.QUESTION_FORM.getVal()));
							QuestionForm = questionFormLoader.load();
							blankQuestionFormUIController = questionFormLoader.getController();
							blankQuestionFormUIController.getQuestionContentTxt()
									.setText(questionRow.getQuestion().getQuestionText());
							blankQuestionFormUIController.getAnswerBtns()
									.get(questionRow.getQuestion().getCorrectAnswer() - 1).setSelected(true);
							for (int j = 0; j < 4; j++)
								blankQuestionFormUIController.getAnswerTextFields().get(j)
										.setText(questionRow.getQuestion().getAnswers().get(j));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						GeneralUIMethods.loadPage(contentPaneAnchor, QuestionForm);
					}
				};

				// Event handler for deletion from table and DB
				questionRow.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() { // delete from table and DB
					@Override
					public void handle(ActionEvent event) {
						JFXButton yesBtn = new JFXButton("Yes");
						yesBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
							ClientController.accept("DELETE_QUESTION-" + questionRow.getID());
							if (ClientController.isQuestionDeleted()) {
								questionRow selectedItem = questionBankTable.getSelectionModel().getSelectedItem();
								if (selectedItem != null)
									questionBankTable.getItems().remove(selectedItem);
								JFXButton okayBtn = new JFXButton("Okay");
								okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e1) -> {
									try {
										GeneralUIMethods.loadPage(contentPaneAnchor, FXMLLoader
												.load(getClass().getResource(Navigator.QUESTION_BANK.getVal())));
									} catch (IOException e2) {
										e2.printStackTrace();
									}
								});
								new PopUp(PopUp.TYPE.INFORM, "Information",
										"The question " + questionRow.getID() + " has been deleted", contentPaneAnchor,
										Arrays.asList(okayBtn), null);
							}
						});
						new PopUp(PopUp.TYPE.ALERT, "Alert", "Are you sure that you want to delete this question?",
								contentPaneAnchor, Arrays.asList(yesBtn, new JFXButton("No")), null);
					}
				});

				// Event handler for view button
				questionRow.getViewBtn().setOnAction(e -> {
					btnEventHandler.handle(e);
					{
						field.add(questionRow.getField());
						blankQuestionFormUIController.getNewQuestionFormLbl()
								.setText("Viewing question " + questionRow.getID() + " by " + questionRow.getAuthor());
						blankQuestionFormUIController.getFieldCBox().setPromptText(field.get(0).toString());
						blankQuestionFormUIController.getQuestionContentTxt().setEditable(false);
						blankQuestionFormUIController.getFieldCBox().setDisable(true);
						for (int p = 0; p < 4; p++) {
							blankQuestionFormUIController.getAnswerTextFields().get(p).setEditable(false);
							blankQuestionFormUIController.getAnswerBtns().get(p).setDisable(true);
							blankQuestionFormUIController.getSaveBtn().setVisible(false);
						}
					}
					;
				});

				// Event handler for edit button
				questionRow.getEditBtn().setOnAction(e -> {
					btnEventHandler.handle(e);
					{
						blankQuestionFormUIController.getNewQuestionFormLbl()
								.setText("Editing question " + questionRow.getID() + " by " + questionRow.getAuthor());
						field.add(questionRow.getField());
						blankQuestionFormUIController.getFieldCBox().setPromptText(field.get(0).toString());
						blankQuestionFormUIController.getFieldCBox().setDisable(true);
					}
					;
				});

			}

		}

		// Search by data which is in a certain row.
		FilteredList<questionRow> filteredData = new FilteredList<>(dataList, p -> true);

		// Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(myObject -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty())
					return true;

				// Compares what we wrote in the text (we searched for) to the appropriate line.
				String lowerCaseFilter = newValue.toLowerCase();
				if (String.valueOf(myObject.getID()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches ID.
				else if (String.valueOf(myObject.getAuthor()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches author.
				else if (String.valueOf(myObject.getField()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches field.
				else if (String.valueOf(myObject.getContent()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches content.
				else if (String.valueOf(myObject.getAuthor()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches author.
				return false; // Does not match.
			});
		});

		// Wrap the FilteredList in a SortedList.
		SortedList<questionRow> sortedData = new SortedList<questionRow>(filteredData);
		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(questionBankTable.comparatorProperty());
		// Add sorted (and filtered) data to the table.
		questionBankTable.setItems(sortedData);
	}

}
