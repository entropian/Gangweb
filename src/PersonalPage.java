import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;

/**
 * Displays all information about a specific person.
 * Allows user to edit the person, or to create a new person.
 * @author Jan Durakiewicz
 *
 */
public class PersonalPage extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 4451714541408853219L;
	
	private JTextField txt_firstName;
	private JTextField txt_middleName;
	private JTextField txt_lastName;
	private JTextField txt_age;
	private JTextField txt_weight;
	private JTextField txt_height;
	private JTextField txt_ID;
	
	
	JRadioButton radio_female;
	JRadioButton radio_male;
	
	JComboBox combo_eyeColor;
	JComboBox combo_hairColor;
	JComboBox combo_ethnic;
	
	JButton btnEditThisPage;
	JButton btnChanges;
	
	public boolean editMode = false;
	public boolean newPerson = true;
	
	private final ButtonGroup buttonGroup_gender = new ButtonGroup();
	private Interface overlord;
	private DatabaseHandler dbhandler;
	private final JButton btnBack = new JButton("Back");
	private JButton btnDeleteThisPerson;
	private JButton btnVisualize;
	
	
	/**
	 * Create the panel.
	 */
	public PersonalPage(DatabaseHandler _dbhandler, Interface _overlord) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(21dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(31dlu;default):grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(28dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(26dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(11dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("65dlu"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(16dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(7dlu;default)"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
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
		
		overlord = _overlord;
		dbhandler = _dbhandler;
		
		add(btnBack, "2, 2");
		btnBack.addActionListener(this);
		
		JLabel label = new JLabel("PERSONAL PROFILE");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("GiovanniITCTT", Font.PLAIN, 17));
		add(label, "4, 2, 13, 1, right, default");
		
		JSeparator separator = new JSeparator();
		add(separator, "2, 4, 15, 1");
		
		JLabel lblId = new JLabel("ID");
		add(lblId, "2, 6, right, default");
		
		txt_ID = new JTextField();
		add(txt_ID, "4, 6, fill, default");
		txt_ID.setColumns(10);
		
		JLabel label_5 = new JLabel("Weight");
		add(label_5, "6, 6, right, default");
		
		txt_weight = new JTextField();
		txt_weight.setColumns(10);
		add(txt_weight, "8, 6, fill, default");
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		add(separator_1, "10, 6, 1, 17");
		
		btnVisualize = new JButton("Visualize");
		add(btnVisualize, "14, 6, 3, 1");
		btnVisualize.addActionListener(this);
		
		JLabel label_4 = new JLabel("Age");
		add(label_4, "2, 8, right, default");
		
		txt_age = new JTextField();
		txt_age.setColumns(10);
		add(txt_age, "4, 8, fill, default");
		
		JLabel label_6 = new JLabel("Height");
		add(label_6, "6, 8, right, default");
		
		txt_height = new JTextField();
		txt_height.setColumns(10);
		add(txt_height, "8, 8, fill, default");
		
		JLabel label_1 = new JLabel("First Name");
		add(label_1, "2, 10, 3, 1, left, default");
		
		txt_firstName = new JTextField();
		txt_firstName.setColumns(10);
		add(txt_firstName, "6, 10, 3, 1, fill, default");
		
		JLabel label_2 = new JLabel("Middle Name");
		add(label_2, "2, 12, 3, 1, left, default");
		
		txt_middleName = new JTextField();
		txt_middleName.setColumns(10);
		add(txt_middleName, "6, 12, 3, 1, fill, default");
		
		JLabel label_3 = new JLabel("Last Name");
		add(label_3, "2, 14, 3, 1, left, default");
		
		txt_lastName = new JTextField();
		txt_lastName.setColumns(10);
		add(txt_lastName, "6, 14, 3, 1, fill, default");
		
		radio_female = new JRadioButton("Female");
		buttonGroup_gender.add(radio_female);
		add(radio_female, "2, 16, 3, 1");
		
		radio_male = new JRadioButton("Male");
		buttonGroup_gender.add(radio_male);
		add(radio_male, "6, 16, 3, 1");
		
		JLabel label_7 = new JLabel("Eye Color");
		add(label_7, "2, 18, 3, 1, left, default");
		
		combo_eyeColor = new JComboBox();
		combo_eyeColor.setModel(new DefaultComboBoxModel(new String[] {"Unknown", "Amber", "Blue", "Brown", "Gray", "Green", "Hazel"}));
		add(combo_eyeColor, "6, 18, 3, 1, fill, default");
		
		btnDeleteThisPerson = new JButton("Delete This Person");
		add(btnDeleteThisPerson, "14, 18, 3, 1");
		btnDeleteThisPerson.addActionListener(this);
		
		JLabel label_8 = new JLabel("Hair Color");
		add(label_8, "2, 20, 3, 1, left, default");
		
		combo_hairColor = new JComboBox();
		combo_hairColor.setModel(new DefaultComboBoxModel(new String[] {"Unknown", "Brown", "Black", "Blonde", "Red"}));
		add(combo_hairColor, "6, 20, 3, 1, fill, default");
		
		btnChanges = new JButton("Save Changes");
		add(btnChanges, "14, 20, 3, 1");
		btnChanges.addActionListener(this);
		btnChanges.setVisible(false);
		
		JLabel label_9 = new JLabel("Primary Ethnicity");
		add(label_9, "2, 22, 3, 1, left, default");
		
		combo_ethnic = new JComboBox();
		combo_ethnic.setModel(new DefaultComboBoxModel(new String[] {"Unknown", "White", "Black", "Native American", "Asian", "Hispanic"}));
		add(combo_ethnic, "6, 22, 3, 1, fill, default");
		
		btnEditThisPage = new JButton("Enable Edit Mode");
		add(btnEditThisPage, "14, 22, 3, 1");
		btnEditThisPage.addActionListener(this);
		
		enableComponents(false);
	}

	/**
	 * Turns on edit mode
	 */
	public void editOn()
	{
		enableComponents(true);
		btnChanges.setVisible(true);
		btnEditThisPage.setLabel("Disable Edit Mode");
		editMode = true;
		if(!newPerson)
			btnDeleteThisPerson.setVisible(true);
	}
	/**
	 * Turns off edit mode
	 */
	public void editOff()
	{
		enableComponents(false);
		btnChanges.setVisible(false);
		btnEditThisPage.setLabel("Enable Edit Mode");
		editMode = false;
		btnDeleteThisPerson.setVisible(false);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if((JButton)arg0.getSource() == btnEditThisPage)
		{
			if(editMode)
				editOff();
			else
				editOn();
		}
		
		else if((JButton)arg0.getSource() == btnBack)
		{
			overlord.cl.show(overlord.getContentPane(), "resultsPage");
		}
		
		else if((JButton)arg0.getSource() == btnChanges)
		{
			  if(JOptionPane.showConfirmDialog(	this, 
					  							"Are you sure you want to commit your changes?", 
					  							"Edit Confirmation", 
					  							JOptionPane.OK_CANCEL_OPTION
					  						  ) == 0)
			  {
				  System.out.println("OK Button Clicked!");
				  if(newPerson)
					  addNewPerson();
				  else		   
					  commitChanges();
			  }
			  else
				  System.out.println("Cancel Button Clicked!");

		}
		else if((JButton)arg0.getSource() == btnDeleteThisPerson)
		{
			deletePerson(fieldNum(txt_ID));
		}
		else if((JButton)arg0.getSource() == btnVisualize)
		{
			Visualizer vis = new Visualizer();
			vis.runVisualizer(fieldNum(txt_ID),2);
		}
	}
	
	private void deletePerson(int ID)
	{
		if(JOptionPane.showConfirmDialog(	this,		
											"Are you sure you wish to delete this entry?",
											"Delete Confirmation",
											JOptionPane.OK_CANCEL_OPTION
										) == 0)
		{
			dbhandler.RemoveElement(ID);
			clearFields();
		}
	}
	
	/**
	 * Enters a new person into the database, made from displayed info.
	 */
	private void addNewPerson()
	{
		Person createdPerson = makePerson();
		dbhandler.AddElement(createdPerson);
		newPerson = false;
	}
	
	/**
	 * Saves changes made to an existing person
	 */
	private void commitChanges()
	{

		Person searchPerson = makePerson();
		System.out.println(searchPerson.toString());
		dbhandler.EditElement(searchPerson);
		
	}
	
	
	/**
	 * Gathers information from the interface and makes it into a person.
	 * @return A person object made from entered info.
	 */
	private Person makePerson()
	{
		Person searchPerson;
		searchPerson = new Person();
		
		searchPerson.id = fieldNum(txt_ID);
		
		searchPerson.firstName = fieldText(txt_firstName);
		searchPerson.middleName = fieldText(txt_middleName);
		searchPerson.lastName = fieldText(txt_lastName);
		
		searchPerson.age = fieldNum(txt_age);
		searchPerson.weight = fieldNum(txt_weight);
		searchPerson.height = fieldNum(txt_height);
		
		searchPerson.eyeColor = comboChoice(combo_eyeColor);
		searchPerson.hairColor = comboChoice(combo_hairColor);
		searchPerson.race = comboChoice(combo_ethnic);
		
		if(radio_male.isSelected())
			searchPerson.gender = "Male";
		else if(radio_female.isSelected())
			searchPerson.gender = "Female";
		
		return searchPerson;
	}
	
	/**
	 * Enters the information of a person into the text fields on the interface
	 * @param _person The person to be displayed
	 */
	public void displayPerson(Person _person)
	{
		
		txt_firstName.setText(_person.firstName);
		txt_middleName.setText(_person.middleName);
		txt_lastName.setText(_person.lastName);
		
		txt_age.setText(_person.age.toString());
		txt_weight.setText(_person.weight.toString());
		txt_height.setText(_person.height.toString());
		txt_ID.setText(_person.id.toString());
		
		combo_eyeColor.setSelectedItem(_person.eyeColor);
		combo_hairColor.setSelectedItem(_person.hairColor);
		combo_ethnic.setSelectedItem(_person.race);
		
		if(_person.gender != null)
		{
			if(_person.gender.equals("male"))
				radio_male.setSelected(true);
			else
				radio_female.setSelected(true);
		}
	}
	
	/**
	 * Sets all combo boxes to 'unknown', empties all text fields and 
	 * deselects radio buttons.
	 */
	public void clearFields()
	{
		for(Component field : this.getComponents())
		{
			if(field instanceof JComboBox)
				((JComboBox)field).setSelectedIndex(0);
			if(field instanceof JTextField)
				((JTextField)field).setText("");
			if(field instanceof JRadioButton)
				((JRadioButton)field).setSelected(false);
		}
	}
	/**
	 * Turns editing of the displayed fields on or off
	 * @param enabled Whether the fields should or should not be enabled
	 */
	public void enableComponents(boolean enabled)
	{
		Color c = new Color(150,150, 150);
		/*
		txt_firstName.setEnabled(enabled);
		txt_middleName.setEnabled(enabled);
		
		txt_Age.setEnabled(enabled);
		txt_Weight.setEnabled(enabled);
		txt_Height.setEnabled(enabled);
		*/
		for (Component field : this.getComponents())
		{
			if(!(field instanceof JLabel))
				field.setEnabled(enabled);
			if(field instanceof JTextField)
				((JTextField)field).setDisabledTextColor(c);
		}
		btnDeleteThisPerson.setVisible(enabled);
		btnEditThisPage.setEnabled(true);
		btnBack.setEnabled(true);
		txt_ID.setEnabled(false);
		btnVisualize.setEnabled(true);
		
		/*
		combo_eyeColor.setEnabled(enabled);
		combo_hairColor.setEnabled(enabled);
		combo_ethnic.setEnabled(enabled);
		
		radio_female.setEnabled(enabled);
		radio_male.setEnabled(enabled);
		*/
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
}
