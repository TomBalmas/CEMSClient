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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
					ScheduleTestRow toReschedule =tr;
					FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.SET_TEST_DATE.getVal()));
					PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "RescheduleTest", "", contentPaneAnchor, null, loader);
					// PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "", "", contentPaneAnchor, null,
					// loader);
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

		}
	}

	@FXML
	void searchBtnClicked(MouseEvent event) {
		// TODO -- implement filter
	}

}