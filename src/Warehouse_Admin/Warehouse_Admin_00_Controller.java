package Warehouse_Admin;

import Back_End.SuperUser;
import Back_End.Warehouse;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
/**
 * Warehouse admin _00 controller class
 * @author 
 *
 */
public class Warehouse_Admin_00_Controller {
	private Stage primaryStage;
	@FXML
	private TextArea Text;

	/**
	 * drag
	 * @param event
	 */
	@FXML
	void drag(MouseEvent event) {
		// System.out.println("hi");
		Node source = (Node) event.getSource();
		primaryStage = (Stage) source.getScene().getWindow();
		load();

	}

	public void load() {
		Warehouse w = SuperUser.Warehouses.get(Integer.parseInt(primaryStage.getTitle().split(" ")[1]));
		Text.setText(w.getAlertMessage());

	}
}
