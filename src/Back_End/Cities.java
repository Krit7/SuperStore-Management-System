package Back_End;

/**
 * interface cities
 * 
 * @author
 *
 */
public interface Cities {
	public static final String Cities[] = { "New Delhi", "Banglore", "Mumbai", "Kolkata", "Pune", "Hyderabad",
			"Chennai", "Ahmedabad", "Visakhapatnam", "Surat", "Jaipur", "Kanpur", "Lucknow", "Nagpur", "Ghaziabad" };
	public static final int Distances[] = { 0, 460, 580, 610, 520, 390, 849, 547, 980, 780, 460, 600, 749, 250, 25 };

	/**
	 * get city
	 * 
	 * @param City
	 * @return
	 */
	public String getCity(String City);

	/**
	 * get the distance
	 * 
	 * @param City
	 * @return
	 */
	public int getDistance(String City);

}
