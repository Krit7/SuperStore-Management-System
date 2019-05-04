package Back_End;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.stage.Stage;

/**
 * Store admin class
 * 
 * @author
 *
 */
public class StoreAdmin extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String Admin = "SA";
	private transient ArrayList<Stage> Stages;
	public transient Category[] c;
	public transient int flag = 0;

	/**
	 * constructor
	 */
	public StoreAdmin() {
		super(Admin);
		Stages = new ArrayList<>();
		c = new Category[3];
	}

	/**
	 * logout function
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
