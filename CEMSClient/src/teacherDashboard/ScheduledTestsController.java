package teacherDashboard;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
	private JFXTreeTableView<?> scheduledTestsTbl;

	@FXML
	private JFXButton changeDateBtn;

	@FXML
	private Label scheduledTestsLbl;
	
    @FXML
    private JFXDatePicker changeDateField;
    
    @FXML
    private JFXTimePicker changeTimeField;

	@FXML
	void changeDateClicked(MouseEvent event) {
		List<JFXButton> list = new ArrayList<JFXButton>();
		list.add(new JFXButton("Okay"));
		System.out.println(GeneralUIMethods.getPopupPane());
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupPane(), contentPaneAnchor, GeneralUIMethods.getSideBar(),
				list, "Date and time changed successfully!", "");
	}

}
