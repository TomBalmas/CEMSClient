package util;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.util.Duration;

public class GeneralUIMethods {

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
}