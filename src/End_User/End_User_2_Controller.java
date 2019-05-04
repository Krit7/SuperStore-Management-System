package End_User;

import java.io.IOException;
import java.util.ArrayList;

import Back_End.Category;
import Back_End.CategoryDoesntExists;
import Back_End.EndUser;
import Back_End.Insufficienrtquantity;
import Back_End.Product;
import Back_End.ProductDoesntExistExcetion;
import Back_End.Store;
import Back_End.SuperUser;
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
 * controller class of enduser_2
 * 
 * @author Jatin
 *
 */
public class End_User_2_Controller {
	public Category c;
	Store s;
	ObservableList<String> l = FXCollections.observableArrayList();
	public String searched;

	public End_User_2_Controller() {
		// this.primaryStage = Users_Page_Controller.primaryStage;
	}

	/**
	 * listview of categories
	 */
	@FXML
	private ListView<String> Search_ResultsC;
	/**
	 * listview of products
	 */
	@FXML
	private ListView<String> Search_ResultsP;
	/**
	 * ok button
	 */
	@FXML
	private Button ok;
	/**
	 * quantity
	 */
	@FXML
	private TextField quan;

	/**
	 * next window
	 * 
	 * @param event
	 */
	@FXML
	void next_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();
		if (!Search_ResultsC.getItems().isEmpty()) {
			Store s = SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[3]));
			c = s.getInventory().CheckInAllCategories(Search_ResultsC.getSelectionModel().getSelectedItem());
			Stage primaryStage = new Stage();
			primaryStage.setTitle(prevStage.getTitle());
			Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
					.add(primaryStage);

			try {
				String filename;
				if (c.CheckForProductOrCategory() == 0) {
					filename = "/End_User/End_User_1_2.fxml";
					Users_Page_Controller.EndUsers.get(
							Integer.parseInt(((Stage) ok.getScene().getWindow()).getTitle().split(" ")[1])).c2[0] = c;
					FXMLLoader Loader = new FXMLLoader(getClass().getResource(filename));
					Parent root = Loader.load();
					Scene scene = new Scene(root, 400, 400);
					primaryStage.setScene(scene);
					End_User_1_2_Controller c = Loader.getController();
					c.c = Users_Page_Controller.EndUsers
							.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).c2[2];
					c.load();
				} else {
					filename = "/End_User/End_User_2_1.fxml";
					FXMLLoader Loader = new FXMLLoader(getClass().getResource(filename));
					Parent root = Loader.load();
					End_User_2_1_Controller c = Loader.getController();
					c.E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
					c.load();

					Scene scene = new Scene(root, 400, 400);
					primaryStage.setScene(scene);
				}

				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * add to cart button
	 */
	@FXML
	private Button Add_cart;

	/**
	 * add to cart function
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void Add_cart(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();
		if (!Search_ResultsP.getItems().isEmpty() && !quan.getText().isEmpty()) {
			// Product p =
			// c.SearchProductbyName(Search_ResultsP.getSelectionModel().getSelectedItem());
			EndUser E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));

			try {
				E.AddProductsToCart(Search_ResultsP.getSelectionModel().getSelectedItem().split(" ")[0],
						Integer.parseInt(quan.getText()));
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

	public void load() {
		ArrayList<Product> A = new ArrayList<>();
		try {
			A = s.getInventory().SearchProduct(searched);
		} catch (ProductDoesntExistExcetion e) {
		}
		ArrayList<Category> B = new ArrayList<>();
		try {
			B = s.getInventory().SearchCategory(searched);
		} catch (CategoryDoesntExists e) {

		}
		l.removeAll(l);
		for (int i = 0; i < A.size(); i++) {
			l.add(A.get(i).getProdName() + " Price " + A.get(i).getPrice() + " Quantity " + A.get(i).getQuantity());
		}
		Search_ResultsP.getItems().addAll(l);
		l.removeAll(l);
		for (int i = 0; i < B.size(); i++) {
			l.add(B.get(i).getCatName());
		}
		Search_ResultsC.getItems().addAll(l);
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
