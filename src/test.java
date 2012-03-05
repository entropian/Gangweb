public class test {
	public static void main(String[] args) {
		DatabaseHandler dbhandler = new DatabaseHandler();
		dbhandler.AddElement("John", "Smith", "Doe", 40, 160, 60, "Male", "White", "Brown", "Brown");
		dbhandler.RemoveElement(2);
		dbhandler.Disconnect();
	}
	//comment
}
