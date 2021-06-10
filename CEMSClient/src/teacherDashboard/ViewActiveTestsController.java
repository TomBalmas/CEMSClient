package teacherDashboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.ActiveTest;
import common.Student;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
	private JFXButton searchBtn;

	@FXML
	private Label timeLeftLbl1;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<rowTableActiveTest> activeTestsTbl;

	@FXML
	private TableColumn<?, ?> idCol;

	@FXML
	private TableColumn<?, ?> fieldCol;

	@FXML
	private TableColumn<?, ?> CourseCol;

	@FXML
	private TableColumn<?, ?> testNameCol;

	@FXML
	private TableColumn<?, ?> AuthorNameCol;

	@FXML
	private TableColumn<?, ?> startTimeCol;

	@FXML
	private TableColumn<?, ?> finishTimeCol;

	@FXML
	private TableColumn<?, ?> lockCol;

	@FXML
	private TableColumn<?, ?> viewCol;

	@FXML
	private AnchorPane testDetailsAnchor;

	@FXML
	private AnchorPane requestTimeAnchor;

	@FXML
	private Label studentsTestLbl;

	@FXML
	private Label testSelectedLbl2;

	@FXML
	private JFXTextArea reasonForRequestTxt;

	@FXML
	private JFXButton senfForApprovalBtn;

	@FXML
	private JFXTextField minutesTxt;

	@FXML
	private AnchorPane testAnchor;

	@FXML
	private JFXButton backToPageBtn;

	@FXML
	private ScrollPane testScrollPane;

	@FXML
	private AnchorPane testAnchor2;

	private String selectedRow;
	private final ObservableList<rowTableActiveTest> dataList = FXCollections.observableArrayList();

	/**
	 * this method shows the popup that the request for time extension is approved
	 * need to connect to active test screen
	 */
	@FXML
	void clicksendForApproval(MouseEvent event) {
		// Request for time extension
		ClientController.accept("ADD_TIME_EXTENSION_REQUEST-" + ClientController.getActiveUser().getSSN() + ","
				+ reasonForRequestTxt.getText() + "," + selectedRow + "," + minutesTxt.getText()); // 																				
		ClientController.accept("NOTIFY_PRINCIPLE");
		if (ClientController.isPrincipleNotified())
			PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Your request sent for principles approval!",
					contentPaneAnchor, null, null);
		else
			PopUp.showMaterialDialog(PopUp.TYPE.ERROR, "Information", "Error in sending request!", contentPaneAnchor,
					null, null);
	}

	// -----------TODO--------------
	@FXML
	void searchBtnClicked(MouseEvent event) {

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
		private JFXButton viewBtn;
		private JFXButton lockBtn;

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
			viewBtn = new JFXButton();
			lockBtn = new JFXButton();
			viewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
			lockBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.LOCK));
		}

		public JFXButton getViewBtn() {
			return viewBtn;
		}

		public void setViewBtn(JFXButton viewBtn) {
			this.viewBtn = viewBtn;
		}

		public JFXButton getLockBtn() {
			return lockBtn;
		}

		public void setLockBtn(JFXButton lockBtn) {
			this.lockBtn = lockBtn;
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
		ArrayList<ActiveTest> activeTests = null;

		// Request from the client(query) to the server.
		if (ClientController.getRoleFrame().equals("Teacher")) {
			ClientController.accept("ACTIVE_TEST-" + ClientController.getActiveUser().getSSN()); 
			activeTests = ClientController.getActiveTests(); // Receiving data the query in the server
		}
		// Insert into columns of the table, columns from DB.
		idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
		testNameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
		AuthorNameCol.setCellValueFactory(new PropertyValueFactory<>("authorName"));
		CourseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		fieldCol.setCellValueFactory(new PropertyValueFactory<>("field"));
		startTimeCol.setCellValueFactory(new PropertyValueFactory<>("startTimeTest"));
		finishTimeCol.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
		lockCol.setCellValueFactory(new PropertyValueFactory<>("lockBtn"));
		viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
		if (activeTests != null)
			for (ActiveTest activeTest : activeTests) {
				rowTableActiveTest tr = new rowTableActiveTest(activeTest);
				activeTestsTbl.getItems().add(tr);
				dataList.add(tr); // Add row to dataList to search field

				// View button
				tr.getViewBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						FXMLLoader viewTestLoader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
						testAnchor.setVisible(true);
						testAnchor.toFront();
						GeneralUIMethods.buildTestForm(testAnchor2, testScrollPane, tr.getActiveTest().getCode(),
								"TEACHER_VIEW_TEST_BY_CODE", viewTestLoader);
					}
				});

				tr.getLockBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						FXMLLoader lockTestLoader = new FXMLLoader(getClass().getResource(Navigator.LOCK_TEST.getVal()));
						PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "LOCK_TEST", "", contentPaneAnchor,
								Arrays.asList(new JFXButton("Cancel")), lockTestLoader);
						LockTestController cont = lockTestLoader.getController();
						cont.getTestNameLbl().setText(tr.getTestName());
						cont.getConfirmTestLock().setOnMouseClicked(e -> {
							ClientController.accept("GET_STUDENTS_IN_TEST_BY_CODE-" + tr.getActiveTest().getCode());
							ArrayList<Student> students = ClientController.getStudents();
							StringBuilder sb = new StringBuilder();
							for (Student student : students) {
								sb.append(student.getSSN());
								sb.append(",");
							}
							sb.deleteCharAt(sb.length() - 1);
							System.out.println(sb);
							ClientController.accept("NOTIFY_STUDENTS_BY_SSN-" + sb.toString()); //TODO - bohad fixed
							System.out.println("tom the orange4");
							if (ClientController.isTestLocked()) {
								PopUp.showMaterialDialog(PopUp.TYPE.ALERT, "Success", "Tests " + tr.getID() + " is now locked.",
										contentPaneAnchor, null, null);
								((JFXButton) arg0.getSource()).setGraphic(new FontAwesomeIconView(FontAwesomeIcon.UNLOCK));
							}
							else
								PopUp.showMaterialDialog(PopUp.TYPE.ALERT, "Failed", "Could not lock test " + tr.getID() ,
										contentPaneAnchor, null, null);
						});
					}
				});
			}
		// Search by data which is in a certain row.
		FilteredList<rowTableActiveTest> filteredData = new FilteredList<>(dataList, p -> true);
		// Set the filter Predicate whenever the filter changes.
		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(myObject -> {
				// If filter text is empty, display all persons.
				if (newValue == null || newValue.isEmpty())
					return true;
				// Compares what we wrote in the text (we searched for) to the appropriate line.
				String lowerCaseFilter = newValue.toLowerCase();
				// Filter matches ID.
				if (String.valueOf(myObject.getID()).toLowerCase().contains(lowerCaseFilter))
					return true;
				else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches course.
				else if (String.valueOf(myObject.getField()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches field.
				else if (String.valueOf(myObject.getTestName()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches test name.
				else if (String.valueOf(myObject.getAuthorName()).toLowerCase().contains(lowerCaseFilter)) 
					return true; // Filter matches author name.
				else if (String.valueOf(myObject.getStartTimeTest()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches start time.
				else if (String.valueOf(myObject.getFinishTime()).toLowerCase().contains(lowerCaseFilter))
					return true; // Filter matches finish time.
				return false; // Does not match.
			});
		});

		// Wrap the FilteredList in a SortedList.
		SortedList<rowTableActiveTest> sortedData = new SortedList<>(filteredData);
		// Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(activeTestsTbl.comparatorProperty());
		// Add sorted (and filtered) data to the table.
		activeTestsTbl.setItems(sortedData);

		// An event lets us click on a row in the table to see details like ID, Finish Time and Time Left.
		activeTestsTbl.setOnMouseClicked((MouseEvent event) -> {
			if (event.getClickCount() >= 1)
				if (activeTestsTbl.getSelectionModel().getSelectedItem() != null) {
					rowTableActiveTest selected = activeTestsTbl.getSelectionModel().getSelectedItem();
					// timeLeftField.setText(selected.get); // TODO: in the future
					// testCodeLable.setText(selected.arg0);
					selectedRow = selected.getActiveTest().getCode(); // Get the ID to request query from server about
					testSelectedLbl2.setText(selected.getTestName() + " (" + selectedRow + ")"); // time extension.
				}
		});

	}

	@FXML
	void backToPageBtnClicked(MouseEvent event) {
		testAnchor.setVisible(false);
		testAnchor.toBack();
	}
}
 