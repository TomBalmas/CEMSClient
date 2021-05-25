package teacherDashboard;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import client.ClientController;
import common.ScheduledTest;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import teacherDashboard.TestBankUIController.TestRow;

public class ScheduledTestsController implements Initializable {
	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<scheduleTestRow> scheduledTestsTbl;

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
	private TableColumn<?, ?> viewCol;

	@FXML
	private TableColumn<?, ?> rescheduleCol;

	@FXML
	private Label scheduledTestsLbl;

	public class scheduleTestRow {
		private String title;
		private String testId;
		private String author;
		private String date;
		private String startingTime;
		private int duration;
		private JFXButton viewBtn;
		private JFXButton setDateBtn;
		private JFXButton editBtn;
		private ScheduledTest scheduledTest;

		public scheduleTestRow(ScheduledTest scheduledTest) {
			this.scheduledTest = scheduledTest;
			this.testId = scheduledTest.getID();
			this.title = scheduledTest.getTitle();
			this.author = scheduledTest.getAuthorName();
			this.date = scheduledTest.getDate();
			this.startingTime = scheduledTest.getStartingTime();
			this.duration = scheduledTest.getDuration();
			setDateBtn = new JFXButton("Resheduled");
			viewBtn = new JFXButton("View");
			setDateBtn.setStyle("-fx-background-color: cyan;");
			viewBtn.setStyle("-fx-background-color: orange;");
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

		public void setViewBtn(JFXButton viewBtn) {
			this.viewBtn = viewBtn;
		}

		public JFXButton getSetDateBtn() {
			return setDateBtn;
		}

		public void setSetDateBtn(JFXButton setDateBtn) {
			this.setDateBtn = setDateBtn;
		}

		public JFXButton getEditBtn() {
			return editBtn;
		}

		public void setEditBtn(JFXButton editBtn) {
			this.editBtn = editBtn;
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
		rescheduleCol.setCellValueFactory(new PropertyValueFactory<>("Rechedule"));
		if (scheduledTests != null) {
			for (ScheduledTest test : scheduledTests) {
				scheduleTestRow tr = new scheduleTestRow(test);
				scheduledTestsTbl.getItems().add(tr);
			}
		}
	}

}