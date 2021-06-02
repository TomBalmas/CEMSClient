package teacherDashboard;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import client.ClientController;
import common.ActiveTest;
import common.Question;
import common.Student;
import common.Teacher;
import common.Test;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
    private JFXButton finishBtn;

    @FXML
    private JFXButton uploadBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton downloadBtn;

    @FXML
    private AnchorPane uploadFileAnchor;

    @FXML
    private JFXTextArea uploadFileTxtArea;

    @FXML
    private AnchorPane questionAnchor;

    @FXML
    private Label questionLbl;

    @FXML
    private AnchorPane insideQuestionAnchor;

    @FXML
    private Label questionAnsweredLbl;

    @FXML
    private Label totalQuestionsLbl;

	@FXML
    private AnchorPane timeAnchor;

    @FXML
    private Label timeLbl;

    @FXML
    private AnchorPane timeValueAnchor;

    @FXML
    private Label timeLbl1;

    @FXML
    private AnchorPane fileUploadedAnchor;

    @FXML
    private AnchorPane timeAnchor1;

    @FXML
    private Label fileNameLbl;

    @FXML
    private JFXButton deleteFileBtn;

    @FXML
    private FontAwesomeIconView deleteFileIcon;

    @FXML
    private JFXTextArea fileCommentsTxtArea;

    @FXML
    private JFXButton editBtn;
	private VBox vbox = new VBox();
	private String fileFullPath, fileName;
	private boolean flag = false; // flag to decide student/teacher
	private int totalNumberOfQuestions = 0;
	final ArrayList<ToggleGroup> questionsToggleGroup = new ArrayList<>();
	Test test = null;
	Student student;
	String testCode = null;

	// getters start
	
	public String getTestCode() {
		return testCode;
	}

	public void setTestCode(String testCode) {
		this.testCode = testCode;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
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
	
    public Label getTotalQuestionsLbl() {
		return totalQuestionsLbl;
	}

	// getters end

    public void setTestFrom() {
    	
    	
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		if (ClientController.getRoleFrame().equals("Student"))
			student = (Student) ClientController.getActiveUser();
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
		setDraggedFileEvents();
		deleteFileBtn.setOnAction((new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
                fileUploadedAnchor.setVisible(false);
                uploadFileAnchor.setVisible(true);
        		uploadBtn.setVisible(true);
        		finishBtn.setVisible(false);
                uploadFileAnchor.setStyle("-fx-background-color: white;");
			}
		}));
	}

	private void setDraggedFileEvents() {
		uploadFileAnchor.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
            	uploadFileAnchor.setStyle("-fx-background-color: #DAD9DD;");
                if (event.getGestureSource() != uploadFileAnchor && event.getDragboard().hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
                }
                event.consume();
            }
        });
		uploadFileAnchor.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                	fileFullPath = db.getFiles().toString();
                	fileName = fileFullPath.substring(fileFullPath.lastIndexOf("\\") + 1);
                	fileName = fileName.substring(0, fileName.length()-1);
					fileNameLbl.setText(fileName);
                    success = true;
                }
                event.setDropCompleted(success);
                event.consume();
                fileUploadedAnchor.setVisible(success);
                uploadFileAnchor.setVisible(!success);
        		uploadBtn.setVisible(false);
        		finishBtn.setVisible(true);
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
//		if (flag)
//			controller.getTeacherNotesTxt().setVisible(false);
		vbox.getChildren().add(question);
		controller.getQuestionNumLbl().setText("Question #" + questionNumber);
		controller.getPointsLbl().setText(String.format("Points: %d", points));
		controller.getContantTxt().setText(q.getQuestionText());
		double questionStyleSpacer = addTextAndresizeTextArea(controller.getContantTxt(), q.getQuestionText());
		controller.getQuestionAnchor().setPrefHeight(controller.getQuestionAnchor().getPrefHeight() + questionStyleSpacer*4);
		controller.getQuestionsVBox().setLayoutY(controller.getQuestionsVBox().getLayoutY()+questionStyleSpacer*4);
		//controller.getQuestionsVBox().setLayoutY(controller.getQuestionsVBox().getLayoutY() + questionStyleSpacer);
		controller.getAnswer1Btn().setText(q.getAnswers().get(0));
		controller.getAnswer2Btn().setText(q.getAnswers().get(1));
		controller.getAnswer3Btn().setText(q.getAnswers().get(2));
		controller.getAnswer4Btn().setText(q.getAnswers().get(3));
		controller.getAnswer1Btn().setToggleGroup(controller.getGroup());
		controller.getAnswer2Btn().setToggleGroup(controller.getGroup());
		controller.getAnswer3Btn().setToggleGroup(controller.getGroup());
		controller.getAnswer4Btn().setToggleGroup(controller.getGroup());
		controller.getGroup().selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (controller.getGroup().getSelectedToggle() != null && old_toggle == null)
					questionAnsweredLbl.setText(String.valueOf(++totalNumberOfQuestions));
			}
		});
		questionsToggleGroup.add(controller.getGroup());
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
		StringBuilder str = new StringBuilder();
		if (!flag) {
			str.append("Teacher instructions:\n");
			str.append(teacherInst + "\n");
		}
		str.append("Student instructions:\n");
		str.append(studentInst);
//		cont.getInstructionsTxtArea().appendText("Teacher instructions:\n");
//		cont.getInstructionsTxtArea().appendText(teacherInst);
//		cont.getInstructionsTxtArea().appendText("\nStudent instructions:\n");
//		cont.getInstructionsTxtArea().appendText(studentInst);
		addTextAndresizeTextArea(cont.getInstructionsTxtArea(), str.toString());
		cont.getTestTitleLbl().setText(title);
		vbox.getChildren().add(element);
		element.prefWidthProperty().bind(scrollPane.widthProperty().subtract(30));
		scrollPane.setContent(vbox);
	}
	
	private double addTextAndresizeTextArea(JFXTextArea textArea, String text) {
		Text t = new Text(text);
		t.setFont(textArea.getFont());
		StackPane pane = new StackPane(t);
		pane.layout();
		double height2 = pane.getHeight();
		double width = t.getLayoutBounds().getWidth();
		double height = t.getLayoutBounds().getHeight();
		double padding = 30;
		textArea.setMaxHeight(height + padding);
		textArea.setText(text);
		return height2 - height;
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
		FileChooser file_chooser = new FileChooser();
		File file = file_chooser.showOpenDialog(AnchorPaneContent.getScene().getWindow());
		if (file != null) {
			fileFullPath = file.getAbsolutePath();
			fileName = file.getName();
			fileNameLbl.setText(fileName);
            fileUploadedAnchor.setVisible(true);
            uploadFileAnchor.setVisible(false);
    		uploadBtn.setVisible(false);
    		finishBtn.setVisible(true);
		}
	}

	/**
	 * finish test clicked, load dashboard
	 * @throws IOException 
	 */
	@FXML
	void finishTestClicked(MouseEvent event) throws IOException {
/*		if (fileFullPath != null) { // Manual test
			// ClientController.accept("FILE: " + fileFullPath);
		} else { // TODO:remove comment when DB is ready
			ArrayList<String> answers = new ArrayList<>();
			for (ToggleGroup tg : questionsToggleGroup)
				answers.add(String.valueOf(tg.getToggles().indexOf(tg.getSelectedToggle()) + 1) + "~");
			answers.set(questionsToggleGroup.size()-1, answers.get(questionsToggleGroup.size() -1).substring(0, answers.get(3).length()-1));
			for (String tg : answers)
				System.out.println(tg);
			//ClientController.accept("SAVE_STUDENT_ANSWERS" + answers);
		}
*/		
		//TODO: save finish test
//		//get scheduler 
//		ClientController.accept("ACTIVE_TEST" + testScheduler);
//		ArrayList<ActiveTest> activeTest = ClientController.getActiveTests();
//		ActiveTest currentTest = null;
//		for (ActiveTest at : activeTest) 
//			if(at.getID().equals(test.getID()))
//					currentTest = at;
		
		//studentSSN,testId,code,startingTime,timeTaken,presentationMethod,title,course,status
		/*		String args;
			args = student.getSSN() + "," + test.getID() + "," + testCode + "," +
		"time taken" + "," + "self" + "," + test.getTitle() + "," + test.getCourse() + "," + "false"; */
		//ClientController.accept("ADD_FINISHED_TEST" + args);
		
//		JFXButton okayBtn = new JFXButton("Okay");
//		okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
			Node page = null;
//			try {
				page = FXMLLoader.load(getClass().getResource(Navigator.STUDENT_DASHBOARD.getVal()));
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
			GeneralUIMethods.loadPage(AnchorPaneContent, page);
//		});
//		AnchorPaneContent.getParent().getParent().toFront();
//		PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Your test has been submited.", (AnchorPane) AnchorPaneContent.getParent().getParent(), Arrays.asList(okayBtn), null);	
	}

}
