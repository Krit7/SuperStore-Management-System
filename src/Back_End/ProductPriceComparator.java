package Back_End;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * comparator for price of roduct
 * 
 * @author
 *
 */
public class ProductPriceComparator implements Comparator<Product> {

	@Override
	public int compare(Product o1, Product o2) {
		if (o1.getPrice() - o2.getPrice() > 0)
			return 1;
		else if (o1.getPrice() - o2.getPrice() < 0)
			return -1;
		return 0;
	}

	/**
	 * 
	 * sort by price
	 * 
	 * @param Products
	 * @return
	 */
	public ArrayList<Product> SortByPrice(ArrayList<Product> Products) {
		ArrayList<Product> left = new ArrayList<Product>();
		ArrayList<Product> right = new ArrayList<Product>();
		int center;

		if (Products.size() == 1) {
			return Products;
		} else {
			center = Products.size() / 2;
			for (int i = 0; i < center; i++) {
				left.add(Products.get(i));
			}

			for (int i = center; i < Products.size(); i++) {
				right.add(Products.get(i));
			}

			left = SortByPrice(left);
			right = SortByPrice(right);

			Merge(left, right, Products);
		}
		return Products;
	}

	/**
	 * merge
	 * 
	 * @param left
	 * @param right
	 * @param whole
	 */
	private void Merge(ArrayList<Product> left, ArrayList<Product> right, ArrayList<Product> whole) {
		int leftIndex = 0;
		int rightIndex = 0;
		int wholeIndex = 0;

		while (leftIndex < left.size() && rightIndex < right.size()) {
			if (compare(left.get(leftIndex), right.get(rightIndex)) < 0) {
				whole.set(wholeIndex, left.get(leftIndex));
				leftIndex++;
			} else {
				whole.set(wholeIndex, right.get(rightIndex));
				rightIndex++;
			}
			wholeIndex++;
		}

		ArrayList<Product> rest;
		int restIndex;
		if (leftIndex >= left.size()) {
			rest = right;
			restIndex = rightIndex;
		} else {
			rest = left;
			restIndex = leftIndex;
		}

		for (int i = restIndex; i < rest.size(); i++) {
			whole.set(wholeIndex, rest.get(i));
			wholeIndex++;
		}
	}

}
