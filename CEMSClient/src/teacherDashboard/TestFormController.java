package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import client.ClientController;
import common.Question;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import util.GeneralUIMethods;
import util.Navigator;

public class TestFormController implements Initializable {

	@FXML
	private AnchorPane AnchorPaneContent;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private AnchorPane testSideBarAnchor;

	@FXML
	private AnchorPane insideTestSideBarAnchor;

	@FXML
	private AnchorPane uploadFileAnchor;

	@FXML
	private AnchorPane questionAnchor;

	@FXML
	private Label questionLbl;

	@FXML
	private AnchorPane insideQuestionAnchor;

	@FXML
	private Label questionAnsweredLbl;

	@FXML
	private JFXButton editBtn;

	@FXML
	private JFXButton downloadBtn;

	@FXML
	private JFXButton uploadBtn;

	@FXML
	private JFXButton finishBtn;

	@FXML
	private JFXButton backBtn;

	@FXML
	private AnchorPane timeAnchor;

	@FXML
	private Label timeLbl;

	@FXML
	private AnchorPane timeValueAnchor;

	@FXML
	private Label timeLbl1;

	private VBox vbox = new VBox();
	private boolean flag = false; // flag to decide student/teacher

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	// getters start

	public AnchorPane getAnchorPaneContent() {
		return AnchorPaneContent;
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public AnchorPane getTestSideBarAnchor() {
		return testSideBarAnchor;
	}

	public AnchorPane getUploadFileAnchor() {
		return uploadFileAnchor;
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
		downloadBtn.setVisible(false);
		uploadBtn.setVisible(false);
		vbox.setSpacing(10);
		// FIX FOR BACKDOOR - REMOVE getRoleFrame
		if (ClientController.getRoleFrame().equals("Teacher"))
			scrollPane.setTranslateX(-280);
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
			}
		});
	}

	/**
	 * this function adds questions to the test form's scroll pane
	 * 
	 * @throws IOException
	 *
	 */
	public void addQuestionToTestForm(Question q, int questionNumber, int points) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.QUESTION.getVal()));
		Node question = loader.load();
		QuestionController controller = loader.getController();
		if (flag)
			controller.getTeacherNotesTxt().setVisible(false);
		vbox.getChildren().add(question);
		controller.getQuestionNumLbl().setText("Question: " + questionNumber);
		controller.getPointsLbl().setText(String.format("Points: %d", points));
		controller.getContantTxt().setText(q.getQuestionText());
		controller.getAnswer1Btn().setText(q.getAnswers().get(0));
		controller.getAnswer2Btn().setText(q.getAnswers().get(1));
		controller.getAnswer3Btn().setText(q.getAnswers().get(2));
		controller.getAnswer4Btn().setText(q.getAnswers().get(3));
		scrollPane.setContent(vbox);
	}

	/**
	 * adds title and instructions to the test form's scroll pane
	 * 
	 * @throws IOException
	 */
	public void addTitleAndInstructionsToTest(String title, String teacherInst, String studentInst) throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.TITLE_AND_INSTRUCTIONS.getVal()));
		Region element = loader.load();
		TitleAndInstructionsController cont = loader.getController();
		cont.getInstructionsTxtArea().appendText("Teacher instructions:\n");
		cont.getInstructionsTxtArea().appendText(teacherInst);
		cont.getInstructionsTxtArea().appendText("\nStudent instructions:\n");
		cont.getInstructionsTxtArea().appendText(studentInst);
		cont.getTestTitleLbl().setText(title);
		vbox.getChildren().add(element);
		element.prefWidthProperty().bind(scrollPane.widthProperty());
		scrollPane.setContent(vbox);
	}

	/**
	 * back to previous screen
	 * 
	 * @throws IOException
	 */
	@FXML
	void backClicked(MouseEvent event) throws IOException {
		Node page = FXMLLoader.load(getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
		GeneralUIMethods.loadPage(AnchorPaneContent, page);
	}

	/**
	 * download word file
	 */
	@FXML
	void downloadFileClicked(MouseEvent event) {

	}

	/**
	 * upload file
	 */
	@FXML
	void uploadFileClicked(MouseEvent event) {
		uploadBtn.setVisible(false);
		finishBtn.setVisible(true);
	}

	/**
	 * finish test clicked, load dashboard
	 */
	@FXML
	void finishTestClicked(MouseEvent event) throws IOException {
		Node page = FXMLLoader.load(getClass().getResource(Navigator.STUDENT_DASHBOARD.getVal()));
		GeneralUIMethods.loadPage(AnchorPaneContent, page);
	}

}
