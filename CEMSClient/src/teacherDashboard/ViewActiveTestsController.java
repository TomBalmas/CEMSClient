package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
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
    private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private Label viewActiveTestsLbl;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXButton filterButton;

    @FXML
    private Label timeLeftLbl1;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private JFXTreeTableView<?> activeTestsTbl;

    @FXML
    private AnchorPane testDetailsAnchor;

    @FXML
    private AnchorPane requestTimeAnchor;

    @FXML
    private Label studentsTestLbl;

    @FXML
    private JFXTextArea reasonForRequestTxt;

    @FXML
    private JFXButton senfForApprovalBtn;

    @FXML
    private AnchorPane lockTestAnchor;

    @FXML
    private Label testSelectedLbl1;

    @FXML
    private Label enterCodeLbl;

    @FXML
    private JFXTextField enterCodeField;

    @FXML
    private JFXButton lockBtn;

    @FXML
    private Label msgLbl;

    @FXML
    private AnchorPane selectedTestAnchor;

    @FXML
    private Label testSelectedLbl;

    @FXML
    private Label timeLeftLbl;

    @FXML
    private JFXButton lequestTimeExtensionBtn;

    @FXML
    private Label finishTimeLbl;

    @FXML
    private JFXButton lockTestBtn;

    @FXML
    private JFXTextField timeLeftField;

    @FXML
    private JFXTextField finishTimeField;

    @FXML
    private Label testCodeLbl;

    @FXML
    private JFXTextField testCodeField;

    @FXML
    private JFXButton viewTestBtn;
    
	private Node requestTimeExtension, viewTest;
	private String CODE = "Toosick22";	//-----------need to compare the code with a code from the DB----------- *1

	/**
	 * clicking lock will open pop up screen that confirms the lock.
	 * 
	 * @param event
	 */
	@FXML
	void lockClicked(MouseEvent event) {
		List<JFXButton> buttonsList = new ArrayList<JFXButton>();
		buttonsList.add(new JFXButton("Okay"));
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupPane(), contentPaneAnchor, GeneralUIMethods.getSideBar(),
				buttonsList, "Test " + CODE + " is now locked!", "");
	}

	/**
	 * clicking lock test will reveal "enter code" segment and
	 * another lock button for extra step of safety before locking a test.
	 * 
	 * @param event
	 */
	@FXML
	void lockTestClicked(MouseEvent event) {
		lockTestAnchor.setVisible(true);
		selectedTestAnchor.setVisible(false);
//		lockBtn.setVisible(true);
//		enterCodeField.setVisible(true);
//		enterCodeLbl.setVisible(true);
//		lockTestBtn.setDisable(true);
//		lockBtn.setDisable(true);
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
	void requestTimeExtensionClicked(MouseEvent event) {
		requestTimeAnchor.setVisible(true);
		selectedTestAnchor.setVisible(false);
		//GeneralUIMethods.loadPage(contentPaneAnchor, requestTimeExtension);
	}
	
	/**
	 * this method shows the popup that the request for time extension is approved
	 * need to connect to active test screen
	 */
	@FXML
	void clicksendForApproval(MouseEvent event) {
		List<JFXButton> l = new ArrayList<JFXButton>();
		l.add(new JFXButton("Okay"));
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupPane(), contentPaneAnchor, GeneralUIMethods.getSideBar(),
				l, "Request sent for principles approval", "");
	}
	
    @FXML
    void viewTestClicked(MouseEvent event) {
    	GeneralUIMethods.loadPage(contentPaneAnchor, viewTest);
    }

    @FXML
    void filterBtn(MouseEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
//		lockBtn.setVisible(false);
//		enterCodeField.setVisible(false);
//		enterCodeLbl.setVisible(false);
		try {
			viewTest = FXMLLoader.load(getClass().getResource(Navigator.TEST_FORM.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
