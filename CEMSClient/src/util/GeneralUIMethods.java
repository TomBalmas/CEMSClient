package util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.Question;
import common.Test;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import teacherDashboard.QuestionController;
import teacherDashboard.TestFormController;

public class GeneralUIMethods {

	private static int menuMovementLeftToRight = 1280 - 283 + 1;
	public static StackPane sp;
	public static VBox sideBar;
	static boolean isFieldEmpty = false;
	static Region testFormNode;
	private static AnchorPane mainAnchorPane;
	private static boolean isCheckingtest = false;

	public static AnchorPane getMainAnchorPane() {
		return mainAnchorPane;
	}

	public static void setMainAnchorPane(AnchorPane mainAnchorPane) {
		GeneralUIMethods.mainAnchorPane = mainAnchorPane;
	}

	public static boolean isCheckingtest() {
		return isCheckingtest;
	}

	public static void setCheckingtest(boolean isCheckingtest) {
		GeneralUIMethods.isCheckingtest = isCheckingtest;
	}

	/**
	 * moves object on the screen "layoutX" pixels in "time" seconds.
	 * 
	 * @param o
	 * @param layoutX
	 * @param time
	 * @param e
	 */
	public static void moveItem(Object o, double layoutX, double time, EventHandler<ActionEvent> e) {
		TranslateTransition t = new TranslateTransition(Duration.seconds(time), (Node) o);
		t.setToX(layoutX);
		t.play();
		t.setOnFinished(e);
	}

	/**
	 * clicking sign out goes back to the login screen contentPaneAnchor - to be
	 * deleted anchorLogin - to be shown as the main anchor menuVBox - to be moved
	 * to original position login - to set anchorLogin
	 * 
	 * @param contentPaneAnchor
	 * @param anchorLogin
	 * @param menuVBox
	 * @param login
	 */
	public static void signOut(AnchorPane contentPaneAnchor, AnchorPane anchorLogin, VBox menuVBox, Node login) {
		menuVBox.toFront();
		contentPaneAnchor.getChildren().clear();
		moveItem(menuVBox, menuMovementLeftToRight, 1, (e) -> {
			loadPage(anchorLogin, login);
		});
	}

	/**
	 * paints the background color of the button given and removes the color from
	 * all other buttons on the menu
	 * 
	 * @param button
	 * @param menuVBox
	 */
	public static void setMenuStyle(JFXButton button, VBox menuVBox) {
		for (Node t : menuVBox.getChildren())
			t.setStyle("");
		button.setStyle("-fx-background-color: #2486b6;");
	}

	public static boolean checkEmptyFields(ArrayList<Node> n) {
		isFieldEmpty = false;
		n.forEach(e -> {
			if (e instanceof JFXTextField) {
				if (((JFXTextField) e).getText().isEmpty()) {
					isFieldEmpty = true;
					((JFXTextField) e).getStyleClass().add("ErrorLine");
				} else
					((JFXTextField) e).getStyleClass().remove("ErrorLine");
			} else if (e instanceof JFXPasswordField) {
				if (((JFXPasswordField) e).getText().isEmpty()) {
					isFieldEmpty = true;
					((JFXPasswordField) e).getStyleClass().add("ErrorLine");
				} else
					((JFXPasswordField) e).getStyleClass().remove("ErrorLine");
			} else if (e instanceof JFXTextArea) {
				if (((JFXTextArea) e).getText().isEmpty()) {
					isFieldEmpty = true;
					((JFXTextArea) e).getStyleClass().add("ErrorLine");
				} else
					((JFXTextArea) e).getStyleClass().remove("ErrorLine");
			} else if (e instanceof JFXComboBox) {
				if (null == ((JFXComboBox) e).getValue()) {
					isFieldEmpty = true;
					((JFXComboBox) e).getStyleClass().add("ErrorLine");
				} else
					((JFXComboBox) e).getStyleClass().remove("ErrorLine");
			} else if (e instanceof JFXRadioButton) {
				if (!((JFXRadioButton) e).isSelected()) {
					isFieldEmpty = true;
					((JFXRadioButton) e).getStyleClass().add("ErrorLine");
				} else
					((JFXRadioButton) e).getStyleClass().remove("ErrorLine");
			}
		});
		return isFieldEmpty;
	}

	/**
	 * loads a given node onto the given anchor node can be created while
	 * initializing the class
	 * 
	 * @param anchor
	 * @param page
	 */
	public static void loadPage(AnchorPane anchor, Node page) {
		if (anchor == null)
			anchor = mainAnchorPane;
		anchor.getChildren().clear();
		anchor.getChildren().setAll(page);
	}

	/*
	 * Return the stack pane of the popup
	 */
	public static StackPane getPopupPane() {
		return sp;
	}

	/*
	 * Set the stack pane of the popup
	 */
	public static void setPopupPane(StackPane sp) {
		GeneralUIMethods.sp = sp;
	}

	public static void setSideBar(VBox sideBar) {
		GeneralUIMethods.sideBar = sideBar;
	}

	public static VBox getSideBar() {
		return sideBar;
	}

	/**
	 * compares the text in the text field to the string
	 * 
	 * @param textField
	 * @param string
	 */
	public static boolean validateCode(JFXTextField textField, String string) {
		if (textField.getText().equals(string))
			return true;
		return false;
	}

	/**
	 * closes the connection of the client to the server
	 */
	public static void closeConnection() {
		ClientController.accept("SIGN_OUT");
	}

	public static String israeliDate(LocalDate date) {
		String[] arr = date.toString().split("-");
		StringBuilder sb = new StringBuilder();
		sb.append(arr[2]);
		sb.append("/");
		sb.append(arr[1]);
		sb.append("/");
		sb.append(arr[0]);
		return sb.toString();
	}

	public interface Func<T, V> {
		boolean compare(T t, V v);
	}

	public static <T> void autoSelectComboBoxValue(JFXComboBox<T> comboBox, String value, Func<T, String> f) {
		for (T t : comboBox.getItems()) {
			if (f.compare(t, value)) {
				comboBox.setValue(t);
			}
		}
	}

	public static void buildTestForm(AnchorPane contentPaneAnchor, ScrollPane testScrollPane, String testCodeOrID,
			String testType, FXMLLoader testFormLoader) {
		testFormNode = null;
		int i = 0;
		if ((ClientController.getRoleFrame().equals("Student") && !testType.equals("STUDENT_LOOK"))
				|| testType.equals("TEACHER_VIEW_TEST_BY_CODE"))
			ClientController.accept("GET_TEST_BY_CODE-" + testCodeOrID);
		else
			ClientController.accept("GET_TEST_BY_ID-" + testCodeOrID);
		Test test = ClientController.getStudentTest();
		if (null == test)
			new PopUp(PopUp.TYPE.ERROR, "Error", "Your code is invalid", contentPaneAnchor, null, null);
		else {
			ClientController.accept("GET_QUESTIONS_FROM_TEST-" + test.getID());
			ArrayList<Question> testQuestions = ClientController.getQuestions();
			if (null != testQuestions)
				try {
					testFormNode = testFormLoader.load();
					TestFormController controller = testFormLoader.getController();
					controller.setTest(test);
					controller.setTestCode(testCodeOrID.toString());
					controller.getEditBtn().setVisible(false);
					controller.getBackBtn().setVisible(false);
					controller.getTestSideBarAnchor().setVisible(true);
					controller.setFlag(true);
					controller.setTestType(testType);
					if (testType.equals("Manual")) {
						controller.getDownloadBtn().setVisible(true);
						controller.getUploadBtn().setVisible(true);
						controller.getUploadFileAnchor().setVisible(true);
						controller.getFinishBtn().setVisible(false);
					} else
						controller.getUploadFileAnchor().setVisible(false);
					controller.addTitleAndInstructionsToTest(test.getTitle(), null, test.getStudentInstructions());
					i = 1;
					ArrayList<QuestionController> questionController = new ArrayList<QuestionController>();
					for (Question q : testQuestions) {
						// Adding questions to preview
						questionController.add(controller.addQuestionToTestForm(q, i, 100 / testQuestions.size())); 
						i++;
					}
					controller.getTotalQuestionsLbl().setText(String.valueOf(--i));

					// Set the correct view for the student
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							if (!testType.equals("Computed"))
								controller.getQuestionsToggleGroup().forEach(toggleGroup -> {
									ToggleGroup tGroup = (ToggleGroup) toggleGroup;
									tGroup.getToggles().forEach(toggle -> {
										Node node = (Node) toggle;
										node.setDisable(true);
									});
								});
							if (testType.equals("Manual"))
								controller.getQuestionAnchor().setVisible(false);
							if (testType.equals("Manual") || testType.equals("Computed")
									|| testType.equals("TEACHER_CHECKING")) {
								contentPaneAnchor.setTranslateX(-1 * (controller.getTestSideBarAnchor().getWidth()));
								GeneralUIMethods.loadPage((AnchorPane) contentPaneAnchor.getParent().getParent(),
										testFormNode);
							} else {
								controller.getTestSideBarAnchor().setVisible(false);
								testFormNode.prefWidthProperty().bind(testScrollPane.widthProperty().subtract(12));
								testFormNode.prefHeightProperty().bind(testScrollPane.heightProperty());
								controller.getScrollPane().prefHeightProperty().bind(testScrollPane.heightProperty());
								controller.getScrollPane().prefWidthProperty().bind(testScrollPane.widthProperty());
								controller.getScrollPane().setTranslateX(20);
								controller.getScrollPane().setTranslateY(45);
								GeneralUIMethods.loadPage(contentPaneAnchor, testFormNode);
							}
						}
					});
					ArrayList<Integer> wrongAnswers;
					// Get students answers and select them
					if (testType.equals("STUDENT_LOOK")) {
						wrongAnswers = controller.setStudentAnswers(test.getID(),
								ClientController.getActiveUser().getSSN());
						for (int j = 0; j < wrongAnswers.size(); j++)
							questionController.get(wrongAnswers.get(j)).getWrongAnswerImage().setVisible(true);
					} else if (testType.equals("TEACHER_CHECKING"))
						controller.setStudentAnswers(test.getID(), ClientController.getActiveUser().getSSN());
					else if (ClientController.getRoleFrame().equals("Teacher") && !testType.equals("TEACHER_CHECKING"))
						controller.setQuestionsFromTest(test.getID());
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		if (ClientController.getRoleFrame().equals("Teacher"))
			GeneralUIMethods.setPopupPane(ClientController.getTeacherDashboardUIController().getPopUpWindow());
		else if (ClientController.getRoleFrame().equals("Principle"))
			GeneralUIMethods.setPopupPane(ClientController.getPrincipleDashboardUIController().getPopUpWindow());
	}

	public static double resizeTxtArea(JFXTextArea textArea) {
		Text textBox = new Text(textArea.getText());
		textBox.setFont(textArea.getFont());
		StackPane pane = new StackPane(textBox);
		pane.layout();
		double paneHeight = pane.getHeight();
		double textBoxHeight = textBox.getLayoutBounds().getHeight();
		double paddingToBeAdded = 50;
		textArea.setMaxHeight(textBoxHeight + paddingToBeAdded);
		textArea.setText(textArea.getText());
		return paneHeight - textBoxHeight;
	}

}
