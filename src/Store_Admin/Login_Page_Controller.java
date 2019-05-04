package Store_Admin;

import java.io.IOException;

import Back_End.InvalidCredentials;
import Back_End.SuperUser;
import Back_End.User;
import application.Error_Controller;
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
 * login controller of store admin
 * 
 * @author
 *
 */
public class Login_Page_Controller {
	// Stage primaryStage;

	public Login_Page_Controller() {
		// primaryStage = Users_Page_Controller.primaryStage;

	}

	/**
	 * password field
	 */
	@FXML
	private PasswordField password;
	/**
	 * username field
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
	 * validate function
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void validate(ActionEvent event) throws IOException {

		try {
			if (User.login_time_validation(username.getText(), password.getText())) {
				// after validation
				try {
					Node source = (Node) event.getSource();
					Stage primaryStage = (Stage) source.getScene().getWindow();
					Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Store_Admin_0.fxml"));
					Scene scene = new Scene(root, 400, 400);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
					primaryStage.show();
					for (int i = 0; i < SuperUser.Stores.size(); i++) {
						if (SuperUser.Stores.get(i).getAdmin().getLoginId().equals(username.getText())) {
							primaryStage.setTitle("Store " + i);// arraylist index
							SuperUser.Stores.get(i).getAdmin().getStages().add(primaryStage);
						}
					}
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

}
