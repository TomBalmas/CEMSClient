package studentDashboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import client.ClientController;
import common.ActiveTest;
import common.StudentGrade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import teacherDashboard.ViewActiveTestsController.rowTableActiveTest;

public class StudentGradesController implements Initializable {

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private JFXComboBox<?> selectFieldCbox;

	@FXML
	private JFXTextField searchField;

	@FXML
	private Label startDPlbl;

	@FXML
	private JFXDatePicker testsFromDP;

	@FXML
	private Label endDPlbl;

	@FXML
	private JFXDatePicker testTillDP;

	@FXML
	private JFXButton filterButton;

	@FXML
	private JFXComboBox<?> selectCourseCbox;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<rowTableGrades> gradesTable;

	@FXML
	private TableColumn<?, ?> testIdCol;

	@FXML
	private TableColumn<?, ?> courseCol;

	@FXML
	private TableColumn<?, ?> titleCol;

	@FXML
	private TableColumn<?, ?> gradeCol;

	private final ObservableList<rowTableGrades> dataList = FXCollections.observableArrayList();



	@FXML
	void filterBtn(MouseEvent event) {

	}

	@FXML
	void courseFilterClick(MouseEvent event) {

	}

	@FXML
	void dpFilter(MouseEvent event) {

	}

	@FXML
	void filedFilterClick(MouseEvent event) {

	}

	public class rowTableGrades {

		private String testId;
		private String course;
		private String title;
		private int grade;

		public rowTableGrades(StudentGrade studentGrade) {

			this.testId = studentGrade.getTestId();
			this.course = studentGrade.getCourse();
			this.title = studentGrade.getTitle();
			this.grade = studentGrade.getGrade();
		}

		public String getTestId() {
			return testId;
		}

		public String getCourse() {
			return course;
		}

		public String getTitle() {
			return title;
		}

		public int getGrade() {
			return grade;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		// selectCourseCbox.setItems(course);

		ArrayList<StudentGrade> studentGrades = null;

		ClientController.accept("GET_GRADES_BY_SSN-" + ClientController.getActiveUser().getSSN());
		studentGrades = ClientController.getGrades(); // Receiving a query from the server.
		
		// Insert into columns of the table, columns from DB.
		testIdCol.setCellValueFactory(new PropertyValueFactory<>("testId"));
		courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		gradeCol.setCellValueFactory(new PropertyValueFactory<>("grade"));

		if (studentGrades != null)
			for (StudentGrade studentGrade : studentGrades) {
				rowTableGrades rowTable = new rowTableGrades(studentGrade);
				gradesTable.getItems().add(rowTable);
				dataList.add(rowTable); // add row to dataList to search field.

			}

		// Search by data which is in a certain row.
		FilteredList<rowTableGrades> filteredData = new FilteredList<>(dataList, p -> true);

		// Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(myObject -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}

				// Compares what we wrote in the text (we searched for) to the appropriate line.
				String lowerCaseFilter = newValue.toLowerCase();

				if (String.valueOf(myObject.getTestId()).toLowerCase().contains(lowerCaseFilter)) {
					return true;
					// Filter matches ID.
				}

				else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches course.
				}

				else if (String.valueOf(myObject.getTitle()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches field.
				}

				else if (String.valueOf(myObject.getGrade()).toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches test name.
				}

				return false; // Does not match.
			});
		});

		// Wrap the FilteredList in a SortedList.
		SortedList<rowTableGrades> sortedData = new SortedList<>(filteredData);

		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(gradesTable.comparatorProperty());
		// Add sorted (and filtered) data to the table.
		gradesTable.setItems(sortedData);

	

	}

}
