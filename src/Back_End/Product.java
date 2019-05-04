package Back_End;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Product class
 * 
 * @author
 *
 */
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * name
	 */
	private String ProdName;
	/**
	 * path in inventory
	 */
	private ArrayList<String> Path;
	/**
	 * product id
	 */
	private String ProdId;
	/**
	 * price
	 */
	private double Price;
	/**
	 * quantity avau\ilable
	 */
	private double Quantity;
	/**
	 * popularity of products
	 */
	private double Popularity;
	/**
	 * demand of product per quarter
	 */
	private int K;
	/**
	 * fixed cost
	 */
	private int D;
	/**
	 * carrying cost per unit per quarter
	 */
	private int H;

	/**
	 * constructor
	 * 
	 * @param ProdName
	 * @param Price
	 * @param Quantity
	 */
	public Product(String ProdName, double Price, double Quantity) {
		this.setProdId();
		this.setProdName(ProdName);
		this.setPrice(Price);
		this.setQuantity(Quantity);
		this.setPopularity();
		this.setK((int) (Math.random() * 100));
		this.setD((int) (Math.random() * 100));
		this.H = (int) (Math.random() * 100);
	}

	/**
	 * get product name
	 * 
	 * @return
	 */
	public String getProdName() {
		return ProdName;
	}

	/**
	 * set product name
	 * 
	 * @param prodName
	 */
	public void setProdName(String prodName) {
		this.ProdName = prodName;
	}

	/**
	 * get product id
	 * 
	 * @return
	 */
	public String getProdId() {
		return ProdId;
	}

	/**
	 * set product id
	 */
	public void setProdId() {
		int x = (int) (Math.random() * 1234);
		this.ProdId = "#P" + x;
	}

	/**
	 * get price
	 * 
	 * @return
	 */
	public double getPrice() {
		return Price;
	}

	/**
	 * set price
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.Price = price;
	}

	/**
	 * get path arraylist
	 * 
	 * @return
	 */
	public ArrayList<String> getPath() {
		return Path;
	}

	/**
	 * set path arraylist
	 * 
	 * @param path
	 */
	public void setPath(ArrayList<String> path) {
		this.Path = path;
	}

	/**
	 * get quantity
	 * 
	 * @return
	 */
	public double getQuantity() {
		return Quantity;
	}

	/**
	 * set quantity
	 * 
	 * @param quantity
	 */
	public void setQuantity(double quantity) {
		Quantity = quantity;
	}

	/**
	 * get popularity
	 * 
	 * @return
	 */
	public double getPopularity() {
		return Popularity;
	}

	/**
	 * get k
	 * 
	 * @return
	 */
	public int getK() {
		return K;
	}

	/**
	 * set k
	 * 
	 * @param k
	 */
	public void setK(int k) {
		K = k;
	}

	/**
	 * get D
	 * 
	 * @return
	 */
	public int getD() {
		return D;
	}

	/**
	 * set D
	 * 
	 * @param d
	 */
	public void setD(int d) {
		D = d;
	}

	/**
	 * get H
	 * 
	 * @return
	 */
	public int getH() {
		return H;
	}

	/**
	 * set popularity
	 */
	public void setPopularity() {
		double x = Math.round((Math.random() * 6) * 100.0) / 100.0;
		if (x < 1)
			x++;
		else if (x > 5)
			x = 5.00;
		this.Popularity = x;
	}

	public int EOQ() {
		return (int) Math.round(Math.sqrt((2 * D * K) / H));

	}

	public String toString() {
		String Path = "";
		for (int j = 0; j < getPath().size(); j++) {
			if (j != getPath().size() - 1)
				Path += getPath().get(j) + "-->";
			else
				Path += getPath().get(j);
		}
		String Details = "Product Name : - " + getProdName() + "\n" + "Product Id : - " + getProdId() + "\n"
				+ "Product Popularity : - " + getPopularity() + "\n" + "Product Quantity : - " + getQuantity() + "\n"
				+ "Product Price : - " + getPrice() + "\n" + "Product Path : -";
		return Details + " " + Path;
	}

	/**
	 * display product details
	 */
	public void DisplayProductDetails() {
		System.out.println(toString());
	}

}
