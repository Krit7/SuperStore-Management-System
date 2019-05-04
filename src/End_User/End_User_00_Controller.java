package End_User;

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
 * class to control end user selection of store
 * 
 *
 */
public class End_User_00_Controller implements Initializable {

	public End_User_00_Controller() {
		// this.primaryStage = Users_Page_Controller.primaryStage;
	}

	ObservableList<String> list = FXCollections.observableArrayList();
	/**
	 * listview of stores
	 */
	@FXML
	private ListView<String> stores;

	/**
	 * function to load the stores listview
	 */
	private void load() {
		list.removeAll(list);
		for (int i = 0; i < SuperUser.Stores.size(); i++) {
			list.add(SuperUser.Stores.get(i).toString());
		}
		stores.getItems().addAll(list);
	}

	@FXML
	private Button ok;

	/**
	 * next window
	 * 
	 * @param event
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@FXML
	void NewWindow(ActionEvent event) throws NumberFormatException {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
				.add(primaryStage);
		if (!stores.getItems().isEmpty()) {
			SuperUser S = Users_Page_Controller.SuperUsers.get(0);
			Store s = S.searchStore(Integer.parseInt(stores.getSelectionModel().getSelectedItem().split(" ")[1]));
			for (int i = 0; i < SuperUser.Stores.size(); i++) {
				if (SuperUser.Stores.get(i).equals(s)) {
					primaryStage.setTitle(primaryStage.getTitle() + " Store " + i);
					Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]))
							.setVisitedStore(SuperUser.Stores.get(i));
				}
			}

			try {
				Parent root = FXMLLoader.load(getClass().getResource("/End_User/End_User_0.fxml"));
				Scene scene = new Scene(root, 400, 400);
				// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		load();

	}

	/**
	 * function to logout
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
