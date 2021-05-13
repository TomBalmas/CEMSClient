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
	TEST_BANK ("/teacherDashboard/bankUI.fxml"),
	VIEW_REPORTS ("ViewReports.fxml"),
	CREATE_REPORT ("CreateReport.fxml"),
	BLANK_QUESTION_FORM ("BlankQuestionFormUI.fxml"),
	ADDING_NEW_TEST ("AddingNewTestUI.fxml"),
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
