package principleDashboard;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReportFormController {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private Label testNameLbl;

    @FXML
    private Label avarageLbl;

    @FXML
    private Label medianLbl;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private BarChart<?, ?> histograma;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton deleteBtn;

}
