package teacherDashboard;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class CheckTestByCodeUIController {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private Label checkTestLbl;

    @FXML
    private JFXTreeTableView<?> testTbl;

    @FXML
    private Label serachLbl;

    @FXML
    private JFXTextArea searchTxt;

    @FXML
    private Label serachLbl1;

    @FXML
    private Label serachLbl2;

    @FXML
    private JFXTextArea testCodeTxt;

    @FXML
    private JFXButton submitBtn;
    
    private Node viewReports;
    /**
     * clicking submit goes to the screen view reports at principle dashboard to 2.2.1
     */    
    @FXML
    void clickSubmit(MouseEvent event) {
    	List<JFXButton> l = new ArrayList<JFXButton>();
		l.add(new JFXButton("Okay"));
		l.add(new JFXButton("View statistics"));
		try {
			viewReports = FXMLLoader.load(getClass().getResource(Navigator.VIEW_REPORTS.getVal()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		l.get(1).setOnAction(e ->GeneralUIMethods.loadPage(contentPaneAnchor, viewReports));
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupStackPane(), contentPaneAnchor, l, "Test realesed", "Students can view the tests");
	
    }

}
