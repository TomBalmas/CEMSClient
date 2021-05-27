package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.ActiveTest;
import common.Teacher;
import common.Test;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import teacherDashboard.TestBankUIController.TestRow;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

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
	private JFXButton searchButton;

	@FXML
	private Label timeLeftLbl1;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<rowTableActiveTest> activeTestsTbl;

	@FXML
	private TableColumn<?, ?> idCol;

	@FXML
	private TableColumn<?, ?> testNameCol;

	@FXML
	private TableColumn<?, ?> AuthorNameCol;

	@FXML
	private TableColumn<?, ?> CourseCol;

	@FXML
	private TableColumn<?, ?> fieldCol1;

	@FXML
	private TableColumn<?, ?> startTimeCol;

	@FXML
	private TableColumn<?, ?> endTimeCol;

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
	private Label enterCodeLbl;

	@FXML
	private JFXTextField enterCodeField;

	@FXML
	private JFXButton lockBtn;

	@FXML
	private Label msgLbl;

	@FXML
	private JFXButton backBtn;

	@FXML
	private Label testSelectedLbl1;

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

	private Node requestTimeExtension;
	private Node viewTest;
	private String CODE = "Toosick22"; // -----------need to compare the code with a code from the DB----------- *1

	/**
	 * clicking lock will open pop up screen that confirms the lock.
	 * 
	 * @param event
	 */
	@FXML
	void lockClicked(MouseEvent event) {
		PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Test " + CODE + " is now locked!", contentPaneAnchor, null, null);	
		lockBtn.setText("Locked");
		lockBtn.setDisable(true);
	}

	/**
	 * clicking lock test will reveal "enter code" segment and another lock button
	 * for extra step of safety before locking a test.
	 * 
	 * @param event
	 */
	@FXML
	void lockTestClicked(MouseEvent event) {
		backBtn.setVisible(true);
		lockTestAnchor.setVisible(true);
		selectedTestAnchor.setVisible(false);
	}

	/**
	 * clicking a test on the table will enable the lock test button and disable the
	 * lock and "enter code" segment for extra safety.
	 * 
	 * @param event
	 */
	@FXML
	void activeTestClicked(MouseEvent event) {// -----------need to complete the method, action: clicking a test on the
												// table----------- *2
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
		if (GeneralUIMethods.validateCode(enterCodeField, CODE)) // -----------need to compare the code with a code from
																	// the DB----------- *1
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
		backBtn.setVisible(true);
		requestTimeAnchor.setVisible(true);
		selectedTestAnchor.setVisible(false);
	}

	/**
	 * this method shows the popup that the request for time extension is approved
	 * need to connect to active test screen
	 */
	@FXML
	void clicksendForApproval(MouseEvent event) {
		List<JFXButton> l = new ArrayList<JFXButton>();
		l.add(new JFXButton("Okay"));
		PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Your request sent for principles approval!", contentPaneAnchor, null, null);	
	}

	@FXML
	void viewTestClicked(MouseEvent event) {
		Node viewTestPage = null;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
		try {
			viewTestPage = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TestFormController testController = loader.getController();
		testController.getEditBtn().setVisible(false);
		GeneralUIMethods.loadPage(contentPaneAnchor, viewTestPage);
	}

	// -----------TODO--------------
	@FXML
	void searchBtnClicked(MouseEvent event) {

	}

	// -----------TODO--------------
	@FXML
	void backBtnClicked(MouseEvent event) {

	}

	
	/**
	 * Internal class to define a row in tableView.
	 *
	 */
	public class rowTableActiveTest {

		private String id;
		private String title;
		private String authorName;
		private String course;
		private String field;
		private String startTimeTest;
		private String finishTime;

		public rowTableActiveTest(ActiveTest activeTest) {

			id = activeTest.getID();
			title = activeTest.getTitle();
			authorName = activeTest.getAuthorName();
			course = activeTest.getCourse();
			field = activeTest.getField();
			startTimeTest = activeTest.getStartTimeTest();
			finishTime = activeTest.getFinishTime();

		}

		public String getFinishTime() {
			return finishTime;
		}

		public String getID() {
			return id;
		}

		public String getTestName() {
			return title;
		}

		public String getAuthorName() {
			return authorName;
		}

		public String getCourse() {
			return course;
		}

		public String getField() {
			return field;
		}

		public String getStartTimeTest() {
			return startTimeTest;
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		backBtn.toFront();
		lockBtn.setDisable(true);
		try {
			viewTest = FXMLLoader.load(getClass().getResource(Navigator.TEST_FORM.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ArrayList<ActiveTest> activeTests = null;

		if (ClientController.getRoleFrame().equals("Teacher")) {
			ClientController.accept("ACTIVE_TEST-" + ClientController.getActiveUser().getSSN()); // Request from the
																									// client(query) to
																									// the server.
			activeTests = ClientController.getActiveTests(); // Receiving a query from the server.
		}
		//Insert into columns of the table, columns from DB.
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		testNameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
		AuthorNameCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
		CourseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		fieldCol1.setCellValueFactory(new PropertyValueFactory<>("field"));
		startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTimeTest"));
		endTimeCol.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
		if (activeTests != null)
			for (ActiveTest activeTest : activeTests) {
				rowTableActiveTest tr = new rowTableActiveTest(activeTest);
				activeTestsTbl.getItems().add(tr);
			}
		//An event lets us click on a row in the table to see details like ID, Finish Time and Time Left.
		activeTestsTbl.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() >= 1) {
				if (activeTestsTbl.getSelectionModel().getSelectedItem() != null) {
					rowTableActiveTest selected = activeTestsTbl.getSelectionModel().getSelectedItem();
					testCodeField.setText(selected.getID());
					// timeLeftField.setText(selected.get); // TODO: in the future
					finishTimeField.setText(selected.getFinishTime());
				}
			}

		});

	}
}
