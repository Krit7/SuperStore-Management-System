package Back_End;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * category class
 * 
 * @author
 *
 */
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * category name
	 */
	private String CatName;
	/**
	 * path
	 */
	private ArrayList<String> Path;
	/**
	 * sub category arraylist
	 */
	private ArrayList<Category> SubCategory;
	/**
	 * products array list
	 */
	private ArrayList<Product> Products;

	/**
	 * constructor
	 * 
	 * @param CatName
	 */
	public Category(String CatName) {
		this.setCatName(CatName);
		this.Path = new ArrayList<String>();
		this.SubCategory = new ArrayList<Category>();
		this.Products = new ArrayList<Product>();
	}

	/**
	 * get the category name
	 * 
	 * @return
	 */
	public String getCatName() {
		return CatName;
	}

	/**
	 * set the caegory name
	 * 
	 * @param catName
	 */
	public void setCatName(String catName) {
		this.CatName = catName;
	}

	/**
	 * get the category path
	 * 
	 * @return
	 */
	public ArrayList<String> getPath() {
		return Path;
	}

	/**
	 * get the category path
	 * 
	 * @return
	 */
	public ArrayList<Category> getSubCategory() {
		return SubCategory;
	}

	/**
	 * get product arraylist
	 * 
	 * @return
	 */
	public ArrayList<Product> getProducts() {
		return Products;
	}

	/**
	 * set path of the product
	 * 
	 * @param path
	 */
	public void setPath(ArrayList<String> path) {
		this.Path = path;
	}

	public String toString() {
		String Path = "";
		for (int j = 0; j < getPath().size(); j++) {
			if (j != getPath().size() - 1)
				Path += getPath().get(j) + "-->";
			else
				Path += getPath().get(j);
		}
		String Details = "Name : - " + getCatName() + "\n" + "Category Path : -";
		return Details + " " + Path;
	}

	/**
	 * category details
	 */
	public void GetCategoryDetails() {
		int PoC = this.CheckForProductOrCategory();
		if (PoC == 0)
			this.getProductsInCategory();
		else if (PoC == 1)
			this.getSubCategoriesInCategory();
		else
			System.out.println("Empty");
	}

	/**
	 * products in a specific category
	 */
	public void getProductsInCategory() {
		for (int i = 0; i < getProducts().size(); i++) {
			System.out.println(getProducts().get(i).getProdName());
		}
	}

	/**
	 * categories in a specific category
	 */
	public void getSubCategoriesInCategory() {
		for (int i = 0; i < getSubCategory().size(); i++) {
			System.out.println(getSubCategory().get(i).getCatName());
		}
	}

	/**
	 * check if category present in a subcategory
	 * 
	 * @param CatName
	 * @return
	 */
	public Category CheckInSubCategory(String CatName) {
		for (int i = 0; i < getSubCategory().size(); i++) {
			if (getSubCategory().get(i).getCatName().equalsIgnoreCase(CatName))
				return getSubCategory().get(i);
		}
		return null;
	}

	/**
	 * check if prodct or category
	 * 
	 * @return
	 */

	public int CheckForProductOrCategory() {
		if (getSubCategory().size() == 0)
			return 0;
		return 1;
	}

	/**
	 * search product by name
	 * 
	 * @param ProdName
	 * @return
	 */
	public Product SearchProductbyName(String ProdName) {

		for (int i = 0; i < getProducts().size(); i++) {
			if (getProducts().get(i).getProdName().equals(ProdName))
				return getProducts().get(i);
		}
		return null;
	}

}
