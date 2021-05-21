package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class AddingNewTestUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private JFXTreeTableView<?> questionTable;

	@FXML
	private JFXButton backBtn;

	@FXML
	private JFXButton continueBtn;

	@FXML
	private Label instructionsLbl;

	@FXML
	private JFXComboBox<?> selectFieldComboBox;

	private Node testBank;

	ObservableList fields = FXCollections.observableArrayList("Math", "History");// -------------need to fill the fields
																					// with a query--------------

	/**
	 * initialize a combo box with the relevant fields
	 *
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectFieldComboBox.setItems(fields);
	}

	/**
	 * clicking back will go back to the test bank screen
	 * 
	 * @param event
	 */
	@FXML
	void clickBack(MouseEvent event) {
		try {
			testBank = FXMLLoader.load(getClass().getResource(Navigator.TEST_BANK.getVal()));
			contentPaneAnchor.getChildren().setAll(testBank);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * clicking continue will move to blank test form only if at least one question
	 * was chosen.
	 * 
	 * @param event
	 */
	@FXML
	void clickContinue(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
			Node test;
			test = loader.load();
			TestFormController controller = loader.getController();
			controller.getEditBtn().setVisible(false);
			controller.addQuestionToTestForm();		//need to get questions from DB
			controller.addQuestionToTestForm();		//need to get questions from DB
			controller.addQuestionToTestForm();		//need to get questions from DB
			GeneralUIMethods.loadPage(contentPaneAnchor, test);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// -------------need to implement an if statement that will block passage if no questions were selected--------------
	}

}
