package Super_User;

import java.io.IOException;

import Back_End.Store;
import Back_End.SuperUser;
import Back_End.Warehouse;
import application.Error_Controller;
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

public class Super_User_5_Controller {
	// private Stage primaryStage;

	public Super_User_5_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	@FXML
	private TextField Store_id;

	@FXML
	private TextField warehouse_id;

	@FXML
	private Button ok;
	@FXML
	private Button back;

	@FXML
	void Store_Id(ActionEvent event) {

	}

	@FXML
	void Warehouse_Id(ActionEvent event) {

	}

	/**
	 * next window
	 * 
	 * @param event
	 * @throws IOException
	 */
	@FXML
	void nextWindow(ActionEvent event) throws IOException {
		SuperUser S = Users_Page_Controller.SuperUsers.get(0);
		Warehouse w = null;
		if (S.searchWarehouse(Integer.parseInt(warehouse_id.getText())) == null) {
			Stage primaryStage1 = new Stage();
			FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
			Parent root1 = L.load();
			Scene scene1 = new Scene(root1, 400, 400);
			primaryStage1.setScene(scene1);
			Error_Controller e = L.getController();
			e.error.setText("WAREHOUSE ID IS INVALID");
			primaryStage1.show();
			return;
		} else {
			w = S.searchWarehouse(Integer.parseInt(warehouse_id.getText()));

		}
		Store s = null;
		if (S.searchStore(Integer.parseInt(Store_id.getText())) == null) {
			Stage primaryStage1 = new Stage();
			FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
			Parent root1 = L.load();
			Scene scene1 = new Scene(root1, 400, 400);
			primaryStage1.setScene(scene1);
			Error_Controller e = L.getController();
			e.error.setText("STORE ID IS INVALID");
			primaryStage1.show();
		} else {
			s = S.searchStore(Integer.parseInt(Store_id.getText()));

		}
		S.LinkStoreAndWarehouse(w, s);
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

	/**
	 * pevous window
	 * 
	 * @param event
	 */
	@FXML
	void prev_window(ActionEvent event) {
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

}
