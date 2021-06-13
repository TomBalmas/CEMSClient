package studentDashboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.StudentGrade;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class StudentGradesController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private JFXTextField searchField;

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

	@FXML
	private TableColumn<?, ?> viewCol;

	@FXML
	private AnchorPane testAnchor;

	@FXML
	private JFXButton backToPageBtn;

	@FXML
	private ScrollPane testScrollPane;

	@FXML
	private AnchorPane testAnchor2;

	@FXML
	void backToPageBtnClicked(MouseEvent event) {
		testAnchor.setVisible(false);
		testAnchor.toBack();
	}

	private final ObservableList<rowTableGrades> dataList = FXCollections.observableArrayList();

	/**
	 * class for the grades data to be presented int the table view
	 *
	 */
	public class rowTableGrades {
		private String testId;
		private String course;
		private String title;
		private int grade;
		private JFXButton gradeBtn;
		private JFXButton viewBtn;

		public rowTableGrades(StudentGrade studentGrade) {
			this.testId = studentGrade.getTestId();
			this.course = studentGrade.getCourse();
			this.title = studentGrade.getTitle();
			this.grade = studentGrade.getGrade();
			gradeBtn = new JFXButton(this.grade + "");
			viewBtn = new JFXButton();
			viewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
			gradeBtn.setStyle("-fx-background-color: transparent; -fx-text-fill: #595869;");
			if (grade < 55)
				gradeBtn.getStyleClass().add("gradeColor");

		}

		public JFXButton getGradeBtn() {
			return gradeBtn;
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

		public JFXButton getViewBtn() {
			return viewBtn;
		}

	}

	/**
	 * sets the grades data into the table view
	 */
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
		gradeCol.setCellValueFactory(new PropertyValueFactory<>("gradeBtn"));
		viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
		if (studentGrades != null)
			for (StudentGrade studentGrade : studentGrades) {
				rowTableGrades rowTable = new rowTableGrades(studentGrade);
				gradesTable.getItems().add(rowTable);
				dataList.add(rowTable); // add row to dataList to search field.

				// View button
				rowTable.getViewBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						FXMLLoader testFormLoader = new FXMLLoader(
								getClass().getResource(Navigator.TEST_FORM.getVal()));
						testAnchor.setVisible(true);
						testAnchor.toFront();
						GeneralUIMethods.buildTestForm(testAnchor2, testScrollPane, rowTable.getTestId(),
								"STUDENT_LOOK", testFormLoader);
					}
				});
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
