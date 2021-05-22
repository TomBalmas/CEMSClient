package studentDashboard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import teacherDashboard.TestFormController;
import util.GeneralUIMethods;
import util.Navigator;

public class StudentTakeTestController {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private JFXRadioButton manualBtn;

    @FXML
    private JFXRadioButton computedBtn;

    @FXML
    private JFXTextField testCodeField;

    @FXML
    private JFXButton beginTestBtn;


	@FXML
	void beginTestClicked(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
			Node test;
			test = loader.load();
			TestFormController controller = loader.getController();
			controller.getTimeLbl().setVisible(true);
			controller.getDownloadBtn().setVisible(true);
			controller.getUploadBtn().setVisible(true);
			controller.getFinishBtn().setVisible(true);
			controller.getEditBtn().setVisible(false);
			controller.getBackBtn().setVisible(false);
			controller.setFlag(true);
			controller.addQuestionToTestForm(); 	//need to get questions from DB
			controller.addQuestionToTestForm(); 	//need to get questions from DB
			controller.addQuestionToTestForm(); 	//need to get questions from DB
			GeneralUIMethods.loadPage(contentPaneAnchor, test);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
