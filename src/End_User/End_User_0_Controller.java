package End_User;

import java.io.IOException;

import Back_End.CategoryDoesntExists;
import Back_End.ProductDoesntExistExcetion;
import Back_End.SuperUser;
import application.Users_Page_Controller;
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
 * class to control the enduser0 page
 */
public class End_User_0_Controller {
	/**
	 * String for store info
	 */
	private String Storeinfo;

	/**
	 * class to control the enduser0 page
	 */

	public End_User_0_Controller() {
		// primaryStage = Users_Page_Controller.primaryStage;
		// this.Storeinfo = primaryStage.getTitle();
	}

	/**
	 * search field text field
	 */
	@FXML
	private TextField search_field;
	/**
	 * button to search
	 */
	@FXML
	private Button search;
	/**
	 * button to browse
	 */
	@FXML
	private Button browse;
	/**
	 * button to checkout
	 */
	@FXML
	private Button checkout;

	/**
	 * function to checkout
	 * 
	 * @param event
	 */
	@FXML
	void Checkout(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();
		Stage primaryStage = new Stage();
		primaryStage.setTitle(prevStage.getTitle());
		Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
				.add(primaryStage);
		try {
			FXMLLoader Loader = new FXMLLoader(getClass().getResource("/End_User/End_User_3.fxml"));

			Parent root = Loader.load();
			End_User_3_Controller c = Loader.getController();
			c.E = Users_Page_Controller.EndUsers.get(Integer.parseInt(prevStage.getTitle().split(" ")[1]));
			c.load();
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(Storeinfo);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * function to search
	 * 
	 * @param event
	 * @throws ProductDoesntExistExcetion 
	 * @throws CategoryDoesntExists 
	 */
	@FXML
	void Search(ActionEvent event) throws ProductDoesntExistExcetion, CategoryDoesntExists {
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();

		if (!search_field.getText().isEmpty()) {

			try {
				Stage primaryStage = new Stage();
				FXMLLoader Loader = new FXMLLoader(getClass().getResource("/End_User/End_User_2.fxml"));

				Parent root = Loader.load();
				End_User_2_Controller c = Loader.getController();
				c.s = SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[3]));
				c.searched = search_field.getText();
				c.load();
				Scene scene = new Scene(root, 400, 400);
				// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle(Storeinfo);
				primaryStage.show();
				primaryStage.setTitle(prevStage.getTitle());
				Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
						.add(primaryStage);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * function to browse
	 * 
	 * @param event
	 */
	@FXML
	void Browse(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage prevStage = (Stage) source.getScene().getWindow();
		try {
			Stage primaryStage = new Stage();
			FXMLLoader Loader = new FXMLLoader(getClass().getResource("/End_User/End_User_1_0.fxml"));
			Parent root = Loader.load();
			End_User_1_0_Controller c = Loader.getController();
			c.initializeStore(SuperUser.Stores.get(Integer.parseInt(prevStage.getTitle().split(" ")[3])));
			c.load();
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(Storeinfo);
			primaryStage.show();
			primaryStage.setTitle(prevStage.getTitle());
			Users_Page_Controller.EndUsers.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1])).getStages()
					.add(primaryStage);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
