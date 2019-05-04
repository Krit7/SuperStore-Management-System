package Store_Admin;

import java.io.IOException;

import Back_End.Insufficienrtquantity;
import Back_End.ProductAlreadyExistExcetion;
import Back_End.Store;
import Back_End.SuperUser;
import Back_End.Warehouse;
import application.Error_Controller;
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
 * Store_Admin_1_2_Controller class
 * 
 * @author
 */
public class Store_Admin_1_2_Controller {
	// private Stage primaryStage;
	Warehouse w;

	public Store_Admin_1_2_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	/**
	 * name textfield
	 */
	@FXML
	private TextField name;
	/**
	 * path of prod
	 */
	@FXML
	private TextField path;
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
	 * quantity
	 */
	@FXML
	private TextField quantity;
	/**
	 * price
	 */
	@FXML
	private TextField price;

	@FXML
	void get_name(ActionEvent event) {

	}

	@FXML
	void get_path(ActionEvent event) {

	}

	@FXML
	void get_price(ActionEvent event) {

	}

	@FXML
	void get_quan(ActionEvent event) {

	}

	/**
	 * next window
	 * 
	 * @param event
	 * @throws Insufficienrtquantity
	 * @throws IOException
	 */
	@FXML
	void next_window(ActionEvent event) throws Insufficienrtquantity, IOException {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!name.getText().isEmpty() && !path.getText().isEmpty() && !price.getText().isEmpty()
				&& !quantity.getText().isEmpty()) {
			Store s = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
			String VPath[] = path.getText().split(" > ");
			if (w.getInventory().CheckInProducts(name.getText()).getQuantity() < Double
					.parseDouble(quantity.getText())) {
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
			} else {
				// decreasing from warehouse
				w.getInventory().CheckInProducts(name.getText())
						.setQuantity(w.getInventory().CheckInProducts(name.getText()).getQuantity()
								- Double.parseDouble(quantity.getText()));
				try {
					s.getInventory().AddProduct(VPath, name.getText(), Double.parseDouble(price.getText()),
							Double.parseDouble(quantity.getText()));
				} catch (NumberFormatException e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("NUMBER FORMAT NOT SUPPORTED");
					primaryStage1.show();
					return;
				} catch (ProductAlreadyExistExcetion e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("PRODUCT ALREADY EXISTS");
					primaryStage1.show();
					return;
				}

			}
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
	}

	/**
	 * previous button
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

	public void setname(String name) {
		this.name.setText(name);
		;
	}

}
