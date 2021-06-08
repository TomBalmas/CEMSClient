package principleDashboard;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;

import client.ClientController;
import common.Course;
import common.Question;
import common.Report;
import common.Student;
import common.Teacher;
import common.User;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import teacherDashboard.QuestionBankUIController.questionRow;
import teacherDashboard.ViewActiveTestsController.rowTableActiveTest;
import util.GeneralUIMethods;
import util.Navigator;
import util.PopUp;

public class CreateReportController implements Initializable {

	@FXML
	private AnchorPane contentPaneAnchor;

	@FXML
	private AnchorPane filterAnchor;

	@FXML
	private AnchorPane insideFilterAnchor;

	@FXML
	private JFXComboBox<?> selectTypeCbox;

	@FXML
	private JFXTextField searchField;

	@FXML
	private Label startDPlbl;

	@FXML
	private JFXDatePicker startCoursesDP;

	@FXML
	private Label endDPlbl;

	@FXML
	private JFXDatePicker finishCoursesDP;

	@FXML
	private JFXButton filterButton;

	@FXML
	private Label selectCourseLbl;

	@FXML
	private AnchorPane tableViewAnchor;

	@FXML
	private TableView<Row> reportsTbl;

	@FXML
	private TableColumn<?, ?> nameCol;

	@FXML
	private TableColumn<?, ?> IDCol;
	ArrayList<Student> students = null;
	ArrayList<Teacher> teachers = null;
	ArrayList<Course> courses = null;

	@FXML
	private JFXListView<?> listView;
	@FXML
	private JFXButton createReportBtn;
	ObservableList coursesSelection = FXCollections.observableArrayList();
	private ObservableList options = FXCollections.observableArrayList("Student", "Teacher", "Courses");
	private ReportFormController reportFormController;
	private Node ReportForm;
	String median;
	String average;

	public class Row {
		private String id;
		private String author;

		public Row(User user) {
			id = user.getSSN();
			author = user.getName();
		}

		public Row(Course course) {
			id = course.getId();
			author = course.getName();
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

	}

	/**
	 * init function that putting values in the combo box, that function also
	 * changing view by the combo box value.
	 *
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		selectTypeCbox.setItems(options);
		listView.setPrefWidth(100);
		listView.setPrefHeight(70);
		listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		// setting up table columns
		PropertyValueFactory IDfactory = new PropertyValueFactory<>("id");
		PropertyValueFactory namefactory = new PropertyValueFactory<>("author");
		IDCol.setCellValueFactory(IDfactory);
		nameCol.setCellValueFactory(namefactory);

		tableViewAnchor.setMouseTransparent(false);
		EventHandler<ActionEvent> btnEventHandler = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
					Node createReport = null;
					createReport = FXMLLoader.load(getClass().getResource(Navigator.REPORT_CHART.getVal()));
					FXMLLoader loader = new FXMLLoader(getClass().getResource(Navigator.REPORT_FORM.getVal()));
					Report report = ClientController.getReport();
					Row selected = reportsTbl.getSelectionModel().getSelectedItem();
					ReportForm = loader.load();
					reportFormController = loader.getController();
					
					if (selectTypeCbox.getValue().equals("Student")) {
						//listView.getItems().clear();
						ObservableList selectedIndices = listView.getSelectionModel().getSelectedIndices();
						ObservableList courses = listView.getSelectionModel().getSelectedItems();
						if (!listView.getSelectionModel().isEmpty()) {
							StringBuilder sb = new StringBuilder();
							for (int j = 0; j < courses.size(); j++) {
								sb.append(courses.get(j));
								if (j != courses.size() - 1)
									sb.append("~");
							}

							ClientController.accept("CREATE_STUDENT_REPORT-" + selected.getId() + "," + sb.toString());
							report = ClientController.getReport();
			
							//if report is not empty
							if (report.isFlag()) {
								median = String.valueOf(report.getMedian());
								average = String.valueOf(report.getAverage());
								GeneralUIMethods.loadPage(contentPaneAnchor, createReport);
								reportFormController.getAverageTxt().setText(median);
								reportFormController.getMedianTxt().setText(average);
								reportFormController.setUserNameLbl("Student: " + selected.getId());
								Series<String, Number> set = new XYChart.Series<String, Number>();
								ArrayList<Pair<String, Integer>> list = report.getTestsAndGrades();
								set.getData().clear();
								if (!list.isEmpty()) {
									for (int i = 0; i < list.size(); i++) {
										set = new XYChart.Series<String, Number>();
										ClientController.accept("GET_COURSE_BY_TEST_ID-" + list.get(i).getKey());
										reportFormController.getxAxisExam().setTickLabelRotation(20);
										set.getData()
												.add(new XYChart.Data<String, Number>(
														list.get(i).getKey() + "  "
																+ ClientController.getCourse().getName(),
														list.get(i).getValue()));
										reportFormController.getHistograma().getData().addAll(set);
									}
									reportFormController.setSet(set);
									//reportFormController.getHistograma().getData().addAll(set);
									GeneralUIMethods.loadPage(contentPaneAnchor, ReportForm);
								}
							}
						}
						if (listView.getSelectionModel().isEmpty() || !report.isFlag()) {
							PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Report doesnt exist " + " ",
									contentPaneAnchor, null, null);
						}
					} // teachers report
					if (selectTypeCbox.getValue().equals("Teacher")) {
						listView.getItems().clear();
						reportFormController.setUserNameLbl("Teacher");
						ClientController.accept("CREATE_TEACHER_REPORT-" + selected.getId());
						Report teachersReport = ClientController.getReport();
						if (teachersReport.isFlag()) {
							String Tmedian = String.valueOf(teachersReport.getMedian());
							String Taverage = String.valueOf(teachersReport.getAverage());
							reportFormController.getAverageTxt().setText(Taverage);
							reportFormController.getMedianTxt().setText(Tmedian);

							ArrayList<Pair<String, Pair<Double, Double>>> testsAveragesMedians = teachersReport
									.getTestsAveragesMedians();

							for (int i = 0; i < testsAveragesMedians.size(); i++) {
								Series<String, Number> DataSet = new XYChart.Series<String, Number>();
								DataSet.getData().clear();
								DataSet.getData()
										.add(new XYChart.Data<String, Number>(
												testsAveragesMedians.get(i).getKey() + " median",
												testsAveragesMedians.get(i).getValue().getValue()));
								DataSet.getData()
										.add(new XYChart.Data<String, Number>(
												testsAveragesMedians.get(i).getKey() + " average",
												testsAveragesMedians.get(i).getValue().getKey()));
								reportFormController.getHistograma().getData().addAll(DataSet);
								reportFormController.setSet(DataSet);
							}
							reportFormController.getxAxisExam().setLabel("Test ID");
							reportFormController.getyAxisGrades().setLabel("Value");
							GeneralUIMethods.loadPage(contentPaneAnchor, ReportForm);
						} else {
							PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Cant creatr report. " + "Relevant tests doesnt exist ",
									contentPaneAnchor, null, null);
						}
					}
					// create course report
					if (selectTypeCbox.getValue().equals("Courses")) {
						reportFormController.setUserNameLbl("Course");
						ClientController.accept("CREATE_COURSE_REPORT-" + selected.getId());
						Report courseReport = ClientController.getReport();
						report = ClientController.getReport();
						String cMedian = String.valueOf(courseReport.getMedian());
						String cAverage = String.valueOf(courseReport.getAverage());
						reportFormController.getAverageTxt().setText(cAverage);
						reportFormController.getMedianTxt().setText(cMedian);
						ArrayList<Pair<String, Pair<Double, Double>>> tests = courseReport.getTestsAveragesMedians();

						if (courseReport.isFlag()) {
							for (int i = 0; i < tests.size(); i++) {
								Series<String, Number> Data = new XYChart.Series<String, Number>();
								Data.getData().clear();
								Data.getData().add(new XYChart.Data<String, Number>(tests.get(i).getKey() + " median",
										tests.get(i).getValue().getValue()));
								Data.getData().add(new XYChart.Data<String, Number>(tests.get(i).getKey() + " average",
										tests.get(i).getValue().getKey()));
								reportFormController.getHistograma().getData().addAll(Data);
								reportFormController.setSet(Data);
							}
							ClientController.accept("GET_COURSE_BY_TEST_ID-" + tests.get(0).getKey());
							reportFormController.setUserNameLbl("Course: " + ClientController.getCourse().getName());
							reportFormController.getxAxisExam().setLabel("Test ID");
							reportFormController.getyAxisGrades().setLabel("Value");
							GeneralUIMethods.loadPage(contentPaneAnchor, ReportForm);

						} else {
							PopUp.showMaterialDialog(PopUp.TYPE.INFORM, "Information", "Cant create report.Relevant tests doesnt exist" + " ",
									contentPaneAnchor, null, null);
						}
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}

				// GeneralUIMethods.loadPage(contentPaneAnchor, ReportForm);

			}

		};

		createReportBtn.setOnAction(e -> {
			btnEventHandler.handle(e);
			{

			}
			;
		});

		// handke clicking on a student row
		reportsTbl.setOnMouseClicked((MouseEvent event) -> {
			listView.getItems().clear();
			Row selected = reportsTbl.getSelectionModel().getSelectedItem();
			if (selectTypeCbox.getValue().equals("Student")) {
				ClientController.accept("GET_COURSES_BY_STUDENT-" + selected.getId());
				courses = ClientController.getCourses();
				coursesSelection.clear();
			
				if (courses!=null) {
					listView.getItems().clear();
					for (int i = 0; i < courses.size(); i++) {
						coursesSelection.add(courses.get(i).getName());
					}

					listView.getItems().addAll(coursesSelection);
				}

				else {
					
					coursesSelection.clear();
					
					listView.getItems().clear();
					listView.getItems().removeAll(listView.getItems());
				}
				coursesSelection.clear();
				ClientController.setCourses(null);

			}

		});

		selectTypeCbox.setOnAction((event) -> {
			listView.getItems().clear();
			Object selectedItem = selectTypeCbox.getSelectionModel().getSelectedItem();
			if (selectTypeCbox.getValue().equals("Student")) {

				startCoursesDP.setVisible(false);
				finishCoursesDP.setVisible(false);
				startDPlbl.setVisible(false);
				endDPlbl.setVisible(false);
				selectCourseLbl.setVisible(true);
				searchField.setPromptText("Search by student name/last name");
				ClientController.accept("GET_STUDENTS-");
				students = ClientController.getStudents();
				reportsTbl.getItems().clear();
				if (students != null) {
					for (int i = 0; i < students.size(); i++) {
						Row usersRow = new Row(students.get(i));
						tableViewAnchor.setMouseTransparent(false);
						reportsTbl.getItems().add(usersRow);

					}
				}

			} else if (selectTypeCbox.getValue().equals("Courses")) {
				startCoursesDP.setVisible(true);
				finishCoursesDP.setVisible(true);
				startDPlbl.setVisible(true);
				endDPlbl.setVisible(true);
				selectCourseLbl.setVisible(false);
				searchField.setPromptText("Search by field/course name or code");
				ClientController.accept("GET_COURSES-");
				courses = ClientController.getCourses();
				reportsTbl.getItems().clear();
				if (courses != null) {
					for (int i = 0; i < courses.size(); i++) {
						Row courseRow = new Row(courses.get(i));
						tableViewAnchor.setMouseTransparent(false);
						reportsTbl.getItems().add(courseRow);
					}
				}
				// teacher
			} else {
				startCoursesDP.setVisible(false);
				finishCoursesDP.setVisible(false);
				startDPlbl.setVisible(false);
				endDPlbl.setVisible(false);
				selectCourseLbl.setVisible(false);
				searchField.setPromptText("Search by teacher name/last name");
				ClientController.accept("GET_TEACHERS-");
				reportsTbl.getItems().clear();
				teachers = ClientController.getTeachers();
				if (teachers != null) {
					for (int i = 0; i < teachers.size(); i++) {
						Row usersRow = new Row(teachers.get(i));
						tableViewAnchor.setMouseTransparent(false);
						reportsTbl.getItems().add(usersRow);
					}
				}
			}

		});

	}

	@FXML
	void createReportBtn(MouseEvent event) throws IOException {

	}

	@FXML
	void filterBtn(MouseEvent event) {

	}

}
