package principleDashboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ViewReportsController implements Initializable {

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private JFXComboBox<?> selectTypeCbox;

    @FXML
    private JFXTextField searchField;

    @FXML
    private Label startDPlbl;

    @FXML
    private JFXDatePicker startReportCreatedDP;

    @FXML
    private Label endDPlbl;

    @FXML
    private JFXDatePicker endReportCreatedDP;

    @FXML
    private JFXButton filterButton;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private JFXTreeTableView<?> reportTable;

    @FXML
    private JFXButton deleteBtn;

    @FXML
    private JFXButton createReportBtn;
    
	private ObservableList options = FXCollections.observableArrayList("Student", "Teacher", "Courses");

    @FXML
    void deleteReportBtn(MouseEvent event) {

    }

    @FXML
    void filterBtn(MouseEvent event) {

    }
	
	@FXML
	void createReportClicked(MouseEvent event) {
		
	}

	@FXML
	void viewReportsBtn(MouseEvent event) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectTypeCbox.setItems(options);
	}


}
