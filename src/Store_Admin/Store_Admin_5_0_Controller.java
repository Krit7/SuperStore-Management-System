package Store_Admin;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import Back_End.Category;
import Back_End.Store;
import Back_End.SuperUser;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Storeadmin_5_0 controller class
 * 
 * @author
 *
 */
public class Store_Admin_5_0_Controller implements Initializable {
	private Stage primaryStage;
	public Category c;

	public Store_Admin_5_0_Controller() {

	}

	@FXML
	private Button ok;

	ObservableList<String> l = FXCollections.observableArrayList();
	@FXML
	private ListView<String> List;

	@FXML
	private Button Sort;

	/**
	 * sort
	 * 
	 * @param event
	 */
	@FXML
	void Sort_Items(ActionEvent event) {
		Collections.sort(List.getItems());
	}

	/**
	 * next window
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
			Store s = SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
			c = s.getInventory().CheckInCategories(List.getSelectionModel().getSelectedItem());
			SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().c[0] = c;
			Stage primaryStage = new Stage();
			primaryStage.setTitle(prevStage.getTitle());
			SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().getStages()
					.add(primaryStage);

			try {
				String filename;
				if (c.CheckForProductOrCategory() == 0) {
					filename = "/Store_Admin/Store_Admin_5_2.fxml";
					SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().c[2] = c;
				} else {
					filename = "/Store_Admin/Store_Admin_5_1.fxml";
					SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().c[1] = c;
				}
				Parent root = FXMLLoader.load(getClass().getResource(filename));
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
	 * drag
	 * 
	 * @param event
	 */
	@FXML
	void drag(MouseEvent event) {
		// System.out.println("hi");
		Node source = (Node) event.getSource();
		primaryStage = (Stage) source.getScene().getWindow();
		load();

	}

	/**
	 * load
	 */
	private void load() {
		Store s = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
		List.getItems().removeAll(l);
		l.removeAll(l);
		for (int i = 0; i < s.getInventory().getCategories().size(); i++) {
			l.add(s.getInventory().getCategories().get(i).getCatName());
		}
		List.getItems().addAll(l);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// load();

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
		SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().logout();

	}

	@FXML
	private Button logout;

}
