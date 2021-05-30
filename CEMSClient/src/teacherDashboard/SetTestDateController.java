package teacherDashboard;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class SetTestDateController implements Initializable {

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

	public AnchorPane getContentPaneAnchor() {
		return contentPaneAnchor;
	}

	public Label getTestNameLbl() {
		return testNameLbl;
	}

	public JFXDatePicker getDateDP() {
		return dateDP;
	}

	public JFXTimePicker getTimeTP() {
		return timeTP;
	}

	public JFXTextField getCodeTxt() {
		return codeTxt;
	}

	public JFXButton getSetDateBtn() {
		return setDateBtn;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setDateBtn.setDisable(true);
		dateDP.setOnAction(e -> {
			if (LocalDate.now().compareTo(dateDP.getValue()) <= 0 )
				setDateBtn.setDisable(false);
			else
				setDateBtn.setDisable(true);
		});
		

	}

}
