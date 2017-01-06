package ie.gmit.sw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * A Dialog popup with a table to display the results of
 * the stability calculation
 */
public class AppSummary extends JDialog {

	private static final long serialVersionUID = 777L;	
	private TypeSummaryTableModel tm = null;
	private JTable table = null;
	private JScrollPane tableScroller = null;
	private JButton btnClose = null;
	private JPanel tablePanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private Container c;
	
	public AppSummary(JFrame parent, boolean modal){

        super(parent, modal);
        super.setTitle("Summary");
        super.setResizable(true);
        
        this.setSize(new Dimension(600, 500));
        
		c = getContentPane();
		c.setLayout(new FlowLayout());

		createTable();
        configureButton();
        
        c.add(tablePanel);
        c.add(buttonPanel);
	}


    /**
     * Method to get the table model from the table in the dialog window
     *
     * @return
     * The TypeSummaryTableModel, which is the model for the table in the dialog window
     */
	public TypeSummaryTableModel getTableModel(){

	    return tm;
    } // getTableModel()

    /**
     * Sets up the table in the dialog window
     */
	private void createTable(){

		tm = new TypeSummaryTableModel();
		table = new JTable(tm);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionBackground(Color.YELLOW);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		TableColumn column = null;
		for (int i = 0; i < table.getColumnCount(); i++){
			column = table.getColumnModel().getColumn(i);
			if (i == 0){
				column.setPreferredWidth(250);
				column.setMaxWidth(500);
				column.setMinWidth(100);
			}else{
				column.setPreferredWidth(60);
				column.setMaxWidth(500);
				column.setMinWidth(50);
			} // if

		} // for

		tableScroller = new JScrollPane(table);
		tableScroller.setPreferredSize(new Dimension(585, 400));
		tableScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		tableScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		tablePanel.add(tableScroller, FlowLayout.LEFT);

	} // createTable()

    /**
     * Configures the button on the dialog window
     */
	private void configureButton(){

    	buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		//Configure the Cancel button
		btnClose = new JButton("Close");		
		btnClose.setToolTipText("Close this Window");
		btnClose.setPreferredSize(new Dimension(100, 40));
		btnClose.setMaximumSize(new Dimension(100, 40));
		btnClose.setMargin(new Insets(2, 2, 2, 2));
		btnClose.setMinimumSize(new Dimension(100, 40));
		btnClose.setIcon(new ImageIcon("images/close.gif"));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}
		});

		buttonPanel.add(btnClose);

	} // configureButton()

} // class
