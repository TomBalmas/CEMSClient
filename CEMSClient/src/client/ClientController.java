package client;

import java.io.IOException;

public class ClientController {
	
	private static CEMSClient client;
	private static String roleFrame = null;
	
	public ClientController(String host, int port) {
		client = new CEMSClient(host, port, this);
	}
	
	public static void accept(String str) {
		try {
			client.handleMessageFromClientUI(str);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				client.closeConnection();
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
	
	
}
