package studentDashboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
			PopUp.showMaterialDialog(PopUp.TYPE.ERROR, "Error", "Your must enter a test code", contentPaneAnchor, null, null);
			return;
		}
		testFormLoader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
		if (testGroup.getSelectedToggle().equals(manualBtn))
			testType = "Manual";
		else
			testType = "Computed";
		GeneralUIMethods.buildTestForm(contentPaneAnchor, null, testCodeField.getText(), testType, testFormLoader);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		manualBtn.setToggleGroup(testGroup);
		computedBtn.setToggleGroup(testGroup);
	}

}
