package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;

import client.ClientController;
import common.TimeExtensionRequest;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ActiveTestController implements Initializable, Observer {

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private JFXScrollPane jfxScrollPane;

	@FXML
	private JFXButton refreshBtn;

	private ArrayList<TimeExtensionRequest> timeExtensionRequests;
	// -------------------------------------------------------

	VBox vBoxScrollPane = new VBox(); // VBox for inserting teacher requests.

	/**
	 * In initialize we call the addTeacherRequest function to add extra time
	 * requests from different teachers.
	 * 
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientController.setActiveTestController(this);
		ClientController.accept("GET_TIME_EXTENSION_REQUESTS");
		timeExtensionRequests = ClientController.getTimeExtensionRequests();
		if (timeExtensionRequests != null)
			for (TimeExtensionRequest request : timeExtensionRequests) {
				ClientController.accept("GET_NAME_BY_ID-" + request.getSsn());
				addTeacherRequest(request.getSsn(), request.getContent(), ClientController.getAuthorName(),
						request.getTestCode(), request.getMinutes());
			}
		refreshBtn.setOnMouseClicked(e -> {
			VBox vbox = (VBox) scrollPane.getContent();
			if (vbox.getChildren() != null)
				vbox.getChildren().clear();
			initialize(location, resources);
			refreshBtn.setDisable(true);
			ClientController.setPrincipleNotified(false);

		});

	}

	/**
	 * The function gets the name of the teacher and his request. Then it creates a
	 * request window that goes into the Vbox and then puts the updated Vbox into
	 * the scrollpane. This way the request of the same teacher can appear in the
	 * window of the principal so that he can approve requests for extra time.
	 *
	 */

	public void addTeacherRequest(String teacherSSN, String requestMsg, String teacherName, String testCode,
			int minutes) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherRequest.fxml"));
			Node request = loader.load();
			TeacherRequestController controller = loader.getController();
			controller.getTeacherName().setText("Name: " + teacherName + " SSN: " + teacherSSN + " Code: " + testCode);
			controller.getTextArea().setText(requestMsg);
			controller.getTextArea().appendText("\nMinutes to add: " + minutes);
			vBoxScrollPane.getChildren().add(request);
			scrollPane.setContent(vBoxScrollPane);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		refreshBtn.setDisable(false);
	}

}
