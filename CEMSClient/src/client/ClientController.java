package client;

import java.io.IOException;

import common.User;

public class ClientController {

	private static CEMSClient client;
	private static String roleFrame = null;


	public ClientController(String host, int port) {
		client = new CEMSClient(host, port, this);
	}

	/**
	 * UI request to server
	 * format: REQUEST_NAME-arg0,arg1,arg2
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

}
