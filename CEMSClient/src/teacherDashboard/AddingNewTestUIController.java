package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import client.ClientController;
import common.Question;
import common.Teacher;
import common.Test;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class AddingNewTestUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private TableView<QuestionRow> questionTable;

	@FXML
	private TableColumn<?, ?> idCol;

	@FXML
	private TableColumn<?, ?> textCol;

	@FXML
	private TableColumn<?, ?> authorCol;

	@FXML
	private TableColumn<?, ?> viewCol;

	@FXML
	private TableColumn<?, ?> selectCol;

	@FXML
	private JFXButton backBtn;

	@FXML
	private JFXButton continueBtn;

	@FXML
	private Label instructionsLbl;

	@FXML
	private JFXComboBox<?> selectFieldComboBox;

	private Node testBank;

	ObservableList fields = FXCollections.observableArrayList();

	public class QuestionRow {
		private String id;
		private String author;
		private String text;
		private JFXButton viewBtn;
		private CheckBox checkBox;

		public QuestionRow(Question question) {
			id = question.getID();
			author = question.getAuthor();
			text = question.getQuestionText();
			viewBtn = new JFXButton();
			viewBtn.setText("View");
			viewBtn.setStyle("-fx-background-color: teal;");
			checkBox = new CheckBox();
			checkBox.setText("Select");
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
		ArrayList<Question> picked = new ArrayList<>();

		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			fields.addAll(teacher.getFields());
		}
		selectFieldComboBox.setItems(fields);
		selectFieldComboBox.setOnAction(event -> {
			questionTable.getItems().clear();
			ClientController.accept("QUESTION_BANK-[" + selectFieldComboBox.getValue() + "]");
			ArrayList<Question> questions = ClientController.getQuestions();
			idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
			textCol.setCellValueFactory(new PropertyValueFactory<>("text"));
			authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
			viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
			selectCol.setCellValueFactory(new PropertyValueFactory<>("checkBox"));
			for (Question q : questions) {
				QuestionRow qr = new QuestionRow(q);
				questionTable.getItems().add(qr);
				qr.getCheckBox().setOnAction(eventCheck -> {
					if (qr.getCheckBox().isSelected())
						picked.add(q);
					else
						picked.remove(q);
					for (Question qe : picked)
						System.out.println(qe.getID());
				});
			}

		});

	}

	/**
	 * clicking back will go back to the test bank screen
	 * 
	 * @param event
	 */
	@FXML
	void clickBack(MouseEvent event) {
		try {
			testBank = FXMLLoader.load(getClass().getResource(Navigator.TEST_BANK.getVal()));
			contentPaneAnchor.getChildren().setAll(testBank);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * clicking continue will move to blank test form only if at least one question
	 * was chosen.
	 * 
	 * @param event
	 */
	@FXML
	void clickContinue(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
			Node test;
			test = loader.load();
			TestFormController controller = loader.getController();
			controller.getEditBtn().setVisible(false);
			controller.addQuestionToTestForm(); // need to get questions from DB
			controller.addQuestionToTestForm(); // need to get questions from DB
			controller.addQuestionToTestForm(); // need to get questions from DB
			GeneralUIMethods.loadPage(contentPaneAnchor, test);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// -------------need to implement an if statement that will block passage if no
		// questions were selected--------------
	}

}
