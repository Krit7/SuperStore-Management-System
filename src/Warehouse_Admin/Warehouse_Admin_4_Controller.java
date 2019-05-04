package Warehouse_Admin;

import java.io.IOException;

import Back_End.Warehouse;
import Back_End.SuperUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Warehouse admin_4 controller class
 * 
 * @author
 *
 */
public class Warehouse_Admin_4_Controller {
	// private Stage primaryStage;

	public Warehouse_Admin_4_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	@FXML
	private Button back;

	@FXML
	private Button ok;
	@FXML
	private TextField name;
	@FXML
	private TextField cost;

	@FXML
	private TextField demand;

	@FXML
	void get_cost(ActionEvent event) {

	}

	@FXML
	void get_demand(ActionEvent event) {

	}

	/**
	 * next window
	 * 
	 * @param event
	 */
	@FXML
	void next_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!name.getText().isEmpty()) {
			Warehouse w = SuperUser.Warehouses.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
			if (cost.getText().isEmpty() && !demand.getText().isEmpty()) {
				w.getInventory().CheckInProducts(name.getText()).setK(Integer.parseInt(demand.getText()));
			}
			if (!cost.getText().isEmpty() && demand.getText().isEmpty()) {
				w.getInventory().CheckInProducts(name.getText()).setD(Integer.parseInt(cost.getText()));
			}
			if (!cost.getText().isEmpty() && !demand.getText().isEmpty()) {
				w.getInventory().CheckInProducts(name.getText()).setK(Integer.parseInt(demand.getText()));
				w.getInventory().CheckInProducts(name.getText()).setD(Integer.parseInt(cost.getText()));
			}
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

}
