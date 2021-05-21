package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import util.GeneralUIMethods;
import util.Navigator;

public class TestFormController implements Initializable {

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private JFXButton editBtn;

	@FXML
	private JFXButton backBtn;

	@FXML
	private Label timeLbl;

	@FXML
	private JFXButton downloadBtn;

	@FXML
	private JFXButton uploadBtn;

	@FXML
	private JFXButton finishBtn;
	
	@FXML
	private AnchorPane AnchorPaneContent;

	private VBox vbox = new VBox();
	private boolean flag = false;	//flag to decide student/teacher

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	// getters start

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public JFXButton getEditBtn() {
		return editBtn;
	}

	public JFXButton getBackBtn() {
		return backBtn;
	}

	public Label getTimeLbl() {
		return timeLbl;
	}

	public JFXButton getDownloadBtn() {
		return downloadBtn;
	}

	public JFXButton getUploadBtn() {
		return uploadBtn;
	}

	public JFXButton getFinishBtn() {
		return finishBtn;
	}

	public VBox getVbox() {
		return vbox;
	}

	// getters end

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			timeLbl.setVisible(false);
			downloadBtn.setVisible(false);
			uploadBtn.setVisible(false);
			finishBtn.setVisible(false);
			addTitleToTest();
			vbox.setSpacing(20);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this function adds questions to the test form's scroll pane
	 * 
	 * @throws IOException
	 *
	 */
	public void addQuestionToTestForm() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.QUESTION.getVal()));
		Node question = loader.load();
		QuestionController controller = loader.getController();
		if(flag)
			controller.getTeacherNotesTxt().setVisible(false);
		vbox.getChildren().add(question);
		scrollPane.setContent(vbox);
	}

	/**
	 * adds title and instructions to the test form's scroll pane
	 * 
	 * @throws IOException
	 */
	public void addTitleToTest() throws IOException {
		Node element = FXMLLoader.load(getClass().getResource(Navigator.TITLE_AND_INSTRUCTIONS.getVal()));
		vbox.getChildren().add(element);
		scrollPane.setContent(vbox);
	}
	
    @FXML
    void backClicked(MouseEvent event) throws IOException {
    	Node page = FXMLLoader.load(getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
    	GeneralUIMethods.loadPage(AnchorPaneContent, page);
    }

}
