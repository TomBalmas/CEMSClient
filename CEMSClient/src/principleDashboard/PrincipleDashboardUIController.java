package principleDashboard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.Navigator;

public class PrincipleDashboardUIController {

	@FXML
	private VBox menuVBox;

	@FXML
	private JFXToggleButton darkModeToggleBtn;

	@FXML
	private ImageView logoImg;

	@FXML
	private Label teacherDashboardLbl;

	@FXML
	private JFXButton activeTestRequestsBtn;

	@FXML
	private JFXButton viewReportsBtn;

	@FXML
	private JFXButton createReportBtn;

	@FXML
	private JFXButton testBankBtn;

	@FXML
	private JFXButton questionBankBtn;

	@FXML
	private JFXButton signOutBtn;

	@FXML
	private AnchorPane contentPaneAnchr;

	private Node viewReports;
	private Node createReport;

	@FXML
	void activeTestRequestClicked(MouseEvent event) {

	}

	@FXML
	void createReportClicked(MouseEvent event) {
		
		try {
			contentPaneAnchr.getChildren().clear();
			createReport = FXMLLoader.load(getClass().getResource(Navigator.CREATE_REPORT.getVal()));
			contentPaneAnchr.getChildren().addAll(createReport);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void questionBankClicked(MouseEvent event) {

	}

	@FXML
	void testBankClicked(MouseEvent event) {

	}

	@FXML
	void viewReportsClicked(MouseEvent event) {
		
		try {
			contentPaneAnchr.getChildren().clear();
			viewReports = FXMLLoader.load(getClass().getResource(Navigator.VIEW_REPORTS.getVal()));
			contentPaneAnchr.getChildren().addAll(viewReports);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
