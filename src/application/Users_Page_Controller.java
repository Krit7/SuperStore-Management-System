package application;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Back_End.EndUser;
import Back_End.StoreAdmin;
import Back_End.SuperUser;
import Back_End.WarehouseAdmin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * class to control the users page
 */
public class Users_Page_Controller extends Thread {
	/**
	 * array list for superuser
	 */
	public static ArrayList<SuperUser> SuperUsers = new ArrayList<>();
	/**
	 * array list for endusers
	 */
	public static ArrayList<EndUser> EndUsers = new ArrayList<>();
	/**
	 * array list for storeadmin
	 */
	public static ArrayList<StoreAdmin> StoreAdmins = new ArrayList<>();
	/**
	 * array list for warehouseadmin
	 */
	public static ArrayList<WarehouseAdmin> WarehouseAdmins = new ArrayList<>();
	/**
	 * Stage for this class
	 */
	private Stage primaryStage;
	/**
	 * button for superuser
	 */
	@FXML
	private Button B1;
	/**
	 * button for storeadmin
	 */
	@FXML
	private Button B2;
	/**
	 * button for warehouseadmin
	 */
	@FXML
	private Button B3;
	/**
	 * button for enduser
	 */
	@FXML
	private Button B4;
	/**
	 * button to exit application
	 */
	@FXML
	private Button exit;

	/**
	 * superuser
	 * 
	 * @param event
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@FXML
	void Control_B1(ActionEvent event) throws ClassNotFoundException, IOException {
//		if (SuperUsers.isEmpty()) {
//			SuperUser SU = SuperUser.RestoreSuprerUser();
//			// SuperUser SU = new SuperUser();
//			SuperUsers.add(SU);
//		} else {
//			// use that same object
//		}
		primaryStage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Super_User/Login_Page.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("SuperUser");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * storeadmin
	 * 
	 * @param event
	 */
	@FXML
	void Control_B2(ActionEvent event) {
		primaryStage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Store_Admin/Login_Page.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("StoreAdmin");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * warehouseadmin
	 * 
	 * @param event
	 */
	@FXML
	void Control_B3(ActionEvent event) {
		// System.out.println("user:3");
		primaryStage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/Warehouse_Admin/Login_Page.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("WarehouseAdmin");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * enduser
	 * 
	 * @param event
	 */
	@FXML
	void Control_B4(ActionEvent event) {
		// System.out.println("user:4");
		EndUser EU = new EndUser();
		EndUsers.add(EU);
		primaryStage = new Stage();
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/End_User/End_User_00.fxml"));
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("EndUser " + (EndUsers.size() - 1));
			// End_User_1_2_Controller.primaryStage1.setTitle(primaryStage.getTitle());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * serialize
	 * 
	 * @param event
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@FXML
	void Serialize(ActionEvent event) throws FileNotFoundException, IOException {
		// serialize
		if (Users_Page_Controller.SuperUsers.size() != 0) {
			SuperUser S = Users_Page_Controller.SuperUsers.get(0);
			S.setSWarehouseAdmins();
			S.setSWarehouses();
			S.setSStoreAdmins();
			S.setSStores();
			ObjectOutputStream Out = new ObjectOutputStream(new FileOutputStream("SuperUser.txt"));
			Out.writeObject(S);
			Out.flush();
			Out.close();
			System.out.println("Database Saved");
		}
		Node source = (Node) event.getSource();
		Stage primaryStage = (Stage) source.getScene().getWindow();
		primaryStage.close();
		System.exit(0);
	}

	@Override
	public void run() {

	}

}
