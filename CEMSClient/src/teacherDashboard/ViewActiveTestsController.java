package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.ActiveTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
	private TableColumn<?, ?> finishTimeCol;

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
	private JFXTextField minutesTxt;

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
	private Label testNameLabel;

	@FXML
	private Label testCodeLable;

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
	private String selectedRow;
	private final ObservableList<rowTableActiveTest> dataList = FXCollections.observableArrayList();
			
	
	/**
	 * clicking lock will open pop up screen that confirms the lock.
	 * 
	 * @param event
	 */
	@FXML
	void lockClicked(MouseEvent event) {
		PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Test " + CODE + " is now locked!",
				contentPaneAnchor, null, null);
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

		ClientController.accept("ADD_TIME_EXTENSION_REQUEST-" + ClientController.getActiveUser().getSSN() + ","
				+ reasonForRequestTxt.getText() + "," + selectedRow + "," + minutesTxt.getText()); // Request for
																									// extension time.
		ClientController.accept("NOTIFY_PRINCIPLE");
		if (ClientController.isPrincipleNotified()) {
			List<JFXButton> l = new ArrayList<JFXButton>();
			l.add(new JFXButton("Okay"));
			PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Your request sent for principles approval!",
					contentPaneAnchor, null, null);
		} else {
			List<JFXButton> l = new ArrayList<JFXButton>();
			l.add(new JFXButton("Okay"));
			PopUp.showMaterialDialog(PopUp.TYPE.ERROR, "Information", "Error in sending request!", contentPaneAnchor,
					null, null);
		}

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

	/**
	 * Pressing the back button on the Lock Test or Request Time Extension
	 * anchorPanes returns to the Test Selected anchorPane.
	 */
	@FXML
	void backBtnClicked(MouseEvent event) {
		lockTestAnchor.setVisible(false);
		requestTimeAnchor.setVisible(false);
		backBtn.setVisible(false);
		selectedTestAnchor.setVisible(true);
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
		private String code;
		private ActiveTest activeTest;

		public rowTableActiveTest(ActiveTest activeTest) {
			this.activeTest = activeTest;
			id = activeTest.getID();
			title = activeTest.getTitle();
			authorName = activeTest.getAuthorName();
			course = activeTest.getCourse();
			field = activeTest.getField();
			startTimeTest = activeTest.getStartTimeTest();
			finishTime = activeTest.getFinishTime();
			code = activeTest.getCode();
		}

		public String getFinishTime() {
			return finishTime;
		}

		public ActiveTest getActiveTest() {
			return activeTest;
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
		// Insert into columns of the table, columns from DB.
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		testNameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
		AuthorNameCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
		CourseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		fieldCol1.setCellValueFactory(new PropertyValueFactory<>("field"));
		startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTimeTest"));
		finishTimeCol.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
		if (activeTests != null)
			for (ActiveTest activeTest : activeTests) {
				rowTableActiveTest tr = new rowTableActiveTest(activeTest);
				activeTestsTbl.getItems().add(tr);
				dataList.add(tr); //add row to dataList to search field.
			}
		//Search by data which is in a certain row.
		FilteredList<rowTableActiveTest> filteredData = new FilteredList<>(dataList, p -> true);

        // Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(myObject -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compares what we wrote in the text (we searched for) to the appropriate line.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(myObject.getID()).toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                    // Filter matches ID.
                } 
                
            	else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter)) {
            		return true; // Filter matches course.
            	} 
                
                else if (String.valueOf(myObject.getField()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches field.
                } 
                
                else if (String.valueOf(myObject.getTestName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches test name.
                } 
                
                else if (String.valueOf(myObject.getAuthorName()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches author name.
                } 
                
                else if (String.valueOf(myObject.getStartTimeTest()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches start time.
                } 
                
                else if (String.valueOf(myObject.getFinishTime()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches finish time.
                } 

                return false; // Does not match.
            });
        });

        //  Wrap the FilteredList in a SortedList. 
        SortedList<rowTableActiveTest> sortedData = new SortedList<>(filteredData);

        //  Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(activeTestsTbl.comparatorProperty());
        //  Add sorted (and filtered) data to the table.
        activeTestsTbl.setItems(sortedData);
        
        
        
		
		// An event lets us click on a row in the table to see details like ID, Finish
		// Time and Time Left.
		activeTestsTbl.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() >= 1) {
				if (activeTestsTbl.getSelectionModel().getSelectedItem() != null) {
					rowTableActiveTest selected = activeTestsTbl.getSelectionModel().getSelectedItem();
					testCodeField.setText(selected.getActiveTest().getCode());
					// timeLeftField.setText(selected.get); // TODO: in the future
					finishTimeField.setText(selected.getFinishTime()); // TODO
					testNameLabel.setText(selected.getCourse()); // TODO
					// testCodeLable.setText(selected.arg0);
					selectedRow = selected.getActiveTest().getCode(); // Get the ID to request query from server about
																		// time extension.
				}
			}

		});
		
		
     
		
	

	}
}
