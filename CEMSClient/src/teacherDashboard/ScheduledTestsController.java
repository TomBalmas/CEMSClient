package teacherDashboard;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientController;
import common.Teacher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ScheduledTestsController implements Initializable{

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private TableView<?> scheduledTestsTbl;

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> authorCol;

    @FXML
    private TableColumn<?, ?> dateCol;

    @FXML
    private TableColumn<?, ?> startingTimeCol;

    @FXML
    private TableColumn<?, ?> durationCol;

    @FXML
    private TableColumn<?, ?> viewCol;

    @FXML
    private TableColumn<?, ?> rescheduleCol;

    @FXML
    private Label scheduledTestsLbl;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Teacher teacher = (Teacher)ClientController.getActiveUser();
		
	}

}
