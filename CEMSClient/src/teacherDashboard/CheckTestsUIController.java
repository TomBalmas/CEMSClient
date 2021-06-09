package teacherDashboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.FinishedTest;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import util.GeneralUIMethods;
import util.Navigator;

public class CheckTestsUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private JFXButton submitBtn;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private Label checkTestLbl;

	@FXML
	private JFXTextField searchField;

	@FXML
	private JFXButton filterButton;

	@FXML
	private Label timeLeftLbl1;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<rowTableCheckTests> testTbl;

	@FXML
	private TableColumn<?, ?> testIDCol;

	@FXML
	private TableColumn<?, ?> courseCol;

	@FXML
	private TableColumn<?, ?> titleCol;

	@FXML
	private TableColumn<?, ?> dateCol;

	@FXML
	private TableColumn<?, ?> startingTime;

	@FXML
	private TableColumn<?, ?> studentSSNCol;

	@FXML
	private TableColumn<?, ?> GradeCol;

	@FXML
	private TableColumn<?, ?> statusCol;
	
    @FXML
    private TableColumn<?, ?> checkCol;
	
    @FXML
    private AnchorPane testAnchor;

    @FXML
    private JFXButton backToPageBtn;

    @FXML
    private ScrollPane testScrollPane;

    @FXML
    private AnchorPane testAnchor2;

    @FXML
    private AnchorPane studentTestDetailsAnchor;

    @FXML
    private Label checkingStudentTestLbl;

    @FXML
    private JFXTextArea notesTxtArea;

    @FXML
    private JFXButton updateBtn;
    
    @FXML
    private JFXTextField newGrade;

    @FXML
    private Label automaticGradeLbl;
    
	private Node checkBtn;
	private TestFormController testForm;
	private final ObservableList<rowTableCheckTests> dataList = FXCollections.observableArrayList();
	private rowTableCheckTests selectedRow;

	@FXML
	void filterBtn(MouseEvent event) {

	}

    @FXML
    void backToPageBtnClicked(MouseEvent event) {
		testAnchor.setVisible(false);
		testAnchor.toBack();
		studentTestDetailsAnchor.setVisible(false);
    }
    
    @FXML
    void updateBtnClicked(MouseEvent event) {

    }
    
//	@FXML
//	void submitClicked(MouseEvent event) {
//		try {
//			FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
//			checkBtn = loader.load();
//			TestFormController cont = loader.getController();// TODO: Ohad get content from selectedRow.getTest();
//			GeneralUIMethods.loadPage(contentPaneAnchor, checkBtn);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//	}

	/**
	 * Internal class to define a row in tableView.
	 *
	 */
	public class rowTableCheckTests {

		private String ID;
		private String course;
		private String title;
		private String date;
		private String startingTime;
		private String studentSSN;
		private int grade;
		private String status;
		private FinishedTest test;
		private JFXButton checkBtn;

		public rowTableCheckTests(FinishedTest checkTest) {
			this.ID = checkTest.getID();
			this.course = checkTest.getCourse();
			this.title = checkTest.getTitle();
			this.date = checkTest.getDate();
			this.startingTime = checkTest.getStartingTime();
			this.studentSSN = checkTest.getStudentSSN();
			this.grade = checkTest.getGrade();
			this.status = checkTest.getStatus();
			test = checkTest;
			checkBtn = new JFXButton();
			checkBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EDIT));
		}

		public String getID() {
			return ID;
		}

		public String getCourse() {
			return course;
		}

		public String getTitle() {
			return title;
		}

		public String getDate() {
			return date;
		}

		public String getStartingTime() {
			return startingTime;
		}

		public String getStudentSSN() {
			return studentSSN;
		}

		public int getGrade() {
			return grade;
		}

		public String getStatus() {
			return status;
		}

		public FinishedTest getTest() {
			return test;
		}

		public JFXButton getCheckBtn() {
			return checkBtn;
		}

		public void setCheckBtn(JFXButton checkBtn) {
			this.checkBtn = checkBtn;
		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<FinishedTest> tests = null;
		ClientController.accept("FINISHED_TESTS-" + ClientController.getActiveUser().getSSN());
		//TODO: ADD MANUAL TESTS
		tests = ClientController.getFinishedTests();
		testIDCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		startingTime.setCellValueFactory(new PropertyValueFactory<>("startingTime"));
		studentSSNCol.setCellValueFactory(new PropertyValueFactory<>("studentSSN"));
		GradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));
		statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));
		checkCol.setCellValueFactory(new PropertyValueFactory<>("checkBtn"));

		if (tests != null)
			for (FinishedTest test : tests) {
				rowTableCheckTests tr = new rowTableCheckTests(test);
				testTbl.getItems().add(tr);
				dataList.add(tr); // add row to dataList to search field.

				// View button
				tr.getCheckBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						FXMLLoader testFormLoader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
						GeneralUIMethods.buildTestForm(contentPaneAnchor, null, tr.getTest().getID(),
								"TEACHER_CHECKING", testFormLoader);
						TestFormController tfc = testFormLoader.getController();
						ClientController.accept("GET_COPY_SUSPECTS-" + test.getID() + "," + test.getDate() + "," + test.getStartingTime());
						ArrayList<Pair<String,String>> copiedStudents = ClientController.getCopiedStudents();
						if(null != copiedStudents && copiedStudents.size() > 0) {
							tfc.getCopyResultLbl().setText("Yes");
							tfc.getCopyWithLbl().setText("With " + copiedStudents.get(0).getValue());
							tfc.getCopyWithLbl().setVisible(true);
						}
						tfc.getScrollPane().setTranslateX(0);
						tfc.getTestTitleFromFXMLLbl().setText("Viewing test " + tr.getTitle() + " by " + tr.getStudentSSN());
						tfc.getTeacherCheckTestSideBar().setVisible(true);
						tfc.getGradeLbl().setText(tr.getGrade() + "");
						if(tr.getGrade() < 55) tfc.getGradeLbl().getStyleClass().add("fGradeLbl");
						else tfc.getGradeLbl().getStyleClass().add("aGradeLbl");
						//TODO: get student answers
						for (ToggleGroup tg : tfc.getQuestionsToggleGroup()) {
							tg.getToggles().get(0).setSelected(true);
						}
						
					}
				});
			}

		// Search by data which is in a certain row.
		FilteredList<rowTableCheckTests> filteredData = new FilteredList<>(dataList, p -> true);
		// Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(myObject -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty())
					return true;
				// Compare first name and last name field in your object with filter.
				String lowerCaseFilter = newValue.toLowerCase();
				if (String.valueOf(myObject.getID()).toLowerCase().contains(lowerCaseFilter)) {
					return true;
					// Filter matches ID.
				} else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches course.
				} else if (String.valueOf(myObject.getTitle()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches title.
				} else if (String.valueOf(myObject.getDate()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches date.
				} else if (String.valueOf(myObject.getStartingTime()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches starting time.
				} else if (String.valueOf(myObject.getStudentSSN()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches studentSSN.
				} else if (String.valueOf(myObject.getGrade()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches grade.
				} else if (String.valueOf(myObject.getStatus()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches status.
				}
				return false; // Does not match.
			});
		});

		// Wrap the FilteredList in a SortedList.
		SortedList<rowTableCheckTests> sortedData = new SortedList<>(filteredData);
		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(testTbl.comparatorProperty());
		// Add sorted (and filtered) data to the table.
		testTbl.setItems(sortedData);
		testTbl.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() >= 1)
				if (testTbl.getSelectionModel().getSelectedItem() != null)
					selectedRow = testTbl.getSelectionModel().getSelectedItem();
		});

	}

} 
 