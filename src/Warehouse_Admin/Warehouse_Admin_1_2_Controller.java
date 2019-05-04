package Warehouse_Admin;

import java.io.IOException;

import Back_End.Warehouse;
import application.Error_Controller;
import Back_End.ProductAlreadyExistExcetion;
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
 * warehouse admin_1_2 controller class
 * 
 * @author Jatin
 *
 */
public class Warehouse_Admin_1_2_Controller {
//	private Stage primaryStage;

	public Warehouse_Admin_1_2_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	@FXML
	private TextField name;

	@FXML
	private TextField path;

	@FXML
	private Button back;

	@FXML
	private Button ok;

	@FXML
	private TextField quantity;

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
	 * @throws IOException
	 */
	@FXML
	void next_window(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!name.getText().isEmpty() && !path.getText().isEmpty() && !price.getText().isEmpty()
				&& !quantity.getText().isEmpty()) {
			Warehouse w = SuperUser.Warehouses.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
			String VPath[] = path.getText().split(" > ");
			try {
				w.getInventory().AddProduct(VPath, name.getText(), Double.parseDouble(price.getText()),
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
