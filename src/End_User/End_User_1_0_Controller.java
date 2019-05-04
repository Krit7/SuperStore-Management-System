package End_User;

import java.io.IOException;
import java.util.Collections;

import Back_End.Category;
import Back_End.Store;
import Back_End.SuperUser;
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
 * class to control enduser 1_0.fxml
 */
public class End_User_1_0_Controller {
	public Category c;
	Store s;

	/**
	 * constructor
	 */
	public End_User_1_0_Controller() {
	}

	@FXML
	private Button ok;

	ObservableList<String> l = FXCollections.observableArrayList();
	/**
	 * listiew for stores list
	 */
	@FXML

	private ListView<String> List;
	/**
	 * button to sort
	 */
	@FXML
	private Button Sort;

	/**
	 * function for sort
	 */
	@FXML
	void Sort_Items(ActionEvent event) {
		Collections.sort(List.getItems());
	}

	/**
	 * nextwindow
	 * 
	 * @param event
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	@FXML
	void next_window(ActionEvent event) throws NumberFormatException, Exception {
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();
		if (!List.getItems().isEmpty()) {
			Store s = SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[3]));
			c = s.getInventory().CheckInCategories(List.getSelectionModel().getSelectedItem());
			Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c1[0] = c;

			Stage primaryStage = new Stage();
			primaryStage.setTitle(prevStage.getTitle());
			Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
					.add(primaryStage);

			try {
				String filename;
				if (c.CheckForProductOrCategory() == 0) {
					filename = "/End_User/End_User_1_2.fxml";
					Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c1[2] = c;
					Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c1[2] = c;
					FXMLLoader Loader = new FXMLLoader(getClass().getResource(filename));
					Parent root = Loader.load();
					Scene scene = new Scene(root, 400, 400);
					primaryStage.setScene(scene);
					End_User_1_2_Controller c = Loader.getController();
					c.c = Users_Page_Controller.EndUsers
							.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).c1[2];
					c.load();
				} else {
					filename = "/End_User/End_User_1_1.fxml";
					Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c1[1] = c;
					FXMLLoader Loader = new FXMLLoader(getClass().getResource(filename));
					Parent root = Loader.load();
					End_User_1_1_Controller c = Loader.getController();
					c.E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
					c.load();
					Scene scene = new Scene(root, 400, 400);
					// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					primaryStage.setScene(scene);
				}
				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * load
	 */
	public void load() {
		l.removeAll(l);
		for (int i = 0; i < s.getInventory().getCategories().size(); i++) {
			l.add(s.getInventory().getCategories().get(i).getCatName());
		}
		List.getItems().addAll(l);
	}

	public void initializeStore(Store s) {
		this.s = s;
		System.out.println("hi");
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
