package teacherDashboard;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class QuestionFormUIController {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private Label questionBankLbl;

    @FXML
    private JFXTextArea QuestionIdTxtArea;

    @FXML
    private JFXTextArea questionContentTxtArea;

    @FXML
    private JFXTextArea authorTxtArea;

    @FXML
    private Text questionIdTxt;

    @FXML
    private Text authorTxt;

    @FXML
    private JFXButton editBtn;

    @FXML
    private JFXButton backBtn;

    @FXML
    private JFXButton addToTestBtn;

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

    @FXML
    private Label correctAnswer3Lbl;

    @FXML
    private Label correctAnswer2Lbl;

    @FXML
    private Label correctAnswer1Lbl;

    @FXML
    private Label correctAnswer4Lbl;

    @FXML
    void clickBack(MouseEvent event) {

    }

    @FXML
    void clickSave(MouseEvent event) {

    }

}


