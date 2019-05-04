package Back_End;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

/**
 * class warehouse
 * 
 * @author
 *
 */
public class Warehouse implements Cities, Serializable {
	private static final long serialVersionUID = 1L;
	static int ID = 0;
	/**
	 * name of warehouse
	 */
	private final String Name;
	/**
	 * warehouse admin
	 */
	private WarehouseAdmin Admin;
	/**
	 * id of warehouse
	 */
	private int Id;
	/**
	 * city of warehouse
	 */
	private String City;
	/**
	 * lit o fstores associated with warehouse
	 */
	private ArrayList<Store> Stores;
	/**
	 * alert essage to be displayed
	 */
	private String AlertMessage;
	/**
	 * inventory of warehouse
	 */
	private Inventory Inventory;

	private ArrayList<Warehouse> Warehouses;

	private HashMap<String, Integer> ProductsToBeOrdered;

	/**
	 * constructor
	 * 
	 * @param name
	 * @param city
	 */
	public Warehouse(String name, String city) {
		ID++;
		this.Name = name;
		this.setId(ID);
		this.setCity(city);
		this.Admin = null;
		this.Stores = new ArrayList<Store>();
		Inventory = new Inventory();
		this.ProductsToBeOrdered = new HashMap<>();
		this.AlertMessage = "";
		System.out.println("---------------------");
		System.out.println("Warehouse Created");
		System.out.println(this.toString());

	}

	public String toString() {
		return "Id: " + this.Id + " , Name: " + this.getName() + " , City: " + this.getCity();
	}

	/**
	 * get warehouse detaisls
	 */
	public void GetWarehouseDetails() {
		System.out.println("------------------------------");
		System.out.println(this.toString());
	}

	/**
	 * get admin details
	 */
	public void GetAdminDetails() {
		System.out.println("------------------------------");
		System.out.println(this.getAdmin().toString());
	}

	/**
	 * get city of warehouse
	 */
	public String getCity(String City) {
		String C = null;
		for (String city : Cities) {
			if (city.equalsIgnoreCase(City)) {
				System.out.println(city);
				C = city;
				break;
			}
		}
		return C;
	}

	/**
	 * get distance of warehouse from city
	 */
	public int getDistance(String City) {
		int D = 0;
		for (int i = 0; i < Cities.length; i++) {
			if (Cities[i].equalsIgnoreCase(City)) {
				D = i;
				break;
			}
		}
		return D;
	}

	/**
	 * get alert messafge
	 * 
	 * @return
	 */
	public String getAlertMessage() {
		return AlertMessage;
	}

	/**
	 * set alert message
	 * 
	 * @param alertMessage
	 */
	public void setAlertMessage(String alertMessage) {
		AlertMessage += alertMessage;
	}

	/**
	 * get inventory
	 * 
	 * @return
	 */
	public Inventory getInventory() {
		return Inventory;
	}

	/**
	 * set inventory
	 * 
	 * @param inventory
	 */
	public void setInventory(Inventory inventory) {
		Inventory = inventory;
	}

	/**
	 * get name
	 * 
	 * @return
	 */
	public String getName() {
		return Name;
	}

	/**
	 * get id
	 * 
	 * @return
	 */
	public int getId() {
		return Id;
	}

	/**
	 * get city
	 * 
	 * @return
	 */
	public String getCity() {
		return City;
	}

	/**
	 * get admin
	 * 
	 * @return
	 */
	public WarehouseAdmin getAdmin() {
		return Admin;
	}

	/**
	 * get stores associated with warehouse
	 * 
	 * @return
	 */
	public ArrayList<Store> getStores() {
		return Stores;
	}

	/**
	 * set admin
	 * 
	 * @param W
	 */
	public void setAdmin(WarehouseAdmin W) {
		this.Admin = W;
	}

	/**
	 * set id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.Id = id;
	}

	/**
	 * set city
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.City = city;
	}

	/**
	 * add store to warehouse
	 * 
	 * @param S
	 */
	public void AddStore(Store S) {
		Stores.add(S);
	}

	/**
	 * get products to be ordered
	 * 
	 * @return
	 */
	public HashMap<String, Integer> getProductsToBeOrdered() {
		return ProductsToBeOrdered;
	}

	/**
	 * set warehouses
	 * 
	 * @param Warehouses
	 */
	public void setWarehouses(ArrayList<Warehouse> Warehouses) {
		this.Warehouses = new ArrayList<Warehouse>(Warehouses);
	}

	/**
	 * search store
	 * 
	 * @param StoreName
	 * @return
	 */
	public Store SearchStore(String StoreName) {
		for (int i = 0; i < Stores.size(); i++) {
			if (Stores.get(i).getName().equalsIgnoreCase(StoreName))
				return Stores.get(i);
		}
		return null;
	}

	/**
	 * Add products to be ordered
	 * 
	 * @param Key
	 * @param Value
	 */
	public void AddProductInproductsToBeOrdered(String Key, int Value) {
		if (ProductsToBeOrdered.containsKey(Key)) {
			Value += ProductsToBeOrdered.get(Key);
			ProductsToBeOrdered.put(Key, Value);
		} else {
			ProductsToBeOrdered.put(Key, Value);
		}
	}

	/**
	 * check products in other warehouses
	 * 
	 * @param StoreName
	 * @param ProdName
	 * @param ProdQuantity
	 */
	public void CheckProductInOtherWarehouse(String StoreName, String ProdName, int ProdQuantity) {
		for (int i = 0; i < Warehouses.size(); i++) {
			Warehouse NextWarehouse = Warehouses.get(i);
			Product ProdFound = NextWarehouse.getInventory().CheckInProducts(ProdName);
			if (ProdFound != null && ProdFound.getQuantity() >= ProdQuantity) {
				System.out.println("Yes");
				ProdFound.setQuantity(ProdFound.getQuantity() - ProdQuantity);
				Store StoreFound = SearchStore(StoreName);
				Product StoreProd = StoreFound.getInventory().CheckInProducts(ProdName);
				System.out
						.println(ProdFound.getProdName() + " " + ProdFound.getQuantity() + " " + StoreFound.getName());
				StoreProd.setQuantity(StoreProd.getQuantity() + ProdQuantity);
			}
		}
	}

	/**
	 * deliver the order
	 */
	public void DeliverTheOrder() {
		for (Entry<String, Integer> Prod : ProductsToBeOrdered.entrySet()) {
			String StoreName = Prod.getKey().split("_")[0];
			String ProdName = Prod.getKey().split("_")[1];
			int ProdQuantity = Prod.getValue();
			Product ProdFound = Inventory.CheckInProducts(ProdName);
			if (ProdFound == null || ProdFound.getQuantity() < ProdQuantity) {
				CheckProductInOtherWarehouse(StoreName, ProdName, ProdQuantity);
			} else {
				ProdFound.setQuantity(ProdFound.getQuantity() - ProdQuantity);
				Store StoreFound = SearchStore(StoreName);
				Product StoreProd = StoreFound.getInventory().CheckInProducts(ProdName);
				StoreProd.setQuantity(StoreProd.getQuantity() + ProdQuantity);
			}
		}
		ProductsToBeOrdered.clear();
		// this.AlertMessage="";
	}

}
