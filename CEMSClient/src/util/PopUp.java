package util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import teacherDashboard.SetTestDateController;

public class PopUp {

	public enum TYPE {
		ALERT, SUCCESS, INFORM, ERROR;
	}

	/*
	 * Class for generic popup
	 */
	public static boolean showMaterialDialog(TYPE type, String header, String body, AnchorPane nodeToBeBlurred,
			List<JFXButton> btnsList, FXMLLoader loader) {

		// Initialize
		StackPane root = GeneralUIMethods.getPopupPane();
		VBox sideBar = GeneralUIMethods.getSideBar();
		boolean returnValue = false;
		AnchorPane fxmlPopUp = null;
		JFXDialogLayout dialogLayout = new JFXDialogLayout();

		// Bring popup to the front!!! WOO-HOO!!!
		root.toFront();

		if(null == btnsList)
			btnsList = new ArrayList<JFXButton>();
		if (btnsList.isEmpty()) // If no button was entered, add okay button
			btnsList.add(new JFXButton("Okay"));

		switch (type) {
		case ALERT:
//			JFXButton noBtn = new JFXButton("No");
//			btnsList.add(noBtn);
			break;
		case ERROR:
			break;
		case SUCCESS:
			break;
		default:
			break;
		}
		
		//Load fxml popup
		if(null != loader) {
			try {
				fxmlPopUp = loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (header.equals("ScheduleTest"))
				dialogLayout.setBody(((SetTestDateController) loader.getController()).getContentPaneAnchor());	
			fxmlPopUp.toFront();
		}
		else {
			dialogLayout.setBody(new Label(body));
			dialogLayout.setHeading(new Label(header));
		}
		
		// Create the popup
		JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

		// Blur the background and prevent from user to access anywhere but the popup
		EventHandler<MouseEvent> handler = MouseEvent::consume;
		if (nodeToBeBlurred != null) {
			BoxBlur blur = new BoxBlur(3, 3, 3);
			nodeToBeBlurred.addEventFilter(MouseEvent.ANY, handler);
			nodeToBeBlurred.setEffect(blur);
		}
		sideBar.addEventFilter(MouseEvent.ANY, handler);

		// On button click, remove bluring and filters
		btnsList.forEach(controlButton -> {
			controlButton.getStyleClass().add("dialog-button");
			controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
				if (nodeToBeBlurred != null)
					nodeToBeBlurred.removeEventFilter(MouseEvent.ANY, handler);
				sideBar.removeEventFilter(MouseEvent.ANY, handler);
				dialog.close();
			});
		});

		// Set popup parameters and display it
		dialogLayout.setActions(btnsList);
		dialog.show();
		dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
			if (nodeToBeBlurred != null)
				nodeToBeBlurred.setEffect(null);
			// Bring popup to the back!!! BOO-HOO!!!
			if (type != TYPE.ALERT)
				root.toBack();
		});
		return false;
	}
}
