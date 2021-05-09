package util;

public enum Navigator {
	LOGIN ("LoginUI.fxml"),
	TEACHERDASHBOARD ("/teacherDashboard/TeacherDashboardUI.fxml"),
	BLANKQUESTIONFORM ("/teacherDashboard/BlankQuestionFormUI.fxml"),
	QUESTIONBANK ("/teacherDashboard/QuestionBankUI.fxml");
	private final String val;
	Navigator(String val){
		this.val = val;
	}
	public String getVal() {
		return val;
	}
}
