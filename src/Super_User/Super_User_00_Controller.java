package Super_User;

import java.net.URL;
import java.util.ResourceBundle;

import Back_End.SuperUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class Super_User_00_Controller implements Initializable {

	ObservableList<String> list_warehouse = FXCollections.observableArrayList();
	@FXML
	private ListView<String> warehouses;
	ObservableList<String> list_store = FXCollections.observableArrayList();
	@FXML
	private ListView<String> stores;
	/**
	 * loadstores
	 */
	private void load_stores() {
		list_store.removeAll(list_store);
		for (int i = 0; i < SuperUser.Stores.size(); i++) {
			list_store.add(SuperUser.Stores.get(i).toString());
		}
		stores.getItems().addAll(list_store);
	}
	/**
	 * load warehouses
	 */
	private void load_warehouses() {
		list_warehouse.removeAll(list_warehouse);
		for (int i = 0; i < SuperUser.Warehouses.size(); i++) {
			list_warehouse.add(SuperUser.Warehouses.get(i).toString());
		}
		warehouses.getItems().addAll(list_warehouse);
	}

	public void initialize(URL location, ResourceBundle resources) {
		load_stores();
		load_warehouses();
	}

}
