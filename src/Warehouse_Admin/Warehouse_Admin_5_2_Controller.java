package Warehouse_Admin;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Back_End.Category;
import Back_End.Product;
import Back_End.ProductNameComparator;
import Back_End.ProductPopularityComparator;
import Back_End.ProductPriceComparator;
import Back_End.SuperUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Warehouse admin_5_2 controller class
 * 
 * @author Jatin
 *
 */
public class Warehouse_Admin_5_2_Controller implements Initializable {
	public Category c;
	private Stage primaryStage;

	@FXML
	private Button ok;

	ObservableList<String> l = FXCollections.observableArrayList();
	@FXML
	private ListView<String> List;

	@FXML
	private Button SbyN;

	@FXML
	private Button SbyP;

	@FXML
	private Button SbyPO;

	/**
	 * sort by name
	 * 
	 * @param event
	 */
	@FXML
	void Name_Sort(ActionEvent event) {
		ProductNameComparator CompareName = new ProductNameComparator();
		ArrayList<Product> SortedProducts = CompareName.SortByName(c.getProducts());
		load(SortedProducts);
	}

	/**
	 * sort by popularity
	 * 
	 * @param event
	 */
	@FXML
	void Pop_Sort(ActionEvent event) {
		ProductPopularityComparator ComparePopularity = new ProductPopularityComparator();
		ArrayList<Product> SortedProducts = ComparePopularity.SortByPopularity(c.getProducts());
		load(SortedProducts);
	}

	/**
	 * sort by price
	 * 
	 * @param event
	 */
	@FXML
	void Price_Sort(ActionEvent event) {
		ProductPriceComparator ComparePrice = new ProductPriceComparator();
		ArrayList<Product> SortedProducts = ComparePrice.SortByPrice(c.getProducts());
		load(SortedProducts);
	}

	private void load() {
		c = SuperUser.Warehouses.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().c[2];
		List.getItems().removeAll(List.getItems());
		l.removeAll(l);
		for (int i = 0; i < c.getProducts().size(); i++) {
			l.add(c.getProducts().get(i).toString());
		}
		List.getItems().addAll(l);
	}

	public void load(ArrayList<Product> A) {
		List.getItems().removeAll(List.getItems());
		l.removeAll(l);
		for (int i = 0; i < A.size(); i++) {
			l.add(A.get(i).toString());
		}
		List.getItems().addAll(l);
	}

	@FXML
	void drag(MouseEvent event) {// as soon as mouse entered list view
		Node source = (Node) event.getSource();
		primaryStage = (Stage) source.getScene().getWindow();
		load();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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
		SuperUser.Warehouses.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().logout();

	}

	@FXML
	private Button logout;

}
