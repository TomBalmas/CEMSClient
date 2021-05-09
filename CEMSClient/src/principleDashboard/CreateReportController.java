package principleDashboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CreateReportController implements Initializable {

	@FXML
	private Label selectTypelbl;

	@FXML
	private JFXComboBox<?> selectTypeCbox;

	@FXML
	private AnchorPane student1Pane;

	@FXML
	private JFXTreeTableView<?> studentTbl;

	@FXML
	private AnchorPane student2pane;

	@FXML
	private JFXDatePicker startDP;

	@FXML
	private JFXDatePicker finishDP;

	@FXML
	private JFXTreeTableView<?> coursesTbl;

	@FXML
	private JFXButton backBtn;

	@FXML
	private JFXButton createStudentBtn;

	@FXML
	private AnchorPane teacherPane;

	@FXML
	private JFXTreeTableView<?> teacherTbl;

	@FXML
	private JFXButton createTeacherBtn;

	@FXML
	private AnchorPane coursesPane;

	@FXML
	private JFXDatePicker finishCoursesDP;

	@FXML
	private JFXDatePicker startCoursesDP;

	@FXML
	private JFXButton createCoursesBtn;

	@SuppressWarnings("rawtypes")
	private ObservableList options = FXCollections.observableArrayList("Student", "Teacher", "Courses");

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectTypeCbox.setItems(options);
	}

}
