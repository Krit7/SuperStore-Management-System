package Back_End;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Inventory class
 * 
 * @author
 *
 */
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * previous category
	 */
	private Category Prev;
	/**
	 * current category
	 */
	private Category Curr;
	/**
	 * next cateory
	 */
	private Category Next;
	/**
	 * root category
	 */
	private Category Root;
	/**
	 * array list of categories
	 */
	private ArrayList<Category> Categories;
	/**
	 * array list of products
	 */
	private ArrayList<Product> Products;
	/**
	 * array list of all categories
	 */
	private ArrayList<Category> AllCategories;

	/**
	 * constructor
	 */
	public Inventory() {
		this.setCurr(null);
		this.setRoot(null);
		this.Categories = new ArrayList<Category>();
		this.Products = new ArrayList<Product>();
		this.AllCategories = new ArrayList<Category>();
	}

	// ===============================================
	// SETTER GETTERS
	// ===============================================
	/**
	 * get previous category
	 * 
	 * @return
	 */
	public Category getPrev() {
		return Prev;
	}

	public void setPrev(Category prev) {
		Prev = prev;
	}

	/**
	 * get current category
	 * 
	 * @return
	 */
	public Category getCurr() {
		return Curr;
	}

	/**
	 * set current category
	 * 
	 * @param curr
	 */
	public void setCurr(Category curr) {
		Curr = curr;
	}

	/**
	 * get next category
	 * 
	 * @return
	 */

	public Category getNext() {
		return Next;
	}

	/**
	 * set next category
	 * 
	 * @param next
	 */
	public void setNext(Category next) {
		Next = next;
	}

	/**
	 * get root caegory
	 * 
	 * @return
	 */
	public Category getRoot() {
		return Root;
	}

	/**
	 * set root category
	 * 
	 * @param root
	 */
	public void setRoot(Category root) {
		Root = root;
	}

	/**
	 * get categories arraylist
	 * 
	 * @return
	 */
	public ArrayList<Category> getCategories() {
		return Categories;
	}

	/**
	 * get products arraylist
	 * 
	 * @return
	 */
	public ArrayList<Product> getProducts() {
		return Products;
	}

	/**
	 * get all categories array list
	 * 
	 * @return
	 */
	public ArrayList<Category> getAllCategories() {
		return AllCategories;
	}

	// ===============================================
	// PRODUCT FUNCTIONS
	// ===============================================
	/**
	 * add product in inventory
	 * 
	 * @param Path
	 * @param ProdName
	 * @param ProdPrice
	 * @param ProdQuantity
	 * @throws ProductAlreadyExistExcetion
	 */
	public void AddProduct(String Path[], String ProdName, double ProdPrice, double ProdQuantity)
			throws ProductAlreadyExistExcetion {
		Category Result = CheckInCategories(Path[0]);
		if (Result != null)
			Curr = Result;
		else {
			Curr = new Category(Path[0]);
			getCategories().add(Curr);
			getAllCategories().add(Curr);
		}
		for (int i = 1; i < Path.length; i++) {
			Category Checked = Curr.CheckInSubCategory(Path[i]);
			if (Checked != null) {
				Prev = Curr;
				Curr = Checked;
			} else {
				Prev = Curr;
				Curr = new Category(Path[i]);
				ArrayList<String> NewSubCatPath = new ArrayList<String>(Prev.getPath());
				NewSubCatPath.add(Prev.getCatName());
				Curr.setPath(NewSubCatPath);
				Prev.getSubCategory().add(Curr);
				getAllCategories().add(Curr);
			}
		}
		Product Checked = CheckInProducts(ProdName);
		if (Checked != null) {
			// Exception Product Already Exist
			System.out.println("Product Already Exist");
			throw (new ProductAlreadyExistExcetion());
		} else {
			Product NewProduct = new Product(ProdName, ProdPrice, ProdQuantity);
			ArrayList<String> NewProductPath = new ArrayList<String>(Curr.getPath());
			NewProductPath.add(Curr.getCatName());
			NewProduct.setPath(NewProductPath);
			Curr.getProducts().add(NewProduct);
			getProducts().add(NewProduct);
		}
	}

	/**
	 * delete product from inventory
	 * 
	 * @param ProdName
	 * @param Path
	 * @return
	 * @throws ProductDoesntExistExcetion
	 * @throws CategoryDoesntExists 
	 */
	public int DeleteProduct(String ProdName, String Path[]) throws ProductDoesntExistExcetion, CategoryDoesntExists {
		this.DeleteProductFromCategory(ProdName, Path);
		Product Checked = CheckInProducts(ProdName);
		if (Checked == null) {
			// Exception Product Does Not Exist
			throw (new ProductDoesntExistExcetion());
			// return 0;
		} else {
			getProducts().remove(Checked);
			return -1;
		}
	}

	/**
	 * search product in inventory
	 * 
	 * @param ProdName
	 * @return
	 * @throws ProductDoesntExistExcetion
	 */
	public ArrayList<Product> SearchProduct(String ProdName) throws ProductDoesntExistExcetion {

		ArrayList<Product> SearchedProducts = new ArrayList<Product>();
		for (int i = 0; i < getProducts().size(); i++) {
			if (getProducts().get(i).getProdName().contains(ProdName))
				SearchedProducts.add(getProducts().get(i));
		}
		if (SearchedProducts.size() != 0) {
			ShowSearchedProducts(SearchedProducts);
		} else {
			// Exception Product Does Not Exist
			throw (new ProductDoesntExistExcetion());
		}
		return SearchedProducts;
	}

	/**
	 * update product price
	 * 
	 * @param ProdName
	 * @param ProdPrice
	 * @throws ProductDoesntExistExcetion 
	 */
	public void UpdateProductPrice(String ProdName, double ProdPrice) throws ProductDoesntExistExcetion {
		Product Checked = CheckInProducts(ProdName);
		if (Checked == null) {
			// Exception Product Does Not Exist
			throw (new ProductDoesntExistExcetion());
		} else {
			Checked.setPrice(ProdPrice);
		}
	}

	/**
	 * update product quantity
	 * 
	 * @param ProdName
	 * @param ProdQuantity
	 * @throws ProductDoesntExistExcetion 
	 */
	public void UpdateProductQuantity(String ProdName, double ProdQuantity) throws ProductDoesntExistExcetion {
		Product Checked = CheckInProducts(ProdName);
		if (Checked == null) {
			// Exception Product Does Not Exist
			throw (new ProductDoesntExistExcetion());
		} else {
			Checked.setQuantity(ProdQuantity);
		}
	}

	/**
	 * delete ctegories and all products
	 * 
	 * @param c
	 * @param CatName
	 */
	public void DeleteCategoryProducts(int c, String CatName) {
		ArrayList<Product> ToDelete = new ArrayList<Product>();
		for (int i = 0; i < getProducts().size(); i++) {
			if (getProducts().get(i).getPath().size() > c) {
				if (getProducts().get(i).getPath().get(c).equalsIgnoreCase(CatName))
					ToDelete.add(getProducts().get(i));
			}
		}
		for (int i = 0; i < ToDelete.size(); i++) {
			getProducts().remove(ToDelete.get(i));
		}
	}

	/**
	 * sort product by name
	 * 
	 * @param SearchedProducts
	 * @return
	 */
	public ArrayList<Product> SortProductsByName(ArrayList<Product> SearchedProducts) {
		ProductNameComparator CompareName = new ProductNameComparator();
		ArrayList<Product> SortedProducts = CompareName.SortByName(SearchedProducts);
		// ShowSearchedProducts(SortedProducts);
		return SortedProducts;
	}

	/**
	 * sort product by price
	 * 
	 * @param SearchedProducts
	 * @return
	 */
	public ArrayList<Product> SortProductsByPrice(ArrayList<Product> SearchedProducts) {
		ProductPriceComparator ComparePrice = new ProductPriceComparator();
		ArrayList<Product> SortedProducts = ComparePrice.SortByPrice(SearchedProducts);
		// ShowSearchedProducts(SortedProducts);
		return SortedProducts;
	}

	/**
	 * sort product by popularity
	 * 
	 * @param SearchedProducts
	 * @return
	 */
	public ArrayList<Product> SortProductsByPopularity(ArrayList<Product> SearchedProducts) {
		ProductPopularityComparator ComparePopularity = new ProductPopularityComparator();
		ArrayList<Product> SortedProducts = ComparePopularity.SortByPopularity(SearchedProducts);
		// ShowSearchedProducts(SortedProducts);
		return SortedProducts;
	}

	/**
	 * check products if exists
	 * 
	 * @param ProdName
	 * @return
	 */
	public Product CheckInProducts(String ProdName) {
		for (int i = 0; i < getProducts().size(); i++) {
			if (getProducts().get(i).getProdName().equalsIgnoreCase(ProdName))
				return getProducts().get(i);
		}
		return null;
	}

	/**
	 * show searches products
	 * 
	 * @param SearchedProducts
	 */
	public void ShowSearchedProducts(ArrayList<Product> SearchedProducts) {
		System.out.println("----Products Found----");
		for (int i = 0; i < SearchedProducts.size(); i++) {
			System.out.println(SearchedProducts.get(i).getProdName());
		}
	}

	/**
	 * delete products from category
	 * 
	 * @param ProdName
	 * @param Path
	 * @throws CategoryDoesntExists 
	 */
	public void DeleteProductFromCategory(String ProdName, String Path[]) throws CategoryDoesntExists {
		Category Result = CheckInCategories(Path[0]);
		if (Result == null) {
			// Throws Exception Category Not Found
			throw(new CategoryDoesntExists());
		} else {
			Curr = Result;
			for (int i = 1; i < Path.length; i++) {
				Category Checked = Curr.CheckInSubCategory(Path[i]);
				if (Checked == null) {
					// Throws Exception Category Not Found
					throw(new CategoryDoesntExists());
				} else {
					Prev = Curr;
					Curr = Checked;
				}
			}
			int s = Curr.getProducts().size();
			int c = 0;
			for (int i = 0; i < Curr.getProducts().size(); i++) {
				if (Curr.getProducts().get(i).getProdName().equalsIgnoreCase(ProdName)) {
					Curr.getProducts().remove(i);
					break;
				}
				c++;
			}
			if (c == s)
				// Throws Exception Category Not Found
				throw(new CategoryDoesntExists());
		}
	}

	// ===============================================
	// CATEGORY FUNCTIONS
	// ===============================================
	/**
	 * adding a category
	 * 
	 * @param Path
	 */
	public void AddCategory(String Path[]) {
		Category Result = CheckInCategories(Path[0]);
		if (Result != null)
			Curr = Result;
		else {
			Curr = new Category(Path[0]);
			getCategories().add(Curr);
			getAllCategories().add(Curr);
		}
		for (int i = 1; i < Path.length; i++) {
			Category Checked = Curr.CheckInSubCategory(Path[i]);
			if (Checked != null) {
				Prev = Curr;
				Curr = Checked;
			} else {
				Prev = Curr;
				Curr = new Category(Path[i]);
				Curr.getPath().add(Prev.getCatName());
				Prev.getSubCategory().add(Curr);
				getAllCategories().add(Curr);
			}
		}
	}

	/**
	 * deleteing a category
	 * 
	 * @param Path
	 * @throws CategoryDoesntExists
	 */
	public void DeleteCategory(String Path[]) throws CategoryDoesntExists {
		DeleteCategoryFromAllCategories(Path[Path.length - 1]);
		Category Result = CheckInCategories(Path[0]);
		if (Result != null)
			if (Path.length == 1) {
				getCategories().remove(Result);
				DeleteCategoryProducts(0, Path[0]);
			} else {
				Curr = Result;
			}
		else {
			// Throws Exception Category Does Not Exist
			throw(new CategoryDoesntExists());
		}
		for (int i = 1; i < Path.length; i++) {
			Category Checked = Curr.CheckInSubCategory(Path[i]);
			if (Checked != null) {
				if (i != Path.length - 1) {
					Prev = Curr;
					Curr = Checked;
				} else {
					Curr.getSubCategory().remove(Checked);
					DeleteCategoryProducts(i, Path[i]);
				}
			} else {
				// Throws Exception Category Does Not Exist
				throw(new CategoryDoesntExists());
			}
		}
	}

	/**
	 * searching a categpry
	 * 
	 * @param CatName
	 * @return
	 * @throws CategoryDoesntExists 
	 */
	public ArrayList<Category> SearchCategory(String CatName) throws CategoryDoesntExists {
		ArrayList<Category> SearchedCategories = new ArrayList<Category>();
		for (int i = 0; i < getAllCategories().size(); i++) {
			if (getAllCategories().get(i).getCatName().contains(CatName))
				SearchedCategories.add(getAllCategories().get(i));
		}
		if (SearchedCategories.size() != 0)
			DisplaySearchedCategories(SearchedCategories);
		else {
			// Exception No Categories Found
			throw(new CategoryDoesntExists());
		}
		return SearchedCategories;
	}

	/**
	 * check in category if exists
	 * 
	 * @param CatName
	 * @return
	 */
	public Category CheckInCategories(String CatName) {
		for (int i = 0; i < getCategories().size(); i++) {
			if (getCategories().get(i).getCatName().equalsIgnoreCase(CatName))
				return getCategories().get(i);
		}
		return null;
	}

	/**
	 * check in all categories
	 * 
	 * @param CatName
	 * @return
	 */
	public Category CheckInAllCategories(String CatName) {
		for (int i = 0; i < getAllCategories().size(); i++) {
			if (getAllCategories().get(i).getCatName().equalsIgnoreCase(CatName))
				return getAllCategories().get(i);
		}
		return null;
	}

	/**
	 * delet category from all category
	 * 
	 * @param CatName
	 */
	public void DeleteCategoryFromAllCategories(String CatName) {
		for (int i = 0; i < getAllCategories().size(); i++) {
			if (getAllCategories().get(i).getCatName().equalsIgnoreCase(CatName))
				getAllCategories().remove(i);
		}

	}

	/**
	 * display searched categories
	 * 
	 * @param SearchedCategories
	 */
	public void DisplaySearchedCategories(ArrayList<Category> SearchedCategories) {
		System.out.println("----Categories Found----");
		for (int i = 0; i < SearchedCategories.size(); i++) {
			System.out.println(SearchedCategories.get(i).getCatName());
		}
	}

}
