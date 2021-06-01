package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.layout.Region;
import sun.awt.SunHints.Value;
import teacherDashboard.TestBankUIController.TestRow;
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
	private JFXComboBox<?> selectCbox;

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
	private JFXButton searchBtn;

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
	private TableColumn<?, ?> viewCol;

	@FXML
	private TableColumn<?, ?> editCol;

	@FXML
	private TableColumn<?, ?> deleteCol;

	@FXML
	private JFXButton addAnewQuestionBtn;

	private Node blankQuestionForm, QuestionForm;
	private QuestionFormUIController blankQuestionFormUIController;
	 String authorName;

	// ----------TODO: add teachers for priciple
	private ObservableList filterBySelectBox = FXCollections.observableArrayList("Anyone", "You", "Others");
	
	//lists for combobox fields .fields is for adding a new question.field is for viewing specific question
	ObservableList fields = FXCollections.observableArrayList();
	ObservableList field = FXCollections.observableArrayList();


	private String authorString;
	public String getAuthorString() {
		return authorString;
	}

	public void setAuthorString(String authorString) {
		this.authorString = authorString;
	}

	@FXML
	void searchBtnClicked(MouseEvent event) {

	}

	/**
	 * clicking add a new question will go to blank question for page.
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

	public class questionRow {
		private String id;
		private String author;
		private String field;
		private JFXButton ViewBtn;
		private JFXButton DeleteBtn;
		private JFXButton EditBtn;
		private Question question;

		public questionRow(Question question) {
			String  authorID=question.getAuthorID();
			ClientController.accept("GET_NAME_BY_ID-"+ authorID);
			author=ClientController.getAuthorName();
			this.question = question;
			id = question.getID();
			field = question.getField();
			this.ViewBtn = new JFXButton();
			this.DeleteBtn = new JFXButton();
			this.EditBtn = new JFXButton();
			DeleteBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TRASH));
			EditBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EDIT));
			ViewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
			DeleteBtn.setStyle("-fx-fill: red !important;");
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

	}

	/**
	 getting data from question table into question bank screen according to teachers fields
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		selectCbox.setItems(filterBySelectBox);
		ArrayList<Question> questions = null;
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			
			//setting the fields into the combo box
			
			String[] fieldsSplit = teacher.getFields().split("~");
			for (String oneField : fieldsSplit)
				fields.add(oneField);
				//calling query for getting teachers field questions 
			ClientController.accept("QUESTION_BANK-" + teacher.getFields());
			questions = ClientController.getQuestions();
		}

		if (ClientController.getRoleFrame().equals("Principle")) {
			Principle  principle  = (Principle) ClientController.getActiveUser();
			//calling query for getting teachers field questions 
			ClientController.accept("GET_QUESTIONS_TABLE-");
			questions = ClientController.getQuestions();
			viewCol.setVisible(false);
			deleteCol.setVisible(false);
			editCol.setVisible(false);
		}
		
	  //adding PropertyValueFactory for the columns
		PropertyValueFactory IDfactory = new PropertyValueFactory<>("ID");
		PropertyValueFactory fieldfactory = new PropertyValueFactory<>("field");
		PropertyValueFactory authorFactory = new PropertyValueFactory<>("author");
		PropertyValueFactory viewFactory = new PropertyValueFactory<>("ViewBtn");
		fieldCol.setCellValueFactory(fieldfactory);
		IDCol.setCellValueFactory(IDfactory);
		authorCol.setCellValueFactory(authorFactory);
		viewCol.setCellValueFactory(viewFactory);
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("DeleteBtn"));
		editCol.setCellValueFactory(new PropertyValueFactory<>("EditBtn"));
		
		if (questions != null) {
			for (int i = 0; i < questions.size(); i++) {
				questionRow questionRow = new questionRow(questions.get(i));
				questionBankTable.getItems().add(questionRow);
				tableViewAnchor.setMouseTransparent(false);
				EventHandler<ActionEvent> btnEventHandler = new EventHandler<ActionEvent>() { 
					@Override
					public void handle(ActionEvent event) {
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.QUESTION_FORM.getVal()));
							QuestionForm = loader.load();
							blankQuestionFormUIController = loader.getController();
							blankQuestionFormUIController.getNewQuestionFormLbl().setText("Editing question " + questionRow.getID() + " by " + ClientController.getActiveUser().getName());
							blankQuestionFormUIController.getQuestionContentTxt().setText(questionRow.getQuestion().getQuestionText());
							blankQuestionFormUIController.getAnswerBtns().get(questionRow.getQuestion().getCorrectAnswer()-1).setSelected(true);
							for(int j = 0; j < 4; j++)
								blankQuestionFormUIController.getAnswerTextFields().get(j).setText(questionRow.getQuestion().getAnswers().get(j));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						GeneralUIMethods.loadPage(contentPaneAnchor, QuestionForm);
					}
				};
				
				//Event handler for deletion from table and DB
				questionRow.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() { // delete form table and DB
					@Override
					public void handle(ActionEvent event) {
						JFXButton yesBtn = new JFXButton("Yes");
						yesBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
					    	ClientController.accept("DELETE_QUESTION-" + questionRow.getID());
					    	if (!ClientController.isQuestionDeleted())
								System.out.println("not working");
					    	questionBankTable.getItems().remove(questionRow);
							PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "The question " + questionRow.getID() + " has been deleted", contentPaneAnchor, null, null);
						});
						PopUp.showMaterialDialog(PopUp.TYPE.ALERT, "Alert", "Are you sure that you want to delete this question?",
								contentPaneAnchor, Arrays.asList(yesBtn, new JFXButton("No")), null);			
					}
				});
				
				//event handler for deletion form table and DB
//				questionRow.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() { 
//					@Override
//					public void handle(ActionEvent event) {
//				    	ClientController.accept("DELETE_QUESTION-" + questionRow.getID());
//				    	if (!ClientController.isQuestionDeleted())
//							System.out.println("not working");
//				    	questionBankTable.getItems().remove(questionRow);
//						
//				    };
//				});
//				
				
				//event handler for view button 
				questionRow.getViewBtn().setOnAction(e ->{
					btnEventHandler.handle(e);
				    {
				    	field.add(questionRow.getField());
						blankQuestionFormUIController.getNewQuestionFormLbl().setText("Viewing question " + questionRow.getID() + " by " + ClientController.getActiveUser().getName());
				    	blankQuestionFormUIController.getFieldCBox().setPromptText(field.get(0).toString());
				    	blankQuestionFormUIController.getQuestionContentTxt().setEditable(false);
				    	blankQuestionFormUIController.getFieldCBox().setDisable(true);
						for(int p = 0; p < 4; p++) {
							blankQuestionFormUIController.getAnswerTextFields().get(p).setEditable(false);
							blankQuestionFormUIController.getAnswerBtns().get(p).setDisable(true);
							blankQuestionFormUIController.getSaveBtn().setVisible(false);
						}
						
				    };
				});
				
				// event handler for edit button
				questionRow.getEditBtn().setOnAction(e -> {
					btnEventHandler.handle(e);
					{
						field.add(questionRow.getField());
						blankQuestionFormUIController.getFieldCBox().setPromptText(field.get(0).toString());
						blankQuestionFormUIController.getFieldCBox().setDisable(true);
					};
				});

			}

		}

	}

}
