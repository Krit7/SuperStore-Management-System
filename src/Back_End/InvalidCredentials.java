package Back_End;

public class InvalidCredentials extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidCredentials() {
		super("Invalid username or password");
	}

}
