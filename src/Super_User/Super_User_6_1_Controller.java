package Super_User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Back_End.Store;
import Back_End.SuperUser;
import application.Users_Page_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

/**
 * Super user __6_1 controller class
 * 
 * @author
 *
 */
public class Super_User_6_1_Controller implements Initializable {
//	private Stage primaryStage;
	public static Store s;

	public Super_User_6_1_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	ObservableList<String> list_store = FXCollections.observableArrayList();
	@FXML
	private ListView<String> stores;

	private void load_stores() {
		list_store.removeAll(list_store);
		for (int i = 0; i < SuperUser.Stores.size(); i++) {
			list_store.add(SuperUser.Stores.get(i).toString());
		}
		stores.getItems().addAll(list_store);
	}

	@FXML
	private Button back;

	@FXML
	private Button ok;

	/**
	 * next window
	 * 
	 * @param event
	 * @throws Exception
	 */
	@FXML
	void next_window(ActionEvent event) throws Exception {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!stores.getItems().isEmpty()) {
			SuperUser S = Users_Page_Controller.SuperUsers.get(0);
			s = S.searchStore(Integer.parseInt(stores.getSelectionModel().getSelectedItem().split(" ")[1]));

			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_6_2.fxml"));
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
	void prevv_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_0.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		load_stores();

	}

	/**
	 * logout
	 * 
	 * @param event
	 */
	@FXML
	void logout(ActionEvent event) {
		Login_Page_Controller.logout();
	}

	@FXML
	private Button logout;

}
