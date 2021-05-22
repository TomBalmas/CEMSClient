package client;

import common.Principle;
import common.Student;
import common.Teacher;
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
		if (msg.startsWith("LOGIN"))
			openConnection();
		else if (msg.equals("SIGN_OUT")) {
			closeConnection();
			return;
		}
		awaitResponse = true;
		sendToServer(msg);
		while (awaitResponse) {
			Thread.sleep(100);
		}
	}

	public void handleMessageFromServer(Object msg) {
		awaitResponse = false;
		if (msg == null)
			ClientController.setRoleFrame("null");
		if (msg instanceof User)
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

}
