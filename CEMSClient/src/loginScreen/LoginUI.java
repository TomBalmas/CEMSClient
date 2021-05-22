package loginScreen;

import client.ClientController;
import javafx.application.Application;
import javafx.stage.Stage;

public class LoginUI extends Application {
	
	private LoginUIController controller;
	private ClientController client;

	@Override
	public void start(Stage stage) throws Exception {
		client = new ClientController("localhost", 5555);
		controller = new LoginUIController();
		controller.start(stage);
	}

	public static void main(String[] args) {
		launch(args);
	}

}