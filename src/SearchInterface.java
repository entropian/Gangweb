import javax.swing.JApplet;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JSeparator;

/**
 * This is a HEAVY work-in-progress. Feel free to play around with the interface,
 * it's a very subjective thing. Right now it looks rather bad.
 * 
 * To open it in the drag-and-drop editor, right click on the file in the explorer and
 * select open with -> WindowBuilder
 * 
 * @author Joe Mikkel
 *
 */
public class SearchInterface extends JApplet {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField_9;

	/**
	 * Create the applet.
	 */
	public SearchInterface() {
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
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
		
		JPanel panel_3 = new JPanel();
		getContentPane().add(panel_3, "2, 2, 3, 1, fill, fill");
		
		JLabel lblPersonalSearch = new JLabel("PERSONAL SEARCH");
		lblPersonalSearch.setFont(new Font("GiovanniITCTT", Font.PLAIN, 17));
		panel_3.add(lblPersonalSearch);
		
		JPanel panel_4 = new JPanel();
		getContentPane().add(panel_4, "2, 4, 3, 1, fill, fill");
		
		JLabel lblDatabaseId = new JLabel("Database ID : ");
		panel_4.add(lblDatabaseId);
		
		textField_9 = new JTextField();
		panel_4.add(textField_9);
		textField_9.setColumns(10);
		
		JButton btnSearch = new JButton("SEARCH");
		panel_4.add(btnSearch);
		
		JSeparator separator = new JSeparator();
		getContentPane().add(separator, "1, 6, 4, 1");
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, "2, 8, 3, 1, fill, fill");
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
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
		panel_2.add(lblLastName, "6, 2");
		
		textField = new JTextField();
		panel_2.add(textField, "2, 4, fill, default");
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1, "4, 4, fill, default");
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2, "6, 4, fill, default");
		textField_2.setColumns(10);
		
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		panel_2.add(rdbtnFemale, "2, 6");
		buttonGroup.add(rdbtnFemale);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		panel_2.add(rdbtnMale, "4, 6");
		buttonGroup.add(rdbtnMale);
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, "2, 10, fill, top");
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
		
		textField_3 = new JTextField();
		panel_1.add(textField_3, "4, 4, fill, default");
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		panel_1.add(textField_4, "6, 4, fill, default");
		textField_4.setColumns(10);
		
		JLabel lblWeight = new JLabel("Weight");
		panel_1.add(lblWeight, "2, 6, right, default");
		
		textField_5 = new JTextField();
		panel_1.add(textField_5, "4, 6, fill, default");
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		panel_1.add(textField_6, "6, 6, fill, default");
		textField_6.setColumns(10);
		
		JLabel lblHeight = new JLabel("Height");
		panel_1.add(lblHeight, "2, 8, right, default");
		
		textField_7 = new JTextField();
		panel_1.add(textField_7, "4, 8, fill, default");
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		panel_1.add(textField_8, "6, 8, fill, default");
		textField_8.setColumns(10);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, "4, 10, fill, fill");
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
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEyeColor = new JLabel("Eye Color");
		panel.add(lblEyeColor, "2, 2, right, default");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Amber", "Blue", "Brown", "Gray", "Green", "Hazel", "Red"}));
		panel.add(comboBox, "4, 2, fill, default");
		
		JLabel lblHairColor = new JLabel("Hair Color");
		panel.add(lblHairColor, "2, 4, right, default");
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Brown ", "Black", "White", "Sandy", "Gray", "Red", "Auburn", "Bald"}));
		panel.add(comboBox_1, "4, 4, fill, default");
		
		JLabel lblPrimaryEthnicity = new JLabel("Primary Ethnicity");
		panel.add(lblPrimaryEthnicity, "2, 6, right, default");
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"White", "Black", "American Indian", "Pacific Islander", "Other"}));
		panel.add(comboBox_2, "4, 6, fill, default");

	}

}
