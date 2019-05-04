package Super_User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Back_End.WarehouseAdmin;
import application.Users_Page_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Super_User_2_Controller implements Initializable {
	//private Stage primaryStage;

	public Super_User_2_Controller() {
		//this.primaryStage = Login_Page_Controller.primaryStage;
	}


	@FXML
	private TextField id;

	@FXML
	private TextField password;

	@FXML
	private Button ok;

	@FXML
	void Collect_Id(ActionEvent event) {

	}
	/**
	 * nexr window
	 * @param event
	 */
	@FXML
	void nextWindow(ActionEvent event) {
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
	public void initialize(URL arg0, ResourceBundle arg1) {
		WarehouseAdmin A = Users_Page_Controller.SuperUsers.get(0).CreateWarehouseAdmin();
		id.setText(A.getLoginId());
		password.setText("" + A.getPassword());
	}
}
