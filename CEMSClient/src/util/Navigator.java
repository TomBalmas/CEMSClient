package util;

/**
 * this enum helps us get the right fxml path for each page of the application
 *
 */
public enum Navigator {
	LOGIN ("/loginScreen/LoginUI.fxml"),
	PRINCIPLE_DASHBOARD("/principleDashboard/PrincipleDashboardUI.fxml"),
	TEACHER_DASHBOARD ("/teacherDashboard/TeacherDashboardUI.fxml"),
	STUDENT_DASHBOARD ("/studentDashboard/StudentDashboardUI.fxml"),
	QUESTION_BANK ("/teacherDashboard/QuestionBankUI.fxml"),
	CHECK_TESTS ("/teacherDashboard/CheckTestsUI.fxml"),
	TEST_BANK ("/teacherDashboard/TestBankUI.fxml"),
	ADDING_NEW_TEST ("/teacherDashboard/AddingNewTestUI.fxml"),
	VIEW_ACTIVE_TESTS ("/teacherDashboard/ViewActiveTests.fxml"),
	ACTIVE_TESTS("/principleDashboard/ActiveTestsPage.fxml"),
	STUDENT_TEST("/studentDashboard/ActiveTest.fxml"),
	BLANK_QUESTION_FORM ("/teacherDashboard/BlankQuestionFormUI.fxml"),
	QUESTION ("/teacherDashboard/Question.fxml"),
	TITLE_AND_INSTRUCTIONS ("/teacherDashboard/TitleAndInstructions.fxml"),
	TEST_FORM("/teacherDashboard/TestForm.fxml"),
	REQUEST_TIME_EXTENSION ("/teacherDashboard/RequestTimeExtensionUI.fxml"),
	SCHEDULED_TESTS ("/teacherDashboard/ScheduledTests.fxml"),
	VIEW_REPORTS ("/principleDashboard/ViewReports.fxml"),
	CREATE_REPORT ("/principleDashboard/CreateReport.fxml"),
	REPORT_CHART ("/principleDashboard/ReportForm.fxml"),
	STUDENT_TAKE_TEST ("StudentTakeTest.fxml"),
	GRADES ("StudentGrades.fxml");

  
	private final String val;

	Navigator(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
