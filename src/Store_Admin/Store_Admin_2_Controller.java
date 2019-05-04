package Store_Admin;

import java.io.IOException;

import Back_End.ProductDoesntExistExcetion;
import Back_End.Store;
import Back_End.SuperUser;
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
 * Store admin 1_2 controller class
 * 
 * @author
 *
 */
public class Store_Admin_2_Controller {
	// private Stage primaryStage;

	public Store_Admin_2_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

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
	 * product name tex filed
	 */
	@FXML
	private TextField prod_name;
	@FXML
	/**
	 * quantity
	 */
	private TextField quan;
	/**
	 * price
	 */
	@FXML
	private TextField price;

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
	 * @throws IOException
	 */
	@FXML
	void next_window(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!prod_name.getText().isEmpty()) {
			Store s = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
			if (quan.getText().isEmpty() && !price.getText().isEmpty()) {
				try {
					s.getInventory().UpdateProductPrice(prod_name.getText(), Double.parseDouble(price.getText()));
				} catch (ProductDoesntExistExcetion e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("PRODUCT DOESNT EXISTS");
					primaryStage1.show();
					return;
				}
			}
			if (!quan.getText().isEmpty() && price.getText().isEmpty()) {
				try {
					s.getInventory().UpdateProductQuantity(prod_name.getText(), Double.parseDouble(quan.getText()));
				} catch (ProductDoesntExistExcetion e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("PRODUCT DOESNT EXISTS");
					primaryStage1.show();
					return;
				}
			}
			if (!quan.getText().isEmpty() && !price.getText().isEmpty()) {
				try {
					s.getInventory().UpdateProductQuantity(prod_name.getText(), Double.parseDouble(quan.getText()));

				} catch (ProductDoesntExistExcetion e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("PRODUCT DOESNT EXISTS");
					primaryStage1.show();
					return;
				}
				try {
					s.getInventory().UpdateProductPrice(prod_name.getText(), Double.parseDouble(price.getText()));

				} catch (ProductDoesntExistExcetion e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("PRODUCT DOESNT EXISTS");
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
	 * previous wnindpw
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

}
