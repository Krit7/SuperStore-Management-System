package Store_Admin;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;

import Back_End.Category;
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
 * Storeadmin_5_1 controller class
 * 
 * @author
 *
 */
public class Store_Admin_5_1_Controller implements Initializable {
	public Category c;
	private Stage primaryStage;
	// public static int flag = 0;

	@FXML
	private Button ok;

	ObservableList<String> l = FXCollections.observableArrayList();
	@FXML
	private ListView<String> List;

	@FXML
	private Button Sort;

	/**
	 * sort items
	 * 
	 * @param event
	 */
	@FXML
	void Sort_Items(ActionEvent event) {
		// l.remove(0);
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
			String filename;
			c = SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().c[0];

			c = SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().c[1]
					.CheckInSubCategory(List.getSelectionModel().getSelectedItem());
			if (c.CheckForProductOrCategory() == 0) {
				filename = "/Store_Admin/Store_Admin_5_2.fxml";
				SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().c[2] = c;

			} else {
				filename = "/Store_Admin/Store_Admin_5_1.fxml";
				SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().flag = 1;
				SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().c[1] = c;

			}
			Stage primaryStage = new Stage();
			primaryStage.setTitle(prevStage.getTitle());
			SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).getAdmin().getStages()
					.add(primaryStage);
			try {
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
	 * load
	 */
	private void load() {
		c = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().c[0];
		if (SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().flag == 0) {
			c = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().c[0];
		} else {
			c = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getAdmin().c[1];

		}
		List.getItems().removeAll(l);
		l.removeAll(l);
		for (int i = 0; i < c.getSubCategory().size(); i++) {
			l.add(c.getSubCategory().get(i).getCatName());
		}
		List.getItems().addAll(l);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

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
