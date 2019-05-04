package Super_User;

import java.io.IOException;
import java.util.ArrayList;

import Back_End.InvalidCredentials;
import application.Error_Controller;
import application.Users_Page_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * login controller class for super user
 * 
 * @author
 *
 */
public class Login_Page_Controller {
	public static int SuperUser_Active = -1;
	public static int login_no_of_super_user = 0;
	public static ArrayList<Stage> Stages = new ArrayList<>();

	public Login_Page_Controller() {
		// primaryStage = Users_Page_Controller.primaryStage;
	}

	/**
	 * paswword
	 */
	@FXML
	private PasswordField password;
	/**
	 * username
	 */
	@FXML
	private TextField username;
	/**
	 * enter button
	 */
	@FXML
	private Button enter;

	@FXML
	void password(ActionEvent event) {

	}

	@FXML
	void user_name(ActionEvent event) {

	}

	/**
	 * validate
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void validate(ActionEvent event) throws IOException {
//		username.setText("SU95");
//		password.setText("518187695");
		try {
			if (Users_Page_Controller.SuperUsers.get(0).validate(username.getText(), password.getText())
					&& SuperUser_Active == -1) {
				// after validation
				SuperUser_Active = 1;
				login_no_of_super_user++;
				if (login_no_of_super_user <= 1) {
					// loading the Super_User_00.fxml file
					try {
						Stage primaryStage = new Stage();
						Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_00.fxml"));
						Scene scene = new Scene(root, 400, 400);
						// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						Stages.add(primaryStage);
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				try {
					Node source = (Node) event.getSource();
					Stage primaryStage = (Stage) source.getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_0.fxml"));
					Scene scene = new Scene(root, 400, 400);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					Stages.add(primaryStage);
					primaryStage.setScene(scene);
					primaryStage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (InvalidCredentials e1) {
			Stage primaryStage1 = new Stage();
			FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
			Parent root1 = L.load();
			Scene scene1 = new Scene(root1, 400, 400);
			primaryStage1.setScene(scene1);
			Error_Controller e = L.getController();
			e.error.setText(e1.getMessage());
			primaryStage1.show();
			return;
		}

	}

	/**
	 * logout
	 */
	public static void logout() {
		for (int i = 0; i < Stages.size(); i++) {
			if (Stages.get(i) != null) {
				Stages.get(i).close();
			}
		}
		SuperUser_Active = -1;
	}
}
