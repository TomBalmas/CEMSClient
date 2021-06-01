package principleDashboard;

import java.io.IOException;
import java.net.URL;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import teacherDashboard.QuestionBankUIController.questionRow;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class ViewReportsController implements Initializable {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private JFXComboBox<?> selectTypeCbox;

    @FXML
    private JFXTextField searchField;

    @FXML
    private Label startDPlbl;

    @FXML
    private JFXDatePicker startReportCreatedDP;

    @FXML
    private Label endDPlbl;

    @FXML
    private JFXDatePicker endReportCreatedDP;

    @FXML
    private JFXButton filterButton;

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

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton createReportBtn;
	private Node ReportForm;
    
	private ObservableList options = FXCollections.observableArrayList("Student", "Teacher", "Courses");

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
	
	public class reportRow {
		private String reportId;
		private String testID;
		private int numberOfStudents;
		private double average;
		private double median;
		private JFXButton ViewBtn;
		private JFXButton DeleteBtn;
		Report report;
		
		public reportRow(Report report) {
		this.reportId = report.getId();
		testID=report.getTestId();
		System.out.println(testID);
		numberOfStudents=report.getNumberOfStudents();
		average=report.getAverage();
		median=report.getMedian();
		this.ViewBtn = new JFXButton();
		this.DeleteBtn = new JFXButton();
		DeleteBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.TRASH));
		ViewBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.EYE));
		DeleteBtn.setStyle("-fx-fill: red !important;");
	}

	public JFXButton getViewBtn() {
		return ViewBtn;
	}

	public void setViewBtn(JFXButton viewBtn) {
		this.ViewBtn = viewBtn;
	}

	public JFXButton getDeleteBtn() {
		return DeleteBtn;
	}

	public void setDeleteBtn(JFXButton deleteBtn) {
		this.DeleteBtn = deleteBtn;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectTypeCbox.setItems(options);
		ArrayList<Report> reports = null;
		ClientController.accept("GET_REPORTS-");
		reports = ClientController.getReports();
		
		//adding PropertyValueFactory for the columns
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
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("DeleteBtn"));

		if (reports != null) {
			for (int i = 0; i < reports.size(); i++) {
				reportRow reportRow = new reportRow(reports.get(i));
				reportTable.getItems().addAll(reportRow);
				tableViewAnchor.setMouseTransparent(false);

				EventHandler<ActionEvent> btnEventHandler = new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent event) {
						try {
							FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.REPORT_FORM.getVal()));
							ReportForm = loader.load();
							JFXButton buttonText = (JFXButton) event.getSource();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
						GeneralUIMethods.loadPage(contentPaneAnchor, ReportForm);
					}

				};
				reportRow.getViewBtn().setOnAction(e -> {
					btnEventHandler.handle(e);
					{

					};
				});

				// Event handler for deletion from table and DB
				reportRow.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() { // delete form table and DB
					@Override
					public void handle(ActionEvent event) {
						JFXButton yesBtn = new JFXButton("Yes");
						yesBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
							ClientController.accept("DELETE_REPORT-" + reportRow.getReportId());
							if (!ClientController.isReportDeleted())
								System.out.println("not working");
							reportTable.getItems().remove(reportRow);
							PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information",
									"The report " + reportRow.getReportId() + " has been deleted", contentPaneAnchor,
									null, null);
						});
						PopUp.showMaterialDialog(PopUp.TYPE.ALERT, "Alert",
								"Are you sure that you want to delete this question?", contentPaneAnchor,
								Arrays.asList(yesBtn, new JFXButton("No")), null);
					}
				});

			}

		}
	}
}
