package ie.gmit.sw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Ross Byrne on 03/01/17.
 *
 * A dialog pop up for displaying an error message.
 */
public class ErrorDialog extends JDialog {

    private static final long serialVersionUID = 777L;
    private JLabel errorLabel = null;
    private JButton btnClose = null;
    private JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private Container c;

    /**
     * The constructor creates the dialog window and adds the error message to it.
     *
     * @param errorMessage
     * The error message to be displayed on the dialog window, as a String.
     */
    public ErrorDialog(String errorMessage){

        // set the dialog title
        super.setTitle("Error!");

        // make not resizable
        super.setResizable(false);

        // setup the dialog
        init(errorMessage);

    } // constructor

    /**
     * Initialises the error dialog window
     *
     * @param errorMessage
     * The message being added to the error dialog, as a String.
     */
    private void init(String errorMessage){

        // set size of dialog
        this.setSize(new Dimension(400, 200));

        // get content panel and set layout
        c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER));

        // setup the panels
        setupPanels();

        // create error label
        createLabel(errorMessage);

        // create button
        configureButton();

        // add panels to window
        c.add(messagePanel);
        c.add(buttonPanel);

    } // init()

    /**
     * Sets up the panels in the dialog window.
     */
    private void setupPanels(){

        // setup message panel
        messagePanel.setPreferredSize(new Dimension(380, 80));
        messagePanel.setMaximumSize(new Dimension(380, 80));
        messagePanel.setMinimumSize(new Dimension(200, 40));

        // setup buttonPanel
        buttonPanel.setPreferredSize(new Dimension(380, 80));
        buttonPanel.setMaximumSize(new Dimension(380, 80));
        buttonPanel.setMinimumSize(new Dimension(200, 40));

    } // setupPanels()

    /**
     * Creates the label with the error message.
     *
     * @param errorMessage
     * The message that in on the dialog window, as a String.
     */
    private void createLabel(String errorMessage){

        // create the error label with error message
        errorLabel = new JLabel(errorMessage);
        errorLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        errorLabel.setPreferredSize(new Dimension(380, 100));
        errorLabel.setMaximumSize(new Dimension(380, 100));
        errorLabel.setMinimumSize(new Dimension(100, 50));

        // add label to panel
        messagePanel.add(errorLabel);

    } // createTable()

    /**
     * Configures the OK close button for the dialog.
     */
    private void configureButton(){

        //Configure the ok button
        btnClose = new JButton("OK");
        btnClose.setVerticalAlignment(SwingConstants.BOTTOM);
        btnClose.setToolTipText("Close this Window");
        btnClose.setPreferredSize(new Dimension(100, 30));
        btnClose.setMaximumSize(new Dimension(100, 30));
        btnClose.setMargin(new Insets(2, 2, 2, 2));
        btnClose.setMinimumSize(new Dimension(100, 30));
        btnClose.setIcon(new ImageIcon("images/close.gif"));

        btnClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                dispose();
            }
        });

        // add button to the button panel
        buttonPanel.add(btnClose);

    } // configureButton()

} // class
