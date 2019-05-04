package End_User;

import Back_End.EndUser;
import application.Users_Page_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * controller class of end_user_4
 * 
 * @author
 *
 */
public class End_User_4_Controller {
	// public static Stage primaryStage;
	EndUser E;

	public End_User_4_Controller() {
		// primaryStage = End_User_3_Controller.primaryStage;
	}

	ObservableList<String> list = FXCollections.observableArrayList();
	/**
	 * listview of payment options
	 */
	@FXML
	private ListView<String> payment_option;
	/**
	 * order button
	 */
	@FXML
	private Button order;
	/**
	 * order function
	 * @param event
	 */
	@FXML
	void Order(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		primaryStage.close();

	}

	public void load() {
		list.removeAll(list);
		list.add("COD");
		list.add("TOTAL BILL: " + E.getTotalBill());
		payment_option.getItems().addAll(list);
	}
	/**
	 * logout
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
