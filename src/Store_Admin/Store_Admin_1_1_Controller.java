package Store_Admin;

import java.io.IOException;

import Back_End.Warehouse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Storeadmin 1_1 controler class
 * 
 * @author
 *
 */
public class Store_Admin_1_1_Controller {
//	private Stage primaryStage;
	Warehouse w;

	public Store_Admin_1_1_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	ObservableList<String> list_warehouse = FXCollections.observableArrayList();
	/**
	 * list view of categories
	 */
	@FXML
	private ListView<String> list;
	/**
	 * back button
	 */
	@FXML
	private Button back;
	/**
	 * ok button
	 */
	@FXML
	private Button ok;

	/**
	 * next window
	 * 
	 * @param event
	 */
	@FXML
	void next_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!list.getItems().isEmpty()) {
			try {
				FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Store_Admin/Store_Admin_1_2.fxml"));
				Parent root = Loader.load();
				Scene scene = new Scene(root, 400, 400);
				primaryStage.setScene(scene);
				Store_Admin_1_2_Controller c = Loader.getController();
				c.setname(list.getSelectionModel().getSelectedItem().split(" ")[0]);
				c.w = w;
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * previous windpw
	 * 
	 * @param event
	 */
	@FXML
	void prev_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Store_Admin_0.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * load
	 */
	public void load() {
		list_warehouse.removeAll(list_warehouse);
		for (int i = 0; i < w.getInventory().getProducts().size(); i++) {
			list_warehouse.add(w.getInventory().getProducts().get(i).getProdName() + " Quantity: "
					+ w.getInventory().getProducts().get(i).getQuantity());
		}
		list.getItems().addAll(list_warehouse);
	}

}
