package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import client.ClientController;
import common.Question;
import common.Teacher;
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

public class QuestionBankUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private Label questionBankLbl;

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
	private JFXButton searchBtn;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private JFXTreeTableView<?> questionBankTable;

	@FXML
	private JFXButton addAnewQuestionBtn;

	private Node blankQuestionForm;
	
	// ----------TODO: add teachers for priciple
	private ObservableList filterBySelectBox = FXCollections.observableArrayList("Anyone", "You", "Others");

	@FXML
	void searchBtnClicked(MouseEvent event) {

	}

	/**
	 * clicking add a new question will go to blank question for page.
	 * 
	 * @param event
	 */
	@FXML
	void clickAddAnewQuestion(MouseEvent event) {
		try {
			blankQuestionForm = FXMLLoader.load(getClass().getResource(Navigator.BLANK_QUESTION_FORM.getVal()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, blankQuestionForm);
	}

	public JFXButton getAddAnewQuestionBtn() {
		return addAnewQuestionBtn;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		selectCbox.setItems(filterBySelectBox);
		if (ClientController.getRoleFrame().equals("Teacher")) {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			ClientController.accept("QUESTION_BANK-" + teacher.getFields().toString());
			ArrayList<Question> arr = ClientController.getQuestions();
		}
		// -------------------- need to implement fill table --------------------
	}
}
