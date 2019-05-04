package End_User;

import java.io.IOException;
import java.util.ArrayList;

import Back_End.Category;
import Back_End.EndUser;
import Back_End.Insufficienrtquantity;
import Back_End.Product;
import Back_End.ProductNameComparator;
import Back_End.ProductPopularityComparator;
import Back_End.ProductPriceComparator;
import application.Error_Controller;
import application.Users_Page_Controller;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * class to control enduser1_2
 */
public class End_User_1_2_Controller {
	// public static Stage primaryStage1;
	public Category c;
	public Product p;

	ObservableList<String> l = FXCollections.observableArrayList();
	/**
	 * listview for categories
	 */
	@FXML
	private ListView<String> List;
	/**
	 * button to sortbyname
	 */
	@FXML
	private Button SbyN;
	/**
	 * button to sortbyprice
	 */
	@FXML
	private Button SbyP;
	/**
	 * button to sortbypopularity
	 */
	@FXML
	private Button SbyPO;
	/**
	 * button to add to cart
	 */
	@FXML
	private Button add_cart;
	/**
	 * quantity
	 */
	@FXML
	private TextField quan;

	/**
	 * add to cart function
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void Add_Cart(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();
		if (!List.getItems().isEmpty() && !quan.getText().isEmpty()) {
			p = c.SearchProductbyName(List.getSelectionModel().getSelectedItem().split(" ")[1]);
			EndUser E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
			try {
				E.AddProductsToCart(p.getProdName(), Integer.parseInt(quan.getText()));
			} catch (NumberFormatException e1) {
				// insufficient quantity
				Stage primaryStage1 = new Stage();
				FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
				Parent root1 = L.load();
				Scene scene1 = new Scene(root1, 400, 400);
				primaryStage1.setScene(scene1);
				Error_Controller e = L.getController();
				e.error.setText("NUMBER FORMAT NOT SUPPORTED");
				primaryStage1.show();
				return;
			} catch (Insufficienrtquantity e1) {
				// insufficient quantity
				Stage primaryStage1 = new Stage();
				FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
				Parent root1 = L.load();
				Scene scene1 = new Scene(root1, 400, 400);
				primaryStage1.setScene(scene1);
				Error_Controller e = L.getController();
				e.error.setText("INSUFFICIENT QUANTITY");
				primaryStage1.show();
				return;
			}
			Stage primaryStage = new Stage();
			try {
				FXMLLoader Loader = new FXMLLoader(getClass().getResource("/End_User/End_User_3.fxml"));
				Parent root = Loader.load();
				End_User_3_Controller c = Loader.getController();
				c.E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
				c.load();
				Scene scene = new Scene(root, 400, 400);
				// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
				primaryStage.setTitle(prevStage.getTitle());
				Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
						.add(primaryStage);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * sort by name function
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
	 * sort by price function
	 * 
	 * @param event
	 */
	@FXML
	void Price_Sort(ActionEvent event) {
		ProductPriceComparator ComparePrice = new ProductPriceComparator();
		ArrayList<Product> SortedProducts = ComparePrice.SortByPrice(c.getProducts());
		load(SortedProducts);
	}

	public void load() {
		List.getItems().removeAll(List.getItems());
		l.removeAll(l);
		for (int i = 0; i < c.getProducts().size(); i++) {
			Product p = c.getProducts().get(i);
			l.add("Name: " + p.getProdName() + " Quantity: " + p.getQuantity() + " Price: " + p.getPrice());
		}
		List.getItems().addAll(l);
	}

	/**
	 * load listview
	 * 
	 * @param A
	 */
	public void load(ArrayList<Product> A) {
		List.getItems().removeAll(List.getItems());
		l.removeAll(l);
		for (int i = 0; i < A.size(); i++) {
			Product p = A.get(i);
			l.add("Name " + p.getProdName() + " Quantity " + p.getQuantity() + " Price " + p.getPrice());

		}
		List.getItems().addAll(l);
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
		Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).logout();
	}

	@FXML
	private Button logout;

}
