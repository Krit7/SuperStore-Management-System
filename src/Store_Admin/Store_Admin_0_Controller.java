package Store_Admin;

import java.io.IOException;

import Back_End.SuperUser;
import application.Error_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Store admin _0ocntroller class
 * 
 * @author
 *
 */
public class Store_Admin_0_Controller {
	// private Stage primaryStage;

	public Store_Admin_0_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	/**
	 * view data buttom
	 */
	@FXML
	private Button view_data;
	/**
	 * update items button
	 */
	@FXML
	private Button update_items;
	/**
	 * delet items button
	 */
	@FXML
	private Button delete_items;
	/**
	 * manage button
	 */
	@FXML
	private Button manage;
	/**
	 * add product button
	 */
	@FXML
	private Button add_product1;

	/**
	 * delet items from inventory
	 * 
	 * @param event
	 */
	@FXML
	void Delete_Items(ActionEvent event) {
		try {
			Node source = (Node) event.getSource();
			Stage primaryStage = (Stage) source.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Store_Admin_3.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * manag ecost and demand of a product
	 * 
	 * @param event
	 */
	@FXML
	void Manage_Cost_demand(ActionEvent event) {
		try {
			Node source = (Node) event.getSource();
			Stage primaryStage = (Stage) source.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Store_Admin_4.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * updat eitems
	 * 
	 * @param event
	 */
	@FXML
	void Update_items(ActionEvent event) {
		try {
			Node source = (Node) event.getSource();
			Stage primaryStage = (Stage) source.getScene().getWindow();
			Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Store_Admin_2.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * view data function
	 * 
	 * @param event
	 */
	@FXML
	void View_Data(ActionEvent event) {
		Stage primaryStage = new Stage();
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Store_Admin_5_0.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(prevStage.getTitle());
			primaryStage.show();
			SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().getStages()
					.add(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * add product ot inventory
	 * 
	 * @param event
	 */
	@FXML
	void Add_Product(ActionEvent event) {

		try {
			Node source = (Node) event.getSource();
			Stage primaryStage = (Stage) source.getScene().getWindow();
			if (SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getWarehouse() == null) {
				Stage primaryStage1 = new Stage();
				FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
				Parent root1 = L.load();
				Scene scene1 = new Scene(root1, 400, 400);
				primaryStage1.setScene(scene1);
				Error_Controller e = L.getController();
				e.error.setText("NO LINKED WAREHOUSE");
				primaryStage1.show();
				return;
			}
			FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Store_Admin/Store_Admin_1_1.fxml"));
			Parent root = Loader.load();
			Scene scene = new Scene(root, 400, 400);
			primaryStage.setScene(scene);
			Store_Admin_1_1_Controller c = Loader.getController();
			c.w = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getWarehouse();
			c.load();
			primaryStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * logout
	 * 
	 * @param event
	 */
	@FXML
	void logout(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().logout();
	}

	/**
	 * logout button
	 */
	@FXML
	private Button logout;

}
