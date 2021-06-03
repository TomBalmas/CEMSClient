package util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.util.Duration;
import teacherDashboard.TestFormController;

public class GeneralUIMethods {

	private static int menuMovementLeftToRight = 1280 - 283 + 1;
	public static StackPane sp;
	public static VBox sideBar;

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
		button.setStyle("-fx-background-color:#00ADB5;");
	}

	/**
	 * loads a given node onto the given anchor node can be created while
	 * initializing the class
	 * 
	 * @param anchor
	 * @param page
	 */
	public static void loadPage(AnchorPane anchor, Node page) {
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
		sp.toBack();
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
		if(textField.getText().equals(string))
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

	public static void buildTestForm(AnchorPane contentPaneAnchor, ScrollPane testScrollPane, String testCodeOrID, String testType,
			FXMLLoader testFormLoader) {
		if (ClientController.getRoleFrame().equals("Student") || testType.equals("TEACHER_VIEW_TEST_BY_CODE"))
			ClientController.accept("GET_TEST_BY_CODE-" + testCodeOrID);
		else 
			ClientController.accept("GET_TEST_BY_ID-" + testCodeOrID);
		Test t = ClientController.getStudentTest();
		if (null == t)
			PopUp.showMaterialDialog(PopUp.TYPE.ERROR, "Error", "Your code is invalid", contentPaneAnchor, null, null);
		else {
			ClientController.accept("GET_QUESTIONS_FROM_TEST-" + t.getID());
			ArrayList<Question> testQuestions = ClientController.getQuestions();
			if (null != testQuestions)
			try {
				Region test = testFormLoader.load();
				TestFormController controller = testFormLoader.getController();
				controller.setTest(t);
				controller.setTestCode(testCodeOrID.toString());
				controller.getEditBtn().setVisible(false);
				controller.getBackBtn().setVisible(false);
				controller.getTestSideBarAnchor().setVisible(true);
				controller.setFlag(true);
				if (testType.equals("Manual")) {
					controller.getDownloadBtn().setVisible(true);
					controller.getUploadBtn().setVisible(true);
					controller.getUploadFileAnchor().setVisible(true);
					controller.getFinishBtn().setVisible(false);
				} else
					controller.getUploadFileAnchor().setVisible(false);
				controller.addTitleAndInstructionsToTest(t.getTitle(), null, t.getStudentInstructions());
				int i = 1;
				for (Question q : testQuestions) {
					controller.addQuestionToTestForm(q, i, 100 / testQuestions.size()); // adding questions to preview
					i++;
				}
				controller.getTotalQuestionsLbl().setText(String.valueOf(--i));

				// Set the correct view for the student
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						if(testType.equals("Manual") || testType.equals("Computed")) {
							contentPaneAnchor.setTranslateX(-1 * (controller.getTestSideBarAnchor().getWidth()));
							GeneralUIMethods.loadPage((AnchorPane) contentPaneAnchor.getParent().getParent(), test);
							if(testType.equals("Manual"))
								controller.getQuestionsToggleGroup().forEach(toggleGroup -> {
									ToggleGroup tGroup = (ToggleGroup) toggleGroup;
									tGroup.getToggles().forEach(toggle -> {
										Node node = (Node) toggle;
										node.setDisable(true);
									});
								});
						}
						else {
							controller.getTestSideBarAnchor().setVisible(false);
							test.prefWidthProperty().bind(testScrollPane.widthProperty().subtract(12));
							test.prefHeightProperty().bind(testScrollPane.heightProperty());
							controller.getScrollPane().prefHeightProperty().bind(testScrollPane.heightProperty());
							controller.getScrollPane().prefWidthProperty().bind(testScrollPane.widthProperty());
							controller.getScrollPane().setTranslateX(20);
							controller.getScrollPane().setTranslateY(45);
							GeneralUIMethods.loadPage(contentPaneAnchor, test);
						}
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
