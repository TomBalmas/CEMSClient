package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import client.ClientController;
import common.Question;
import common.Report;
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
import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import teacherDashboard.QuestionBankUIController.questionRow;
import teacherDashboard.ScheduledTestsController.ScheduleTestRow;
import teacherDashboard.TestBankUIController.TestRow;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class ViewReportsController implements Initializable {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private JFXTextField searchField;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private TableView<reportRow> reportTable;

    @FXML
    private TableColumn<?, ?> reportIDCol;

    @FXML
    private TableColumn<?, ?> testIDCol;

    @FXML
    private TableColumn<?, ?> studentNumCol;

    @FXML
    private TableColumn<?, ?> averageCol;

    @FXML
    private TableColumn<?, ?> medianCol;

    @FXML
    private TableColumn<?, ?> viewCol;

    @FXML
    private TableColumn<?, ?> deleteCol;
	
	
	private Node ReportForm;
	private ReportFormController reportFormController;
	private ObservableList options = FXCollections.observableArrayList("Student", "Teacher", "Courses");;
	private final ObservableList<reportRow> dataList = FXCollections.observableArrayList();
	ArrayList<Integer> studentsDetails = new ArrayList<>();

	@FXML
	void deleteReportBtn(MouseEvent event) {

	}

	@FXML
	void filterBtn(MouseEvent event) {

	}

	@FXML
	void createReportClicked(MouseEvent event) {

	}

	@FXML
	void viewReportsBtn(MouseEvent event) {

	}

	/**
	 * this class represents report row with all relevant data for report row in
	 * table
	 *
	 */
	public class reportRow {
		private String reportId;
		private String testID;
		private int numberOfStudents;
		private double average;
		private double median;
		private JFXButton ViewBtn;
		Report report;

		public reportRow(Report report) {
			this.report = report;
			this.reportId = report.getId();
			testID = report.getTestId();
			numberOfStudents = report.getNumberOfStudents();
			average = report.getAverage();
			median = report.getMedian();
			this.ViewBtn = new JFXButton();
			ViewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
		}

		public JFXButton getViewBtn() {
			return ViewBtn;
		}

		public void setViewBtn(JFXButton viewBtn) {
			this.ViewBtn = viewBtn;
		}

		public String getReportId() {
			return reportId;
		}

		public void setReportId(String reportId) {
			this.reportId = reportId;
		}

		public int getNumberOfStudents() {
			return numberOfStudents;
		}

		public void setNumberOfStudents(int numberOfStudents) {
			this.numberOfStudents = numberOfStudents;
		}

		public double getAverage() {
			return average;
		}

		public void setAverage(double average) {
			this.average = average;
		}

		public double getMedian() {
			return median;
		}

		public void setMedian(double median) {
			this.median = median;
		}

		public Report getReport() {

			return report;
		}

		public void setQuestion(Report report) {
			this.report = report;
		}

		public String getTestID() {
			return testID;
		}

		public void setTestID(String testID) {
			this.testID = testID;
		}

	}

	/**
	 * 
	 * this method sets the values for the graph, and updates them in the report
	 * histogram
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Report> reports = null;
		ClientController.accept("GET_REPORTS-");
		reports = ClientController.getReports();
		// Adding PropertyValueFactory for the columns
		PropertyValueFactory reportIDfactory = new PropertyValueFactory<>("reportId");
		PropertyValueFactory testIDFactory = new PropertyValueFactory<>("testID");
		PropertyValueFactory viewFactory = new PropertyValueFactory<>("ViewBtn");
		PropertyValueFactory studentNumFactory = new PropertyValueFactory<>("numberOfStudents");
		PropertyValueFactory medianFactory = new PropertyValueFactory<>("median");
		PropertyValueFactory averageFactory = new PropertyValueFactory<>("average");
		reportIDCol.setCellValueFactory(reportIDfactory);
		testIDCol.setCellValueFactory(testIDFactory);
		viewCol.setCellValueFactory(viewFactory);
		studentNumCol.setCellValueFactory(studentNumFactory);
		medianCol.setCellValueFactory(medianFactory);
		averageCol.setCellValueFactory(averageFactory);

		if (reports != null) {
			for (int i = 0; i < reports.size(); i++) {
				reportRow reportRow = new reportRow(reports.get(i));
				dataList.add(reportRow); // Add row to dataList to search field.
				ClientController.accept("GET_NUMBER_OF_STUDENTS_DETAILS_BY_TEST_REPORT_ID-" + reportRow.getReportId());
				studentsDetails = ClientController.getStudentsInTestDetails();
				reportTable.getItems().addAll(reportRow);
				tableViewAnchor.setMouseTransparent(false);
				EventHandler<ActionEvent> btnEventHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.REPORT_FORM.getVal()));
							ReportForm = loader.load();
							reportFormController = loader.getController();
							String median = new DecimalFormat("##.##").format(reportRow.getMedian());
							String average = new DecimalFormat("##.##").format(reportRow.getAverage());
							reportFormController.getAverageTxt().setText(average);
							reportFormController.getMedianTxt().setText(median);
							reportFormController.getTotalStudentsTxt().setText(studentsDetails.get(0).toString());
							reportFormController.getFinishedOnTimeTxt().setText(studentsDetails.get(2).toString());
							reportFormController.getForcedSubmittionTxt().setText(studentsDetails.get(1).toString());
							if (Double.parseDouble(average) < 55)
								reportFormController.getAverageTxt().getStyleClass().add("fGradeLbl");
							else
								reportFormController.getAverageTxt().getStyleClass().add("aGradeLbl");

							if (Double.parseDouble(median) < 55)
								reportFormController.getMedianTxt().getStyleClass().add("fGradeLbl");
							else
								reportFormController.getMedianTxt().getStyleClass().add("aGradeLbl");
							reportFormController.getxAxisExam().setLabel("range");
							reportFormController.getyAxisGrades().setLabel("Students");
							reportFormController.setUserNameLbl("Test: " + reportRow.getTestID());
							Series<String, Number> set = new XYChart.Series<String, Number>();
							set.getData().add(new XYChart.Data<String, Number>("0-54.9", reportRow.getReport().getF()));
							set.getData()
									.add(new XYChart.Data<String, Number>("55-64", reportRow.getReport().getDMinus()));
							set.getData()
									.add(new XYChart.Data<String, Number>("65-69", reportRow.getReport().getDPlus()));
							set.getData()
									.add(new XYChart.Data<String, Number>("70-74", reportRow.getReport().getCMinus()));
							set.getData()
									.add(new XYChart.Data<String, Number>("75-79", reportRow.getReport().getCPlus()));
							set.getData()
									.add(new XYChart.Data<String, Number>("80-84", reportRow.getReport().getBMinus()));
							set.getData()
									.add(new XYChart.Data<String, Number>("85-59", reportRow.getReport().getBPlus()));
							set.getData()
									.add(new XYChart.Data<String, Number>("90-94", reportRow.getReport().getAMinus()));
							set.getData()
									.add(new XYChart.Data<String, Number>("95-100", reportRow.getReport().getAPlus()));
							reportFormController.setSet(set);
							reportFormController.getHistograma().getData().addAll(set);

						} catch (IOException e1) {
							e1.printStackTrace();
						}
						GeneralUIMethods.loadPage(contentPaneAnchor, ReportForm);
					}

				};
				reportRow.getViewBtn().setOnAction(e -> {
					btnEventHandler.handle(e);
				});
			}
			
			// Search by data which is in a certain row.
			FilteredList<reportRow> filteredData = new FilteredList<>(dataList, p -> true);

			// Set the filter Predicate whenever the filter changes.
			searchField.textProperty().addListener((observable, oldValue, newValue) -> {
				filteredData.setPredicate(myObject -> {
				
					if (newValue == null || newValue.isEmpty())
						return true;

					// Compares what we wrote in the text (we searched for) to the appropriate line.
					String lowerCaseFilter = newValue.toLowerCase();

					if (String.valueOf(myObject.getReportId()).toLowerCase().contains(lowerCaseFilter))
						return true; // Filter matches code.

					else if (String.valueOf(myObject.getTestID()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches field.
					}

					else if (String.valueOf(myObject.getNumberOfStudents()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches course.
					}

					else if (String.valueOf(myObject.getMedian()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches name.
					}

					else if (String.valueOf(myObject.getAverage()).toLowerCase().contains(lowerCaseFilter)) {
						return true; // Filter matches author.
					}

					return false; // Does not match.
				});
			});

			// Wrap the FilteredList in a SortedList.
			SortedList<reportRow> sortedData = new SortedList<>(filteredData);

			// Bind the SortedList comparator to the TableView comparator.
			sortedData.comparatorProperty().bind(reportTable.comparatorProperty());
			// Add sorted (and filtered) data to the table.
			reportTable.setItems(sortedData);
			

		}
	}
}