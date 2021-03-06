package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.Question;
import common.Teacher;
import common.Test;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

public class TestBankUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private Label testBankLbl;

	@FXML
	private JFXTextField searchField;

	@FXML
	private Label startDPlbl;

	@FXML
	private JFXDatePicker startCoursesDP;

	@FXML
	private Label endDPlbl;

	@FXML
	private JFXDatePicker finishCoursesDP;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<TestRow> testTable;

	@FXML
	private TableColumn<?, ?> IDcol;

	@FXML
	private TableColumn<?, ?> fieldCol;

	@FXML
	private TableColumn<?, ?> courseCol;

	@FXML
	private TableColumn<?, ?> testNameCol;

	@FXML
	private TableColumn<?, ?> authorCol;

	@FXML
	private TableColumn<?, ?> setDateCol;

	@FXML
	private TableColumn<?, ?> viewCol;

	@FXML
	private TableColumn<?, ?> editCol;

	@FXML
	private TableColumn<?, ?> deleteCol;

	@FXML
	private JFXButton addNewTestButton;

	@FXML
	private AnchorPane testAnchor;

	@FXML
	private JFXButton backToPageBtn;

	@FXML
	private ScrollPane testScrollPane;

	@FXML
	private AnchorPane testAnchor2;

	private Node addNewTest, AddingFormNode;
	private final ObservableList<TestRow> dataList = FXCollections.observableArrayList();
	private final ObservableList<TestRow> datarow = FXCollections.observableArrayList();
	PopUp p;
	
	public JFXButton getAddNewTestButton() {
		return addNewTestButton;
	}
	
	/**
	 * Go back to the previous page
	 * @param event
	 */
	@FXML
	void backToPageBtnClicked(MouseEvent event) {
		testAnchor.setVisible(false);
		testAnchor.toBack();
		addNewTestButton.setVisible(true);
	}

	/**
	 * Clicking add new test opens question bank screen
	 * 
	 * @param event
	 */
	@FXML
	void addNewTest(MouseEvent event) {
		try {
			addNewTest = FXMLLoader.load(getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, addNewTest);
	}

	/**
	 * Class that defines the properties of the main table its gets
	 * a test and eliminates the unwanted attributes
	 *
	 */
	public class TestRow {
		private String testName;
		private String testId;
		private String author;
		private String course;
		private String field;
		private Test test;
		private JFXButton deleteBtn;
		private JFXButton viewBtn;
		private JFXButton setDateBtn;
		private JFXButton editBtn;

		public TestRow(Test test) {
			this.test = test;
			this.testName = test.getTitle();
			this.testId = test.getID();
			this.author = test.getAuthorName();
			this.course = test.getCourse();
			this.field = test.getField();
			viewBtn = new JFXButton();
			setDateBtn = new JFXButton();
			deleteBtn = new JFXButton();
			editBtn = new JFXButton();
			setDateBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CALENDAR_ALT));
			deleteBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TRASH));
			editBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EDIT));
			viewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
		}

		public JFXButton getViewBtn() {
			return viewBtn;
		}

		public JFXButton getSetDateBtn() {
			return setDateBtn;
		}

		public JFXButton getEditBtn() {
			return editBtn;
		}

		public Test getTest() {
			return test;
		}

		public String getTestId() {
			return testId;
		}

		public void setTestId(String testId) {
			this.testId = testId;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getCourse() {
			return course;
		}

		public void setCourse(String course) {
			this.course = course;
		}

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getTestName() {
			return testName;
		}

		public void setTestName(String testName) {
			this.testName = testName;
		}

		public JFXButton getDeleteBtn() {
			return deleteBtn;
		}

	}

	/**
	 * Setting the test table and its functionalities
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Test> tests = null;
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			ClientController.accept("TEST_BANK-" + teacher.getFields());
			tests = ClientController.getTests();
		}
		if (ClientController.getRoleFrame().equals("Principle")) {
			ClientController.accept("GET_TESTS_TABLE-");
			tests = ClientController.getTests();
			int addToColum = 39;
			IDcol.setPrefWidth(IDcol.getWidth() + addToColum);
			authorCol.setPrefWidth(authorCol.getWidth() + addToColum);
			courseCol.setPrefWidth(courseCol.getWidth() + addToColum);
			fieldCol.setPrefWidth(fieldCol.getWidth() + addToColum);
			testNameCol.setPrefWidth(testNameCol.getWidth() + addToColum);
			authorCol.setPrefWidth(authorCol.getWidth() - 12);
			viewCol.setPrefWidth(viewCol.getWidth() + 12);
			deleteCol.setVisible(false);
			editCol.setVisible(false);
			setDateCol.setVisible(false);
		}
		IDcol.setCellValueFactory(new PropertyValueFactory<>("testId"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		fieldCol.setCellValueFactory(new PropertyValueFactory<>("field"));
		testNameCol.setCellValueFactory(new PropertyValueFactory<>("testName"));
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("deleteBtn"));
		setDateCol.setCellValueFactory(new PropertyValueFactory<>("setDateBtn"));
		viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
		editCol.setCellValueFactory(new PropertyValueFactory<>("editBtn"));
		Node viewTest;
		FXMLLoader viewTestLoader = new FXMLLoader(getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
		AddingNewTestUIController addingNewTestUIController = viewTestLoader.getController();
		try {
			viewTest = viewTestLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (tests != null) {
			for (int i = 0; i < tests.size(); i++) {
				TestRow tr = new TestRow(tests.get(i));
				testTable.getItems().add(tr);
				dataList.add(tr); // Add row to dataList to search field.
				datarow.add(tr);
				if (!ClientController.getActiveUser().getName().equals(tr.getAuthor())) {
					tr.getEditBtn().setDisable(true);
					tr.getDeleteBtn().setDisable(true);
				}
				// View button
				tr.getViewBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
						testAnchor.setVisible(true);
						testAnchor.toFront();
						addNewTestButton.setVisible(false);
						GeneralUIMethods.buildTestForm(testAnchor2, testScrollPane, tr.getTestId(), "", loader);
					}
				});

				// Edit button
				tr.getEditBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						try {
							FXMLLoader loader = new FXMLLoader(
									getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
							AddingFormNode = loader.load();
							AddingNewTestUIController editTestController = loader.getController();
							Set<Question> picked = editTestController.getPickedQuestions();
							ClientController.accept("GET_QUESTIONS_FROM_TEST-" + tr.getTestId());
							for (Question q : ClientController.getQuestions())
								picked.add(q);
							editTestController.getSelectFieldCBox().getSelectionModel().select(tr.getField());
							editTestController.getSelectFieldCBox().setDisable(true);
							editTestController.getSelectFieldCBox().fireEvent(arg0);
							editTestController.getSelectCourseCBox().getSelectionModel().select(tr.getCourse());
							editTestController.getSelectCourseCBox().setDisable(true);
							editTestController.getTitleTxt().setText(tr.getTestName());
							editTestController.getDurationCbox().getSelectionModel()
									.select(tr.getTest().getTestDuration().toString());
							editTestController.getTeacherInstructionsTxtArea()
									.setText(tr.getTest().getTeacherInstructions());
							editTestController.getStudentInstructionsTxtArea()
									.setText(tr.getTest().getStudentInstructions());

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									editTestController.setEditingTest(tr.getTestId());
								}
							});
						} catch (IOException e) {
							e.printStackTrace();
						}
						GeneralUIMethods.loadPage(contentPaneAnchor, AddingFormNode);
					}
				});

				// Schedule button
				tr.getSetDateBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.SET_TEST_DATE.getVal()));
						PopUp scheduleTestPopUp = new PopUp(PopUp.TYPE.INFORM, "ScheduleTest", "", contentPaneAnchor,
								Arrays.asList(new JFXButton("Cancel")), loader);
						SetTestDateController cont = loader.getController();
						cont.getTestNameLbl().setText(tr.getTestName());
						cont.getSetDateBtn().setOnMouseClicked(e -> {
							JFXButton approveBtn = new JFXButton("Okay");
							approveBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e1) -> {
								p.closePopUp();
							});
							// Check for empty fields
							if (cont.getCodeTxt().getText().isEmpty()
									|| cont.getDateDP().getValue() == null
									|| cont.getTimeTP().getValue() == null) {
								p = new PopUp(PopUp.TYPE.ALERT, "Error", "Some fields are missing", contentPaneAnchor,
										Arrays.asList(approveBtn), null);
								return;
							} else if (cont.getCodeTxt().getText().length() != 4) {
								p = new PopUp(PopUp.TYPE.ALERT, "Error",
										"Test code must be assembled from 4 chars and/or numbers", contentPaneAnchor,
										Arrays.asList(approveBtn), null);
								return;
							}
							// Set date for the test
							ClientController.accept("SET_TEST_DATE-" + tr.getTestId() + ","
									+ GeneralUIMethods.israeliDate(cont.getDateDP().getValue()) + ","
									+ cont.getTimeTP().getValue().toString() + ","
									+ ClientController.getActiveUser().getSSN() + "," + cont.getCodeTxt().getText());
							if (ClientController.isTestScheduled()) {
								// Show popup
								JFXButton okayBtn = new JFXButton("Okay");
								okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e1) -> {
									scheduleTestPopUp.hidePopUp();
								});
								new PopUp(PopUp.TYPE.INFORM, "Success", "Tests scheduled successfully",
										contentPaneAnchor, Arrays.asList(okayBtn), null);
							} else
								new PopUp(PopUp.TYPE.ALERT, "Failed", "Tests schedule failed, that code already exsist",
										contentPaneAnchor, null, null);
						});
					}
				});

				// Delete button
				tr.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() { // Delete form table and DB
					@Override
					public void handle(ActionEvent event) {
						JFXButton yesBtn = new JFXButton("Yes");
						yesBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
							ClientController.accept("DELETE_TEST-" + tr.test.getID());
							if (ClientController.isTestDeleted()) {
									JFXButton okayBtn = new JFXButton("Okay");
									okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e1) -> {
										try {
											GeneralUIMethods.loadPage(contentPaneAnchor, FXMLLoader
													.load(getClass().getResource(Navigator.TEST_BANK.getVal())));
										} catch (IOException e2) {
											e2.printStackTrace();
										}
									});
									new PopUp(PopUp.TYPE.INFORM, "Information",
											"The test " + tr.getTestId() + " has been deleted", contentPaneAnchor,
											Arrays.asList(okayBtn), null);
							}
						});
						new PopUp(PopUp.TYPE.ALERT, "Alert", "Are you sure that you want to delete this test?",
								contentPaneAnchor, Arrays.asList(yesBtn, new JFXButton("No")), null);
					}
				});
			}

			// Search by data which is in a certain row.
			FilteredList<TestRow> filteredData = new FilteredList<>(dataList, p -> true);

			// Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(myObject -> {
					// If filter text is empty, display all persons.
					if (newValue == null || newValue.isEmpty())
						return true;
					// Compares what we wrote in the text (we searched for) to the appropriate line.
					String lowerCaseFilter = newValue.toLowerCase();

					if (String.valueOf(myObject.getTestId()).toLowerCase().contains(lowerCaseFilter))
						return true; // Filter matches code.
					else if (String.valueOf(myObject.getField()).toLowerCase().contains(lowerCaseFilter))
						return true; // Filter matches field.
					else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter))
						return true; // Filter matches course.
					else if (String.valueOf(myObject.getTestName()).toLowerCase().contains(lowerCaseFilter))
						return true; // Filter matches name.
					else if (String.valueOf(myObject.getAuthor()).toLowerCase().contains(lowerCaseFilter))
						return true; // Filter matches author.
					return false; // Does not match.
				});
			});

			// Wrap the FilteredList in a SortedList.
			SortedList<TestRow> sortedData = new SortedList<>(filteredData);
			// Bind the SortedList comparator to the TableView comparator.
			sortedData.comparatorProperty().bind(testTable.comparatorProperty());
			// Add sorted (and filtered) data to the table.
			testTable.setItems(sortedData);
		}
	}

}