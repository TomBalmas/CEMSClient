package studentDashboard;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXToggleButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class StudentDashboardUIController {

    @FXML
    private VBox menuVBox;

    @FXML
    private JFXToggleButton darkModeToggleBtn;

    @FXML
    private ImageView logoImg;

    @FXML
    private Label studentDashboardLbl;

    @FXML
    private JFXButton gradesBtn;

    @FXML
    private JFXButton takeTestBtn;

    @FXML
    private JFXButton signOutBtn;

    @FXML
    private AnchorPane contentPaneAnchr;

    @FXML
    void gradesPage(MouseEvent event) {
    	loadPage("studentGrades");
    }

    @FXML
    void takeTestPage(MouseEvent event) {
    	loadPage("studentTakeTest");
    }
    
    public void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource((page + ".fxml")));
        try {
            AnchorPane loadAnchorPane = (AnchorPane) fxmlLoader.load();
        	contentPaneAnchr.getChildren().clear();
        	contentPaneAnchr.getChildren().add(loadAnchorPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
