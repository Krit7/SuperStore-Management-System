package Store_Admin;

import java.io.IOException;

import Back_End.CategoryDoesntExists;
import Back_End.ProductDoesntExistExcetion;
import Back_End.Store;
import Back_End.SuperUser;
import application.Error_Controller;
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
 * Store admin _3 controller class
 * 
 * @author Jatin
 *
 */
public class Store_Admin_3_Controller {
	// private Stage primaryStage;

	public Store_Admin_3_Controller() {
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
	 * @throws IOException
	 */
	@FXML
	void next_window(ActionEvent event) throws IOException {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		if (!name.getText().isEmpty() && !path.getText().isEmpty()) {
			Store s = SuperUser.Stores.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
			String VPath[] = path.getText().split(" > ");
			int PathLength = VPath.length;
			String Path[] = new String[PathLength + 1];
			for (int i = 0; i < PathLength; i++) {
				Path[i] = VPath[i];
			}
			Path[Path.length - 1] = name.getText();

			if (s.getInventory().CheckInProducts(name.getText()) == null) {
				try {
					s.getInventory().DeleteCategory(Path);
				} catch (CategoryDoesntExists e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("ITEM DOESNT EXISTS");
					primaryStage1.show();
					return;

				}
			} else {
				try {
					s.getInventory().DeleteProduct(name.getText(), VPath);
				} catch (ProductDoesntExistExcetion e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("PRODUCT DOESNT EXISTS");
					primaryStage1.show();
					return;
				} catch (CategoryDoesntExists e1) {
					Stage primaryStage1 = new Stage();
					FXMLLoader L = new FXMLLoader(getClass().getResource("/application/Error.fxml"));
					Parent root1 = L.load();
					Scene scene1 = new Scene(root1, 400, 400);
					primaryStage1.setScene(scene1);
					Error_Controller e = L.getController();
					e.error.setText("CATEGORY DOESNT EXISTS");
					primaryStage1.show();
					return;
				}
			}

			try {
				Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Store_Admin_0.fxml"));
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
	 * previous window
	 * 
	 * @param event
	 */
	@FXML
	void prev_window(ActionEvent event) {
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Store_Admin_0.fxml"));
			Scene scene = new Scene(root, 400, 400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
