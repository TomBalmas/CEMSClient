package principleDashboard;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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

	/**
	 * init function that putting values in the combo box, that function also
	 * changing view by the combo box value.
	 *
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectTypeCbox.setItems(options);

		selectTypeCbox.setOnAction((event) -> {
			Object selectedItem = selectTypeCbox.getSelectionModel().getSelectedItem();
			if (selectTypeCbox.getValue().equals("Student")) {
				System.out.println(selectTypeCbox.getValue());
				coursesPane.setVisible(false);
				teacherPane.setVisible(false);
				student1Pane.setVisible(true);

			} else if (selectTypeCbox.getValue().equals("Courses")) {
				System.out.println(selectTypeCbox.getValue());
				student1Pane.setVisible(false);
				teacherPane.setVisible(false);
				coursesPane.setVisible(true);
			} else {
				System.out.println(selectTypeCbox.getValue());
				coursesPane.setVisible(false);
				student1Pane.setVisible(false);
				teacherPane.setVisible(true);
			}

		});

	}

}
