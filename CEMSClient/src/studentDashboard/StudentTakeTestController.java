package studentDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import teacherDashboard.TestFormController;
import util.GeneralUIMethods;
import util.Navigator;

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

	@FXML
	void beginTestClicked(MouseEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TEST_FORM.getVal()));
			Node test = loader.load();
			TestFormController controller = loader.getController();
			controller.getEditBtn().setVisible(false);
			controller.getBackBtn().setVisible(false);
			controller.getTestSideBarAnchor().setVisible(true);
			controller.setFlag(true);
			if (testGroup.getSelectedToggle().equals(manualBtn)) {
				controller.getDownloadBtn().setVisible(true);
				controller.getUploadBtn().setVisible(true);
				controller.getUploadFileAnchor().setVisible(true);
				controller.getFinishBtn().setVisible(false);
			}
			else 
				controller.getUploadFileAnchor().setVisible(false);
			GeneralUIMethods.loadPage((AnchorPane) contentPaneAnchor.getParent().getParent(), test);
			
			//Set the correct view for the student
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					contentPaneAnchor.setTranslateX(-1 * (controller.getTestSideBarAnchor().getWidth()));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		manualBtn.setToggleGroup(testGroup);
		computedBtn.setToggleGroup(testGroup);
	}

}
