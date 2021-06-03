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
    private CategoryAxis xAxisExam;

    @FXML
    private NumberAxis yAxisGrades;

	@FXML
    private Label medianTxt;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private BarChart<String, Number> histograma;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton deleteBtn;
    Series<String,Number> set = new XYChart.Series<String,Number>();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	
		xAxisExam.setTickLabelRotation(45);
		// Set Font
		xAxisExam.setTickLabelFont( new Font("Arial", 12));
		xAxisExam.setAnimated(false);
		set.getData().clear();
		set.getData().add(new XYChart.Data<String,Number>("ME",98));
		xAxisExam.setLabel("Exam ");
		yAxisGrades.setLabel("Grades");
		histograma.getData().addAll(set);
		histograma.setLegendVisible(false);
		xAxisExam.setAnimated(false);
		//histograma.getData().clear();
		
		
	}
	
	@FXML
	void clickBack() throws IOException {
		Node page;
		try {
			page = FXMLLoader.load(getClass().getResource(Navigator.VIEW_REPORTS.getVal()));
			GeneralUIMethods.loadPage(contentPaneAnchor, page);
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
    public Label getTestNameLbl() {
		return userNameLbl;
	}
	public void setTestNameLbl(Label userNameLbl) {
		this.userNameLbl = userNameLbl;
	}
}
