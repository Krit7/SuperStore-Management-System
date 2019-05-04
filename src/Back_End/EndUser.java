package Back_End;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.stage.Stage;

/**
 * enduser class
 * 
 * @author
 *
 */
public class EndUser {
	/**
	 * cart arraylist
	 */
	private ArrayList<Product> Cart;
	/**
	 * quantity arraylist
	 */
	private ArrayList<Double> Quantity;
	/**
	 * stores visites by the enduser
	 */
	private Store VisitedStore;
	/**
	 * total bill of enduser
	 */
	private double TotalBill;
	private ArrayList<Stage> Stages;
	public Category[] c1;
	public Category[] c2;
	public int flag1 = 0;
	public int flag2 = 0;

	/**
	 * constructor
	 */
	public EndUser() {
		this.Cart = new ArrayList<Product>();
		this.Quantity = new ArrayList<Double>();
		this.TotalBill = 0;
		setStages(new ArrayList<>());
		c1 = new Category[3];
		c2 = new Category[3];
	}

	public void logout() {
		for (int i = 0; i < Stages.size(); i++) {
			if (Stages.get(i) != null) {
				Stages.get(i).close();
			}
		}
	}

	/**
	 * get the total bill
	 * 
	 * @return
	 */
	public double getTotalBill() {
		return TotalBill;
	}

	/**
	 * set the total bill
	 * 
	 * @param totalBill
	 */
	public void setTotalBill(double totalBill) {
		TotalBill += totalBill;
	}

	/**
	 * get the cart
	 * 
	 * @return
	 */
	public ArrayList<Product> getCart() {
		return Cart;
	}

	/**
	 * get the wquantity arraylist
	 * 
	 * @return
	 */
	public ArrayList<Double> getQuantity() {
		return Quantity;
	}

	/**
	 * get the visited stores list
	 * 
	 * @return
	 */

	public Store getVisitedStore() {
		return VisitedStore;
	}

	/**
	 * set the viisted stores list
	 * 
	 * @param VisitedStore
	 */
	public void setVisitedStore(Store VisitedStore) {
		this.VisitedStore = VisitedStore;
	}

	/**
	 * add product in cart function
	 * 
	 * @param ProdName
	 * @param Quantity
	 * @throws Insufficienrtquantity
	 */
	public void AddProductsToCart(String ProdName, double Quantity) throws Insufficienrtquantity {
		for (int i = 0; i < getCart().size(); i++) {
			if (getCart().get(i).getProdName().equals(ProdName)) {
				if (getVisitedStore().getInventory().getProducts().get(i).getQuantity() < Quantity) {
					// insufficient quantity
					throw (new Insufficienrtquantity());
				} else {
					getQuantity().set(i, getQuantity().get(i) + Quantity);
					return;
				}

			}
		}
		for (int i = 0; i < getVisitedStore().getInventory().getProducts().size(); i++) {
			if (getVisitedStore().getInventory().getProducts().get(i).getProdName().equalsIgnoreCase(ProdName)) {
				Cart.add(getVisitedStore().getInventory().getProducts().get(i));
				if (getVisitedStore().getInventory().getProducts().get(i).getQuantity() < Quantity) {
					// insufficient quantity
					throw (new Insufficienrtquantity());
				} else {
					getQuantity().add(Quantity);
				}
			}
		}
	}

	/**
	 * checkout function
	 * 
	 * @throws Insufficienrtquantity
	 */
	public void CheckOutCart() throws Insufficienrtquantity {

		String Message = "";
		String Key = "";
		int Value;
		for (int i = 0; i < Cart.size(); i++) {
			Product Prod = Cart.get(i);
			if (Prod.getQuantity() >= Quantity.get(i)) {
				if (Prod.getQuantity() - Quantity.get(i) == 0) {
					Message += Prod.getProdName() + " " + Prod.getProdId() + " " + Prod.EOQ() + " "
							+ LocalDate.now().plusDays((int) (Math.random() * 31) + 1) + "\n";
					Key = VisitedStore.getName() + "_" + Prod.getProdName();
					Value = Prod.EOQ();
					VisitedStore.getWarehouse().AddProductInproductsToBeOrdered(Key, Value);
				}
				Prod.setQuantity(Prod.getQuantity() - Quantity.get(i));
				setTotalBill(Prod.getPrice() * Quantity.get(i));
				getVisitedStore().setRevenue(getTotalBill());

			} else {
				// Throw Error
				throw (new Insufficienrtquantity());

			}

		}

		VisitedStore.sendMessageToWarehouse(Message);

	}

	/**
	 * clear cart after checkout
	 */
	public void clearcart() {
		for (int i = 0; i < getCart().size(); i++) {
			getCart().remove(i);
		}
	}

	public ArrayList<Stage> getStages() {
		return Stages;
	}

	public void setStages(ArrayList<Stage> stages) {
		Stages = stages;
	}

}
