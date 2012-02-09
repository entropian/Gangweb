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
	 * @return The established connection.
	 */
	private Connection Connect(){
		String url = "jdbc:mysql://gangweb.db.3018661.hostedresource.com:3306/gangweb";
		String driver = "com.mysql.jdbc.Driver";
		String username = "gangweb";
		String password = "PassworD1";
		
		try{
			Class.forName(driver);
			connection = DriverManager.getConnection(url,username,password);
			System.out.println("Database connection established.");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: Could not establish a database connection.");
		}
		
		return(connection);
	}

	/**
	 * Terminates the connection with the GangWeb database.
	 */
	public void Disconnect(){
		try{
			this.connection.close();
			System.out.println("Database connection terminated.");
		}
		catch(Exception e){
			System.out.println("Error: Could not terminate database connection.");
		}
	}
}
