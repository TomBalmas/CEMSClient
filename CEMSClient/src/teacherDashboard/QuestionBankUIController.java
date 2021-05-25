package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.Question;
import common.Teacher;
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
import util.GeneralUIMethods;
import util.Navigator;

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

	private Node blankQuestionForm;
	private Node QuestionForm;

	// ----------TODO: add teachers for priciple
	private ObservableList filterBySelectBox = FXCollections.observableArrayList("Anyone", "You", "Others");

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
			blankQuestionForm = FXMLLoader.load(getClass().getResource(Navigator.BLANK_QUESTION_FORM.getVal()));
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
			this.question = question;
			id = question.getID();
			author = question.getAuthor();
			field = question.getField();
			this.ViewBtn = new JFXButton("View");
			this.DeleteBtn = new JFXButton();
			this.EditBtn = new JFXButton();
			this.DeleteBtn.setText("Delete");
			this.EditBtn.setText("Edit");

			ViewBtn.setMaxWidth(Double.MAX_VALUE);

			DeleteBtn.setMaxWidth(Double.MAX_VALUE);
			EditBtn.setMaxWidth(Double.MAX_VALUE);
			DeleteBtn.setStyle("-fx-background-color: TEAL;");
			EditBtn.setStyle("-fx-background-color: TEAL;");
			ViewBtn.setStyle("-fx-background-color: TEAL;");
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

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectCbox.setItems(filterBySelectBox);
		ArrayList<Question> questions = null;
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			ClientController.accept("QUESTION_BANK-" + teacher.getFields());
			questions = ClientController.getQuestions();
		}
		PropertyValueFactory IDfactory = new PropertyValueFactory<>("ID");
		PropertyValueFactory fieldfactory = new PropertyValueFactory<>("field");
		PropertyValueFactory authorFactory = new PropertyValueFactory<>("author");
		PropertyValueFactory viewFactory = new PropertyValueFactory<>("ViewBtn");
		fieldCol.setCellValueFactory(fieldfactory);
		IDCol.setCellValueFactory(IDfactory);
		authorCol.setCellValueFactory(authorFactory);
		// viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
		viewCol.setCellValueFactory(viewFactory);
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("DeleteBtn"));
		editCol.setCellValueFactory(new PropertyValueFactory<>("EditBtn"));
		if (questions != null) {
			for (int i = 0; i < questions.size(); i++) {
				questionRow qr = new questionRow(questions.get(i));
				questionBankTable.getItems().add(qr);
				tableViewAnchor.setMouseTransparent(false);
				qr.getViewBtn().setOnAction(new EventHandler<ActionEvent>() { // delete form table and DB
					@Override
					public void handle(ActionEvent event) {
						try {
							QuestionForm = FXMLLoader.load(getClass().getResource(Navigator.QUESTION_FORM.getVal()));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						GeneralUIMethods.loadPage(contentPaneAnchor, QuestionForm);
					}
				});

			}
		}

	}

}
