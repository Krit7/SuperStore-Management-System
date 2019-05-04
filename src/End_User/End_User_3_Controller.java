package End_User;

import java.io.IOException;

import Back_End.EndUser;
import Back_End.Insufficienrtquantity;
import Back_End.Product;
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
import javafx.stage.Stage;

/**
 * controller class of end_user_3
 * 
 * @author
 *
 */
public class End_User_3_Controller {
	// public static Stage primaryStage;
	EndUser E;

	public End_User_3_Controller() {
		// this.primaryStage = Users_Page_Controller.primaryStage;
	}

	ObservableList<String> list = FXCollections.observableArrayList();
	/**
	 * listview of status of cart
	 */
	@FXML
	private ListView<String> cart_status;
	/**
	 * checkout button
	 */

	@FXML
	private Button checkout;

	/**
	 * checkout function
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void Checkout(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();
		try {
			Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).CheckOutCart();
		} catch (Insufficienrtquantity e1) {
			Stage primaryStage1 = new Stage();
			FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
			Parent root1 = L.load();
			Scene scene1 = new Scene(root1, 400, 400);
			primaryStage1.setScene(scene1);
			Error_Controller e = L.getController();
			e.error.setText("INSUFFICIENT STOCK");
			primaryStage1.show();
			return;
		}
		Stage primaryStage = new Stage();
		primaryStage.setTitle(prevStage.getTitle());
		Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
				.add(primaryStage);
		try {
			FXMLLoader Loader = new FXMLLoader(getClass().getResource("/End_User/End_User_4.fxml"));
			Parent root = Loader.load();
			End_User_4_Controller c = Loader.getController();
			c.E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
			c.load();
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		prevStage.close();
	}

	public void load() {
		list.removeAll(list);
		for (int i = 0; i < E.getCart().size(); i++) {
			Product pro = E.getCart().get(i);
			list.add(pro.getProdName() + " quantity: " + E.getQuantity().get(i));
		}
		cart_status.getItems().addAll(list);

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
