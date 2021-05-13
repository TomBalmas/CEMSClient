package teacherDashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class ViewActiveTestController {

    @FXML
    private JFXPasswordField TimeLeftField;

    @FXML
    private JFXButton RequestTimeExtensionBtn;

    @FXML
    private JFXPasswordField finishTimeField;

    @FXML
    private JFXButton LockTestBtn;

    @FXML
    private JFXTextField EnterCodeField;

    @FXML
    private JFXButton LockBtn;

    @FXML
    void beginTestClick(MouseEvent event) {

    }

}
