package Back_End;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.stage.Stage;

/**
 * warehouse class
 * 
 * @author
 *
 */
public class WarehouseAdmin extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * warehouse string
	 */
	private static final String Admin = "WA";
	private transient ArrayList<Stage> Stages;
	public transient Category[] c;
	public transient int flag = 0;

	public int Admin_no;

	/**
	 * constructor
	 */
	public WarehouseAdmin() {
		super(Admin);
		Stages = new ArrayList<>();
		c = new Category[3];
	}

	/**
	 * logout
	 */
	public void logout() {
		for (int i = 0; i < Stages.size(); i++) {
			if (Stages.get(i) != null) {
				Stages.get(i).close();
			}
		}
	}

	public ArrayList<Stage> getStages() {
		return Stages;
	}

	public void setStages(ArrayList<Stage> stages) {
		Stages = stages;
	}

}
