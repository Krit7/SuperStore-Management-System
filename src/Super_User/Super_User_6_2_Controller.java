package Super_User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Back_End.Store;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
/**
 * Super user_6_2 controller class
 * @author 
 *
 */
public class Super_User_6_2_Controller implements Initializable {
	//private Stage primaryStage;

	public Super_User_6_2_Controller() {
		//this.primaryStage = Login_Page_Controller.primaryStage;
	}

	@FXML
	private Button back;

	@FXML
	private TextArea data;
	/**
	 * previous window
	 * @param event
	 */
	@FXML
	void prevv_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_6_1.fxml"));
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
		Store s = Super_User_6_1_Controller.s;
		data.setText(s.toString());
		data.appendText("\n" + "Admin details " + "\n" + s.getAdmin().toString());
		if (s.getWarehouse() != null) {
			data.appendText("\n" + "Linked warehouse " + "\n" + s.getWarehouse().toString());
		}
	}

}
