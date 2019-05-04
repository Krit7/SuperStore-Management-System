package Warehouse_Admin;

import java.io.IOException;

import Back_End.SuperUser;
import Back_End.Warehouse;
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
 * Warhouse admin_1_1 controller clas
 * 
 * @author Jatin
 *
 */
public class Warehouse_Admin_1_1_Controller {
	// private Stage primaryStage;

	public Warehouse_Admin_1_1_Controller() {
		// this.primaryStage = Login_Page_Controller.primaryStage;
	}

	@FXML
	private TextField name;

	@FXML
	private TextField path;

	@FXML
	private Button back;

	@FXML
	private Button ok;

	@FXML
	void get_name(ActionEvent event) {

	}

	@FXML
	void get_path(ActionEvent event) {

	}

	/**
	 * next window
	 * 
	 * @param event
	 */
	@FXML
	void next_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!name.getText().isEmpty() && !path.getText().isEmpty()) {
			Warehouse w = SuperUser.Warehouses.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
			String VPath[] = path.getText().split(" > ");
			int PathLength = VPath.length;
			String Path[] = new String[PathLength + 1];
			for (int i = 0; i < PathLength; i++) {
				Path[i] = VPath[i];
			}
			Path[Path.length - 1] = name.getText();
			w.getInventory().AddCategory(Path);
			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Warehouse_Admin/Warehouse_Admin_0.fxml"));
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
	 * previous wndow
	 * 
	 * @param event
	 */
	@FXML
	void prev_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Warehouse_Admin/Warehouse_Admin_0.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
