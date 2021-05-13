package teacherDashboard;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;

public class ScheduledTestsController implements Initializable{

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private JFXTreeTableView<?> scheduledTestsTbl;

    @FXML
    private JFXButton changeDateBtn;

    @FXML
    private Label scheduledTestsLbl;
    
    private Node changeDatePopUp;	//------------need to set the pop up page 9.1------------

    @FXML
    void changeDateClicked(MouseEvent event) {
    	GeneralUIMethods.loadPage(contentPaneAnchor, changeDatePopUp);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
