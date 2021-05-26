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

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	private Node questionBank;
	// toggle group for allowing one choice of radio button
	final ToggleGroup group = new ToggleGroup();

	@FXML
	void clickBack() {
		GeneralUIMethods.loadPage(contentPaneAnchor, questionBank);
	}

	@FXML
	void clickSave() {
		String questionID = "OVER 9000"; //--------TODO: fetch from db
		JFXButton okayBtn = new JFXButton("Okay");
		okayBtn.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e)->{
			clickBack();
		});
		PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Question Saved", "question Id: " + questionID,
				contentPaneAnchor, Arrays.asList(okayBtn), null);
	}

	/**
	 * this method initializes the toggle group for radio button and other data
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
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
					if (button.equals(answer2Btn))
						correctAnswer2Lbl.setVisible(true);
					if (button.equals(answer3Btn))
						correctAnswer3Lbl.setVisible(true);
					if (button.equals(answer4Btn))
						correctAnswer4Lbl.setVisible(true);

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

}