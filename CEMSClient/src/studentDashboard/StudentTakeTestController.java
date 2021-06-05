package studentDashboard;

import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.ScheduledTest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import teacherDashboard.TestFormController;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class StudentTakeTestController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private JFXRadioButton computedBtn;

	@FXML
	private ToggleGroup testGroup;

	@FXML
	private JFXRadioButton manualBtn;

	@FXML
	private Label codeLbl;

	@FXML
	private JFXTextField testCodeField;

	@FXML
	private JFXButton beginTestBtn;

	@FXML
	private Label takeTestLbl;

	@FXML
	private Label testOptLbl;

	FXMLLoader testFormLoader = null;
	String testType = null;

	@FXML
	void beginTestClicked(MouseEvent event) {
		if (testCodeField.getText().equals("")) {
			PopUp.showMaterialDialog(PopUp.TYPE.ERROR, "Error", "Your must enter a test code", contentPaneAnchor, null,
					null);
			return;
		}
		testFormLoader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
		if (testGroup.getSelectedToggle().equals(manualBtn))
			testType = "Manual";
		else
			testType = "Computed";
		GeneralUIMethods.buildTestForm(contentPaneAnchor, null, testCodeField.getText(), testType, testFormLoader);
		TestFormController tfc = testFormLoader.getController();
		ClientController.accept("GET_TEST_BY_CODE-" + testCodeField.getText());
		int duration = ClientController.getStudentTest().getTestDuration();
		ClientController.accept("GET_SCHEDULED_TEST_BY_CODE-" + testCodeField.getText());
		ScheduledTest st = ClientController.getScheduledTest();
		String startingTime = st.getStartingTime();
		String[] splitTime = startingTime.split(":");
		LocalTime testTime = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
		testTime = testTime.plusMinutes(duration);
		// initial duration
		tfc.getTimeLbl1().setText(testTime.toString());
		// timer comes here maybe: TODO

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		manualBtn.setToggleGroup(testGroup);
		computedBtn.setToggleGroup(testGroup);
	}

}
