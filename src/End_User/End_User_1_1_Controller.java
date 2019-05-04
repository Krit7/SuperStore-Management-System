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
 * class to control enduser 1_1
 */
public class End_User_1_1_Controller {

	public Category c;
	EndUser E;
	/**
	 * ok button
	 */
	@FXML
	private Button ok;

	ObservableList<String> l = FXCollections.observableArrayList();
	/**
	 * listview for categories
	 */
	@FXML
	private ListView<String> List;
	/**
	 * button to sort
	 */
	@FXML
	private Button Sort;

	/**
	 * sort function
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
			Stage primaryStage = new Stage();
			primaryStage.setTitle(prevStage.getTitle());
			Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
					.add(primaryStage);

			try {
				String filename;
				c = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c1[0];
				c = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c1[1]
						.CheckInSubCategory(List.getSelectionModel().getSelectedItem());
				if (c.CheckForProductOrCategory() == 0) {
					filename = "/End_User/End_User_1_2.fxml";
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
					Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).flag1 = 1;
					Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1])).c1[1] = c;

					FXMLLoader Loader = new FXMLLoader(getClass().getResource(filename));
					Parent root = Loader.load();
					Scene scene = new Scene(root, 400, 400);
					primaryStage.setScene(scene);
					End_User_1_1_Controller c = Loader.getController();
					c.E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
					c.load();
				}

				primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void load() {
		c = E.c1[0];
		if (E.flag1 == 0) {
			c = E.c1[0];
		} else {
			c = E.c1[1];
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