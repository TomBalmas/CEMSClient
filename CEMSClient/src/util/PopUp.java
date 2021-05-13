package util;

import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class PopUp {
	public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header, String body) {
	    BoxBlur blur = new BoxBlur(3, 3, 3);
	    if (controls.isEmpty()) {
	        controls.add(new JFXButton("Okay"));
	    }
	    JFXDialogLayout dialogLayout = new JFXDialogLayout();
	    JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
	    EventHandler<MouseEvent> handler = MouseEvent::consume;
	    nodeToBeBlurred.addEventFilter(MouseEvent.ANY, handler);
	    controls.forEach(controlButton -> {
	        controlButton.getStyleClass().add("dialog-button");
	        controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
	        	nodeToBeBlurred.removeEventFilter(MouseEvent.ANY, handler);
	            dialog.close();
	        });
	    });

	    dialogLayout.setHeading(new Label(header));
	    dialogLayout.setBody(new Label(body));
	    dialogLayout.setActions(controls);
	    dialog.show();
	    dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
	        nodeToBeBlurred.setEffect(null);
	    });
	    nodeToBeBlurred.setEffect(blur);
	}
}
