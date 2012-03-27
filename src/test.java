

public class test {
	public static void main(String[] args) {
		DatabaseHandler dbhandler = new DatabaseHandler();
		
		// Example of searching for a person
		//dbhandler.AddElement(new Person("John", null, "Doe", 19, 86, 160, "Male", "White", "Brown", null));
		
		// Example of removing a person (USE ID)
		//dbhandler.RemoveElement(2);
		
		// Example of searching for a person
		Person person2 = new Person();					// 1) Create the person you are searching for.
		person2.id =			null;					// Note: Ranges will be added later
	    person2.firstName =		"Daniel";
	    person2.middleName =	null;
	    person2.lastName =		null;
	    person2.age =			null;
	    person2.weight =		93;
	    person2.height =		null;
	    person2.gender =		"Male";
	    person2.race =			"White";
	    person2.hairColor =		"Brown";
	    person2.eyeColor =		null;
		Person[] people = dbhandler.Search(person2);		// 2) Using the person you created, search for that person
														// A list of all possible matches are returned.
		
		// CAN ALSO BE DONE LIKE THIS FOR COMPACTNESS
		//Person[] people = dbhandler.Search(new Person("John", null, "Doe", 19, 86, 160, "Male", "White", "Brown", null));
		
		dbhandler.generateData(10000);
		
		dbhandler.Disconnect();
	}
}
