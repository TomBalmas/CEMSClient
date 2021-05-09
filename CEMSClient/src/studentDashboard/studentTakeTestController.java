package studentDashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class studentTakeTestController {

    @FXML
    private JFXPasswordField testCodeField;

    @FXML
    private JFXRadioButton manualBtn;

    @FXML
    private JFXRadioButton computedBtn;

    @FXML
    private JFXButton beginTestBtn;

    @FXML
    void beginTestClick(MouseEvent event) {
    }

}