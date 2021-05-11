package util;

public enum Navigator {
	LOGIN ("/loginScreen/LoginUI.fxml"),
	PRINCIPLE_DASHBOARD("/principleDashboard/PrincipleDashboardUI.fxml"),
	TEACHER_DASHBOARD ("/teacherDashboard/TeacherDashboardUI.fxml"),
	STUDENT_DASHBOARD ("/studentDashboard/StudentDashboardUI.fxml"),
	VIEW_REPORTS ("ViewReports.fxml"),
	CREATE_REPORT ("CreateReport.fxml"),
	BLANK_QUESTION_FORM ("BlankQuestionFormUI.fxml"),
	QUESTION_BANK ("QuestionBankUI.fxml"),
	TEST_BANK ("bankUI.fxml"),
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
