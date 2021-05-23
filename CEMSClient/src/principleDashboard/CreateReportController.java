package principleDashboard;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private AnchorPane tableViewAnchor;

    @FXML
    private JFXTreeTableView<?> tblView;

    @FXML
    private JFXButton createReportBtn;

	private ObservableList options = FXCollections.observableArrayList("Student", "Teacher", "Courses");

	/**
	 * init function that putting values in the combo box, that function also
	 * changing view by the combo box value.
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectTypeCbox.setItems(options);

		selectTypeCbox.setOnAction((event) -> {
			Object selectedItem = selectTypeCbox.getSelectionModel().getSelectedItem();
			if (selectTypeCbox.getValue().equals("Student")) {
				startCoursesDP.setVisible(false);
				finishCoursesDP.setVisible(false);
				startDPlbl.setVisible(false);
				endDPlbl.setVisible(false);
				searchField.setPromptText("Search by student name/last name");
			} else if (selectTypeCbox.getValue().equals("Courses")) {
				startCoursesDP.setVisible(true);
				finishCoursesDP.setVisible(true);
				startDPlbl.setVisible(true);
				endDPlbl.setVisible(true);
				searchField.setPromptText("Search by field/course name or code");
			} else {
				startCoursesDP.setVisible(false);
				finishCoursesDP.setVisible(false);
				startDPlbl.setVisible(false);
				endDPlbl.setVisible(false);
				searchField.setPromptText("Search by teacher name/last name");
			}

		});

	}

	@FXML
	void createReportBtn(MouseEvent event) {
		Node createReport = null;
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
