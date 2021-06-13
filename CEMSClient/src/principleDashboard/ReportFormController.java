package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import util.GeneralUIMethods;
import util.Navigator;

public class ReportFormController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private Label userNameLbl;

	@FXML
	private Label avarageLbl;

	@FXML
	private Label medianLbl;

	@FXML
	private Label averageTxt;

	@FXML
	private Label medianTxt;

	@FXML
	private CategoryAxis xAxisExam;

	@FXML
	private NumberAxis yAxisGrades;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private JFXButton backBtn;

	@FXML
	private Label totalStudentsLbl;

	@FXML
	private Label totalStudentsTxt;

	@FXML
	private Label finishedOnTimeLbl;

	@FXML
	private Label finishedOnTimeTxt;

	@FXML
	private Label forcedSubmittionLbl;

	@FXML
	private Label forcedSubmittionTxt;

	@FXML
	private JFXButton deleteBtn;
	Series<String, Number> set = new XYChart.Series<String, Number>();

	@FXML
	private BarChart<String, Number> histograma;

	/**
	 * initializing report form with general settings
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		xAxisExam.setAnimated(true);
		histograma.getData().clear();
		set.getData().clear();
		xAxisExam.setTickLabelRotation(45);
		// Set Font
		xAxisExam.setTickLabelFont(new Font("Arial", 12));
		xAxisExam.setAnimated(false);
		yAxisGrades.setLabel("Grades");
		xAxisExam.setLabel("Exam ID");
		xAxisExam.setAnimated(false);

	}

	@FXML
	void clickBack() throws IOException {
		Node page;
		try {
			String s = getUserNameLbl().getText();
			// click back from viewing test reports in view reports
			if (s.contains("Test")) {
				page = FXMLLoader.load(getClass().getResource(Navigator.VIEW_REPORTS.getVal()));
				GeneralUIMethods.loadPage(contentPaneAnchor, page);
				// click back from creating report
			} else {
				page = FXMLLoader.load(getClass().getResource(Navigator.CREATE_REPORT.getVal()));
				GeneralUIMethods.loadPage(contentPaneAnchor, page);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public Label getAverageTxt() {
		return averageTxt;
	}

	public Label getMedianTxt() {
		return medianTxt;
	}

	public Label getUserNameLbl() {
		return userNameLbl;
	}

	public void setUserNameLbl(String text) {
		userNameLbl.setText(text);
	}

	public Series<String, Number> getSet() {
		return set;
	}

	public void setSet(Series<String, Number> set) {
		this.set = set;
	}

	public CategoryAxis getxAxisExam() {
		return xAxisExam;
	}

	public void setxAxisExam(CategoryAxis xAxisExam) {
		this.xAxisExam = xAxisExam;
	}

	public NumberAxis getyAxisGrades() {
		return yAxisGrades;
	}

	public void setyAxisGrades(NumberAxis yAxisGrades) {
		this.yAxisGrades = yAxisGrades;
	}

	public Label getMedianLbl() {
		return medianLbl;
	}

	public void setMedianLbl(Label medianLbl) {
		this.medianLbl = medianLbl;

	}

	public void setAverageTxt(Label averageTxt) {
		this.averageTxt = averageTxt;
	}

	public void setMedianTxt(Label medianTxt) {
		this.medianTxt = medianTxt;
	}

	public BarChart<String, Number> getHistograma() {
		return histograma;
	}

	public void setHistograma(BarChart<String, Number> histograma) {
		this.histograma = histograma;
	}
}