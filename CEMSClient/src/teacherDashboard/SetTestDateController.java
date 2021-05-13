package teacherDashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class SetTestDateController {

	
	/**
	 * 
	 *	The test name
	 */
    @FXML
    private Label testNameLbl;

    @FXML
    private JFXDatePicker dateDP;

    @FXML
    private JFXTimePicker timeTP;

    @FXML
    private JFXTextField codeTxt;

    @FXML
    private JFXButton setBtn;

}
