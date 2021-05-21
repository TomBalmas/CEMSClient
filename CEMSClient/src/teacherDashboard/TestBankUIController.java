package teacherDashboard;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class TestBankUIController implements Initializable {


    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private Label testBankLbl;

    @FXML
    private JFXComboBox<?> selectCbox;

    @FXML
    private JFXTextField searchField;

    @FXML
    private Label startDPlbl;

    @FXML
    private JFXDatePicker startCoursesDP;

    @FXML
    private Label endDPlbl;

    @FXML
    private JFXDatePicker finishCoursesDP;

    @FXML
    private JFXButton filterButton;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private JFXTreeTableView<?> testTable;

    @FXML
    private JFXButton addNewTestButton;

	private ObservableList options = FXCollections.observableArrayList("Anyone", "You", "Others"); //----------TODO: add teachers for priciple

	private Node addNewTest;

	public JFXButton getAddNewTestButton() {
		return addNewTestButton;
	}

	/**
	 * clicking add new test opens question bank screen
	 * 
	 * @param event
	 */
	@FXML
	void addNewTest(MouseEvent event) {
		try {
			addNewTest = FXMLLoader.load(getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, addNewTest);
	}
	
    @FXML
    void filterBtn(MouseEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectCbox.setItems(options);		
	}

}
