package swing;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelBarcos extends JPanel{
	
	JTable table;
	JScrollPane scrollPane;

	public PanelBarcos() {
		setBounds(26, 278, 553, 238);
		setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 553, 238);
		add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
	}
}
