package Back_End;

public class ProductAlreadyExistExcetion extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistExcetion() {
		super("product already exist");
	}
}