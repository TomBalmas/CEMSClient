package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import client.ClientController;
import common.Course;
import common.Question;
import common.Report;
import common.Student;
import common.Teacher;
import common.User;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import teacherDashboard.QuestionBankUIController.questionRow;
import teacherDashboard.ViewActiveTestsController.rowTableActiveTest;
import util.GeneralUIMethods;
import util.Navigator;

public class CreateReportController implements Initializable {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private JFXComboBox<?> selectTypeCbox;

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
    private JFXButton filterButton;
    
    @FXML
    private Label selectCourseLbl;

    @FXML
    private JFXComboBox<?> selectCourseCbox;

    @FXML
    private AnchorPane tableViewAnchor;


    @FXML
    private TableView<userRow> reportsTbl;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> IDCol;
    ArrayList<Student> students = null;
    ArrayList<Teacher> teachers = null;
    ArrayList<Course> courses = null;

    @FXML
    private JFXButton createReportBtn;
	ObservableList coursesSelection = FXCollections.observableArrayList();
	private ObservableList options = FXCollections.observableArrayList("Student", "Teacher", "Courses");

	
	public class userRow {
		private String id;
		private String author;
		
		public userRow(User user) {
		  id= user.getSSN();
			author=user.getName();
		}
		public userRow(Course course) {
			  id= course.getId();
				author=course.getName();
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		
		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


	}
	public class courseRow {
		private String id;
		private String author;
		
		public courseRow(Course course) {
		  id= course.getId();
			author=course.getName();
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		
		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}

	}
	
	
	
	
	/**
	 * init function that putting values in the combo box, that function also
	 * changing view by the combo box value.
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectTypeCbox.setItems(options);
		selectCourseCbox.valueProperty().set(null);
		
		//setting up table columns 
		PropertyValueFactory IDfactory = new PropertyValueFactory<>("id");
		PropertyValueFactory namefactory = new PropertyValueFactory<>("author");
		IDCol.setCellValueFactory(IDfactory);
		nameCol.setCellValueFactory(namefactory);
		
	
		//handke clicking on a student row 
		reportsTbl.setOnMouseClicked((MouseEvent event) -> {
			
				userRow selected = reportsTbl.getSelectionModel().getSelectedItem();
				ClientController.accept("GET_COURSES_BY_STUDENT-"+selected.getId());
				courses = ClientController.getCourses();
				selectCourseCbox.getItems().removeAll(selectCourseCbox.getItems());
				for (int i=0;i<courses.size();i++)
					coursesSelection.add(courses.get(i).getName());
				selectCourseCbox.setItems(coursesSelection);
			
			

		});
		
		selectTypeCbox.setOnAction((event) -> {
		
			Object selectedItem = selectTypeCbox.getSelectionModel().getSelectedItem();
			if (selectTypeCbox.getValue().equals("Student")) {
				
				startCoursesDP.setVisible(false);
				finishCoursesDP.setVisible(false);
				startDPlbl.setVisible(false);
				endDPlbl.setVisible(false);
				selectCourseCbox.setVisible(true);
				selectCourseLbl.setVisible(true);
				searchField.setPromptText("Search by student name/last name");
				ClientController.accept("GET_STUDENTS-");
				students = ClientController.getStudents();
				reportsTbl.getItems().clear();
				if (students != null) {
					for (int i = 0; i < students.size(); i++) {
						userRow usersRow = new userRow(students.get(i));
						tableViewAnchor.setMouseTransparent(false);
						reportsTbl.getItems().add(usersRow);
						
					}
				}
				
			} else if (selectTypeCbox.getValue().equals("Courses")) {
				startCoursesDP.setVisible(true);
				finishCoursesDP.setVisible(true);
				startDPlbl.setVisible(true);
				endDPlbl.setVisible(true);
				selectCourseCbox.setVisible(false);
				selectCourseLbl.setVisible(false);
				searchField.setPromptText("Search by field/course name or code");
				ClientController.accept("GET_COURSES-");
				courses = ClientController.getCourses();
				reportsTbl.getItems().clear();
				if (courses != null) {
					for (int i = 0; i < courses.size(); i++) {
						userRow courseRow = new userRow(courses.get(i));
						tableViewAnchor.setMouseTransparent(false);
						reportsTbl.getItems().add(courseRow);
					}
				}
				//teacher
			} else {
				startCoursesDP.setVisible(false);
				finishCoursesDP.setVisible(false);
				startDPlbl.setVisible(false);
				endDPlbl.setVisible(false);
				selectCourseCbox.setVisible(false);
				selectCourseLbl.setVisible(false);
				searchField.setPromptText("Search by teacher name/last name");
				ClientController.accept("GET_TEACHERS-");
				reportsTbl.getItems().clear();
				teachers = ClientController.getTeachers();
				if (teachers != null) {
					for (int i = 0; i < teachers.size(); i++) {
						userRow usersRow = new userRow(teachers.get(i));
						tableViewAnchor.setMouseTransparent(false);
						reportsTbl.getItems().add(usersRow);
					}
				}
			}
			
		});

	}

	@FXML
	void createReportBtn(MouseEvent event) {
		Node createReport = null;
		userRow selected = reportsTbl.getSelectionModel().getSelectedItem();
		System.out.println("CREATE_STUDENT_REPORT-"+selected.getId()+ ","+selectCourseCbox.getValue());
		ClientController.accept("CREATE_STUDENT_REPORT-"+selected.getId()+ ","+selectCourseCbox.getValue());
		Report report = ClientController.getReport();
		try {
			createReport = FXMLLoader.load(getClass().getResource(Navigator.REPORT_CHART.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, createReport);
	}

	@FXML
	void filterBtn(MouseEvent event) {

	}

}
