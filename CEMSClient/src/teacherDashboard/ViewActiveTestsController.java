package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class ViewActiveTestsController implements Initializable {

	@FXML
	private Label viewActiveTestsLbl;

	@FXML
	private Label timeLeftLbl;

	@FXML
	private JFXButton lequestTimeExtensionBtn;

	@FXML
	private Label finishTimeLbl;

	@FXML
	private JFXButton lockTestBtn;

	@FXML
	private Label enterCodeLbl;

	@FXML
	private JFXTextField enterCodeField;

	@FXML
	private JFXButton lockBtn;

	@FXML
	private JFXTreeTableView<?> activeTestsTbl;

	@FXML
	private JFXTextField timeLeftField;

	@FXML
	private JFXTextField finishTimeField;

	@FXML
	private AnchorPane contentPaneAnchor;

	private Node requestTimeExtension;
	private String CODE = "Toosick22";	//-----------need to compare the code with a code from the DB----------- *1

	/**
	 * clicking lock will open pop up screen that confirms the lock.
	 * 
	 * @param event
	 */
	@FXML
	void lockClicked(MouseEvent event) {
		List<JFXButton> l = new ArrayList<JFXButton>();
		l.add(new JFXButton("Okay"));
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupPane(), contentPaneAnchor, GeneralUIMethods.getSideBar(),
				l, "Test " + CODE + " is now locked!", "");
	}

	/**
	 * clicking lock test will reveal "enter code" segment and
	 * another lock button for extra step of safety before locking a test.
	 * 
	 * @param event
	 */
	@FXML
	void lockTestClicked(MouseEvent event) {
		lockBtn.setVisible(true);
		enterCodeField.setVisible(true);
		enterCodeLbl.setVisible(true);
		lockTestBtn.setDisable(true);
		lockBtn.setDisable(true);
	}
	
	/**
	 * clicking a test on the table will enable the lock test button
	 * and disable the lock and "enter code" segment for extra safety.
	 * 
	 * @param event
	 */
	@FXML
	void activeTestClicked(MouseEvent event) {//-----------need to complete the method, action: clicking a test on the table----------- *2
		lockBtn.setVisible(false);
		enterCodeField.setVisible(false);
		enterCodeLbl.setVisible(false);
		lockTestBtn.setDisable(false);
		lockBtn.setDisable(false);
	}
	
	/**
	 * validates that the code in the text field is the same as a given string.
	 */
	@FXML
	void checkCode() {
		if(GeneralUIMethods.validateCode(enterCodeField, CODE))	//-----------need to compare the code with a code from the DB----------- *1
			lockBtn.setDisable(false);
		else
			lockBtn.setDisable(true);
	}

	/**
	 * clicking request time extension loads the "request time extension" screen.
	 * 
	 * @param event
	 */
	@FXML
	void requestTimeExtension(MouseEvent event) {
		GeneralUIMethods.loadPage(contentPaneAnchor, requestTimeExtension);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lockBtn.setVisible(false);
		enterCodeField.setVisible(false);
		enterCodeLbl.setVisible(false);
		try {
			requestTimeExtension = FXMLLoader.load(getClass().getResource(Navigator.REQUEST_TIME_EXTENSION.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
