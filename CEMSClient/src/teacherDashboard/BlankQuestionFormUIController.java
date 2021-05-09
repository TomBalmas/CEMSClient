package teacherDashboard;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
public class BlankQuestionFormUIController {

    @FXML
    private AnchorPane contentPaneAnchr;

    @FXML
    private Label newQuestionFormLbl;

    @FXML
    private JFXTextArea questionContentTxt;

    @FXML
    private Label chooseAnswersLbl;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton saveBtn;

    @FXML
    private JFXRadioButton answer1Btn;

    @FXML
    private JFXTextArea answer1Txt;

    @FXML
    private JFXRadioButton answer2Btn;

    @FXML
    private JFXTextArea answer2Txt;

    @FXML
    private JFXRadioButton answer3Btn;

    @FXML
    private JFXTextArea answer3Txt;

    @FXML
    private JFXRadioButton answer4Btn;

    @FXML
    private JFXTextArea answer4Txt;
	    
	    
	    
	    //need to add a method that allows to select only one answer at a time
	    private void setCorrectAnswer(JFXButton b) {
	        testBankBtn.setStyle("");
	        questionBankBtn.setStyle("");
	        viewActiveTestsBtn.setStyle("");
	        scheduledTestsBtn.setStyle("");
	        testReportsBtn.setStyle("");
	        checkTestsBtn.setStyle("");
	        b.setStyle("-fx-background-color:#00ADB5;");
	    }

}
