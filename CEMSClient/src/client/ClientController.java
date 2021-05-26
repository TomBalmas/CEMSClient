package client;

import java.io.IOException;
import java.util.ArrayList;

import common.ActiveTest;
import common.Course;
import common.FinishedTest;
import common.Question;
import common.ScheduledTest;
import common.Test;
import common.User;

public class ClientController {

	private static CEMSClient client;
	private static String roleFrame = null;
	private static ArrayList<Question> questions = null;
	private static ArrayList<Test> tests = null;
	private static ArrayList<ScheduledTest> scheduledTests = null;
	private static boolean testDeleted;
	private static ArrayList<ActiveTest> activeTests;
	private static boolean testScheduled;
	private static ArrayList<FinishedTest> finishedTests = null;
	private static ArrayList<Course> courses = null;
	private static String id =null;

	public ClientController(String host, int port) {
		client = new CEMSClient(host, port, this);
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

	public static boolean isTestDeleted() {
		return testDeleted;
	}

	public static void setTestDeleted(boolean testDeleted) {
		ClientController.testDeleted = testDeleted;
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
	
	

}
