package teacherDashboard;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import util.GeneralUIMethods;
import util.PopUp;

public class SetTestDateController {

    @FXML
    private AnchorPane contentPaneAnchor;

	@FXML
    private Label testNameLbl;

    @FXML
    private JFXDatePicker dateDP;

    @FXML
    private JFXTimePicker timeTP;

    @FXML
    private JFXTextField codeTxt;

    @FXML
    private JFXButton setDateBtn;

    @FXML
    void setDateClicked(MouseEvent event) {
		List<JFXButton> list = new ArrayList<JFXButton>();
		list.add(new JFXButton("Okay"));
		PopUp.showMaterialDialog(PopUp.TYPE.SUCCESS, "Success", "Date and time changed successfully!", contentPaneAnchor, null, null);	
    }

    public AnchorPane getContentPaneAnchor() {
		return contentPaneAnchor;
	}

}
