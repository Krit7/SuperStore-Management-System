package Back_End;

import java.io.Serializable;

import application.Users_Page_Controller;

/**
 * User abstract class
 * 
 * @author
 *
 */
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String User;
	private String Id;
	private int Password;

	/**
	 * constructor
	 * 
	 * @param User
	 */
	public User(String User) {
		setUser(User);
		setPassword();
		setLoginId(User);
		System.out.println("------------------------------");
		System.out.println(this.toString());
	}

	// ==================================================
	// Setter Getter Functions
	// ==================================================
	public String getLoginId() {
		return Id;
	}

	public int getPassword() {
		return Password;
	}

	/**
	 * get user
	 * 
	 * @return
	 */
	public String getUser() {
		return User;
	}

	/**
	 * set login id
	 * 
	 * @param User
	 */
	public void setLoginId(String User) {
		if (this.User.equals("Super User")) {
			this.Id = "SU95";
		} else {
			String S = Integer.toString(this.getPassword());
			S = S.substring(S.length() - 3);
			this.Id = User + S;
		}
	}

	/**
	 * set user
	 * 
	 * @param User
	 */
	public void setUser(String User) {
		if (User.equalsIgnoreCase("SA"))
			this.User = "Store Admin";
		else if (User.equalsIgnoreCase("WA"))
			this.User = "Warehouse Admin";
		else
			this.User = "Super User";
	}

	/**
	 * set password
	 */
	public void setPassword() {
		if (this.User.equals("Super User")) {
			this.Password = 518187695;
		} else {
			this.Password = GeneratePassword();
		}
	}

	/**
	 * generate password for user
	 * 
	 * @return
	 */
	public int GeneratePassword() {
		return (int) (Math.random() * Integer.MAX_VALUE);
	}

	public String toString() {
		return "User = " + this.getUser() + "\n" + "Login Id = " + this.getLoginId() + "\n" + "Password = "
				+ this.getPassword();
	}

	// ==================================================
	// External Setter Functions
	// ==================================================
	public void setLoginId(Object O) {
		this.Id = O.toString();
	}

	/**
	 * set user
	 * 
	 * @param O
	 */
	public void setUser(Object O) {
		this.User = O.toString();
	}

	/**
	 * set pssword of user
	 * 
	 * @param Password
	 */
	public void setPassword(int Password) {
		this.Password = Password;
	}

	// ==================================================
	// Working Functions
	// ==================================================
	/**
	 * validate method
	 * 
	 * @param Id
	 * @param Password
	 * @return
	 * @throws InvalidCredentials 
	 */
	public boolean validate(String Id, String Password) throws InvalidCredentials {// only for super user
		if (this.Id.equalsIgnoreCase(Id) && this.Password == Integer.parseInt(Password)) {
			return true;
		}
		throw (new InvalidCredentials());
	}

	/**
	 * validate user durib=ng login
	 * 
	 * @param Id
	 * @param Password
	 * @return
	 * @throws InvalidCredentials
	 */
	public static boolean login_time_validation(String Id, String Password) throws InvalidCredentials {
		for (int i = 0; i < Users_Page_Controller.StoreAdmins.size(); i++) {
			if (Users_Page_Controller.StoreAdmins.get(i).getLoginId().equalsIgnoreCase(Id)
					&& Users_Page_Controller.StoreAdmins.get(i).getPassword() == Integer.parseInt(Password)) {
				return true;
			}
		}
		for (int i = 0; i < Users_Page_Controller.WarehouseAdmins.size(); i++) {
			if (Users_Page_Controller.WarehouseAdmins.get(i).getLoginId().equalsIgnoreCase(Id)
					&& Users_Page_Controller.WarehouseAdmins.get(i).getPassword() == Integer.parseInt(Password)) {
				return true;
			}
		}
		throw (new InvalidCredentials());
	}

}
