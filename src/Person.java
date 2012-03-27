/**
 * The structure of a person in the GangWeb database
 */
public class Person {
	public Integer 	id = null;
	public String 	firstName = null;
	public String 	middleName = null;
	public String 	lastName = null;
	public Integer	age = null;
	public Integer	weight = null;
	public Integer	height = null;
	public String	gender = null;
	public String	race = null;
	public String	hairColor = null;
	public String 	eyeColor = null;
	
	/**
	 * Creates an instance of Person.
	 */
	public Person(){
		
	}
	
	/**
	 * Creates an instance of Person with initialized attributes.
	 */
	public Person(String firstName, String middleName, String lastName, Integer age, Integer weight, Integer height, String gender, String race, String hairColor, String eyeColor){
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.age = age;
		this.weight = weight;
		this.height = height;
		this.gender = gender;
		this.race = race;
		this.hairColor = hairColor;
		this.eyeColor = eyeColor;
	}
}

