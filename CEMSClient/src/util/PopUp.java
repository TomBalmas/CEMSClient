package util;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PopUp {
	/*
	 * Class for generic popup
	 * @param StackPane - where the popup will be
	 * @param AnchorPane - blur
	 * @param VBox - where to prevent outside clicks
	 * @param List<JFXButton> - buttons inside the popup
	 * @param String - popup title
	 * @param String - popup body
	 */
	public static void showMaterialDialog(StackPane root, AnchorPane nodeToBeBlurred, VBox sideBar,
			List<JFXButton> controls, String header, String body) {
		if (controls.isEmpty()) // If no button was entered, add okay button
			controls.add(new JFXButton("Okay"));

		// Create the popup
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
		
		// Bring popup to the front!!! WOO-HOO!!!
		root.toFront();

		// Blur the background and prevent from user to access anywhere but the popup
		EventHandler<MouseEvent> handler = MouseEvent::consume;
		if (nodeToBeBlurred != null) {
			BoxBlur blur = new BoxBlur(3, 3, 3);
			nodeToBeBlurred.addEventFilter(MouseEvent.ANY, handler);
			nodeToBeBlurred.setEffect(blur);
		}
		sideBar.addEventFilter(MouseEvent.ANY, handler);

		// On button click, remove bluring and filters
		controls.forEach(controlButton -> {
			controlButton.getStyleClass().add("dialog-button");
			controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
				if (nodeToBeBlurred != null)
					nodeToBeBlurred.removeEventFilter(MouseEvent.ANY, handler);
				sideBar.removeEventFilter(MouseEvent.ANY, handler);
				dialog.close();
			});
		});

		// Set popup parameters and display it
		dialogLayout.setHeading(new Label(header));
		dialogLayout.setBody(new Label(body));
		dialogLayout.setActions(controls);
		dialog.show();
		dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
			if (nodeToBeBlurred != null)
				nodeToBeBlurred.setEffect(null);
			// Bring popup to the back!!! BOO-HOO!!!
			root.toBack();
		});
	}
}
