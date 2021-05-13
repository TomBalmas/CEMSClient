package principleDashboard;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;

public class ReportFormController {

    @FXML
    private Label testNameTxt;

    @FXML
    private Label avarageTxt;

    @FXML
    private Label medianTxt;

    @FXML
    private BarChart<?, ?> histograma;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton deleteBtn;

}
