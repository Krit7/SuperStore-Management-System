package Store_Admin;

import java.io.IOException;

import Back_End.Store;
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
 * storeadmin_4 cotroler class
 * 
 * @author
 *
 */
public class Store_Admin_4_Controller {
	// private Stage primaryStage;

	public Store_Admin_4_Controller() {
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
	 * next windpow
	 * 
	 * @param event
	 */
	@FXML
	void next_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!name.getText().isEmpty()) {
			Store s = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
			if (!cost.getText().isEmpty() && demand.getText().isEmpty()) {
				s.getInventory().CheckInProducts(name.getText()).setD(Integer.parseInt(cost.getText()));
			}
			if (cost.getText().isEmpty() && !demand.getText().isEmpty()) {
				s.getInventory().CheckInProducts(name.getText()).setK(Integer.parseInt(demand.getText()));
			}
			if (!cost.getText().isEmpty() && !demand.getText().isEmpty()) {
				s.getInventory().CheckInProducts(name.getText()).setK(Integer.parseInt(demand.getText()));
				s.getInventory().CheckInProducts(name.getText()).setD(Integer.parseInt(cost.getText()));
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
	 * previous wndow
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
