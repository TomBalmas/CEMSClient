package studentDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.Question;
import common.Test;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import teacherDashboard.TestFormController;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class StudentTakeTestController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private JFXRadioButton computedBtn;

	@FXML
	private ToggleGroup testGroup;

	@FXML
	private JFXRadioButton manualBtn;

	@FXML
	private Label codeLbl;

	@FXML
	private JFXTextField testCodeField;

	@FXML
	private JFXButton beginTestBtn;

	@FXML
	private Label takeTestLbl;

	@FXML
	private Label testOptLbl;
	
	FXMLLoader testFormLoader = null;
	String testType = null;

	@FXML
	void beginTestClicked(MouseEvent event) {
		if (testCodeField.getText().equals("")) {
			PopUp.showMaterialDialog(PopUp.TYPE.ERROR, "Error", "Your code is invalid", contentPaneAnchor, null, null);
			return;
		}
		testFormLoader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
		if (testGroup.getSelectedToggle().equals(manualBtn))
			testType = "Manual";
		else
			testType = "Computed";
		GeneralUIMethods.buildTestForm(contentPaneAnchor, null, testCodeField.getText(), testType, testFormLoader);
//		ClientController.accept("GET_TEST_BY_CODE-" + testCodeField.getText());
//		Test t = ClientController.getStudentTest();
//		if (null == t)
//			PopUp.showMaterialDialog(PopUp.TYPE.ERROR, "Error", "Your code is invalid", contentPaneAnchor, null, null);
//		else {
//			ClientController.accept("GET_QUESTIONS_FROM_TEST-" + t.getID());
//			ArrayList<Question> testQuestions = ClientController.getQuestions();
//			if (null != testQuestions)
//			try {
//				FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
//				Node test = loader.load();
//				TestFormController controller = loader.getController();
//				controller.setTest(t);
//				controller.setTestCode(testCodeField.getText().toString());
//				controller.getEditBtn().setVisible(false);
//				controller.getBackBtn().setVisible(false);
//				controller.getTestSideBarAnchor().setVisible(true);
//				controller.setFlag(true);
//				if (testGroup.getSelectedToggle().equals(manualBtn)) {
//					controller.getDownloadBtn().setVisible(true);
//					controller.getUploadBtn().setVisible(true);
//					controller.getUploadFileAnchor().setVisible(true);
//					controller.getFinishBtn().setVisible(false);
//				} else
//					controller.getUploadFileAnchor().setVisible(false);
//				controller.addTitleAndInstructionsToTest(t.getTitle(), null, t.getStudentInstructions());
//				int i = 1;
//				for (Question q : testQuestions) {
//					controller.addQuestionToTestForm(q, i, 100 / testQuestions.size()); // adding questions to preview
//					i++;
//				}
//				controller.getTotalQuestionsLbl().setText(String.valueOf(--i));
//				GeneralUIMethods.loadPage((AnchorPane) contentPaneAnchor.getParent().getParent(), test);
//
//				// Set the correct view for the student
//				Platform.runLater(new Runnable() {
//					@Override
//					public void run() {
//						contentPaneAnchor.setTranslateX(-1 * (controller.getTestSideBarAnchor().getWidth()));
//					}
//				});
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		manualBtn.setToggleGroup(testGroup);
		computedBtn.setToggleGroup(testGroup);
	}

}
