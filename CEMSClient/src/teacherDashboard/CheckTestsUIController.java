package teacherDashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import util.GeneralUIMethods;
import util.Navigator;

public class CheckTestsUIController {

    @FXML
    private AnchorPane contentPaneAnchor;

    @FXML
    private JFXButton submitBtn;

    @FXML
    private AnchorPane filterAnchor;

    @FXML
    private AnchorPane insideFilterAnchor;

    @FXML
    private Label checkTestLbl;

    @FXML
    private JFXTextField searchField;

    @FXML
    private JFXButton filterButton;

    @FXML
    private Label timeLeftLbl1;

    @FXML
    private AnchorPane tableViewAnchor;

    @FXML
    private JFXTreeTableView<?> testTbl;

	private Node viewReports;
	
    @FXML
    void filterBtn(MouseEvent event) {

    }

	/**
	 * clicking submit goes to the screen view reports at principle dash board to
	 * 2.2.1
	 */
	@FXML
	void submitClicked(MouseEvent event) {
		List<JFXButton> list = new ArrayList<JFXButton>();
		list.add(new JFXButton("Okay"));
		list.add(new JFXButton("View statistics"));
		try {
			viewReports = FXMLLoader.load(getClass().getResource(Navigator.VIEW_REPORTS.getVal()));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		list.get(1).setOnAction(e -> GeneralUIMethods.loadPage(contentPaneAnchor, viewReports));
		util.PopUp.showMaterialDialog(GeneralUIMethods.getPopupPane(), contentPaneAnchor, GeneralUIMethods.getSideBar(),
				list, "Test realesed", "Students can view the tests");

	}

}
