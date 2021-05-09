package util;

public enum Navigator {
	LOGIN("LoginUI.fxml"), TEACHER_DASHBOARD("/teacherDashboard/TeacherDashboardUI.fxml"),
	PRINCIPLE_DASHBOARD("/principleDashboard/PrincipleDashboardUI.fxml");

	private final String val;

	Navigator(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
