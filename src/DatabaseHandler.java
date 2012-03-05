import java.sql.*;

/**
 * Handles all interactions with the GangWeb database.
 */
public class DatabaseHandler {
	private Connection connection = null;
	
	/**
	 * Creates an instance of DatabaseHandler with an initialized connection.
	 */
	public DatabaseHandler(){
		this.connection = Connect();
	}
	
	/**
	 * Creates a connection with the GangWeb database.
	 * @return An established connection.
	 */
	private Connection Connect(){
		String url = "jdbc:mysql://gangweb.db.3018661.hostedresource.com:3306/gangweb";
		String driver = "com.mysql.jdbc.Driver";
		String username = "gangweb";
		String password = "PassworD1";
		
		try{
			Class.forName(driver);
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("GangWeb> Database connection established.");
		}
		catch(Exception e){
			System.out.println("GangWeb> ERROR: Could not establish database connection.");
		}
		
		return(connection);
	}

	/**
	 * Terminates the connection with the GangWeb database.
	 */
	public void Disconnect(){
		try{
			this.connection.close();
			System.out.println("GangWeb> Database connection terminated.");
		}
		catch(Exception e){
			System.out.println("GangWeb> ERROR: Could not terminate database connection.");
		}
	}
	
	/**
	 * Executes an SQL query
	 * @param queryString The SQL query to be executed
	 */
	public void Query(String queryString){
		try{
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(queryString);
			System.out.println("GangWeb> " + queryString);
		}
		catch(Exception e){
			System.out.println("GangWeb> ERROR: Query could not be executed. " + queryString);
		}
	}
	
	/**
	 * Adds a new individual to the database
	 * @param firstName First Name
	 * @param middleName Middle Name
	 * @param lastName Last Name
	 * @param age Age
	 * @param weight Weight
	 * @param height Height
	 * @param gender Gender
	 * @param race Race
	 * @param hairColor Hair Color
	 * @param eyeColor Eye Color
	 */
	public void AddElement(String firstName, String middleName, String lastName, int age, int weight, int height, String gender, String race, String hairColor, String eyeColor){
		String queryString =  "INSERT INTO people " +
				"(FirstName, MiddleName, LastName, Age, Weight, Height, Gender, Race, HairColor, EyeColor) VALUES('" +
				firstName + "', '" + middleName + "', '" + lastName + "', " + age + ", " + weight + ", " + height + ", '" + gender + "', '" + race + "', '" + hairColor + "', '" + eyeColor + "')";
		
		this.Query(queryString);
		
	}
	
	/**
	 * Removes the specified individual from the database
	 * @param id ID of element to be removed
	 */
	public void RemoveElement(int id){
		String queryString = "DELETE FROM people WHERE id=" + id;
		this.Query(queryString);
	}
}
