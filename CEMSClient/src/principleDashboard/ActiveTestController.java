package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
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

		addTeacherRequest("vered","tom");
		addTeacherRequest("vered","tom");
		addTeacherRequest("vered","tom");
		// for (int i = 0; i < 10; i++)
		// vBoxScrollPane.getChildren().add(new JFXButton(i + ""));

		// scrollPane.setContent(vBoxScrollPane);

	}

	public void addTeacherRequest(String teacherName, String requestMsg) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherRequest.fxml"));
			Node request = loader.load();
			TeacherTestController controller = loader.getController();
			controller.getTeacherName().setText(teacherName);
			controller.getTextArea().setText(requestMsg);
			
			vBoxScrollPane.getChildren().add(request);
			scrollPane.setContent(vBoxScrollPane);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}
