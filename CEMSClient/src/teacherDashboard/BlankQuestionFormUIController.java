package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import util.GeneralUIMethods;
import util.Navigator;

public class BlankQuestionFormUIController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

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

	@FXML
	private Label correctAnswer1Lbl;

	@FXML
	private Label correctAnswer2Lbl;

	@FXML
	private Label correctAnswer3Lbl;

	@FXML
	private StackPane questionSaved;
	@FXML
	private Label correctAnswer4Lbl;

	private Node questionBank;
	// toggle group for allowing one choice of radio button
	final ToggleGroup group = new ToggleGroup();

	@FXML
	void clickBack() {
		GeneralUIMethods.loadPage(contentPaneAnchor, questionBank);
	}

	@FXML
	void clickSave() {
		List<JFXButton> l = new ArrayList<JFXButton>();
		l.add(new JFXButton("Okay"));
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupStackPane(), contentPaneAnchor, l, "Question Saved",
				"question Id:");
		if (l.get(0).isPressed())
			clickBack();
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
}