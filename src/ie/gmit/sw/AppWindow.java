package ie.gmit.sw;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.HashMap;

public class AppWindow {

	private JFrame frame;
	private JButton btnOther;
	private AppSummary as;
	
	public AppWindow(){

		//Create a window for the application
		frame = new JFrame();
		frame.setTitle("JAR Stability Measurement Tool");
		frame.setSize(600, 500);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		
        //The file panel will contain the file chooser
        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.setBorder(new javax.swing.border.TitledBorder("Select a JAR File"));
        top.setPreferredSize(new Dimension(560, 140));
        top.setMaximumSize(new Dimension(560, 140));
        top.setMinimumSize(new Dimension(560, 140));

        // Create panel to hold text box with file path
        JPanel fileNamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fileNamePanel.setPreferredSize(new Dimension(500, 60));
        fileNamePanel.setMaximumSize(new Dimension(500, 60));
        fileNamePanel.setMinimumSize(new Dimension(500, 60));

        // create panel to hold buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.setPreferredSize(new Dimension(500, 40));
        buttonsPanel.setMaximumSize(new Dimension(500, 40));
        buttonsPanel.setMinimumSize(new Dimension(500, 40));

        // create a label for text input
        final JLabel filePathLabel = new JLabel("Selected JAR file path:");
        filePathLabel.setHorizontalTextPosition(SwingConstants.LEFT);
        filePathLabel.setPreferredSize(new Dimension(490, 20));
        filePathLabel.setMaximumSize(new Dimension(400, 20));
        filePathLabel.setMinimumSize(new Dimension(100, 20));

        // create text field for selected file
        final JTextField txtFileName =  new JTextField(44);
		txtFileName.setPreferredSize(new Dimension(400, 30));
		txtFileName.setMaximumSize(new Dimension(400, 30));
		txtFileName.setMargin(new Insets(4, 2, 2, 2));
		txtFileName.setMinimumSize(new Dimension(100, 30));
		txtFileName.setEditable(false);

		// create button for choosing jar file
		JButton btnChooseFile = new JButton("Browse...");
		btnChooseFile.setToolTipText("Select JAR File");
        btnChooseFile.setPreferredSize(new Dimension(90, 30));
        btnChooseFile.setMaximumSize(new Dimension(90, 30));
        btnChooseFile.setMargin(new Insets(4, 2, 2, 2));
        btnChooseFile.setMinimumSize(new Dimension(90, 30));
		btnChooseFile.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {

        		JFileChooser fc = new JFileChooser("./");
        		int returnVal = fc.showOpenDialog(frame);

            	if (returnVal == JFileChooser.APPROVE_OPTION) {

            	    // get handle on selected file
                	File file = fc.getSelectedFile().getAbsoluteFile();

                	// get file name
                	String name = file.getAbsolutePath();

                	// check that the file is a .jar
                    if(name.endsWith(".jar") == true){

                        // add file path to text field
                        txtFileName.setText(name);

                        // make readonly
                       // txtFileName.setEditable(false);

                        // activate do something button
                        btnOther.setEnabled(true);


                    } else { // if file is not a .jar

                        // create an error dialog box
                        ErrorDialog errorDialog = new ErrorDialog("Error! Selected File is Not a .jar!");

                        // show the dialog
                        errorDialog.setVisible(true);

                    } // if

            	} // if
			} // actionPerformed()
        });

		// button for doing something
		btnOther = new JButton("Do Something");
		btnOther.setToolTipText("Do Something");
		btnOther.setPreferredSize(new Dimension(150, 30));
		btnOther.setMaximumSize(new Dimension(150, 30));
		btnOther.setMargin(new Insets(4, 2, 2, 2));
		btnOther.setMinimumSize(new Dimension(150, 30));
		btnOther.setEnabled(false);

		btnOther.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                // check if their is something entered in the filepath
            	if(txtFileName.getText().length() > 1){

                    // analyse JAR
                    BasicMetricCalculator basicMetricCalculator = new BasicMetricCalculator(txtFileName.getText());

                    // create the summary
                    as =  new AppSummary(frame, true);

                    // get handle on summary table model
                    TypeSummaryTableModel tm = as.getTableModel();

                    // add metric data into table model
                    tm.setTableData(basicMetricCalculator.getMetricData());

                    // make the dialog visible
                    as.setVisible(true);

                } else {

                    System.out.println("No jar selected");
                } // if
            	
			}
        });

        // add components to JPanel
        fileNamePanel.add(filePathLabel);
        fileNamePanel.add(txtFileName);
        buttonsPanel.add(btnChooseFile);
        buttonsPanel.add(btnOther);

        // add panels to top panel
        top.add(fileNamePanel);
        top.add(buttonsPanel);
        frame.getContentPane().add(top); //Add the panel to the window

        //A separate panel for the programme output
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.CENTER));
        mid.setBorder(new javax.swing.border.TitledBorder("Weird Custom Control"));
        //mid.setBorder(new BevelBorder(BevelBorder.RAISED));
        mid.setPreferredSize(new Dimension(560, 250));
        mid.setMaximumSize(new Dimension(560, 250));
        mid.setMinimumSize(new Dimension(560, 250));

        // custom control
        CustomControl cc = new CustomControl(new Dimension(540, 220));
        cc.setBackground(Color.WHITE);
        cc.setPreferredSize(new Dimension(300, 220));
        cc.setMaximumSize(new Dimension(300, 220));
        cc.setMinimumSize(new Dimension(300, 220));
        mid.add(cc);
		frame.getContentPane().add(mid);

		// add panel to bottom of window
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setPreferredSize(new Dimension(560, 50));
        bottom.setMaximumSize(new Dimension(560, 50));
        bottom.setMinimumSize(new Dimension(560, 50));

        // create button for showing dialog
        JButton btnDialog = new JButton("Show Dialog"); //Create Quit button
        btnDialog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                // create the summary
                as =  new AppSummary(frame, true);
            	as.setVisible(true);

			}
        });

        // create quit application button
        JButton btnQuit = new JButton("Quit"); //Create Quit button
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.exit(0);
			}
        });

        // add buttons to bottom panel
        bottom.add(btnDialog);
        bottom.add(btnQuit);

        // add bottom panel to window
        frame.getContentPane().add(bottom);

        // make window visible
		frame.setVisible(true);

	} // constructor

} // class