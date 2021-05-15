package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXScrollPane;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;



public class ActiveTestController implements Initializable {

	@FXML
	private ScrollPane scrollPane;

	@FXML
	private JFXScrollPane jfxScrollPane;

	// -------------------------------------------------------

	VBox vBoxScrollPane = new VBox(); //VBox for inserting teacher requests.
	
	/**
	 * 	In initialize we call the addTeacherRequest function to add extra time
	 *  requests from different teachers.
	 *	
	 */
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		vBoxScrollPane.setPadding(new Insets(0,0,0,210));
		addTeacherRequest("vered","tom");
		addTeacherRequest("vered","tom");
		addTeacherRequest("vered","tom");
	

	}

	/**
	 * 	The function gets the name of the teacher and his request.
	 *  Then it creates a request window that goes into the Vbox and then puts the updated Vbox into the scrollpane.
	 *  This way the request of the same teacher can appear in the window of the principal so that he can approve requests for extra time.
	 *
	 */	
	
	public void addTeacherRequest(String teacherName, String requestMsg) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherRequest.fxml"));
			Node request = loader.load();
			TeacherRequestController controller = loader.getController();
			controller.getTeacherName().setText(teacherName);
			controller.getTextArea().setText(requestMsg);		
			vBoxScrollPane.getChildren().add(request);	
			scrollPane.setContent(vBoxScrollPane);
		
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
