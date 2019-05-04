package Warehouse_Admin;

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
 * login page controlller for warehouse admin
 * 
 * @author
 *
 */
public class Login_Page_Controller {
	// public static Stage primaryStage;
	public static int login_no_of_super_user = 0;

	public Login_Page_Controller() {
		// primaryStage = Users_Page_Controller.primaryStage;
	}

	/**
	 * password
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
		try {
			if (User.login_time_validation(username.getText(), password.getText())) {
				// after validation
				Node source = (Node) event.getSource();
				Stage prevStage = (Stage) source.getScene().getWindow();
				for (int i = 0; i < SuperUser.Warehouses.size(); i++) {
					if (SuperUser.Warehouses.get(i).getAdmin().getLoginId().equals(username.getText())) {
						prevStage.setTitle("Warehouse " + i);// arraylist index
						SuperUser.Warehouses.get(i).getAdmin().getStages().add(prevStage);
						// delivering the order
						SuperUser.Warehouses.get(i).DeliverTheOrder();
					}
				}
				try {

					Parent root = FXMLLoader.load(getClass().getResource("/Warehouse_Admin/WareHouse_Admin_0.fxml"));
					Scene scene = new Scene(root, 400, 400);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					prevStage.setScene(scene);
					prevStage.show();

				} catch (IOException e) {
					e.printStackTrace();
				}

				try {
					Stage primaryStage = new Stage();
					SuperUser.Warehouses.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin()
							.getStages().add(primaryStage);
					primaryStage.setTitle(prevStage.getTitle());
					Parent root = FXMLLoader.load(getClass().getResource("/Warehouse_Admin/Warehouse_Admin_00.fxml"));
					Scene scene = new Scene(root, 400, 400);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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

}
