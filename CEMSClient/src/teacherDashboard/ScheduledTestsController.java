package teacherDashboard;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;

public class ScheduledTestsController {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private Label scheduledTestsLbl;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXButton filterButton;

    @FXML
    private Label timeLeftLbl1;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private JFXTreeTableView<?> scheduledTestsTbl;

    @FXML
    private AnchorPane setTestTimeAnchor;

    @FXML
    private AnchorPane insideFilterAnchor1;

    @FXML
    private Label testCodeLbl;

    @FXML
    private JFXTextField testCodeField;

    @FXML
    private Label testSelectedLbl;

    @FXML
    private JFXButton changeDateBtn;

    @FXML
    private JFXDatePicker changeDateField;

    @FXML
    private JFXTimePicker changeTimeField;

    @FXML
    private Label testCodeLbl1;

    @FXML
    private Label testCodeLbl11;

    @FXML
    void filterBtn(MouseEvent event) {

    }

	@FXML
	void changeDateClicked(MouseEvent event) {
		List<JFXButton> list = new ArrayList<JFXButton>();
		list.add(new JFXButton("Okay"));
		System.out.println(GeneralUIMethods.getPopupPane());
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupPane(), contentPaneAnchor, GeneralUIMethods.getSideBar(),
				list, "Date and time changed successfully!", "");
	}

}
