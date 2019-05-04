package Back_End;

public class ProductDoesntExistExcetion extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductDoesntExistExcetion() {
		super("product doesnt exist");
	}
}