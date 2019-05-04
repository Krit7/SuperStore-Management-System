package Super_User;

import java.io.IOException;

import Back_End.Warehouse;
import application.Error_Controller;
import application.Users_Page_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Super_User_4_Controller {
	// private Stage primaryStage;

	public Super_User_4_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	@FXML
	private TextField name;

	@FXML
	private TextField city;
	@FXML
	private Button back;
	@FXML
	private Button ok;

	@FXML
	private TextField admin_id;

	@FXML
	void Collect_Name(ActionEvent event) {

	}

	@FXML
	void Collect_city(ActionEvent event) {

	}

	/**
	 * previous window
	 * 
	 * @param event
	 */
	@FXML
	void prevWindow(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_0.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * next window
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void nextWindow(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (Users_Page_Controller.SuperUsers.get(0).searchWarehouseAdmin(admin_id.getText()) == null) {
			Stage primaryStage1 = new Stage();
			FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
			Parent root1 = L.load();
			Scene scene1 = new Scene(root1, 400, 400);
			primaryStage1.setScene(scene1);
			Error_Controller e = L.getController();
			e.error.setText("WAREHOUSE ADMIN ID IS INVALID");
			primaryStage1.show();
			return;
		} else {
			Warehouse w = Users_Page_Controller.SuperUsers.get(0).CreateWarehouse(name.getText(), city.getText());
			w.setAdmin(Users_Page_Controller.SuperUsers.get(0).searchWarehouseAdmin(admin_id.getText()));
		}
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_0.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
