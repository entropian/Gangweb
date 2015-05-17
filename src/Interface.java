import javax.swing.JApplet;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creates and handles the three interface pages.
 * Allows them to interact with each other.
 * @author Joe Mikkel
 *
 */
public class Interface extends JApplet{

	/**
	 * Create the applet.
	 */
	PersonalPage personalPage;
	SearchPage searchPage;
	ResultsPage resultsPage;
	CardLayout cl;
	
	public Interface()  {
		
		setPreferredSize(new Dimension(500,500));
	
		cl = new CardLayout(0,0);
		getContentPane().setLayout(cl);
		
		DatabaseHandler dbhandler = new DatabaseHandler();
		
		personalPage = new PersonalPage(dbhandler, this);
		getContentPane().add(personalPage, "personalPage");
		
		searchPage = new SearchPage(dbhandler, this);
		getContentPane().add(searchPage, "searchPage");
		
		resultsPage = new ResultsPage(dbhandler, this);
		getContentPane().add(resultsPage, "resultsPage");
		
		cl.show(getContentPane(), "searchPage");
		
	}

}
