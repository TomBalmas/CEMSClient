package client;

import java.util.ArrayList;

import common.ActiveTest;
import common.Course;
import common.FinishedTest;
import common.Principle;
import common.Question;
import common.Report;
import common.ScheduledTest;
import common.Student;
import common.Teacher;
import common.Test;
import common.User;
import ocsf.client.ObservableClient;

public class CEMSClient extends ObservableClient {
	private User activeUser;
	private ClientController controller;
	private static boolean awaitResponse = false;

	public User getActiveUser() {
		return activeUser;
	}

	public CEMSClient(String host, int port, ClientController controller) {
		super(host, port);
		this.controller = controller;
	}

	/**
	 * receives message from the UI and sends it to the server
	 * 
	 * @param msg
	 * @throws Exception
	 */
	public void handleMessageFromClientUI(String msg) throws Exception {
		openConnection();
		if (msg.equals("SIGN_OUT")) {
			closeConnection();
			return;
		}
		awaitResponse = true;
		sendToServer(msg);
		while (awaitResponse) {
			Thread.sleep(100);
		}
	}

	@SuppressWarnings("unchecked")
	public void handleMessageFromServer(Object msg) {
		awaitResponse = false;
		if (msg == null)
			ClientController.setRoleFrame("null");
		else {
			// case of login
			if (msg instanceof User) {
				if (msg instanceof Teacher) {
					ClientController.setRoleFrame("Teacher");
					activeUser = (Teacher) msg;
				} else if (msg instanceof Student) {
					ClientController.setRoleFrame("Student");
					activeUser = (Student) msg;
				} else if (msg instanceof Principle) {
					ClientController.setRoleFrame("Principle");
					activeUser = (Principle) msg;
				}
			}
			// case of filling a table
			else if (msg instanceof ArrayList<?>) {
				if (((ArrayList<?>) msg).isEmpty()) {
					ClientController.setActiveTests(null);
					ClientController.setQuestions(null);
					ClientController.setScheduledTests(null);
					ClientController.setTests(null);
				}
				// get questions from questions DB
				else if (((ArrayList<?>) msg).get(0) instanceof Question)
					ClientController.setQuestions((ArrayList<Question>) msg);
				// get tests from test DB
				else if (((ArrayList<?>) msg).get(0) instanceof Test)
					ClientController.setTests((ArrayList<Test>) msg);
				// get scheduled tests from scheduled_tests DB
				else if (((ArrayList<?>) msg).get(0) instanceof ScheduledTest) {
					ClientController.setScheduledTests((ArrayList<ScheduledTest>) msg);
				}
				// get active tests from active_tests DB
				else if (((ArrayList<?>) msg).get(0) instanceof ActiveTest)
					ClientController.setActiveTests((ArrayList<ActiveTest>) msg);
				//get finished tests from finished_tests DB
				else if(((ArrayList<?>)msg).get(0) instanceof FinishedTest)
					ClientController.setFinishedTests((ArrayList<FinishedTest>) msg);
				//get courses by field 
				else if(((ArrayList<?>)msg).get(0) instanceof Course)
					ClientController.setCourses((ArrayList<Course>) msg);
				//get reports
				else if (((ArrayList<?>) msg).get(0) instanceof Report)
					ClientController.setReports((ArrayList<Report>) msg);
				
			} else if (msg instanceof String) {
				String str = (String) msg;
				//getting message from query when adding a new question
				String[] questionAdded = str.split(":");
				
				if (str.equals("deleted")) {
					ClientController.setTestDeleted(true);
					ClientController.setQuestionDeleted(true);
				
				}
				else if (str.equals("reportDeleted")) {
					ClientController.setReportDeleted(true);
				
				}
				else if (str.equals("notDeleted"))
				{
					ClientController.setTestDeleted(false);
					ClientController.setQuestionDeleted(false);
					
				}
				else if (str.equals("scheduled"))
					ClientController.setTestScheduled(true);
				else if(str.equals("notScheduled"))
					ClientController.setTestScheduled(false);
				else if(questionAdded[0].equals("questionAdded")){
					ClientController.setQuestionAdded(true);
					//getting new question ID
					ClientController.setNewQuestionId(questionAdded[1]);
				}
				else if(str.equals("editSuccess"))
					ClientController.setQuestionEdited(true);
				else if(str.startsWith("testAdded")) {
					String[] tmp = str.split(":");
					ClientController.setId(tmp[1]);
				}
				else {
					//getting author name from query
					String[] toSplit = ((String) msg).split(":"); 
					ClientController.setAuthorName(toSplit[1]);
					
				}

			}

		}
	}

}
