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
import common.Teacher;
import common.Test;
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

public class TestBankUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private AnchorPane anchorLogin;

	@FXML
	private AnchorPane tableViewAnchor;

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
	private Label testBankLbl;

	@FXML
	private TableView<TestRow> testTable;

	@FXML
	private TableColumn<?, ?> IDcol;

	@FXML
	private TableColumn<?, ?> testNameCol;

	@FXML
	private TableColumn<?, ?> authorCol;

	@FXML
	private TableColumn<?, ?> courseCol;

	@FXML
	private TableColumn<?, ?> fieldCol;

	@FXML
	private TableColumn<?, ?> editCol;

	@FXML
	private TableColumn<?, ?> setDateCol;

	@FXML
	private TableColumn<?, ?> viewCol;

	@FXML
	private TableColumn<?, ?> deleteCol;

	@FXML
	private JFXButton addNewTestButton;

	private Node addNewTest;

	public JFXButton getAddNewTestButton() {
		return addNewTestButton;
	}

	// ----------TODO: add teachers for priciple
	private ObservableList filterBySelectBox = FXCollections.observableArrayList("Anyone", "You", "Others");

	@FXML
	void searchBtnClicked(MouseEvent event) {

	}

	/**
	 * clicking add new test opens question bank screen
	 * 
	 * @param event
	 */
	@FXML
	void addNewTest(MouseEvent event) {
		try {
			addNewTest = FXMLLoader.load(getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, addNewTest);
	}

	/**
	 * this class is a class that defines the properties of the main table its gets
	 * a test and eliminates the unwanted attributes
	 *
	 */
	public class TestRow {
		private String testName;
		private int testId;
		private String author;
		private String course;
		private String field;
		private Test test;
		private JFXButton deleteBtn;
		private JFXButton viewBtn;
		private JFXButton setDateBtn;
		private JFXButton editBtn;

		public TestRow(Test test) {
			this.test = test;
			this.testName = test.getTitle();
			this.testId = test.getID();
			this.author = test.getAuthorName();
			this.course = test.getCourse();
			this.field = test.getField();
			viewBtn = new JFXButton();
			setDateBtn = new JFXButton();
			deleteBtn = new JFXButton();
			editBtn = new JFXButton();
			deleteBtn.setText("Delete");
			editBtn.setText("Edit");
			setDateBtn.setText("Set Date");
			viewBtn.setText("View");
			deleteBtn.setStyle("-fx-background-color: red;");
			editBtn.setStyle("-fx-background-color: green;");
			setDateBtn.setStyle("-fx-background-color: cyan;");
			viewBtn.setStyle("-fx-background-color: orange;");
		}

		public JFXButton getViewBtn() {
			return viewBtn;
		}

		public JFXButton getSetDateBtn() {
			return setDateBtn;
		}

		public JFXButton getEditBtn() {
			return editBtn;
		}

		public Test getTest() {
			return test;
		}

		public int getTestId() {
			return testId;
		}

		public void setTestId(int testId) {
			this.testId = testId;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getCourse() {
			return course;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getTestName() {
			return testName;
		}

		public void setTestName(String testName) {
			this.testName = testName;
		}

		public JFXButton getDeleteBtn() {
			return deleteBtn;
		}

	}

	/**
	 * setting the test table and its functionalities
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectCbox.setItems(filterBySelectBox);
		ArrayList<Test> arr = null;
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			ClientController.accept("TEST_BANK-" + teacher.getFields().toString());
			arr = ClientController.getTests();
		}
		IDcol.setCellValueFactory(new PropertyValueFactory<>("testId"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		fieldCol.setCellValueFactory(new PropertyValueFactory<>("field"));
		testNameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));
		setDateCol.setCellValueFactory(new PropertyValueFactory<>("setDateBtn"));
		viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
		editCol.setCellValueFactory(new PropertyValueFactory<>("editBtn"));

		for (int i = 0; i < arr.size(); i++) {
			TestRow tr = new TestRow(arr.get(i));
			testTable.getItems().add(tr);
			tr.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() { // delete form table and DB
				@Override
				public void handle(ActionEvent event) {
					TestRow toDelete = tr;
					ClientController.accept("DELETE_TEST-" + tr.test.getID());
					if (!ClientController.getTestDeleted())
						System.out.println("not working");
					testTable.getItems().remove(toDelete);
				}
			});
		}

	}

}
