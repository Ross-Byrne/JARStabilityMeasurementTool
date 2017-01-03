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
		frame.setSize(550, 500);
		frame.setResizable(false);
		frame.setLayout(new FlowLayout());
		
        //The file panel will contain the file chooser
        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEADING));
        top.setBorder(new javax.swing.border.TitledBorder("Select a JAR File"));
        top.setPreferredSize(new Dimension(500, 100));
        top.setMaximumSize(new Dimension(500, 100));
        top.setMinimumSize(new Dimension(500, 100));
        
        final JTextField txtFileName =  new JTextField(20);
		txtFileName.setPreferredSize(new Dimension(100, 30));
		txtFileName.setMaximumSize(new Dimension(100, 30));
		txtFileName.setMargin(new Insets(2, 2, 2, 2));
		txtFileName.setMinimumSize(new Dimension(100, 30));
		
		JButton btnChooseFile = new JButton("Browse...");
		btnChooseFile.setToolTipText("Select JAR File");
        btnChooseFile.setPreferredSize(new Dimension(90, 30));
        btnChooseFile.setMaximumSize(new Dimension(90, 30));
        btnChooseFile.setMargin(new Insets(2, 2, 2, 2));
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
		
		JButton btnOther = new JButton("Do Something");
		btnOther.setToolTipText("Do Something");
		btnOther.setPreferredSize(new Dimension(150, 30));
		btnOther.setMaximumSize(new Dimension(150, 30));
		btnOther.setMargin(new Insets(2, 2, 2, 2));
		btnOther.setMinimumSize(new Dimension(150, 30));
		btnOther.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.out.println("Do Something");
            	
			}
        });
		
        top.add(txtFileName);
        top.add(btnChooseFile);
        top.add(btnOther);
        frame.getContentPane().add(top); //Add the panel to the window
        
        
        //A separate panel for the programme output
        JPanel mid = new JPanel(new FlowLayout(FlowLayout.LEADING));
        mid.setBorder(new BevelBorder(BevelBorder.RAISED));
        mid.setPreferredSize(new Dimension(500, 300));
        mid.setMaximumSize(new Dimension(500, 300));
        mid.setMinimumSize(new Dimension(500, 300));
        
        CustomControl cc = new CustomControl(new Dimension(500, 300));
        cc.setBackground(Color.WHITE);
        cc.setPreferredSize(new Dimension(300, 300));
        cc.setMaximumSize(new Dimension(300, 300));
        cc.setMinimumSize(new Dimension(300, 300));
        mid.add(cc);
		frame.getContentPane().add(mid);
		
        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottom.setPreferredSize(new Dimension(500, 50));
        bottom.setMaximumSize(new Dimension(500, 50));
        bottom.setMinimumSize(new Dimension(500, 50));
        
        JButton btnDialog = new JButton("Show Dialog"); //Create Quit button
        btnDialog.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	AppSummary as =  new AppSummary(frame, true);
            	as.setVisible(true);

			}
        });
        
        JButton btnQuit = new JButton("Quit"); //Create Quit button
        btnQuit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	System.exit(0);
			}
        });
        bottom.add(btnDialog);
        bottom.add(btnQuit);

        frame.getContentPane().add(bottom);       
		frame.setVisible(true);

	} // constructor

} // class