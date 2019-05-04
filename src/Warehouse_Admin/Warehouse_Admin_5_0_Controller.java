package Warehouse_Admin;

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
 * warehouse admin %_0 controller class
 * 
 * @author
 *
 */
public class Warehouse_Admin_5_0_Controller implements Initializable {
	// private Stage primaryStage;
	// public static Stage primaryStage1;

	public Warehouse_Admin_5_0_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	@FXML
	private Button ok;

	@FXML
	private Button back;
	ObservableList<String> list = FXCollections.observableArrayList();
	@FXML
	private ListView<String> warehouse_list;

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
		Stage prevStage = (Stage) source.getScene().getWindow();
		if (!warehouse_list.getItems().isEmpty()) {
			Stage primaryStage = new Stage();
			SuperUser.Warehouses.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().getStages()
					.add(primaryStage);
			primaryStage.setTitle(prevStage.getTitle());
			SuperUser S = Users_Page_Controller.SuperUsers.get(0);
			Warehouse s = S.searchWarehouse(
					Integer.parseInt(warehouse_list.getSelectionModel().getSelectedItem().split(" ")[1]));
			for (int i = 0; i < SuperUser.Warehouses.size(); i++) {
				if (SuperUser.Warehouses.get(i).equals(s)) {
					primaryStage.setTitle("Warehouse " + i);
				}
			}
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Warehouse_Admin/Warehouse_Admin_5_01.fxml"));
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
	 * previous window
	 * 
	 * @param event
	 */
	@FXML
	void prev_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Warehouse_Admin/Warehouse_Admin_0.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void load_warehouse_list() {
		list.removeAll(list);
		for (int i = 0; i < SuperUser.Warehouses.size(); i++) {
			list.add(SuperUser.Warehouses.get(i).toString());
		}
		warehouse_list.getItems().addAll(list);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		load_warehouse_list();

	}
	/**
	 * logout
	 * @param event
	 */
	@FXML
	void logout(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		SuperUser.Warehouses.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().logout();
	}

	@FXML
	private Button logout;

}
