package studentDashboard;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
  
public class studentGradesController {

    @FXML
    private JFXTreeTableView<?> gradesTable;

    @FXML
    private JFXComboBox<?> courseFilter;
    
    @FXML
    private JFXComboBox<?> filedFilter;

    @FXML
    private JFXDatePicker dpEnd;

    @FXML
    private JFXDatePicker dpBegin;

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
