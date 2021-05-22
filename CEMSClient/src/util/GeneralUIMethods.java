package util;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
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
		contentPaneAnchor.getChildren().clear();
		menuVBox.toFront();
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
	
	/**
	 * load sidebar with fadein effect
	 * 
	 * @param anchor
	 * @param page
	 */
	public static void loadSideBarWithEffect(VBox sideBar) {
//		SequentialTransition slideshow = new SequentialTransition();
//		for (Node component : sideBar.getChildren()) {
//		    SequentialTransition seq = new SequentialTransition();
//		    FadeTransition fade = new FadeTransition(Duration.millis(1000), component);
//		    fade.setFromValue(0);
//		    fade.setToValue(1);
//		    PauseTransition stop = new PauseTransition(Duration.millis(3000));
//		    seq.getChildren().addAll(fade, stop);
//		    slideshow.getChildren().add(seq);
//		}
//		slideshow.play();
		
//		
//		VBox node = new VBox();
//        Platform.runLater(new Runnable() {
//            @Override
//            public void run() {
//
//                for (Node component : sideBar.getChildren()) {
//                	component.setOpacity(0);
//                	node.getChildren().add(component);
//                }
//            }
//        });
//		VBox node = new VBox();
//		int i = 0;
//        for (Node component : sideBar.getChildren()) {
//        	component.setOpacity(0);
//        	node.getChildren().add(component);
//        }
//		FadeTransition ft = new FadeTransition(Duration.millis(200), node);
//		ft.setFromValue(0.0);
//		ft.setToValue(1.0);
//		ft.play();
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
}
