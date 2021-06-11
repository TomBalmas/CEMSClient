package teacherDashboard;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.ScheduledTest;
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
import teacherDashboard.ViewActiveTestsController.rowTableActiveTest;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class ScheduledTestsController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<ScheduleTestRow> scheduledTestsTbl;

	@FXML
	private TableColumn<?, ?> idCol;

	@FXML
	private TableColumn<?, ?> titleCol;

	@FXML
	private TableColumn<?, ?> authorCol;

	@FXML
	private TableColumn<?, ?> dateCol;

	@FXML
	private TableColumn<?, ?> startingTimeCol;

	@FXML
	private TableColumn<?, ?> durationCol;

	@FXML
	private TableColumn<?, ?> codeCol;

	@FXML
	private TableColumn<?, ?> viewCol;

	@FXML
	private TableColumn<?, ?> rescheduleCol;

	@FXML
	private TableColumn<?, ?> deleteCol;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private Label checkTestLbl;

	@FXML
	private JFXTextField searchField;

	@FXML
	private JFXButton searchBtn;
	
	private final ObservableList<ScheduleTestRow> dataList = FXCollections.observableArrayList();
    @FXML
    private AnchorPane testAnchor;

    @FXML
    private JFXButton backToPageBtn;

    @FXML
    private ScrollPane testScrollPane;

    @FXML
    private AnchorPane testAnchor2;

	public class ScheduleTestRow {
		private String title;
		private String testId;
		private String author;
		private String date;
		private String startingTime;
		private String code;
		private int duration;
		private JFXButton viewBtn;
		private JFXButton reScheduleBtn;
		private JFXButton removeBtn;
		private ScheduledTest scheduledTest;

		public ScheduleTestRow(ScheduledTest scheduledTest) {
			this.scheduledTest = scheduledTest;
			this.testId = scheduledTest.getID();
			this.title = scheduledTest.getTitle();
			this.author = scheduledTest.getAuthorName();
			this.date = scheduledTest.getDate();
			this.startingTime = scheduledTest.getStartingTime();
			this.duration = scheduledTest.getDuration();
			this.code = scheduledTest.getCode();
			removeBtn = new JFXButton("");
			reScheduleBtn = new JFXButton("");
			viewBtn = new JFXButton("");
			viewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
			reScheduleBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.CALENDAR_ALT));
			removeBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.MINUS_CIRCLE));
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
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

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getStartingTime() {
			return startingTime;
		}

		public void setStartingTime(String startingTime) {
			this.startingTime = startingTime;
		}

		public int getDuration() {
			return duration;
		}

		public void setDuration(int duration) {
			this.duration = duration;
		}

		public JFXButton getViewBtn() {
			return viewBtn;
		}

		public JFXButton getReScheduleBtn() {
			return reScheduleBtn;
		}

		public JFXButton getRemoveBtn() {
			return removeBtn;
		}

		public ScheduledTest getScheduledTest() {
			return scheduledTest;
		}

		public void setScheduledTest(ScheduledTest scheduledTest) {
			this.scheduledTest = scheduledTest;
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ClientController.accept("SCHEDULED_TESTS-" + ClientController.getActiveUser().getSSN());
		ArrayList<ScheduledTest> scheduledTests = ClientController.getScheduledTests();
		idCol.setCellValueFactory(new PropertyValueFactory<>("TestId"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
		startingTimeCol.setCellValueFactory(new PropertyValueFactory<>("StartingTime"));
		durationCol.setCellValueFactory(new PropertyValueFactory<>("Duration"));
		viewCol.setCellValueFactory(new PropertyValueFactory<>("viewBtn"));
		rescheduleCol.setCellValueFactory(new PropertyValueFactory<>("reScheduleBtn"));
		codeCol.setCellValueFactory(new PropertyValueFactory<>("code"));
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));
		if (scheduledTests != null) {
			for (ScheduledTest test : scheduledTests) {
				ScheduleTestRow tr = new ScheduleTestRow(test);
				scheduledTestsTbl.getItems().add(tr);
				dataList.add(tr); //add row to dataList to search field.
				
				// View button
				tr.getViewBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
						testAnchor.setVisible(true);
						testAnchor.toFront();
						GeneralUIMethods.buildTestForm(testAnchor2, testScrollPane, tr.getCode(), "TEACHER_VIEW_TEST_BY_CODE", loader);
					}
				});
				
				// remove button functionality
				tr.getRemoveBtn().setOnMouseClicked(e -> {
					ScheduleTestRow toDelete = tr;
					JFXButton yesBtn = new JFXButton("Yes");
					yesBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e2) -> {
						ClientController.accept("REMOVE_SCHEDULED_TEST-" + tr.getCode());
						if (!ClientController.isTestRemoved())
							System.out.println("not working");
						scheduledTestsTbl.getItems().remove(toDelete);
						PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information",
								"The test " + tr.getTestId() + " has been deleted", contentPaneAnchor, null, null);
					});
					PopUp.showMaterialDialog(PopUp.TYPE.ALERT, "Alert",
							"Are you sure that you want to delete this test?", contentPaneAnchor,
							Arrays.asList(yesBtn, new JFXButton("No")), null);
				});

				tr.getReScheduleBtn().setOnAction(e -> { // reSchedule
					ScheduleTestRow toReschedule = tr;
					FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.SET_TEST_DATE.getVal()));
					PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "RescheduleTest", "", contentPaneAnchor, null, loader);
					SetTestDateController cont = loader.getController();
					cont.getCodeTxt().setDisable(true);
					cont.getCodeTxt().setText(tr.getCode());
					cont.getSetDateBtn().setOnMouseClicked(e2 -> {
						ClientController.accept("RESCHEDULE_TEST-" + tr.getCode() + ","
								+ GeneralUIMethods.israeliDate(cont.getDateDP().getValue()) + ","
								+ cont.getTimeTP().getValue().toString());
						if (ClientController.isTestRescheduled()) {
							PopUp.showMaterialDialog(PopUp.TYPE.SCHEDULE, "Success", "Tests rescheduled successfully",
									contentPaneAnchor, null, null);
							scheduledTestsTbl.refresh();
//							scheduledTestsTbl.getItems().clear();
//							initialize(arg0, arg1);
						}
						else
							PopUp.showMaterialDialog(PopUp.TYPE.SCHEDULE, "Failed", "Tests reschedule failed",
									contentPaneAnchor, null, null);
					});
				});
				
				//TODO - view test 
				
				
			}
			
			//Search by data which is in a certain row.
			FilteredList<ScheduleTestRow> filteredData = new FilteredList<>(dataList, p -> true);

	        //  Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
	            filteredData.setPredicate(myObject -> {
	                // If filter text is empty, display all persons.
	                if (newValue == null || newValue.isEmpty()) {
	                    return true;
	                }

	                // Compares what we wrote in the text (we searched for) to the appropriate line.
	                String lowerCaseFilter = newValue.toLowerCase();

	                if (String.valueOf(myObject.getCode()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true;
	                    // Filter matches code.
	                } 
	                
	            	else if (String.valueOf(myObject.getTestId()).toLowerCase().contains(lowerCaseFilter)) {
	            		return true; // Filter matches test id.
	            	} 
	                
	                else if (String.valueOf(myObject.getTitle()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches title.
	                } 
	                
	                else if (String.valueOf(myObject.getDate()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches date.
	                } 
	                
	                else if (String.valueOf(myObject.getAuthor()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches author.
	                } 
	                else if (String.valueOf(myObject.getStartingTime()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches starting time.
	                } 
	                
	                else if (String.valueOf(myObject.getDuration()).toLowerCase().contains(lowerCaseFilter)) {
	                    return true; // Filter matches duration.
	                } 

	                return false; // Does not match.
	            });
	        });

	        //  Wrap the FilteredList in a SortedList. 
	        SortedList<ScheduleTestRow> sortedData = new SortedList<>(filteredData);

	        //  Bind the SortedList comparator to the TableView comparator.
	        sortedData.comparatorProperty().bind(scheduledTestsTbl.comparatorProperty());
	        //  Add sorted (and filtered) data to the table.
	        scheduledTestsTbl.setItems(sortedData);
	        
			

		}
		
		
		
	}

	@FXML
	void searchBtnClicked(MouseEvent event) {
		// TODO -- implement filter
	}
	
    @FXML
    void backToPageBtnClicked(MouseEvent event) {
		testAnchor.setVisible(false);
		testAnchor.toBack();
    }

}