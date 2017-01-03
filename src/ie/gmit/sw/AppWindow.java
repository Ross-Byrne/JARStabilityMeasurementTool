package ie.gmit.sw;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class AppWindow {

	private JFrame frame;
	
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
                	File file = fc.getSelectedFile().getAbsoluteFile();
                	String name = file.getAbsolutePath(); 
                	txtFileName.setText(name);
                	System.out.println("You selected the following file: " + name);
            	}
			}
        });

		// button for doing something
		JButton btnOther = new JButton("Do Something");
		btnOther.setToolTipText("Do Something");
		btnOther.setPreferredSize(new Dimension(150, 30));
		btnOther.setMaximumSize(new Dimension(150, 30));
		btnOther.setMargin(new Insets(4, 2, 2, 2));
		btnOther.setMinimumSize(new Dimension(150, 30));
		btnOther.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.out.println("Do Something");
            	
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
        mid.setBorder(new BevelBorder(BevelBorder.RAISED));
        mid.setPreferredSize(new Dimension(560, 250));
        mid.setMaximumSize(new Dimension(560, 250));
        mid.setMinimumSize(new Dimension(560, 250));

        // custom control
        CustomControl cc = new CustomControl(new Dimension(540, 230));
        cc.setBackground(Color.WHITE);
        cc.setPreferredSize(new Dimension(300, 230));
        cc.setMaximumSize(new Dimension(300, 230));
        cc.setMinimumSize(new Dimension(300, 230));
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
            	AppSummary as =  new AppSummary(frame, true);
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