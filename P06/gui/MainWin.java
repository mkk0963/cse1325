package gui;

import javax.swing.JFrame;           // for main window
import javax.swing.JOptionPane;      // for standard dialogs
// import javax.swing.JDialog;          // for custom dialogs (for alternate About dialog)
import javax.swing.JMenuBar;         // row of menu selections
import javax.swing.JMenu;            // menu selection that offers another menu
import javax.swing.JMenuItem;        // menu selection that does something
import javax.swing.JToolBar;         // row of buttons under the menu
import javax.swing.JButton;          // regular button
import javax.swing.JComboBox;
//import javax.swing.JToggleButton;    // 2-state button
//import javax.swing.BorderFactory;    // manufacturers Border objects around buttons
import javax.swing.Box;              // to create toolbar spacer
//import javax.swing.UIManager;        // to access default icons
import javax.swing.JLabel;           // text or image holder
import javax.swing.ImageIcon;        // holds a custom icon
import javax.swing.SwingConstants;   // useful values for Swing method calls

//import javax.imageio.ImageIO;        // loads an image from a file

//import java.io.File;                 // opens a file
//import java.io.IOException;          // reports an error reading from a file

import java.awt.BorderLayout;        // layout manager for main window
//import java.awt.FlowLayout;          // layout manager for About dialog

//import java.awt.Color;               // the color of widgets, text, or borders
import java.awt.Font;                // rich text in a JLabel or similar widget
//import java.awt.image.BufferedImage; // holds an image loaded from a file

import store.Customer;
import store.Option;
import store.Computer;
import store.Store;

enum Record
{
    CUSTOMER, OPTION, COMPUTER, ORDER;
}

public class MainWin extends JFrame 
{
    private Store store;
    private JLabel display;

    public MainWin(String title) 
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem quit = new JMenuItem("Quit");

        JMenu insert = new JMenu("Insert");
        JMenuItem insertCust  = new JMenuItem("Customer");
        JMenuItem  insertOpt = new JMenuItem("Option");
        JMenuItem insertComp = new JMenuItem("Computer");

        JMenu view = new JMenu("View");
        JMenuItem viewCust = new JMenuItem("Customer");
        JMenuItem viewOpt = new JMenuItem("Options");
        JMenuItem viewComp = new JMenuItem("Computer");

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");

        quit.addActionListener(event -> onQuitClick());
        insertCust.addActionListener(event -> onInsertCustomerClick());
        insertOpt.addActionListener(event -> onInsertOptionClick());
        insertComp.addActionListener(event -> onInsertComputerClick());
        viewCust.addActionListener(event -> onViewClick(Record.CUSTOMER));
        viewOpt.addActionListener(event -> onViewClick(Record.OPTION));
        viewComp.addActionListener(event -> onViewClick(Record.COMPUTER));
        about.addActionListener(event -> onAboutClick());

        menubar.add(file);
        menubar.add(insert);
        menubar.add(view);
        menubar.add(help);
        setJMenuBar(menubar);

        file.add(quit);
        insert.add(insertCust);
        insert.add(insertOpt);
        insert.add(insertComp);
        view.add(viewCust);
        view.add(viewOpt);
        view.add(viewComp);
        help.add(about);

        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R 
        // Add a toolbar to the PAGE_START region below the menu
        
        JToolBar toolbar = new JToolBar("ELSA");

        JButton customer = new JButton(new ImageIcon("gui/resources/add_customer.png"));
        customer.setActionCommand("Insert Customer");
        customer.setToolTipText("Provide a name and email for a new customer");
        customer.setBorder(null);
        toolbar.add(customer);
        customer.addActionListener(event -> onInsertCustomerClick());

        JButton option = new JButton(new ImageIcon("gui/resources/add_option.png"));
        option.setActionCommand("Insert Option");
        option.setToolTipText("Provide a name and cost for a new option");
        toolbar.add(option);
        option.addActionListener(event -> onInsertOptionClick());

        JButton computer = new JButton(new ImageIcon("gui/resources/add_computer.png"));
        computer.setActionCommand("Insert Computer");
        computer.setToolTipText("Insert a new Computer");
        toolbar.add(computer);
        computer.addActionListener(event -> onInsertComputerClick());

        toolbar.add(Box.createHorizontalStrut(25));

        JButton vCustomer = new JButton(new ImageIcon("gui/resources/view_customers.png"));
        vCustomer.setActionCommand("View Customer");
        vCustomer.setToolTipText("View the list of customers");
        toolbar.add(vCustomer);
        vCustomer.addActionListener(event -> onViewClick(Record.CUSTOMER));

        JButton vOption = new JButton(new ImageIcon("gui/resources/view_options.png"));
        vOption.setActionCommand("View Option");
        vOption.setToolTipText("View the list of options");
        vOption.setBorder(null);
        toolbar.add(vOption);
        vOption.addActionListener(event -> onViewClick(Record.OPTION));

        JButton vComputer = new JButton(new ImageIcon("gui/resources/view_computers.png"));
        vComputer.setActionCommand("View Computers");
        vComputer.setToolTipText("View the list of computers");
        vComputer.setBorder(null);
        toolbar.add(vComputer);
        vComputer.addActionListener(event -> onViewClick(Record.COMPUTER));
        toolbar.addSeparator();

        getContentPane().add(toolbar, BorderLayout.PAGE_START);

        // /////////////////////////// ////////////////////////////////////////////
        // D I S P L A Y
        // Provide a text entry box to show the remaining computers
        display = new JLabel();
        display.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(display, BorderLayout.CENTER);
        
        // Make everything in the JFrame visible
        setVisible(true);
        store = new Store("KIMEX 243");
    }
    
    // Listeners

    protected void onQuitClick() // Exit the game
    {
        System.exit(0);
    }  

    protected void onInsertCustomerClick()
    {
        
        try
        {
            String name = JOptionPane.showInputDialog(this, "Customer name", "New Customer", JOptionPane.QUESTION_MESSAGE);
            String email = JOptionPane.showInputDialog(this, "Customer email", "New Customer", JOptionPane.QUESTION_MESSAGE);
            store.add(new Customer(name, email));
        }
        catch(Exception email)
        {
        }
    }

    protected  void onInsertOptionClick()
    {
        try
        {
            String name = JOptionPane.showInputDialog(this, "Enter Option", "New Option", JOptionPane.QUESTION_MESSAGE);
            String cost = JOptionPane.showInputDialog(this, "Enter Cost", "New Option", JOptionPane.QUESTION_MESSAGE);
            
            long clong = 0;
            double cdouble = Double.parseDouble(cost);
            clong = (long)(cdouble * 100);
            store.add(new Option(name, clong));
        }
        catch(Exception e)
        {
        }
    }

    protected  void onInsertComputerClick()
    {
        String name = JOptionPane.showInputDialog(this, "Computer name", "New Computer", JOptionPane.QUESTION_MESSAGE);
        String model = JOptionPane.showInputDialog(this, "Computer Model", "New Computer", JOptionPane.QUESTION_MESSAGE);

        Object[] options = store.options();
        JComboBox<Object> comboBox = new JComboBox<>(options);

        for (Object o : options) 
        {
            Option i = (Option) o;
            comboBox.addItem(i);
        }

        Object[] message = {"Select options for the computer:",comboBox};

        JFrame confirmation = new JFrame();
        int result = JOptionPane.showConfirmDialog(confirmation, message, "Options", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Computer computer = new Computer(name, model);
            Option option = (Option) comboBox.getSelectedItem();
            computer.addOption(option);
            store.add(computer);
        }
    }

    protected void onViewClick(Record record)
    {
        
        String header = "";
        StringBuilder string = new StringBuilder();  
        switch(record)
        {
            case CUSTOMER:
                header = "Customers";
                for(var i : store.customers())
                {
                    string.append(i.toString());
                    
                }
                break;
            case OPTION:
                header = "Options";
                for(var i : store.options())
                {
                    string.append(i.toString());
                }
                break;
            case COMPUTER:
                header = "Computers";
                for(var i : store.computers())
                {
                    string.append(i.toString());
                }
                break;
            default:
                display.setText("Invalid Displays Type: " + display);
        }
        display.setText("<html><p><font size=+4>" + header +
                        "</font></p></br><ol><font size=+2><li>" + string.toString() +
                        "</li><li></li></font></ol></html>"); 
    }

    protected void onAboutClick() // Display About dialog
    {    
        /*            
        JLabel logo = null;
        try {
            BufferedImage myPicture = ImageIO.read(new File("128px-Pyramidal_matches.png"));
            logo = new JLabel(new ImageIcon(myPicture));
        } catch(IOException e) {
        }*/
        
        JLabel title = new JLabel("<html>"
        + "<p><font size=+4>ELSA</font></p>"
        + "<p><font size=+4>Exceptional Laptops and Supercomputers Always</font></p>"
        + "<p>Version 0.2</p>"
        + "</html>",
          SwingConstants.CENTER);

        JLabel artists = new JLabel("<html>"
        + "<br/><p>Copyright 2017-2023 by George F. Rice</p>"
        + "<p>Licensed under Gnu GPL 3.0</p><br/>"
        + "<p><font size=-2>Add Customer icon based on work by Dreamstate per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icon/user_3114957</font></p>"
        + "<p><font size=-2>View Customers icon based on work by Ilham Fitrotul Hayat per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icon/group_694642</font></p>"
        + "<p><font size=-2>Add Option icon based on work by Freepik per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icon/quantum-computing_4103999</font></p>"
        + "<p><font size=-2>View Options icon based on work by Freepik per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icon/network_9094450</font></p>"
        + "<p><font size=-2>Add Computer icon based on work by Freepik per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icon/laptop_689396</font></p>"
        + "<p><font size=-2>View Computers icon based on work by Futuer per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icon/computer-networks_9672993</font></p>"
        + "</html>");
          
         JOptionPane.showMessageDialog(this, 
             new Object[]{title, artists},
             "ELSA",
             JOptionPane.PLAIN_MESSAGE
         );
    }
}
