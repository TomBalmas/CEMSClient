package util;

/**
 * this enum helps us get the right fxml path for each page of the application
 *
 */
public enum Navigator {
	LOGIN ("/loginScreen/LoginUI.fxml"),
	
	PRINCIPLE_DASHBOARD("/principleDashboard/PrincipleDashboardUI.fxml"),
	ACTIVE_TESTS("/principleDashboard/ActiveTestsPage.fxml"),
	CREATE_REPORT ("/principleDashboard/CreateReport.fxml"),
	REPORT_CHART ("/principleDashboard/ReportForm.fxml"),
	VIEW_REPORTS ("/principleDashboard/ViewReports.fxml"),
	QUESTION_FORM ("/teacherDashboard/QuestionFormUI.fxml"),
	TEACHER_DASHBOARD ("/teacherDashboard/TeacherDashboardUI.fxml"),
	ADDING_NEW_TEST ("/teacherDashboard/AddingNewTestUI.fxml"),
	CHECK_TESTS ("/teacherDashboard/CheckTestsUI.fxml"),
	QUESTION ("/teacherDashboard/Question.fxml"),
	QUESTION_BANK ("/teacherDashboard/QuestionBankUI.fxml"),
	SCHEDULED_TESTS ("/teacherDashboard/ScheduledTests.fxml"),
	SET_TEST_DATE ("/teacherDashboard/SetTestDate.fxml"),
	TEST_BANK ("/teacherDashboard/TestBankUI.fxml"),
	TEST_FORM("/teacherDashboard/TestForm.fxml"),
	TITLE_AND_INSTRUCTIONS ("/teacherDashboard/TitleAndInstructions.fxml"),
	VIEW_ACTIVE_TESTS ("/teacherDashboard/ViewActiveTests.fxml"),
	
	STUDENT_DASHBOARD ("/studentDashboard/StudentDashboardUI.fxml"),
	GRADES ("StudentGrades.fxml"),
	STUDENT_TAKE_TEST ("StudentTakeTest.fxml"),
	STUDENT_TEST("/studentDashboard/ActiveTest.fxml"),
	REPORT_FORM ("/principleDashboard/ReportForm.fxml");
	
	
  
	private final String val;

	Navigator(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
	
	public String toString() {
		return this.name();
	}
}
