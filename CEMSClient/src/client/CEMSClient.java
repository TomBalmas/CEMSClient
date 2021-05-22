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
		String[] str = msg.split("-");
		
		if (str[0].equals("LOGIN"))
			openConnection();
		awaitResponse = true;
		sendToServer(msg);
		while (awaitResponse) {
			Thread.sleep(100);
		}
	}

	public void handleMessageFromServer(Object msg) {
		awaitResponse = false;
		if (msg instanceof Teacher)
			ClientController.setRoleFrame("Teacher");
		else if (msg instanceof Student)
			ClientController.setRoleFrame("Student");
		else if (msg instanceof Principle)
			ClientController.setRoleFrame("Principle");
	}
	public User getActiveUser() {
        return activeUser;
    }

}
