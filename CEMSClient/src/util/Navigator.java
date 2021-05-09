package util;

public enum Navigator {
	LOGIN ("LoginUI.fxml"),
	TEACHERDASHBOARD ("/teacherDashboard/TeacherDashboardUI.fxml"),
	TESTBANK ("/teacherDashboard/bankUI.fxml"),
	ADDINGNEWTEST ("/teacherDashboard/AddingNewTestUI.fxml");
	private final String val;
	Navigator(String val){
		this.val = val;
	}
	public String getVal() {
		return val;
	}
}
