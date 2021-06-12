package client;

import java.io.IOException;
import java.util.ArrayList;

import common.ActiveTest;
import common.Course;
import common.FinishedTest;
import common.Question;
import common.Report;
import common.ScheduledTest;
import common.Student;
import common.StudentGrade;
import common.Teacher;
import common.Test;
import common.TestFile;
import common.TimeExtensionRequest;
import common.User;
import javafx.util.Pair;
import principleDashboard.ActiveTestController;
import principleDashboard.PrincipleDashboardUIController;
import studentDashboard.StudentTakeTestController;
import teacherDashboard.TeacherDashboardUIController;

public class ClientController {

	private static CEMSClient client;
	private static String roleFrame = null;
	private static boolean questionAdded;
	private static boolean testDeleted;
	private static boolean questionDeleted;
	private static boolean reportDeleted;
	private static boolean testRemoved;
	private static boolean testRescheduled;
	private static ActiveTestController activeTestController;
	private static TeacherDashboardUIController teacherDashboardUIController;
	private static PrincipleDashboardUIController principleDashboardUIController;
	private static StudentTakeTestController studentTakeTestController;
	private static boolean questionEdited;
	private static boolean testScheduled;
	private static boolean isTimeForTest;
	private static boolean isStudentAddedToTest;
	private static boolean isStudentDeletedFromTest;
	private static boolean isActiveTest;
	private static boolean isLastStudentInTest;
	private static boolean isTestLocked;
	private static boolean isStudentNotified;
	private static String authorName = null;
	private static String newQuestionId;
	private static Report report;
	private static ScheduledTest scheduledTest = null;
	private static TimeExtensionRequest timeExtensionRequest;
	private static boolean principleNotified = false;
	private static ArrayList<TimeExtensionRequest> timeExtensionRequests = null;
	private static ArrayList<Question> questions = null;
	private static ArrayList<Test> tests = null;
	private static ArrayList<Report> reports = null;
	private static ArrayList<Student> students = null;
	private static ArrayList<Teacher> teachers = null;
	private static ArrayList<ScheduledTest> scheduledTests = null;
	private static ArrayList<ActiveTest> activeTests;
	private static ArrayList<FinishedTest> finishedTests = null;
	private static ArrayList<Course> courses = null;
	private static ArrayList<StudentGrade> grades;
	private static ArrayList<Pair<String,String>> copiedStudents;
	private static ArrayList<Pair<String,Integer>> studentAnswers;
	private static TestFile studentAnswersFile;
	private static String id = null;
	private static Test studentTest = null;
	private static Course course;

	public static boolean isPrincipleNotified() {
		return principleNotified;
	}

	public static void setPrincipleNotified(boolean principleNotified) {
		ClientController.principleNotified = principleNotified;
	}

	public static ScheduledTest getScheduledTest() {
		return scheduledTest;
	}

	public static TimeExtensionRequest getTimeExtensionRequest() {
		return timeExtensionRequest;
	}

	public static void setTimeExtensionRequest(TimeExtensionRequest timeExtensionRequest) {
		ClientController.timeExtensionRequest = timeExtensionRequest;
	}

	public static void setScheduledTest(ScheduledTest scheduledTest) {
		ClientController.scheduledTest = scheduledTest;
	}

	public ClientController(String host, int port) {
		client = new CEMSClient(host, port, this);

	}

	public static Course getCourse() {
		return course;
	}

	public static void setCourse(Course course) {
		ClientController.course = course;
	}

	public static ActiveTestController getActiveTestController() {
		return activeTestController;
	}

	public static void setActiveTestController(ActiveTestController atc) {
		activeTestController = atc;
		client.addObserver(activeTestController);
	}

	public static TeacherDashboardUIController getTeacherDashboardUIController() {
		return teacherDashboardUIController;
	}

	public static void setTeacherDashboardUIController(TeacherDashboardUIController teacherDashboardUIController) {
		ClientController.teacherDashboardUIController = teacherDashboardUIController;
		client.addObserver(teacherDashboardUIController);
	}

	public static StudentTakeTestController getStudentTakeTestController() {
		return studentTakeTestController;
	}

	public static void setStudentTakeTestController(StudentTakeTestController studentTakeTestController) {
		ClientController.studentTakeTestController = studentTakeTestController;
		client.addObserver(studentTakeTestController);
	}

	/**
	 * UI request to server format: REQUEST_NAME-arg0,arg1,arg2
	 * 
	 * @param str
	 */
	public static void accept(String str) {
		try {
			client.handleMessageFromClientUI(str);
		} catch (Exception e) {
			try {
				client.closeConnection();
				e.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public static String getRoleFrame() {
		return roleFrame;
	}

	public static void setRoleFrame(String roleFrame) {
		ClientController.roleFrame = roleFrame;
	}

	public static User getActiveUser() {
		return client.getActiveUser();
	}

	public static boolean isTestScheduled() {
		return testScheduled;
	}

	public static void setTestScheduled(boolean testScheduled) {
		ClientController.testScheduled = testScheduled;
	}

	public static ArrayList<TimeExtensionRequest> getTimeExtensionRequests() {
		return timeExtensionRequests;
	}

	public static void setTimeExtensionRequests(ArrayList<TimeExtensionRequest> timeExtensionRequests) {
		ClientController.timeExtensionRequests = timeExtensionRequests;
	}

	public static boolean isTestDeleted() {
		return testDeleted;
	}

	public static boolean isTestRescheduled() {
		return testRescheduled;
	}

	public static void setTestRescheduled(boolean testResccheduled) {
		ClientController.testRescheduled = testResccheduled;
	}

	public static void setTestDeleted(boolean testDeleted) {
		ClientController.testDeleted = testDeleted;
	}

	public static boolean isTestRemoved() {
		return testRemoved;
	}

	public static void setTestRemoved(boolean testRemoved) {
		ClientController.testRemoved = testRemoved;
	}

	public static boolean isQuestionDeleted() {
		return questionDeleted;
	}

	public static void setQuestionDeleted(boolean questionDeleted) {
		ClientController.questionDeleted = questionDeleted;
	}

	public static boolean isQuestionEdited() {
		return questionEdited;
	}

	public static void setQuestionEdited(boolean questionEdited) {
		ClientController.questionEdited = questionEdited;
	}

	public static void setQuestionAdded(boolean questionAdded) {
		ClientController.questionAdded = questionAdded;
	}

	public static boolean isQuestionAdded() {
		return questionAdded;
	}

	public static String getNewQuestionId() {
		return newQuestionId;
	}

	public static void setNewQuestionId(String newQuestionId) {
		ClientController.newQuestionId = newQuestionId;
	}

	public static ArrayList<Question> getQuestions() {
		return questions;
	}

	public static void setQuestions(ArrayList<Question> questions) {
		ClientController.questions = questions;
	}

	public static ArrayList<Test> getTests() {
		return tests;
	}

	public static void setTests(ArrayList<Test> tests) {
		ClientController.tests = tests;
	}

	public static ArrayList<ScheduledTest> getScheduledTests() {
		return scheduledTests;
	}

	public static void setScheduledTests(ArrayList<ScheduledTest> scheduledTests) {
		ClientController.scheduledTests = scheduledTests;
	}

	public static void setActiveTests(ArrayList<ActiveTest> activeTest) {

		ClientController.activeTests = activeTest;
	}

	public static ArrayList<ActiveTest> getActiveTests() {
		return activeTests;
	}

	public static ArrayList<FinishedTest> getFinishedTests() {
		return finishedTests;
	}

	public static void setFinishedTests(ArrayList<FinishedTest> finishedTests) {
		ClientController.finishedTests = finishedTests;
	}

	public static ArrayList<Course> getCourses() {
		return courses;
	}

	public static void setCourses(ArrayList<Course> courses) {
		ClientController.courses = courses;
	}

	public static String getId() {
		return id;
	}

	public static void setId(String id) {
		ClientController.id = id;
	}

	public static String getAuthorName() {
		return authorName;
	}

	public static void setAuthorName(String authorName) {
		ClientController.authorName = authorName;
	}

	public static ArrayList<Report> getReports() {
		return reports;
	}

	public static void setReports(ArrayList<Report> reports) {
		ClientController.reports = reports;
	}

	public static boolean isReportDeleted() {
		return reportDeleted;
	}

	public static void setReportDeleted(boolean reportDeleted) {
		ClientController.reportDeleted = reportDeleted;
	}

	public static void setStudentTest(Test test) {
		ClientController.studentTest = test;
	}

	public static Test getStudentTest() {
		return studentTest;
	}

	public static void setStudents(ArrayList<Student> students) {
		ClientController.students = students;
	}

	public static ArrayList<Student> getStudents() {
		return students;
	}

	public static ArrayList<Teacher> getTeachers() {
		return teachers;
	}

	public static void setTeachers(ArrayList<Teacher> teachers) {
		ClientController.teachers = teachers;
	}

	public static Report getReport() {
		return report;
	}

	public static void setReport(Report report) {
		ClientController.report = report;
	}

	public static void setGrades(ArrayList<StudentGrade> grades) {

		ClientController.grades = grades;
	}

	public static ArrayList<StudentGrade> getGrades() {
		return grades;
	}

	public static boolean isTimeForTest() {
		return isTimeForTest;
	}

	public static void setTimeForTest(boolean isTimeForTest) {
		ClientController.isTimeForTest = isTimeForTest;
	}

	public static boolean isStudentAddedToTest() {
		return isStudentAddedToTest;
	}

	public static void setStudentAddedToTest(boolean isStudentAddedToTest) {
		ClientController.isStudentAddedToTest = isStudentAddedToTest;
	}

	public static boolean isStudentDeletedFromTest() {
		return isStudentDeletedFromTest;
	}

	public static void setStudentDeletedFromTest(boolean isStudentDeletedFromTest) {
		ClientController.isStudentDeletedFromTest = isStudentDeletedFromTest;
	}

	public static boolean getIsActiveTest() {
		return isActiveTest;
	}

	public static void setIsActiveTest(boolean isActiveTest) {
		ClientController.isActiveTest = isActiveTest;
	}

	public static boolean isLastStudentInTest() {
		return isLastStudentInTest;
	}

	public static void setLastStudentInTest(boolean isLastStudentInTest) {
		ClientController.isLastStudentInTest = isLastStudentInTest;
	}

	public static boolean isTestLocked() {
		return isTestLocked;
	}

	public static void setTestLocked(boolean isTestLocked) {
		ClientController.isTestLocked = isTestLocked;
	}

	public static ArrayList<Pair<String,String>> getCopiedStudents() {
		return copiedStudents;
	}

	public static void setCopiedStudents(ArrayList<Pair<String, String>> copiedStudents) {
		ClientController.copiedStudents = copiedStudents;
	}

	public static ArrayList<Pair<String,Integer>> getStudentAnswers() {
		return studentAnswers;
	}

	public static void setStudentAnswers(ArrayList<Pair<String,Integer>> studentAnswers) {
		ClientController.studentAnswers = studentAnswers;
	}

	public static PrincipleDashboardUIController getPrincipleDashboardUIController() {
		return principleDashboardUIController;
	}

	public static void setPrincipleDashboardUIController(PrincipleDashboardUIController principleDashboardUIController) {
		ClientController.principleDashboardUIController = principleDashboardUIController;
	}

	public static boolean isStudentNotified() {
		return isStudentNotified;
	}

	public static void setStudentNotified(boolean isStudentNotified) {
		ClientController.isStudentNotified = isStudentNotified;
	}

	public static TestFile getStudentAnswersFile() {
		return studentAnswersFile;
	}

	public static void setStudentAnswersFile(TestFile studentAnswersFile) {
		ClientController.studentAnswersFile = studentAnswersFile;
	}
}
 