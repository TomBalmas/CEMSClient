package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Set;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
	private AnchorPane anchorLogin;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private JFXComboBox<?> selectCbox;

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
	private JFXButton searchBtn;

	@FXML
	private Label testBankLbl;

	@FXML
	private TableView<TestRow> testTable;

	@FXML
	private TableColumn<?, ?> IDcol;

	@FXML
	private TableColumn<?, ?> testNameCol;

	@FXML
	private TableColumn<?, ?> authorCol;

	@FXML
	private TableColumn<?, ?> courseCol;

	@FXML
	private TableColumn<?, ?> fieldCol;

	@FXML
	private TableColumn<?, ?> editCol;

	@FXML
	private TableColumn<?, ?> setDateCol;

	@FXML
	private TableColumn<?, ?> viewCol;

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

	private Node TestFormNode, addNewTest, AddingFormNode;
	private FXMLLoader TestFormLoader;

	public JFXButton getAddNewTestButton() {
		return addNewTestButton;
	}

	// ----------TODO: add teachers for principle
	private ObservableList filterBySelectBox = FXCollections.observableArrayList("Anyone", "You", "Others");
	private final ObservableList<TestRow> dataList = FXCollections.observableArrayList();

	@FXML
	void searchBtnClicked(MouseEvent event) {

	}

	/**
	 * clicking add new test opens question bank screen
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
	 * this class is a class that defines the properties of the main table its gets
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
			deleteBtn.setStyle("-fx-fill: red;");
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
	 * setting the test table and its functionalities
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectCbox.setItems(filterBySelectBox);
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
				dataList.add(tr); // add row to dataList to search field.
				// View button
				tr.getViewBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
//						try {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
						testAnchor.setVisible(true);
						testAnchor.toFront();
						GeneralUIMethods.buildTestForm(testAnchor2, testScrollPane, tr.getTestId(), "", loader);
//						TestFormNode = loader.load();
//						TestFormController controller = loader.getController();
//						controller.getScrollPane().setTranslateX(10);
//						controller.getScrollPane().setTranslateY(11);
//						controller.getEditBtn().setVisible(false);
//						controller.addTitleAndInstructionsToTest(tr.getTestName(), tr.getTest().getTeacherInstructions(), tr.getTest().getStudentInstructions());
//						int i = 1;
//						for (Question q : tr.getTest().getQuestions()) {
//							controller.addQuestionToTestForm(q, i, 100 / pickedQuestions.size()); // adding questions to preview
//							i++;
//						}
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
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
							editTestController.getDurationTxt().setText(tr.getTest().getTestDuration().toString());
							editTestController.getTeacherInstructionsTxtArea()
									.setText(tr.getTest().getTeacherInstructions());
							editTestController.getStudentInstructionsTxtArea()
									.setText(tr.getTest().getStudentInstructions());
							

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									editTestController.setEditingTest(tr.getTestId());
									//editTestController.getQuestionTable().getSelectionModel().select(1);
									//editTestController.setQuestionController(tr.getTestId());
								}
							});
//
//							//ClientController.accept("GET_QUESTIONS_FROM_TEST-" + tr.getTestId());
//							editTestController.getQuestionTable().getItems().remove(0);
//							for (Question question : ClientController.getQuestions()) {
//								//System.out.println(editTestController.getQuestionTable().getItems().forEach(null));
//								//if(ClientController.getQuestions())
//								editTestController.getQuestionTable().getItems().forEach(e -> {
//									if(e.getID().equals(question.getID())) editTestController.getQuestionTable().getSelectionModel().select(e);
//								});
//								editTestController.getQuestionTable().getItems().removeIf((t) -> {
//									return false || !((QuestionRow) t).getID().equals(question);
//								});
//							}
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
						PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "ScheduleTest", "", contentPaneAnchor,
								Arrays.asList(new JFXButton("Cancel")), loader);
						// PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "", "", contentPaneAnchor, null,
						// loader);
						SetTestDateController cont = loader.getController();
						cont.getSetDateBtn().setOnMouseClicked(e -> {
							ClientController.accept("SET_TEST_DATE-" + tr.getTestId() + ","
									+ GeneralUIMethods.israeliDate(cont.getDateDP().getValue()) + ","
									+ cont.getTimeTP().getValue().toString() + ","
									+ ClientController.getActiveUser().getSSN() + "," + cont.getCodeTxt().getText());
							if (ClientController.isTestScheduled())
								PopUp.showMaterialDialog(PopUp.TYPE.ALERT, "Success", "Tests scheduled successfully",
										contentPaneAnchor, null, null);
							else
								PopUp.showMaterialDialog(PopUp.TYPE.ALERT, "Failed", "Tests schedule failed",
										contentPaneAnchor, null, null);
						});
					}
				});

				// Delete button
				tr.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() { // delete form table and DB
					@Override
					public void handle(ActionEvent event) {
						TestRow toDelete = tr;
						JFXButton yesBtn = new JFXButton("Yes");
						yesBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
							ClientController.accept("DELETE_TEST-" + tr.test.getID());
							if (!ClientController.isTestDeleted())
								System.out.println("not working");
							else {
								testTable.getItems().remove(toDelete);
								PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information",
										"The test " + tr.getTestId() + " has been deleted", contentPaneAnchor, null, null);
							}
						});
						PopUp.showMaterialDialog(PopUp.TYPE.ALERT, "Alert",
								"Are you sure that you want to delete this test?", contentPaneAnchor,
								Arrays.asList(yesBtn, new JFXButton("No")), null);
					}
				});
			}

			// Search by data which is in a certain row.
			FilteredList<TestRow> filteredData = new FilteredList<>(dataList, p -> true);

			// Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(myObject -> {
					// If filter text is empty, display all persons.
					if (newValue == null || newValue.isEmpty()) {
						return true;
					}

					// Compares what we wrote in the text (we searched for) to the appropriate line.
					String lowerCaseFilter = newValue.toLowerCase();

					if (String.valueOf(myObject.getTestId()).toLowerCase().contains(lowerCaseFilter)) {
						return true;
						// Filter matches code.
					}

					else if (String.valueOf(myObject.getField()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches field.
					}

					else if (String.valueOf(myObject.getCourse()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches course.
					}

					else if (String.valueOf(myObject.getTestName()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches name.
					}

					else if (String.valueOf(myObject.getAuthor()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches author.
					}

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

	@FXML
	void backToPageBtnClicked(MouseEvent event) {
		testAnchor.setVisible(false);
		testAnchor.toBack();
	}

}