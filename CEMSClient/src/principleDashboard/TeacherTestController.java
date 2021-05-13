package principleDashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TeacherTestController {

	
	    @FXML
	    private AnchorPane requestPane;

	    @FXML
	    private JFXTextArea textArea;
	    
	    @FXML
	    private Label teacherName;;

	    @FXML
	    private JFXButton btnDisapprove;

	    @FXML
	    private JFXButton btnAprove;
	
	
	public JFXButton getDisapprove() {return btnDisapprove;}
		
	public JFXButton getAprove() {return btnAprove;}
	
	public Label getTeacherName() {return teacherName;}
	
	public JFXTextArea getTextArea() {return textArea;}
	
}
