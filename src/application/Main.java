package application;

import java.io.IOException;

import Back_End.SuperUser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * class where the application starts
 */
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws ClassNotFoundException, IOException {
		if (Users_Page_Controller.SuperUsers.isEmpty()) {
			SuperUser SU = SuperUser.RestoreSuprerUser();
			// SuperUser SU = new SuperUser();
			Users_Page_Controller.SuperUsers.add(SU);
		} else {
			// use that same object
		}
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Welcome_Page.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("DIKY");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
