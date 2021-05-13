package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class ViewActiveTestsController implements Initializable{

    @FXML
    private Label viewActiveTestsLbl;

    @FXML
    private Label timeLeftLbl;

    @FXML
    private JFXButton lequestTimeExtensionBtn;

    @FXML
    private Label finishTimeLbl;

    @FXML
    private JFXButton lockTestBtn;

    @FXML
    private Label enterCodeLbl;

    @FXML
    private JFXTextField EnterCodeField;

    @FXML
    private JFXButton lockBtn;

    @FXML
    private JFXTreeTableView<?> activeTestsTbl;

    @FXML
    private JFXTextField timeLeftField;

    @FXML
    private JFXTextField finishTimeField;
    
    @FXML
    private AnchorPane contentPaneAnchor;

    private Node requestTimeExtension;
    
    @FXML
    void lockClicked(MouseEvent event) {

    }

    @FXML
    void lockTestClicked(MouseEvent event) {
		lockBtn.setVisible(true);
		EnterCodeField.setVisible(true);
		enterCodeLbl.setVisible(true);
		lockTestBtn.setDisable(true);
		lockBtn.setDisable(true);	//-----------need to enable the button when the code is in the table-----------
    }

    @FXML
    void requestTimeExtension(MouseEvent event) {
    	GeneralUIMethods.loadPage(contentPaneAnchor, requestTimeExtension);
    }


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lockBtn.setVisible(false);
		EnterCodeField.setVisible(false);
		enterCodeLbl.setVisible(false);
		try {
			requestTimeExtension = FXMLLoader.load(getClass().getResource(Navigator.REQUEST_TIME_EXTENSION.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
