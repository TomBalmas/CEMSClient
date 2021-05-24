package util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.Test;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
	
	
	
	
	//public static void buildTestForm(Test test) {
		
	//}
	
	
	
	
	
}
