package Super_User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Back_End.Warehouse;
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
 * Super user_7_2 controller class
 * @author Jatin
 *
 */
public class Super_User_7_2_Controller implements Initializable {
	// private Stage primaryStage;

	public Super_User_7_2_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	@FXML
	private Button back;

	@FXML
	private TextArea TEXT;
	/**
	 * previous window
	 * @param event
	 */
	@FXML
	void prevv_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Super_User_7_1.fxml"));
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
		Warehouse w = Super_User_7_1_Controller.w;
		TEXT.setText(w.toString());
		TEXT.appendText("\n" + "Admin: " + "\n" + w.getAdmin().toString());

		TEXT.appendText("\n" + "Liked Stores " + "\n");
		if (w.getStores() != null) {
			for (int i = 0; i < w.getStores().size(); i++) {
				TEXT.appendText(w.getStores().toString() + "\n");
			}
		}

	}

}
