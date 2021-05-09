package util;

public enum Navigator {
	PRINCIPLE_DASHBOARD("/principleDashboard/PrincipleDashboardUI.fxml"),
	VIEW_REPORTS ("/principleDashboard/ViewReports.fxml"),
	CREATE_REPORT ("/principleDashboard/CreateReport.fxml"),
	LOGIN ("LoginUI.fxml"),
	TEACHER_DASHBOARD ("/teacherDashboard/TeacherDashboardUI.fxml"),
	BLANK_QUESTION_FORM ("/teacherDashboard/BlankQuestionFormUI.fxml"),
	QUESTION_BANK ("/teacherDashboard/QuestionBankUI.fxml"),
	TEST_BANK ("/teacherDashboard/bankUI.fxml"),
	ADDING_NEW_TEST ("/teacherDashboard/AddingNewTestUI.fxml"),
	PRINCIPLE_DASHBOARD("/principleDashboard/PrincipleDashboardUI.fxml"),
	STUDENT_DASHBOARD ("/studentDashboard/StudentDashboardUI.fxml");
	
	private final String val;

	Navigator(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
