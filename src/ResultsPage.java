import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

/**
 * Displays a clickable table of People objects with extraneous information about each.
 * @author Joe Mikkel
 *
 */
public class ResultsPage extends JPanel implements MouseListener, ActionListener{
	private JTable table;
	DatabaseHandler dbhandler;
	Interface overlord;
	JButton btnBack;
	/**
	 * Create the panel.
	 */
	public ResultsPage(DatabaseHandler _dbhandler, Interface _overlord) {
		dbhandler = _dbhandler;
		overlord = _overlord;
		setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(25dlu;default)"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(233dlu;default):grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),}));
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		add(btnBack, "2, 2");
		
		JLabel lblSearchResults = new JLabel("SEARCH RESULTS");
		lblSearchResults.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSearchResults.setFont(new Font("GiovanniITCTT", Font.PLAIN, 17));
		add(lblSearchResults, "4, 2");
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, "2, 4, 3, 1, fill, fill");
		
		table = new JTable();
		table.addMouseListener(this);
		table.setAutoCreateRowSorter(true);
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "First Name", "Middle Name", "Last Name", "Age", "Weight", "Height", "Race"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(62);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(45);
		table.getColumnModel().getColumn(6).setPreferredWidth(45);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);

	}
	
	/**
	 * Adds an array of people into the displayed table
	 * @param people Array of people objects to be displayed
	 */
	public void addPeople(Person[] people)
	{
		for(Person p : people)
		{
			((DefaultTableModel)table.getModel()).addRow
				(
					new Object[]{
							(int)p.id,p.firstName,p.middleName,p.lastName,(int)p.age,(int)p.weight,(int)p.height,p.race
					}
				);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	 
		
		//System.out.println("Click at " + target.getSelectedColumn() + "," + target.getSelectedRow());
		if(arg0.getClickCount() >= 2)
		{	
			JTable target = (JTable)arg0.getSource();
			Person searchPerson = new Person();
			
			int id = (Integer) target.getModel().getValueAt(
					target.convertRowIndexToModel(target.getSelectedRow()), 
					target.convertColumnIndexToModel(0));
			
			System.out.println(dbhandler.Search(id).toString());
			overlord.personalPage.newPerson = false;
			overlord.personalPage.displayPerson(dbhandler.Search(id));
			overlord.cl.show(overlord.getContentPane(), "personalPage");
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	

		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {


		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == btnBack)
		{
			System.out.println("<--------- BACK button clicked");
			overlord.cl.show(overlord.getContentPane(), "searchPage");
			DefaultTableModel model;
			model = (DefaultTableModel)table.getModel();
			model.setRowCount(0);
		}
	}

}
