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

public class BlankQuestionFormUIController implements Initializable {


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
		return correctAnswer;
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

	@FXML
	void clickSave() throws IOException {
		
		List<JFXButton> list = new ArrayList<JFXButton>();
		list.add(new JFXButton("Okay"));
		if (list.get(0).isPressed())
			clickBack();
		ArrayList<JFXTextArea> answers = getAnswerTextFields();
		Teacher teacher = (Teacher) ClientController.getActiveUser();
		//query to add question to dataBase
		if(getNewQuestionFormLbl().getText().toString().equals("New Question Form"))
		{
			
			//author,questionContent,correctAnswer,field,answer1,answer2,answer3,answer4
			String queryAddQuestion= "ADD_QUESTION-" + teacher.getName() + "," + getQuestionContentTxt().getText() + "," + correctAnswer +"," +fieldCBox.getValue().toString()+ "," +
			answers.get(0).getText() + "," +  answers.get(1).getText() + "," +  answers.get(2).getText() + "," +  answers.get(3).getText();
			ClientController.accept(queryAddQuestion);
			//show popup
			String toShow="Question ID: " ;
			toShow=toShow.concat(ClientController.getNewQuestionId());
			util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupPane(), contentPaneAnchor, GeneralUIMethods.getSideBar(), list, "Question Saved",toShow);
				
			  System.out.println(ClientController.isQuestionAdded());
		}
		//query for editing question
		else {
			String[] questionID = getNewQuestionFormLbl().getText().toString().split(" "); 
			ArrayList<JFXTextArea> answersArray = getAnswerTextFields();
			String queryEditQuestion= "EDIT_QUESTION-" +questionID[2] +","+ teacher.getName() + "," +getQuestionContentTxt().getText() + "," + getCorrectAnswer() +"," +fieldCBox.getPromptText().toString()+ "," +
	    	answersArray.get(0).getText() + "," +  answersArray.get(1).getText() + "," +  answersArray.get(2).getText() + "," +  answersArray.get(3).getText();
			ClientController.accept(queryEditQuestion );
			boolean answerEdit =  ClientController.isQuestionEdited();
			System.out.println(answerEdit);
		 
		
			
			
		}
		
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
					if (button.equals(answer1Btn))
						correctAnswer1Lbl.setVisible(true);
						correctAnswer=1;
					if (button.equals(answer2Btn))
						correctAnswer2Lbl.setVisible(true);
						correctAnswer=2;
					if (button.equals(answer3Btn))
						correctAnswer3Lbl.setVisible(true);
						correctAnswer=3;
					if (button.equals(answer4Btn))
						correctAnswer4Lbl.setVisible(true);
						correctAnswer=4;

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