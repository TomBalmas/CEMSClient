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
import teacherDashboard.LockTestController;
import teacherDashboard.QuestionFormUIController;
import teacherDashboard.SetTestDateController;

public class PopUp {
	public enum TYPE {
		ALERT, SUCCESS, INFORM, ERROR, SCHEDULE, LOGIN;
	}

	// Initialize
	StackPane root = GeneralUIMethods.getPopupPane();
	VBox sideBar = GeneralUIMethods.getSideBar();
	AnchorPane fxmlPopUp = null;
	TYPE type;
	String header;
	String body;
	AnchorPane nodeToBeBlurred;
	List<JFXButton> btnsList;
	FXMLLoader loader;
	JFXDialog dialog;
	JFXDialogLayout dialogLayout = new JFXDialogLayout();
	EventHandler<MouseEvent> handler = MouseEvent::consume;

	public PopUp(TYPE type, String header, String body, AnchorPane nodeToBeBlurred, List<JFXButton> btnsList,
			FXMLLoader loader) {
		this.type = type;
		this.header = header;
		this.body = body;
		this.nodeToBeBlurred = nodeToBeBlurred;
		this.btnsList = btnsList;
		this.loader = loader;
		setPopUp();
	}

	public void setPopUp() {
		setButtons();
		setFXMLLoader(loader);
		setBlur();
		dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);
		dialogLayout.setActions(btnsList);
		showPopUp();
		dialog.setOnDialogClosed((JFXDialogEvent event) -> {
			if (nodeToBeBlurred != null)
				nodeToBeBlurred.setEffect(null);
		});

	}

	public void showPopUp() {
		dialog.show();
		root.toFront();
	}

	public void hidePopUp() {
		dialog.close();
		root.toBack();
	}

	public void setBlur() {
		// Blur the background and prevent from user to access anywhere but the popup
		if (nodeToBeBlurred != null) {
			BoxBlur blur = new BoxBlur(3, 3, 3);
			nodeToBeBlurred.addEventFilter(MouseEvent.ANY, handler);
			nodeToBeBlurred.setEffect(blur);
		}
	}

	public void setButtons() {
		if (null == btnsList)
			btnsList = new ArrayList<JFXButton>();
		if (btnsList.isEmpty()) // If no button was entered, add okay button
			btnsList.add(new JFXButton("Okay"));
		// On button click, remove bluring and filters
		btnsList.forEach(controlButton -> {
			controlButton.getStyleClass().add("dialog-button");
			controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
				if (nodeToBeBlurred != null)
					nodeToBeBlurred.removeEventFilter(MouseEvent.ANY, handler);
				sideBar.removeEventFilter(MouseEvent.ANY, handler);
				dialog.close();
				if (type == TYPE.LOGIN)
					root.toBack();
			});
		});
	}

	public void setFXMLLoader(FXMLLoader loader) {
		// Load fxml popup
		if (null != loader) {
			System.out.println(loader);
			try {
				if (header.equals("ScheduleTest") || header.equals("RescheduleTest")) {
					fxmlPopUp = loader.load();
					fxmlPopUp.toFront();
					dialogLayout.setBody(((SetTestDateController) loader.getController()).getContentPaneAnchor());
				} else if (header.equals("LOCK_TEST")) {
					fxmlPopUp = loader.load();
					fxmlPopUp.toFront();
					dialogLayout.setBody(((LockTestController) loader.getController()).getContentPaneAnchor());
				} else if (header.equals("VIEW_QUESTION"))
					dialogLayout.setBody(((QuestionFormUIController) loader.getController()).getContentPaneAnchor());
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			dialogLayout.setBody(new Label(body));
			dialogLayout.setHeading(new Label(header));
		}
	}
}