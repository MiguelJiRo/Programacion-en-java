package simulator.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import simulator.control.Controller;

public class BodiesTable extends JPanel{
	private BodiesTableModel _btm;
	private Controller _ctrl;
	private JTable table;
	
	BodiesTable(Controller ctrl){
		_ctrl = ctrl;	
		_btm = new BodiesTableModel(_ctrl);
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createLineBorder(Color.black,2),
				"Bodies",
				TitledBorder.LEFT,
				TitledBorder.TOP));
		
		setPreferredSize(new Dimension(1000,200));
		// Complete
		initGUI();
	}
	
	private void initGUI() {
		
		table = new JTable();
		table.setModel(_btm);
		
		JScrollPane scrollPanel = new JScrollPane(table,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, 
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		///////////////////////////
		/// HEADER COLUMNS
		///////////////////////////
		
		JTableHeader header = table.getTableHeader();
		TableColumnModel columnMod = header.getColumnModel();
		DefaultTableCellRenderer rend = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer(); 
		rend.setHorizontalAlignment(JLabel.LEFT); 
		header.setDefaultRenderer(rend);
		for(int i = 0; i < _btm.getColumnCount(); ++i) {
			TableColumn tabColumn = columnMod.getColumn(i);
			tabColumn.setHeaderValue(_btm.getColumnName(i));	
		}
		
		header.repaint();		
		add(scrollPanel,BorderLayout.CENTER);
	}
}
