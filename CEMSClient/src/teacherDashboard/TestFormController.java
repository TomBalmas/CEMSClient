package teacherDashboard;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.Question;
import common.ScheduledTest;
import common.Student;
import common.Test;
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
import util.PopUp;

public class TestFormController implements Initializable {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private AnchorPane testSideBarAnchor;

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
    private Label questionAnsweredLbl1;

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
    private Label newTimeLbl;

    @FXML
    private AnchorPane fileUploadedAnchor;

    @FXML
    private AnchorPane timeAnchor1;

    @FXML
    private Label fileNameLbl;

    @FXML
    private JFXButton deleteFileBtn;

    @FXML
    private JFXTextArea fileCommentsTxtArea;

    @FXML
    private AnchorPane teacherCheckTestSideBar;

	@FXML
    private JFXButton approveBtn;

    @FXML
    private JFXButton disapproveBtn;

    @FXML
    private Label copyResultLbl;

    @FXML
    private Label copyWithLbl;

    @FXML
    private Label gradeLbl;

	@FXML
    private AnchorPane disapproveGradeAnchor;

    @FXML
    private JFXTextField newGrade;

    @FXML
    private JFXTextArea teacherNotes;

    @FXML
    private JFXButton editBtn;
    
    @FXML
    private Label testGradeLbl;

	@FXML
    private StackPane popUpWindow;
    
	private VBox vbox = new VBox();
	private String fileFullPath = "", fileName, submittedBy = "self", teacherNotesOnTest;
	private boolean isStudent = false, isDisapproveClicked = false; // flag to decide student/teacher
	private int totalNumberOfQuestions = 0;
	private String testType;
	final ArrayList<ToggleGroup> questionsToggleGroup = new ArrayList<>();
	//private Pair<String, String> studentValues;
	private long startTime = 0;
	private ArrayList<String> studentValues = new ArrayList<>(4); // SSN, testId, grade, teacherNotes
	Node teacherDashboardPageLoader = null;
	TitleAndInstructionsController titleAndInstructionsController;
	Label testTitleFromFXMLLbl;
	Test test = null;
	Student student;
	String testCode = null;

	// getters start
	
    public AnchorPane getQuestionAnchor() {
		return questionAnchor;
	}
	
    public Label getTestGradeLbl() {
		return testGradeLbl;
	}
	
	public void setTeacherNotesOnTest(String teacherNotesOnTest) {
		this.teacherNotesOnTest = teacherNotesOnTest;
	}
	
    public Label getCopyResultLbl() {
		return copyResultLbl;
	}

	public Label getCopyWithLbl() {
		return copyWithLbl;
	}
	
    public Label getGradeLbl() {
		return gradeLbl;
	}
    
    public AnchorPane getTeacherCheckTestSideBar() {
		return teacherCheckTestSideBar;
	}

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
		this.isStudent = flag;
	}

	public AnchorPane getContentPaneAnchor() {
		return contentPaneAnchor;
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
	
	public Label getNewTimeLbl() {
		return newTimeLbl;
	}

	public Label getTimeLbl() {
		return timeLbl;
	}

	public Label getTimeLbl1() {
		return timeLbl1;
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

	public ArrayList<ToggleGroup> getQuestionsToggleGroup() {
		return questionsToggleGroup;
	}
	
	public Label getTestTitleFromFXMLLbl() {
		return testTitleFromFXMLLbl;
	}
	
	public ArrayList<String> getStudentValues() {
		return studentValues;
	}

	public void setStudentValues(ArrayList<String> studentValues) {
		this.studentValues = studentValues;
	}

	// getters end
	
    public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}
	
	public void setTestFrom() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		isDisapproveClicked = false;
		studentValues = null;
		GeneralUIMethods.setPopupPane(popUpWindow);
		if (ClientController.getRoleFrame().equals("Student"))
			student = (Student) ClientController.getActiveUser();
		downloadBtn.setVisible(false);
		uploadBtn.setVisible(false);
		vbox.setSpacing(10);
		
		if (ClientController.getRoleFrame().equals("Teacher"))
			scrollPane.setTranslateX(-280);
		
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
		startTime = System.currentTimeMillis(); // Start count time
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
		vbox.getChildren().add(question);
		controller.getQuestionNumLbl().setText("Question #" + questionNumber);
		controller.getPointsLbl().setText(String.format("Points: %d", points));
		controller.getContentTxt().setText(q.getQuestionText());
		double questionStyleSpacer = addTextAndresizeTextArea(controller.getContentTxt(), q.getQuestionText());
		controller.getQuestionAnchor()
				.setPrefHeight(controller.getQuestionAnchor().getPrefHeight() + questionStyleSpacer * 4);
		controller.getQuestionsVBox().setLayoutY(controller.getQuestionsVBox().getLayoutY() + questionStyleSpacer * 4);
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
	 * Adds title and instructions to the test form's scroll pane
	 * 
	 * @throws IOException
	 */
	public void addTitleAndInstructionsToTest(String title, String teacherInst, String studentInst) throws IOException {
		FXMLLoader titleAndInstructionsLoader = new FXMLLoader(getClass().getResource(Navigator.TITLE_AND_INSTRUCTIONS.getVal()));
		Region titleAndInstructionsPage = titleAndInstructionsLoader.load();
		titleAndInstructionsController = titleAndInstructionsLoader.getController();
		StringBuilder str = new StringBuilder();
		testTitleFromFXMLLbl = titleAndInstructionsController.getTestTitleLbl();
		if (null != testType && testType.equals("STUDENT_LOOK")) {
			int i = 0;
			ClientController.accept("GET_GRADES_BY_SSN-" + ClientController.getActiveUser().getSSN());
			for (i = 0; i < ClientController.getGrades().size(); i++) {
				if (ClientController.getGrades().get(i).getTestId().equals(test.getID())) {
					break;
				}
			}
			testGradeLbl.setVisible(true);
			int studentGrade = ClientController.getGrades().get(i).getGrade();
			testGradeLbl.setText(studentGrade + "");
			if (studentGrade < 55)
				testGradeLbl.getStyleClass().add("fGradeLbl");
			else
				testGradeLbl.getStyleClass().add("aGradeLbl");
			teacherNotesOnTest = ClientController.getGrades().get(i).getTeacherNotes();
			if (null != teacherNotesOnTest
					&& (!teacherNotesOnTest.equals("")
							|| !teacherNotesOnTest.equals("null")
							|| !teacherNotesOnTest.equals(null)
							|| !teacherNotesOnTest.isEmpty())) {
				titleAndInstructionsController.getInstructionsLbl().setText("Teacher notes:");
				str.append(teacherNotesOnTest + "\n");
			}
			else titleAndInstructionsController.getInstructionsLbl().setVisible(false);
			ClientController.setGrades(null);
		} else {
			if (!isStudent) {
				str.append("Teacher instructions:\n");
				str.append(teacherInst + "\n");
			}
			str.append("Student instructions:\n");
			str.append(studentInst);
		}
		
		addTextAndresizeTextArea(titleAndInstructionsController.getInstructionsTxtArea(), str.toString());
		titleAndInstructionsController.getTestTitleLbl().setText(title);
		vbox.getChildren().add(titleAndInstructionsPage);
		titleAndInstructionsPage.prefWidthProperty().bind(scrollPane.widthProperty().subtract(30));
		scrollPane.setContent(vbox);
	}

	public double addTextAndresizeTextArea(JFXTextArea textArea, String text) {
		Text textBox = new Text(text);
		textBox.setFont(textArea.getFont());
		StackPane pane = new StackPane(textBox);
		pane.layout();
		double paneHeight = pane.getHeight();
		double textBoxHeight = textBox.getLayoutBounds().getHeight();
		double paddingToBeAdded = 30;
		textArea.setMaxHeight(textBoxHeight + paddingToBeAdded);
		textArea.setText(text);
		return paneHeight - textBoxHeight;
	}

	/**
	 * back to previous screen
	 * 
	 * @throws IOException
	 */
	@FXML
	void backClicked(MouseEvent event) throws IOException {
		Node page = FXMLLoader.load(getClass().getResource(Navigator.ADDING_NEW_TEST.getVal()));
		GeneralUIMethods.loadPage(contentPaneAnchor, page);
	}

	/**
     * Download word file
     */
    @FXML
    void downloadFileClicked(MouseEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    String path = System.getProperty("user.home");
                    path += "/Downloads/" + test.getID() + "_" + test.getTitle() + "_" +student.getSSN() + ".docx";
                    File studentWordTest= new File(path);
                    FileOutputStream fos = new FileOutputStream(studentWordTest);
                    fos.close();
                    if (Desktop.isDesktopSupported())
                        Desktop.getDesktop().open(studentWordTest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
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
					fileName = fileName.substring(0, fileName.length() - 1);
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
	 * upload file
	 */
	@FXML
	void uploadFileClicked(MouseEvent event) {
		FileChooser file_chooser = new FileChooser();
		File file = file_chooser.showOpenDialog(contentPaneAnchor.getScene().getWindow());
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
	 * 
	 * @throws IOException
	 */
	@FXML
	void finishTestClicked(ActionEvent event) throws IOException {
		System.out.println(testType);
		if (fileFullPath != "" || testType.equals("Manual")) { // Manual test
			ClientController.accept("GET_SCHEDULED_TEST_BY_CODE-" + testCode);
			ScheduledTest scheduledTest = ClientController.getScheduledTest();
			ClientController.accept("FILE-" + fileFullPath + "~" + "ADD_MANUAL_TEST-" + test.getID() + ","
					+ student.getSSN() + "," + scheduledTest.getBelongsToID() + "," + scheduledTest.getDate() + ","
					+ scheduledTest.getStartingTime());
			ClientController.setTestType("Manual");
		} else { // Computed test - save student answers
			String answers = "";
			for (ToggleGroup tg : questionsToggleGroup)
				answers += (String.valueOf(tg.getToggles().indexOf(tg.getSelectedToggle()) + 1) + "~");
			answers = answers.substring(0, answers.length() - 1);
			ClientController.accept("SAVE_STUDENT_ANSWERS-" + student.getSSN() + "," + test.getID() + "," + answers);

			// Add the student test to the finished test table
			ClientController.accept("ADD_FINISHED_TEST-" + student.getSSN() + "," + test.getID() + "," + testCode + ","
					+ ((System.currentTimeMillis() - startTime) / 60000) + "," + submittedBy + "," + test.getTitle()
					+ "," + test.getCourse() + "," + "UnChecked");
			ClientController.setTestType("Computed");
		}

		// Delete the student from the test
		ClientController.accept("DELETE_STUDENT_FROM_TEST-" + ClientController.getActiveUser().getSSN());
		if (!ClientController.isStudentDeletedFromTest()) {
			new PopUp(PopUp.TYPE.ERROR, "Error", "An error accured while submission of the test", contentPaneAnchor, null,
					null);
			return;
		}
		
		// Check if its the last student in the
		ClientController.accept("IS_LAST_STUDENT_IN_TEST-" + testCode);
		if (ClientController.isLastStudentInTest() && ClientController.getTestType().equals("Computed")) {
			// If so, lock the computed test
			ClientController.accept("LOCK_TEST-" + testCode);
			ClientController.setStudentTest(null);
			ClientController.setTimeForTest(false);
			ClientController.setTestLocked(false);
			ClientController.setLastStudentInTest(false);
		} else if (ClientController.isLastStudentInTest() && ClientController.getTestType().equals("Manual")) {
			// If so, lock the manual test
			ClientController.accept("LOCK_MANUAL_TEST-" + testCode);
			ClientController.setStudentTest(null);
			ClientController.setTimeForTest(false);
			ClientController.setTestLocked(false);
			ClientController.setLastStudentInTest(false);
		}
		
		// Reset variables
		ClientController.setStudentTest(null);
		ClientController.setIsActiveTest(false);
		ClientController.setTimeForTest(false);

		// Show popup window
		JFXButton okayBtn = new JFXButton("Okay");
		okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
		Node studentDashboardLoader = null;
		try {
			studentDashboardLoader = FXMLLoader.load(getClass().getResource(Navigator.STUDENT_DASHBOARD.getVal()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		GeneralUIMethods.loadPage(contentPaneAnchor, studentDashboardLoader);
		});

		new PopUp(PopUp.TYPE.INFORM, "Information", "Your test has been submited.", null, Arrays.asList(okayBtn), null);	
	}
	
	@FXML
    void approveBtnClicked(MouseEvent event) {
		// Update Finished test table
		if (isDisapproveClicked) {
			if (!newGrade.getText().matches("\\d+") || Integer.parseInt(newGrade.getText()) < 0
					|| Integer.parseInt(newGrade.getText()) > 100) {
				new PopUp(PopUp.TYPE.ERROR, "Error", "Grade text field must be a valid grade.", null,
						null, null);
				return;
			} else if (newGrade.getText().isEmpty() || teacherNotes.getText().isEmpty()) {
				new PopUp(PopUp.TYPE.ERROR, "Error", "Some field are missing", null, null, null);
				return;
			}
			studentValues.set(2, newGrade.getText());
			studentValues.set(3, teacherNotes.getText());
		}
		ClientController.accept("UPDATE_FINISHED_TEST-" + studentValues.get(1) + "," + studentValues.get(0) + "," + studentValues.get(2));
		// Add the test to the grades table
		ClientController.accept("ADD_GRADE-" + studentValues.get(1) + "," + studentValues.get(0) + "," + studentValues.get(3));

		// Show popup window
		JFXButton okayBtn = new JFXButton("Okay");
		okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
			try {
				teacherDashboardPageLoader = FXMLLoader.load(getClass().getResource(Navigator.TEACHER_DASHBOARD.getVal()));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			GeneralUIMethods.loadPage(contentPaneAnchor, teacherDashboardPageLoader);
		});
		GeneralUIMethods.setPopupPane(popUpWindow);
		new PopUp(PopUp.TYPE.INFORM, "Information", "You succesfully checked " + studentValues.get(1) + " test.", teacherCheckTestSideBar, Arrays.asList(okayBtn), null);
    }

	@FXML
    void disapproveBtnClicked(MouseEvent event) {
    	disapproveGradeAnchor.setVisible(true);
    	approveBtn.setText("Approve new grade");
    	isDisapproveClicked = true;
    }
	
	
	public void setStudentAnswers(String testId, String studentSSN) {
		// Get students answers and select them
		ClientController.accept("GET_STUDENT_ANSWERS_BY_SSN_AND_TEST_ID-" + testId + "," + studentSSN);
		int i = 0;
		for (ToggleGroup tg : getQuestionsToggleGroup())
			if (!ClientController.getStudentAnswers().isEmpty() && !ClientController.getStudentAnswers().get(0).getKey().equals("studentDidn'tTakeTest")) {
				if(ClientController.getStudentAnswers().get(i).getValue() != 0)
					tg.getToggles().get(ClientController.getStudentAnswers().get(i).getValue() - 1).setSelected(true);
				i++;
			}
			else {
				
			}
		ClientController.setStudentAnswers(null);
	}
	
	public void setQuestionsFromTest(String testId) {
		ClientController.accept("GET_QUESTIONS_FROM_TEST-" + testId);
		int i = 0;
		for (ToggleGroup tg : getQuestionsToggleGroup())
			if (!ClientController.getQuestions().isEmpty()) {
				tg.getToggles().get(ClientController.getQuestions().get(i).getCorrectAnswer() - 1).setSelected(true);
				i++;
			}
		ClientController.setQuestions(null);
	}
	
	

}
 