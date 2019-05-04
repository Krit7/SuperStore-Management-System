package End_User;

import java.io.IOException;
import java.util.Collections;

import Back_End.Category;
import Back_End.EndUser;
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
 * controller class of enduser2_1
 * 
 * @author
 *
 */
public class End_User_2_1_Controller {
	EndUser E;
	public Category c;
	/**
	 * ok bitton
	 */
	@FXML
	private Button ok;

	ObservableList<String> l = FXCollections.observableArrayList();
	/**
	 * listview of products
	 */
	@FXML
	private ListView<String> List;
	/**
	 * sort button
	 */
	@FXML
	private Button Sort;

	/**
	 * function to sort
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
		if (!List.getItems().isEmpty()) {
			try {
				String filename;
				Node source = (Node) event.getSource();
				Stage prevStage = (Stage) source.getScene().getWindow();
				Stage primaryStage = new Stage();
				primaryStage.setTitle(prevStage.getTitle());
				Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
						.add(primaryStage);
				c = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c2[0];
				c = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c2[1]
						.CheckInSubCategory(List.getSelectionModel().getSelectedItem());
				if (c.CheckForProductOrCategory() == 0) {
					filename = "/End_User/End_User_1_2.fxml";
					Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c2[2] = c;
					FXMLLoader Loader = new FXMLLoader(getClass().getResource(filename));
					Parent root = Loader.load();
					Scene scene = new Scene(root, 400, 400);
					primaryStage.setScene(scene);
					End_User_1_2_Controller c = Loader.getController();
					c.c = Users_Page_Controller.EndUsers
							.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).c2[2];
					c.load();
				} else {
					filename = "/End_User/End_User_2_1.fxml";
					Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).flag2 = 1;
					Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c2[1] = c;

					FXMLLoader Loader = new FXMLLoader(getClass().getResource(filename));
					Parent root = Loader.load();
					Scene scene = new Scene(root, 400, 400);
					primaryStage.setScene(scene);
					End_User_2_1_Controller c = Loader.getController();
					c.E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
					c.load();
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
		c = E.c2[0];
		if (E.flag2 == 0) {
			c = E.c2[0];
		} else {
			c = E.c2[1];

		}
		l.removeAll(l);
		for (int i = 0; i < c.getSubCategory().size(); i++) {
			l.add(c.getSubCategory().get(i).getCatName());
		}
		List.getItems().addAll(l);
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