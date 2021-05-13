package teacherDashboard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.Navigator;

public class TestBankUIController {
	
    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane anchorLogin;

    @FXML
    private Label testBankLbl;

    @FXML
    private JFXTreeTableView<?> testTable;

    @FXML
    private JFXButton addNewTestButton;
    
    private Node addNewTest;

    public JFXButton getAddNewTestButton() {
		return addNewTestButton;
	}

	/**clicking add new test opens question bank screen
     * 
     * @param event
     */
    @FXML
    void addNewTest(MouseEvent event) {
		try {
			addNewTest = FXMLLoader.load(getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
			contentPaneAnchor.getChildren().setAll(addNewTest);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
