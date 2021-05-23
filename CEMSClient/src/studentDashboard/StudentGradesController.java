package studentDashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
  
public class StudentGradesController {

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private JFXComboBox<?> selectFieldCbox;

    @FXML
    private JFXTextField searchField;

    @FXML
    private Label startDPlbl;

    @FXML
    private JFXDatePicker testsFromDP;

    @FXML
    private Label endDPlbl;

    @FXML
    private JFXDatePicker testTillDP;

    @FXML
    private JFXButton filterButton;

    @FXML
    private JFXComboBox<?> selectCourseCbox;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private JFXTreeTableView<?> gradesTable;

    @FXML
    void filterBtn(MouseEvent event) {

    }

    @FXML
    void courseFilterClick(MouseEvent event) {

    }

    @FXML
    void dpFilter(MouseEvent event) {

    }

    @FXML
    void filedFilterClick(MouseEvent event) {

    }

}
