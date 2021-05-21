package client;

import ocsf.client.ObservableClient;

public class CEMSClient extends ObservableClient {

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
		String[] str = ((String) msg).split("-");
		switch (str[0]) {
		case "LOGIN":
			String[] role = ((String) str[1]).split(":");
			ClientController.setRoleFrame(role[0]);
		default:
			break;
		}
}

}
