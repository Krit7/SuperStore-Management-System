 package Back_End;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

import application.Users_Page_Controller;

/**
 * Super user class
 * 
 * @author
 *
 */
public class SuperUser extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * admin string
	 */
	private static final String Admin = "SU";
	/**
	 * stores arraylist
	 */
	public static ArrayList<Store> Stores = new ArrayList<>();
	/**
	 * warehouse arraylist
	 */
	public static ArrayList<Warehouse> Warehouses = new ArrayList<>();

	private ArrayList<Warehouse> SWarehouses;
	private ArrayList<Store> SStores;
	private ArrayList<StoreAdmin> SStoreAdmins;
	private ArrayList<WarehouseAdmin> SWarehouseAdmins;

	/**
	 * constructor
	 */
	public SuperUser() {
		super(Admin);
	}

	/**
	 * create warehouse
	 * 
	 * @param name
	 * @param City
	 * @return
	 */
	public Warehouse CreateWarehouse(String name, String City) {
		Warehouse W = new Warehouse(name, City);
		W.setWarehouses(Warehouses);
		Warehouses.add(W);
		return W;
	}

	/**
	 * create store
	 * 
	 * @param name
	 * @param City
	 * @return
	 */
	public Store CreateStore(String name, String City) {
		Store S = new Store(name, City);
		Stores.add(S);
		return S;

	}

	/**
	 * create warehouse admin
	 * 
	 * @return
	 */
	public WarehouseAdmin CreateWarehouseAdmin() {
		WarehouseAdmin W = new WarehouseAdmin();
		Users_Page_Controller.WarehouseAdmins.add(W);
		return W;
	}

	/**
	 * create store admin
	 * 
	 * @return
	 */
	public StoreAdmin CreateStoreAdmin() {
		StoreAdmin S = new StoreAdmin();
		Users_Page_Controller.StoreAdmins.add(S);
		return S;
	}

	/**
	 * link stores to warehouse
	 * 
	 * @param W
	 * @param S
	 */
	public void LinkStoreAndWarehouse(Warehouse W, Store S) {
		W.AddStore(S);
		S.setWarehouse(W);
	}

	/**
	 * search store
	 * 
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public Store searchStore(int Id) {// searching store via ID
		for (int i = 0; i < Stores.size(); i++) {
			if (Stores.get(i).getId() == Id) {
				return Stores.get(i);
			}
		}
		// error
		return null;
	}

	/**
	 * search store admin
	 * 
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public StoreAdmin searchStoreAdmin(String Id) {// searching store via ID
		for (int i = 0; i < Users_Page_Controller.StoreAdmins.size(); i++) {
			if (Users_Page_Controller.StoreAdmins.get(i).getLoginId().equalsIgnoreCase(Id)) {
				return Users_Page_Controller.StoreAdmins.get(i);
			}
		}
		// error
		return null;
	}

	/**
	 * search warehouse
	 * 
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public Warehouse searchWarehouse(int Id) {// searching warehouse via ID
		for (int i = 0; i < Warehouses.size(); i++) {
			if (Warehouses.get(i).getId() == Id) {
				return Warehouses.get(i);
			}
		}
		// error
		return null;
	}

	/**
	 * 
	 * search warehouse admin
	 * 
	 * @param Id
	 * @return
	 * @throws Exception
	 */
	public WarehouseAdmin searchWarehouseAdmin(String Id) {// searching warehouse via ID
		for (int i = 0; i < Users_Page_Controller.WarehouseAdmins.size(); i++) {
			if (Users_Page_Controller.WarehouseAdmins.get(i).getLoginId().equalsIgnoreCase(Id)) {
				return Users_Page_Controller.WarehouseAdmins.get(i);
			}
		}
		// error
		return null;
	}

	/**
	 * deseralize
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static SuperUser RestoreSuprerUser() throws IOException, ClassNotFoundException {
		SuperUser S;
		ObjectInputStream In = null;
		try {
			In = new ObjectInputStream(new FileInputStream("SuperUser.txt"));
			S = (SuperUser) In.readObject();
			SuperUser.Warehouses = S.getSWarehouses();
			SuperUser.Stores = S.getSStores();
			Users_Page_Controller.WarehouseAdmins = S.getSWarehouseAdmins();
			Users_Page_Controller.StoreAdmins = S.getSStoreAdmins();
			System.out.println("Old Database Restored");
			Store.ID = S.getSStores().size();
			Warehouse.ID = S.getSWarehouses().size();
		} catch (FileNotFoundException e) {
			@SuppressWarnings("unused")
			File file = new File("SuperUser.txt");
			System.out.println("New Database Created");
			S = new SuperUser();
			SuperUser.Warehouses = new ArrayList<>();
			SuperUser.Stores = new ArrayList<>();
			Users_Page_Controller.WarehouseAdmins = new ArrayList<>();
			Users_Page_Controller.StoreAdmins = new ArrayList<>();
		} finally {
			if (In != null) {
				In.close();
			}
		}

		return S;
	}

	public ArrayList<Warehouse> getSWarehouses() {
		return SWarehouses;
	}

	public void setSWarehouses() {
		SWarehouses = new ArrayList<Warehouse>(Warehouses);
	}

	public ArrayList<Store> getSStores() {
		return SStores;
	}

	public void setSStores() {
		SStores = new ArrayList<Store>(Stores);
	}

	public ArrayList<StoreAdmin> getSStoreAdmins() {
		return SStoreAdmins;
	}

	public void setSStoreAdmins() {
		SStoreAdmins = new ArrayList<StoreAdmin>(Users_Page_Controller.StoreAdmins);
	}

	public ArrayList<WarehouseAdmin> getSWarehouseAdmins() {
		return SWarehouseAdmins;
	}

	public void setSWarehouseAdmins() {
		SWarehouseAdmins = new ArrayList<WarehouseAdmin>(Users_Page_Controller.WarehouseAdmins);
	}

}
