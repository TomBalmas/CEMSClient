package studentDashboard;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.ScheduledTest;
import javafx.application.Platform;
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

public class StudentTakeTestController implements Initializable, Observer {

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
	private String testCode;
	private LocalTime testTime;
	private TestFormController tfc;

	/**
	 * gets the test for the student when he clicks begin test. includes checking
	 * all relevant conditions for him to be able to take the test.
	 */
	@FXML
	void beginTestClicked(MouseEvent event) {
		// Check if the student entered a test code
		if (testCodeField.getText().equals("")) {
			new PopUp(PopUp.TYPE.ERROR, "Error", "Your must enter a test code", contentPaneAnchor, null, null);
			return;
		}

		// Check if the test is already active
		ClientController.accept("IS_TEST_ACTIVE-" + testCodeField.getText());
		if (!ClientController.getIsActiveTest()) // If not, check if its the time for test
			ClientController.accept("IS_TIME_FOR_TEST-" + GeneralUIMethods.israeliDate(LocalDate.now()) + ","
					+ LocalTime.now() + "," + testCodeField.getText());
		if (ClientController.isTimeForTest()) {
			testFormLoader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
			if (testGroup.getSelectedToggle().equals(manualBtn))
				testType = "Manual";
			else
				testType = "Computed";
			// Create the test form
			GeneralUIMethods.buildTestForm(contentPaneAnchor, null, testCodeField.getText(), testType, testFormLoader);

			// Add student to the students in tests table
			if (ClientController.getStudentTest() != null) {
				tfc = testFormLoader.getController();
				ClientController.accept("ADD_STUDENT_IN_TEST-" + ClientController.getActiveUser().getSSN() + ","
						+ testCodeField.getText());
				if (!ClientController.isStudentAddedToTest()) {
					new PopUp(PopUp.TYPE.ERROR, "Error", "An error accured while registration to the test",
							contentPaneAnchor, null, null);
					return;
				}
				// Set test due to
				int duration = ClientController.getStudentTest().getTestDuration();
				ClientController.accept("GET_SCHEDULED_TEST_BY_CODE-" + testCodeField.getText());
				ScheduledTest st = ClientController.getScheduledTest();
				// Set test time label
				String startingTime = st.getStartingTime();
				String[] splitTime = startingTime.split(":");
				testTime = LocalTime.of(Integer.parseInt(splitTime[0]), Integer.parseInt(splitTime[1]));
				testTime = testTime.plusMinutes(duration);
				// initial duration
				tfc.getTimeLbl1().setText(testTime.toString());
				// timer comes here maybe: TODO
			}
		} else // If the test is not scheduled for now
			new PopUp(PopUp.TYPE.ERROR, "Error", "The test is locked for entrance", contentPaneAnchor, null, null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClientController.setStudentTakeTestController(this);
		manualBtn.setToggleGroup(testGroup);
		computedBtn.setToggleGroup(testGroup);
	}

	/**
	 * this method gets notification from server when the principle approves time
	 * extension or locks the test. test locks, or test gets time time extension on
	 * clients side.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println((String) arg1);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (((String) arg1).startsWith("timeExtension")) {
					String[] split = ((String) arg1).split(":");
					testTime = testTime.plusMinutes(Integer.parseInt(split[1]));
					tfc.getTimeLbl1().setText(testTime.toString());
					tfc.getNewTimeLbl().setVisible(true);
				} else if (((String) arg1).startsWith("lockTest")) {
					if (tfc.getFinishBtn().isDisable())
						tfc.getFinishBtn().setDisable(false);
					tfc.setSubmittedBy("Forced");
					ClientController.setStudentLocked(true);
					tfc.getFinishBtn().fire();
					ClientController.setStudentLocked(false);
				}
			}
		});

	}

}
