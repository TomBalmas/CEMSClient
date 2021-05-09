package util;

public enum Navigator {
	LOGIN("LoginUI.fxml"), TEACHER_DASHBOARD("/teacherDashboard/TeacherDashboardUI.fxml"),
	PRINCIPLE_DASHBOARD("/principleDashboard/PrincipleDashboardUI.fxml"),
	VIEW_REPORTS ("/principleDashboard/ViewReports.fxml"),
	CREATE_REPORT ("/principleDashboard/CreateReport.fxml");

	private final String val;

	Navigator(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
