package Super_User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Back_End.SuperUser;
import Back_End.Warehouse;
import application.Users_Page_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Super user _7_1_controller class
 * 
 * @author Jatin
 *
 */
public class Super_User_7_1_Controller implements Initializable {
	// private Stage primaryStage;
	public static Warehouse w;

	public Super_User_7_1_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	ObservableList<String> list_warehouse = FXCollections.observableArrayList();
	@FXML
	private ListView<String> warehouses;

	private void load_warehouses() {
		list_warehouse.removeAll(list_warehouse);
		for (int i = 0; i < SuperUser.Warehouses.size(); i++) {
			list_warehouse.add(SuperUser.Warehouses.get(i).toString());
		}
		warehouses.getItems().addAll(list_warehouse);
	}

	@FXML
	private Button back;

	@FXML
	private Button ok;

	/**
	 * next window
	 * 
	 * @param event
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@FXML
	void next_window(ActionEvent event) throws NumberFormatException, Exception {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!warehouses.getItems().isEmpty()) {
			SuperUser S = Users_Page_Controller.SuperUsers.get(0);
			w = S.searchWarehouse(Integer.parseInt(warehouses.getSelectionModel().getSelectedItem().split(" ")[1]));

			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_7_2.fxml"));
				Scene scene = new Scene(root, 400, 400);
				// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * previous windoew
	 * 
	 * @param event
	 */
	@FXML
	void prevv_window(ActionEvent event) {
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		load_warehouses();

	}

	/**
	 * logout
	 * 
	 * @param event
	 */
	@FXML
	void logout(ActionEvent event) {
		Login_Page_Controller.logout();
	}

	@FXML
	private Button logout;

}
