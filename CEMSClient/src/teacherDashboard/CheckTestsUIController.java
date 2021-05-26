package teacherDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import client.ClientController;
import common.TestToBeChecked;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private TableView<rowTableCheckTests> testTbl;

	@FXML
	private TableColumn<?, ?> testIDCol;

	@FXML
	private TableColumn<?, ?> dateCol;

	@FXML
	private TableColumn<?, ?> startingTime;

	@FXML
	private TableColumn<?, ?> testCodeCol;

	@FXML
	private TableColumn<?, ?> titleCol;

	@FXML
	private TableColumn<?, ?> courseCol;

	@FXML
	private TableColumn<?, ?> pointsPerQuestinCol;

	@FXML
	private TableColumn<?, ?> statusCol;

	private Node viewReports;

	@FXML
	void filterBtn(MouseEvent event) {

	}

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

	public class rowTableCheckTests {

		private String testID;
		private String date;
		private String startingTime;
		private String testCode;
		private String title;
		private String course;
		private String pointsPErQuestion;

		public rowTableCheckTests(TestToBeChecked checkTest) {

			this.testID = checkTest.getID();
			this.date = checkTest.getDate();
			this.startingTime = checkTest.getStartingTime();
			this.testCode = checkTest.getTestCode();
			this.title = checkTest.getTitle();
			this.course = checkTest.getCourse();
			this.pointsPErQuestion = checkTest.getPointsPErQuestion();

		}

	}

	public void initialize(URL arg0, ResourceBundle arg1) {

		ArrayList<TestToBeChecked> tests = null;
		ClientController.accept("CHECK_TESTS-");
		tests = ClientController.getTestToBeChecked();

		testIDCol.setCellValueFactory(new PropertyValueFactory<>("testID"));
		dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
		startingTime.setCellValueFactory(new PropertyValueFactory<>("startingTime"));
		testCodeCol.setCellValueFactory(new PropertyValueFactory<>("testCode"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		courseCol.setCellValueFactory(new PropertyValueFactory<>("course"));
		pointsPerQuestinCol.setCellValueFactory(new PropertyValueFactory<>("pointsPErQuestion"));

		
		for (TestToBeChecked test : tests) {
			rowTableCheckTests row = new rowTableCheckTests(test);
			testTbl.getItems().add(row);
		}

	}

}
