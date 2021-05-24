package client;

import java.util.ArrayList;

import common.ActiveTest;
import common.Principle;
import common.Question;
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
			} else if (msg instanceof ArrayList<?>) {
				// get questions from question bank
				if (((ArrayList<?>) msg).get(0) instanceof Question)
					ClientController.setQuestions((ArrayList<Question>) msg);
				// get tests from test bank
				else if (((ArrayList<?>) msg).get(0) instanceof Test)
					ClientController.setTests((ArrayList<Test>) msg);
				else if (((ArrayList<?>) msg).get(0) instanceof ActiveTest)
					ClientController.setActiveTest((ArrayList<ActiveTest>) msg);

			}

		}
	}

}
