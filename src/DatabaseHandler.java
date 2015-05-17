import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.sql.SQLException;

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
	 * @param person Specifies which attributes to search on Note: Does not include age, weight, or height
	 * @param minAge The minimum age to include
	 * @param maxAge The maximum age to include
	 * @param minWeight The minimum weight to include
	 * @param maxWeight The maximum weight to include
	 * @param minHeight The minimum height to include
	 * @param maxHeight The maximum height to include
	 * @return An array of people who fit the search criteria.
	 */
	public Person[] Search(Person person, Integer minAge, Integer maxAge, Integer minWeight, Integer maxWeight, Integer minHeight, Integer maxHeight){
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
		if(person.gender != null){		queryString += " Gender = '" + person.gender + "' AND";}
		if(person.race != null){		queryString += " Race = '" + person.race + "' AND";}
		if(person.hairColor != null){	queryString += " HairColor = '" + person.hairColor + "' AND";}
		if(person.eyeColor != null){	queryString += " EyeColor = '" + person.eyeColor + "' AND";}
		if(minAge != null){				queryString += " Age >= " + minAge + " AND";}
		if(maxAge != null){				queryString += " Age <= " + maxAge + " AND";}
		if(minWeight != null){			queryString += " Weight >= " + minWeight + " AND";}
		if(maxWeight != null){			queryString += " Weight <= " + maxWeight + " AND";}
		if(minHeight != null){			queryString += " Height >= " + minHeight + " AND";}
		if(maxHeight != null){			queryString += " Height <= " + maxHeight + " AND";}
		
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
	
	public ArrayList<Relationship> searchRelationshipsDepth(Integer id, Integer depth){
		ArrayList<Relationship> r = new ArrayList<Relationship>(0);
		Relationship[] temp;
		
		if(depth == 0)
			return r;
		
		if(depth > 0){
			temp = this.searchRelationships(id);
			for(int i = 1; i < temp.length; i++){
				r.add(temp[i]);
				r.addAll(this.searchRelationshipsDepth(temp[i].id1, depth - 1));
				r.addAll(this.searchRelationshipsDepth(temp[i].id2, depth - 1));
			}
		}
		return r;
	}
	public Person Search(Integer id){
		String queryString;	// The query to be executed
		ResultSet results;	// The results of the query
		Person person;		// The person found
		
		if(id == null){return(null);}
		
		queryString = "SELECT * FROM people WHERE id = " + id;
		results = this.Query(queryString);
		
		try{
			results.next();
			person = new Person();
			person.id = 			results.getInt("id");
	        person.firstName = 	results.getString("FirstName");
	        person.middleName =	results.getString("MiddleName");
	        person.lastName = 	results.getString("LastName");
	        person.age = 			results.getInt("Age");
	        person.weight =		results.getInt("Weight");
	        person.height = 		results.getInt("Height");
	        person.gender = 		results.getString("Gender");
	        person.race = 		results.getString("Race");
	        person.hairColor = 	results.getString("HairColor");
	        person.eyeColor = 	results.getString("EyeColor");
		}
		catch(Exception e){
			System.out.println("GangWeb> ERROR: Could not convert query results to Java objects.");
			return(null);
		}
		
		return(person);
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
	 * Finds the person in the database and updates their information.
	 * @param person The person to update in the database
	 */
	public void EditElement(Person person){
		if(person.id == null){return;}
		
		String updateString = "UPDATE people SET";
		if(person.firstName != null){	updateString += " FirstName = '" + person.firstName + "',";}
		if(person.middleName != null){	updateString += " MiddleName = '" + person.middleName + "',";}
		if(person.lastName != null){	updateString += " LastName= '" + person.lastName + "',";}
		if(person.gender != null){		updateString += " Gender = '" + person.gender + "',";}
		if(person.race != null){		updateString += " Race = '" + person.race + "',";}
		if(person.hairColor != null){	updateString += " HairColor = '" + person.hairColor + "',";}
		if(person.eyeColor != null){	updateString += " EyeColor = '" + person.eyeColor + "',";}
		if(person.age != null){			updateString += " Age = " + person.age + ",";}
		if(person.weight != null){		updateString += " Weight = " + person.weight + ",";}
		if(person.height != null){		updateString += " Height = " + person.height + ",";}
		updateString = updateString.substring(0, updateString.length() - 1); // Clip the last 'AND'
		updateString += " WHERE id = " + person.id;
		
		this.Update(updateString); // Run the update
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
	 * Adds a new entry in the table "relationships"
	 * @param p1 The first person in this new relationship.
	 * @param p2 The second person in this new relationship.
	 */
	public void addRelationship(Integer p1, Integer p2, String status){
		String updateString = "INSERT INTO relationships (id1, id2, status) VALUES(" + p1 + ", " + p2 + ", '" + status + "')";
		
		this.Update(updateString);
	}
	
	/**
	 * Removes an entry from the table "relationships"
	 * @param p1 The first person in this relationship.
	 * @param p2 The second person in this relationship.
	 */
	public void removeRelationship(Integer p1, Integer p2){
		String updatestring = "DELETE FROM relationships WHERE id1=" + p1 + " AND id2=" + p2;
		this.Update(updatestring);
	}
	
	public Relationship[] searchRelationships(Integer id){
		Relationship relationships[];
		String queryString;
		ResultSet results1;
		ResultSet results2;
		int count = 0;
		
		queryString = "SELECT * FROM relationships WHERE id1=" + id;
		results1 = this.Query(queryString);
		queryString = "SELECT * FROM relationships WHERE id2=" + id;
		results2 = this.Query(queryString);
		
		try{
			while(results1.next()){count++;}
			while(results2.next()){count++;}
			relationships = new Relationship[count];
			
			while(results2.previous()){
				Relationship r = new Relationship(id, results2.getInt("id1"), results2.getString("status"));
				relationships[count-1] = r;
				count--;
			}
			
			while(results1.previous() && count != 0){
				Relationship r = new Relationship(id, results1.getInt("id2"), results1.getString("status"));
				relationships[count-1] = r;
				count--;
			}
			
			return(relationships);
			
		}catch(Exception e){
			System.out.println("GangWeb> ERROR: Could not convert query results to Java objects.");
			return(null);
		}
	}
	/**
	 * Finds all relationships involving the specified id
	 * @param id The id of the person to find relationships for
	 * @return An array of relationships
	 */
	public Relationship[] searchRelationship(Integer id){
		Relationship relationships[];
		String queryString;
		ResultSet results1;
		ResultSet results2;
		int count = 0;
		
		queryString = "SELECT * FROM relationships WHERE id1=" + id;
		results1 = this.Query(queryString);
		queryString = "SELECT * FROM relationships WHERE id2=" + id;
		results2 = this.Query(queryString);
		
		try{
			while(results1.next()){count++;}
			while(results2.next()){count++;}
			relationships = new Relationship[count];
			
			while(results2.previous()){
				Relationship r = new Relationship(id, results2.getInt("id1"), results2.getString("status"));
				relationships[count-1] = r;
				count--;
			}
			
			while(results1.previous() && count != 0){
				Relationship r = new Relationship(id, results1.getInt("id2"), results1.getString("status"));
				relationships[count-1] = r;
				count--;
			}
			
			return(relationships);
			
		}catch(Exception e){
			System.out.println("GangWeb> ERROR: Could not convert query results to Java objects.");
			return(null);
		}
	}
	
	/**
	 * Finds all relationships involving the specified ids
	 * @param id1 The first persons id
	 * @param id1 The second persons id
	 * @return An array of relationships
	 */
	public Relationship searchRelationship(Integer id1, Integer id2){
		String queryString = "SELECT * FROM relationships WHERE (id1=" + id1 + " AND id2=" + id2 + ") OR (id1=" + id2 + " AND id2=" + id1 + ")";
		ResultSet results;
		Relationship r;
		
		results = Query(queryString);
		
		try{
			if(results.getInt("id1") == id1){
				r = new Relationship(id1, id2, results.getString("status"));
				return r;
			}else{
				r = new Relationship(id2, id1, results.getString("status"));
			}
		}catch(SQLException ex){
			
		}
	
		return(null);
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
