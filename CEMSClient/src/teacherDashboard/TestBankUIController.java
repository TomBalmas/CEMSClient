package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import client.ClientController;
import common.Teacher;
import common.Test;
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
	private Label testBankLbl;

	@FXML
	private TableView<TestRow> testTable;

	@FXML
	private TableColumn<?, ?> IDcol;

	@FXML
	private TableColumn<TestRow, String> testNameCol;

	@FXML
	private TableColumn<?, ?> authorCol;

	@FXML
	private TableColumn<?, ?> courseCol;

	@FXML
	private TableColumn<?, ?> fieldCol;

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

	/**clicking add new test opens question bank screen
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

	public class TestRow {
		private String testName;
		private int testId;
		private String author;
		private String course;
		private String field;

		public TestRow(int testId, String testName, String author, String course, String field) {
			this.testName = testName;
			this.testId = testId;
			this.author = author;
			this.course = course;
			this.field = field;
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

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectCbox.setItems(filterBySelectBox);
		ArrayList<Test> arr = null;
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			ClientController.accept("TEST_BANK-" + teacher.getFields().toString());
			arr = ClientController.getTests();
			for (Test test : arr)
				System.out.println(test);
		}
		IDcol.setCellValueFactory(new PropertyValueFactory<>("testId"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		fieldCol.setCellValueFactory(new PropertyValueFactory<>("field"));
		testNameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
		for (int i = 0; i < arr.size(); i++)
			testTable.getItems().add(new TestRow(arr.get(i).getID(), arr.get(i).getTestName(),
					arr.get(i).getAuthorName(), arr.get(i).getCourse(), arr.get(i).getField()));
	}

}
