import java.sql.*;
import java.util.Random;

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
		String url = 		"jdbc:mysql://gangweb.db.3018661.hostedresource.com:3306/gangweb?autoReconnect=true";
		String driver = 	"com.mysql.jdbc.Driver";
		String username = 	"gangweb";
		String password = 	"PassworD1";
		
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
	 * @param updateString The SQL query to be executed
	 * @return The results of the SQL query
	 */
	private ResultSet Query(String queryString){
		Statement statement; // A statement in which to run the query
		ResultSet resultSet; // The results of the query
		
		try{
			statement = this.connection.createStatement();
			resultSet = statement.executeQuery(queryString);
			System.out.println("GangWeb> " + queryString);
			return(resultSet);
		}
		catch(Exception e){
			System.out.println("GangWeb> ERROR: Query could not be executed. " + queryString);
			return(null);
		}
	}
	
	/**
	 * Executes an SQL update
	 * @param updateString The SQL update query to be executed
	 */
	private void Update(String updateString){
		Statement statement; // A statement in which to run the update
		
		try{
			statement = this.connection.createStatement();
			statement.executeUpdate(updateString);
			System.out.println("GangWeb> " + updateString);
		}
		catch(Exception e){
			System.out.println("GangWeb> ERROR: Query could not be executed. " + updateString);
		}
	}
	
	/**
	 * Searches the database on the specified criteria. If you do not wish to search for a specified attribute enter null.
	 * @param person Specifies which attributes to search on
	 * @return An array of people who fit the search criteria.
	 */
	public Person[] Search(Person person){
		Person[] people;	// The array of people who fit the search criteria
		String queryString;	// The query to be executed
		ResultSet results;	// The results of the query
		int count = 0;		// The number of elements returned by the query
		
		// Format queryString according to parameters
		queryString = "SELECT * FROM people WHERE";
		if(person.id != null){			queryString += " id = " + person.id + " AND";}
		if(person.firstName != null){	queryString += " FirstName = '" + person.firstName + "' AND";}
		if(person.middleName != null){	queryString += " MiddleName = '" + person.middleName + "' AND";}
		if(person.lastName != null){	queryString += " LastName= '" + person.lastName + "' AND";}
		if(person.age != null){			queryString += " Age = " + person.age + " AND";}
		if(person.weight != null){		queryString += " Weight = " + person.weight + " AND";}
		if(person.height != null){		queryString += " Height = " + person.height + " AND";}
		if(person.gender != null){		queryString += " Gender = '" + person.gender + "' AND";}
		if(person.race != null){		queryString += " Race = '" + person.race + "' AND";}
		if(person.hairColor != null){	queryString += " HairColor = '" + person.hairColor + "' AND";}
		if(person.eyeColor != null){	queryString += " EyeColor = '" + person.eyeColor + "' AND";}
		
		queryString = queryString.substring(0, queryString.length() - 4); // Clip the last 'AND'
		results = this.Query(queryString); // Run the query and store the results
		
		try{
			while(results.next()){count++;} // Determine the number of elements in the ResultSet
			people = new Person[count];
			
			// Convert each result into a Person
			while (results.previous() && count != 0) {
		        Person personResult = new Person();
		        
		        // Get values from ResultSet
		        personResult.id = 			results.getInt("id");
		        personResult.firstName = 	results.getString("FirstName");
		        personResult.middleName =	results.getString("MiddleName");
		        personResult.lastName = 	results.getString("LastName");
		        personResult.age = 			results.getInt("Age");
		        personResult.weight =		results.getInt("Weight");
		        personResult.height = 		results.getInt("Height");
		        personResult.gender = 		results.getString("Gender");
		        personResult.race = 		results.getString("Race");
		        personResult.hairColor = 	results.getString("HairColor");
		        personResult.eyeColor = 	results.getString("EyeColor");
		        
		        people[count-1] = personResult; // Store person in array
		        count--;
		    }
			return(people);
		}
		catch(Exception e){
			System.out.println("GangWeb> ERROR: Could not convert query results to Java objects.");
			return(null);
		}
	}
	
	/**
	 * Adds a new individual to the database
	 * @param person The person to be added to the database
	 */
	public void AddElement(Person person){
		String updateString =  "INSERT INTO people " +
				"(FirstName, MiddleName, LastName, Age, Weight, Height, Gender, Race, HairColor, EyeColor) VALUES('" +
				person.firstName + "', '" + person.middleName + "', '" + person.lastName + "', " + person.age + ", " + person.weight + ", " + person.height + ", '" + person.gender + "', '" + person.race + "', '" + person.hairColor + "', '" + person.eyeColor + "')";
		this.Update(updateString);
	}
	
	/**
	 * Removes the specified individual from the database
	 * @param id ID of the element to be removed
	 */
	public void RemoveElement(Integer id){
		String updateString = "DELETE FROM people WHERE id=" + id;
		this.Update(updateString);
	}
	
	/**
	 * Generates random identities and adds them to the database.
	 * @param i The number of people to be created and added to the database
	 */
	public void generateData(int i){
		while(i > 0){
			Random random = new Random();
			String[] maleNames = {"Jacob", "Michael", "Matthew", "Joshua", "Christopher", "Nicholas", "Andrew", "Joseph", "Daniel", "Tyler"};
			String[] femaleNames = {"Emily", "Hannah", "Madison", "Ashley", "Sarah", "Alexis", "Samantha", "Jessica", "Elizabeth", "Taylor"};
			String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor"};
			String[] genders = {"Male", "Female"};
			String[] races = {"White", "Black", "Native American", "Asian", "Hispanic"};
			String[] hairColors = {"Brown", "Black", "Blonde", "Red"};
			String[] eyeColors = {"Amber", "Blue", "Brown", "Gray", "Green", "Hazel"};
			
			Person person = new Person();
			person.gender = genders[random.nextInt(2)];
			
			if(person.gender == "Male"){
				person.firstName = maleNames[random.nextInt(10)];
				person.middleName = maleNames[random.nextInt(10)];
			}
			else if(person.gender == "Female"){
				person.firstName = femaleNames[random.nextInt(10)];
				person.middleName = femaleNames[random.nextInt(10)];
			}
			
			person.lastName = lastNames[random.nextInt(10)];
			person.race = races[random.nextInt(5)];
			person.age = random.nextInt(50 - 15) + 15; // 15 is min age, 50 is max age
			person.weight = random.nextInt(136 - 40) + 40;
			person.height = random.nextInt(213 - 152) + 152;
			person.hairColor = hairColors[random.nextInt(4)];
			person.eyeColor = eyeColors[random.nextInt(6)];
			this.AddElement(person);
			i--;
		}
	}
}
