package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import client.ClientController;
import common.Teacher;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class QuestionFormUIController implements Initializable {


    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private Label chooseAnswersLbl11;

    @FXML
    private JFXComboBox<String> fieldCBox;

	@FXML
    private Label newQuestionFormLbl;

    @FXML
    private Label chooseAnswersLbl1;

    @FXML
    private JFXTextArea questionContentTxt;

    @FXML
    private AnchorPane insideFilterAnchor1;

    @FXML
    private Label chooseAnswersLbl;

    @FXML
    private JFXRadioButton answer1Btn;

    @FXML
    private JFXRadioButton answer2Btn;

    @FXML
    private JFXRadioButton answer3Btn;

    @FXML
    private JFXRadioButton answer4Btn;

    @FXML
    private Label correctAnswer3Lbl;

    @FXML
    private Label correctAnswer2Lbl;

    @FXML
    private Label correctAnswer1Lbl;

    @FXML
    private Label correctAnswer4Lbl;

    @FXML
    private JFXTextArea answer4Txt;

    @FXML
    private JFXTextArea answer3Txt;

    @FXML
    private JFXTextArea answer2Txt;

    @FXML
    private JFXTextArea answer1Txt;
    private int correctAnswer;


	public int getCorrectAnswer() {
		return correctAnswer=0;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	private Node questionBank;
	// toggle group for allowing one choice of radio button
	final ToggleGroup group = new ToggleGroup();
	private int selectedAnswer=0;
	private String author;
	ObservableList fields = FXCollections.observableArrayList();





	@FXML
	void clickBack() throws IOException {
		
		Node page;
		try {
			page = FXMLLoader.load(getClass().getResource(Navigator.QUESTION_BANK.getVal()));
			GeneralUIMethods.loadPage(contentPaneAnchor, page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		//GeneralUIMethods.loadPage(contentPaneAnchor, questionBank);
	}

	/**
	 *the method handles sending a query to DB for editing question  and adding a new question
	 */
	@FXML
	void clickSave() throws IOException {
		
		List<JFXButton> list = new ArrayList<JFXButton>();
		list.add(new JFXButton("Okay"));
		if (list.get(0).isPressed())
			clickBack();
		//get data from UI 
		Teacher teacher = (Teacher) ClientController.getActiveUser();
		String teacherName = teacher.getName();
		ArrayList<JFXTextArea> answers = getAnswerTextFields();
		String questionContent=getQuestionContentTxt().getText();
		String answer1 = answers.get(0).getText() ;
		String answer2 = answers.get(1).getText() ;
		String answer3 = answers.get(2).getText() ;
		String answer4 = answers.get(3).getText() ;
		//query to add question to dataBase
		if(getNewQuestionFormLbl().getText().toString().equals("New Question Form"))
		{
			
			//send query only if fields arent empty 
			if( correctAnswer!=0  && !questionContent.isEmpty() && !fieldCBox.getValue().toString().isEmpty()  && !answer1.isEmpty() && !answer2.isEmpty() && !answer3.isEmpty() && !answer4.isEmpty())
			 {
				Teacher connected = (Teacher) ClientController.getActiveUser();
				
				//author,questionContent,correctAnswer,field,answer1,answer2,answer3,answer4
				String queryAddQuestion= "ADD_QUESTION-" + connected.getSSN() + "," + questionContent + "," + correctAnswer +"," +fieldCBox.getValue().toString()+ "," +
						answer1 + "," +  answer2 + "," + answer2 + "," + answer4;
				ClientController.accept(queryAddQuestion);
				
				//check if question added correctly
				if(ClientController.isQuestionAdded()) {
				//show POP UP:
				String toShow="Question ID: " ;
				toShow=toShow.concat(ClientController.getNewQuestionId());
				util.PopUp.showMaterialDialog(util.PopUp.TYPE .SUCCESS ,"Question Saved",toShow, contentPaneAnchor,list,null );
				
				}
		
			//handle empty fields
			}
			else {
				
				util.PopUp.showMaterialDialog(util.PopUp.TYPE .SUCCESS ,"Question not  Saved","some fields are empty", contentPaneAnchor,list,null );
			}
			
			
			
			
		}
		//query for editing question
		else {
		
			String[] questionID = getNewQuestionFormLbl().getText().toString().split(" "); 
			String queryEditQuestion= "EDIT_QUESTION-" +questionID[2] +","+ teacherName + "," +getQuestionContentTxt().getText() + "," + correctAnswer +"," +fieldCBox.getPromptText().toString()+ "," +
					answer1 + "," + answer2 + "," +  answer3 + "," + answer4;
			if(  correctAnswer!=0 && !getQuestionContentTxt().getText().isEmpty() && !fieldCBox.getPromptText().toString().isEmpty() && !answer1.isEmpty() && !answer2.isEmpty()
					&& !answer3.isEmpty() && !answer4.isEmpty()) {//send query onlt if all fields are not empty 
				ClientController.accept(queryEditQuestion );
				boolean answerEdit =  ClientController.isQuestionEdited();
				//check if answer edited correctly in DB
				if(answerEdit) {
					util.PopUp.showMaterialDialog(util.PopUp.TYPE .SUCCESS ,"Question Edited", " " , contentPaneAnchor,null,null );
				}
			}
			else {
				util.PopUp.showMaterialDialog(util.PopUp.TYPE .SUCCESS ,"Question not  Edited", "Some fields are missing! " , contentPaneAnchor,null,null );
			}
			
		}
		correctAnswer=0;
	}

	/**
	 * this method initializes the toggle group for radio button and other data
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Teacher teacher = (Teacher) ClientController.getActiveUser();
			String[] fieldsSplit = teacher.getFields().split("~");
			for (String oneField : fieldsSplit)
				fields.add(oneField);
			getFieldCBox().setItems(fields);
			
			questionBank = FXMLLoader.load(getClass().getResource(Navigator.QUESTION_BANK.getVal()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		answer1Btn.setToggleGroup(group);
		answer2Btn.setToggleGroup(group);
		answer3Btn.setToggleGroup(group);
		answer4Btn.setToggleGroup(group);
		setToggleGroupNotVisible();
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {// observable to view
																									// changes in radio
																									// button
				if (group.getSelectedToggle() != null) {
					JFXRadioButton button = (JFXRadioButton) group.getSelectedToggle();
					setToggleGroupNotVisible();
					if (button.equals(answer1Btn)) {
						correctAnswer1Lbl.setVisible(true);
						correctAnswer=1;
					}
					if (button.equals(answer2Btn)) {
						correctAnswer2Lbl.setVisible(true);
						correctAnswer=2;
					}
					if (button.equals(answer3Btn)) {
						correctAnswer3Lbl.setVisible(true);
						correctAnswer=3;
					}
					if (button.equals(answer4Btn)) {
						correctAnswer4Lbl.setVisible(true);
						correctAnswer=4;
					}

				}

			}

		});
	}

	/**
	 * this method sets the correct answers label to be not visible
	 */
	public void setToggleGroupNotVisible() {
		correctAnswer1Lbl.setVisible(false);
		correctAnswer2Lbl.setVisible(false);
		correctAnswer3Lbl.setVisible(false);
		correctAnswer4Lbl.setVisible(false);
	}
	
    public Label getNewQuestionFormLbl() {
		return newQuestionFormLbl;
	}

    public JFXTextArea getQuestionContentTxt() {
		return questionContentTxt;
	}
    
    public ArrayList<JFXTextArea> getAnswerTextFields() {
    	ArrayList<JFXTextArea> answers = new ArrayList<JFXTextArea>();
    	answers.add(answer1Txt);
    	answers.add(answer2Txt);
    	answers.add(answer3Txt);
    	answers.add(answer4Txt);
    	return answers;
	}
    
    public ArrayList<JFXRadioButton> getAnswerBtns() {
    	ArrayList<JFXRadioButton> answersBtns = new ArrayList<JFXRadioButton>();
    	answersBtns.add(answer1Btn);
    	answersBtns.add(answer2Btn);
    	answersBtns.add(answer3Btn);
    	answersBtns.add(answer4Btn);
    	return answersBtns;
	}

    public JFXButton getSaveBtn() {
		return saveBtn;
	}
    
    public JFXComboBox<?> getFieldCBox() {
		return fieldCBox;
	}
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
   

}