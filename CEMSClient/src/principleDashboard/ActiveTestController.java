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

	VBox vBoxScrollPane = new VBox();
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
		vBoxScrollPane.setPadding(new Insets(0,0,0,210));
		addTeacherRequest("vered","tom");
		addTeacherRequest("vered","tom");
		addTeacherRequest("vered","tom");
	

	}

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
