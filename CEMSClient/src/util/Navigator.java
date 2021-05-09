package util;

public enum Navigator {
	LOGIN ("LoginUI.fxml"),
	TEACHERDASHBOARD ("/teacherDashboard/TeacherDashboardUI.fxml"),
	STUDENTDASHBOARD ("/studentDashboard/StudentDashboardUI.fxml");
	private final String val;
	Navigator(String val){
		this.val = val;
	}
	public String getVal() {
		return val;
	}
}
