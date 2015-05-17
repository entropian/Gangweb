import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 * Lets user search the persons database based on arbitrary fields.
 * @author Joe Mikkel
 *
 */
public class SearchPage extends JPanel implements ActionListener, KeyListener {
	

	private JTextField txt_firstName;
	private JTextField txt_middleName;
	private JTextField txt_lastName;
	private JTextField txt_ageFrom;
	private JTextField txt_ageTo;
	private JTextField txt_weightFrom;
	private JTextField txt_weightTo;
	private JTextField txt_heightFrom;
	private JTextField txt_heightTo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txt_ID;
	private JComboBox combo_eyeColor;
	private JComboBox combo_hairColor;
	private JComboBox combo_ethnic;
	
	JRadioButton radio_female;
	JRadioButton radio_male;
	
	JButton btnSearch;
	JButton btnNewEntry;
	
	public Person searchPerson;
	
	DatabaseHandler dbhandler;
	
	Interface overlord;
	
	/**
	 * Create the panel.
	 */
	public SearchPage(DatabaseHandler _dbhandler, Interface _overlord) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(19dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(41dlu;default):grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		dbhandler = _dbhandler;
		overlord = _overlord;
		
		JPanel panel_3 = new JPanel();
		add(panel_3, "2, 2, 3, 1, right, fill");
		
		JLabel lblPersonalSearch = new JLabel("PERSONAL SEARCH");
		lblPersonalSearch.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPersonalSearch.setFont(new Font("GiovanniITCTT", Font.PLAIN, 17));
		panel_3.add(lblPersonalSearch);
		
		JPanel panel_4 = new JPanel();
		add(panel_4, "2, 4, 3, 1, fill, fill");
		
		btnNewEntry = new JButton("NEW ENTRY");
		panel_4.add(btnNewEntry);
		btnNewEntry.addActionListener(this);
		
		JSeparator separator = new JSeparator();
		add(separator, "1, 6, 4, 1");
		
		JPanel panel_2 = new JPanel();
		add(panel_2, "2, 8, 3, 1, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(89dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(97dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblFirstName = new JLabel("First Name");
		panel_2.add(lblFirstName, "2, 2");
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		panel_2.add(lblMiddleName, "4, 2");
		
		JLabel lblLastName = new JLabel("Last Name");
		panel_2.add(lblLastName, "6, 2, 3, 1");
		
		txt_firstName = new JTextField();
		panel_2.add(txt_firstName, "2, 4, fill, default");
		txt_firstName.setColumns(10);
		
		txt_middleName = new JTextField();
		panel_2.add(txt_middleName, "4, 4, fill, default");
		txt_middleName.setColumns(10);
		
		txt_lastName = new JTextField();
		panel_2.add(txt_lastName, "6, 4, 3, 1, fill, default");
		txt_lastName.setColumns(10);
		
		radio_female = new JRadioButton("Female");
		panel_2.add(radio_female, "2, 6");
		buttonGroup.add(radio_female);
		
		radio_male = new JRadioButton("Male");
		panel_2.add(radio_male, "4, 6");
		buttonGroup.add(radio_male);
		
		JLabel lblDatabaseId = new JLabel("Database ID : ");
		panel_2.add(lblDatabaseId, "6, 6");
		
		txt_ID = new JTextField();
		panel_2.add(txt_ID, "8, 6");
		txt_ID.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, "2, 10, fill, top");
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(59dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(39dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblFrom = new JLabel("From");
		panel_1.add(lblFrom, "4, 2");
		
		JLabel lblTo = new JLabel("To");
		panel_1.add(lblTo, "6, 2");
		
		JLabel lblAge = new JLabel("Age");
		panel_1.add(lblAge, "2, 4, right, default");
		
		txt_ageFrom = new JTextField();
		panel_1.add(txt_ageFrom, "4, 4, fill, default");
		txt_ageFrom.setColumns(10);
		
		txt_ageTo = new JTextField();
		panel_1.add(txt_ageTo, "6, 4, fill, default");
		txt_ageTo.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight");
		panel_1.add(lblWeight, "2, 6, right, default");
		
		txt_weightFrom = new JTextField();
		panel_1.add(txt_weightFrom, "4, 6, fill, default");
		txt_weightFrom.setColumns(10);
		
		txt_weightTo = new JTextField();
		panel_1.add(txt_weightTo, "6, 6, fill, default");
		txt_weightTo.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height");
		panel_1.add(lblHeight, "2, 8, right, default");
		
		txt_heightFrom = new JTextField();
		panel_1.add(txt_heightFrom, "4, 8, fill, default");
		txt_heightFrom.setColumns(10);
		
		txt_heightTo = new JTextField();
		panel_1.add(txt_heightTo, "6, 8, fill, default");
		txt_heightTo.setColumns(10);
		
		JPanel panel = new JPanel();
		add(panel, "4, 10, fill, fill");
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEyeColor = new JLabel("Eye Color");
		panel.add(lblEyeColor, "2, 2, right, default");
		
		combo_eyeColor = new JComboBox();
		combo_eyeColor.setModel(new DefaultComboBoxModel(new String[] {"Unknown", "Amber", "Blue", "Brown", "Gray", "Green", "Hazel"}));
		panel.add(combo_eyeColor, "4, 2, fill, default");
		
		JLabel lblHairColor = new JLabel("Hair Color");
		panel.add(lblHairColor, "2, 4, right, default");
		
		combo_hairColor = new JComboBox();
		combo_hairColor.setModel(new DefaultComboBoxModel(new String[] {"Unknown", "Brown", "Black", "Blonde", "Red"}));
		panel.add(combo_hairColor, "4, 4, fill, default");
		
		JLabel lblPrimaryEthnicity = new JLabel("Primary Ethnicity");
		panel.add(lblPrimaryEthnicity, "2, 6, right, default");
		
		combo_ethnic = new JComboBox();
		combo_ethnic.setModel(new DefaultComboBoxModel(new String[] {"Unknown", "White", "Black", "Native American", "Asian", "Hispanic"}));
		panel.add(combo_ethnic, "4, 6, fill, default");
		
		btnSearch = new JButton("SEARCH");
		btnSearch.setForeground(Color.RED);
		panel.add(btnSearch, "4, 8");
		btnSearch.addActionListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource() == btnSearch)
			doSearch();
	
		if(arg0.getSource() == btnNewEntry)
			addPerson();
	}
	
	/**
	 * Handles the addPerson button;
	 * displays the personalPage.
	 */
	private void addPerson()
	{	
		overlord.personalPage.newPerson = true;
		overlord.cl.show(overlord.getContentPane(), "personalPage");
		overlord.personalPage.editOn();
		overlord.personalPage.clearFields();
	}
	
	/**
	 * Queries the database handler for people matching the entered information.
	 * Displays the results page with results.
	 */
	private void doSearch()
	{
		searchPerson = new Person();
		
		searchPerson.id = fieldNum(txt_ID);
		
		searchPerson.firstName = fieldText(txt_firstName);
		searchPerson.middleName = fieldText(txt_middleName);
		searchPerson.lastName = fieldText(txt_lastName);
		
		searchPerson.eyeColor = comboChoice(combo_eyeColor);
		searchPerson.hairColor = comboChoice(combo_hairColor);
		searchPerson.race = comboChoice(combo_ethnic);
		
		if(radio_female.isSelected())
			searchPerson.gender = "female";
		else if(radio_male.isSelected())
			searchPerson.gender = "male";
		
		
		System.out.println(searchPerson.toString());
		
		overlord.resultsPage.addPeople(dbhandler.Search	(
															searchPerson, 
															fieldNum(txt_ageFrom), 
															fieldNum(txt_ageTo),
															fieldNum(txt_weightFrom), 
															fieldNum(txt_weightTo), 
															fieldNum(txt_heightFrom), 
															fieldNum(txt_heightTo)
														));
		overlord.cl.show(overlord.getContentPane(),"resultsPage");
	}
	
	/**
	 * Gets the value selected in a combo box
	 * @param box The combo box to be read
	 * @return A string representing the choice selected in the box
	 */
	private String comboChoice(JComboBox box)
	{
		if(box.getSelectedItem() != "Unknown")
			return box.getSelectedItem().toString();
		else
			return null;
	}
	
	/**
	 * Gets the text in a given text field
	 * @param field The text field to be read
	 * @return A string representation of the text in the box
	 */
	private String fieldText(JTextField field)
	{
		if(field.getText().length() != 0)
			return field.getText();
		else
			return null;
	}
	
	/**
	 * Gets an integer from the given text field
	 * @param field The text field to be read from 
	 * @return Integer read from the field
	 */
	private Integer fieldNum(JTextField field)
	{
		if(field.getText().length() != 0)
			return Integer.parseInt(field.getText());
		else
			return null;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
