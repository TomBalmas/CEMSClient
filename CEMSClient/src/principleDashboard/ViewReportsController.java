package principleDashboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ViewReportsController{

	@FXML
	private JFXTreeTableView<?> reportTable;

	@FXML
	private JFXButton deleteBtn;

	@FXML
	private VBox hiddenBtn;

	@FXML
	private JFXButton viewReportsBtn;

	@FXML
	private JFXButton createReportBtn;

	@FXML
	void createReportClicked(MouseEvent event) {

	}

	@FXML
	void viewReportsClicked(MouseEvent event) {

	}


}
