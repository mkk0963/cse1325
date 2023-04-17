package gui;

import javax.swing.JFrame;           // for main window
import javax.swing.JOptionPane;      // for standard dialogs
import javax.swing.JPanel;
// import javax.swing.JDialog;          // for custom dialogs (for alternate About dialog)
import javax.swing.JMenuBar;         // row of menu selections
import javax.swing.JMenu;            // menu selection that offers another menu
import javax.swing.JMenuItem;        // menu selection that does something
import javax.swing.JToolBar;         // row of buttons under the menu
import javax.swing.JButton;          // regular button
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.imageio.ImageIO;
//import javax.swing.JToggleButton;    // 2-state button
//import javax.swing.BorderFactory;    // manufacturers Border objects around buttons
import javax.swing.Box;              // to create toolbar spacer
import javax.swing.BoxLayout;
//import javax.swing.UIManager;        // to access default icons
import javax.swing.JLabel;           // text or image holder
import javax.swing.ImageIcon;        // holds a custom icon
import javax.swing.SwingConstants;   // useful values for Swing method calls


//import javax.imageio.ImageIO;        // loads an image from a file
import java.io.File;                 // opens a file
import java.awt.BasicStroke;
//import java.io.IOException;          // reports an error reading from a file 
import java.awt.BorderLayout;        // layout manager for main window
import java.awt.Color;
//import java.awt.FlowLayout;        // layout manager for About dialog
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.awt.Color;               // the color of widgets, text, or borders
import java.awt.Font;                // rich text in a JLabel or similar widget
//import java.awt.image.BufferedImage; // holds an image loaded from a file

import store.Customer;
import store.Option;
import store.Computer;
//import store.Order;
import store.Store;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException; 

enum Record
{
    CUSTOMER, OPTION, COMPUTER, ORDER;
}

public class MainWin extends JFrame 
{
    private Store store;
    private JLabel display;
    private File filename;

    public MainWin(String title) 
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 300);
        
        // /////// ////////////////////////////////////////////////////////////////
        // M E N U
        // Add a menu bar to the PAGE_START area of the Border Layout

        JMenuBar menubar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem newWin = new JMenuItem("New");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem quit = new JMenuItem("Quit");

        JMenu insert = new JMenu("Insert");
        JMenuItem insertCust  = new JMenuItem("Customer");
        JMenuItem  insertOpt = new JMenuItem("Option");
        JMenuItem insertComp = new JMenuItem("Computer");
        JMenuItem insertOrd = new JMenuItem("Order");

        JMenu view = new JMenu("View");
        JMenuItem viewCust = new JMenuItem("Customers");
        JMenuItem viewOpt = new JMenuItem("Options");
        JMenuItem viewComp = new JMenuItem("Computers");
        JMenuItem viewOrd = new JMenuItem("Orders");

        JMenu help = new JMenu("Help");
        JMenuItem about = new JMenuItem("About");

        newWin.addActionListener(event -> onNewClick());
        open.addActionListener(event -> onOpenClick());
        save.addActionListener(event -> onSaveClick());
        saveAs.addActionListener(event -> onSaveAsClick());
        quit.addActionListener(event -> onQuitClick());

        insertCust.addActionListener(event -> onInsertCustomerClick());
        insertOpt.addActionListener(event -> onInsertOptionClick());
        insertComp.addActionListener(event -> onInsertComputerClick());
        insertOrd.addActionListener(event -> onInsertOrderClick());

        viewCust.addActionListener(event -> onViewClick(Record.CUSTOMER));
        viewOpt.addActionListener(event -> onViewClick(Record.OPTION));
        viewComp.addActionListener(event -> onViewClick(Record.COMPUTER));
        viewOrd.addActionListener(event -> onViewClick(Record.ORDER));

        about.addActionListener(event -> onAboutClick());

        menubar.add(file);
        menubar.add(insert);
        menubar.add(view);
        menubar.add(help);
        setJMenuBar(menubar);

        file.add(newWin);
        file.add(open);
        file.add(save);
        file.add(saveAs);
        file.add(quit);
        insert.add(insertCust);
        insert.add(insertOpt);
        insert.add(insertComp);
        insert.add(insertOrd);
        view.add(viewCust);
        view.add(viewOpt);
        view.add(viewComp);
        view.add(viewOrd);
        help.add(about);

        // ///////////// //////////////////////////////////////////////////////////
        // T O O L B A R 
        // Add a toolbar to the PAGE_START region below the menu
        
        JToolBar toolbar = new JToolBar("ELSA");

        JButton newButton = new JButton(new ImageIcon("gui/resources/new.png")); // add image
        newButton.setActionCommand("New Store");
        newButton.setToolTipText("New Store");
        toolbar.add(newButton);
        newButton.addActionListener(event -> onNewClick());

        toolbar.add(Box.createHorizontalStrut(30));
        
        JButton openButton = new JButton(new ImageIcon("gui/resources/open.png")); // add image
        openButton.setActionCommand("Open a specific file");
        openButton.setToolTipText("Open a specific file");
        toolbar.add(openButton);
        openButton.addActionListener(event -> onOpenClick());

        JButton saveButton = new JButton(new ImageIcon("gui/resources/save.png")); // add image
        saveButton.setActionCommand("Save to a default file");
        saveButton.setToolTipText("Save to a default file");
        toolbar.add(saveButton);
        saveButton.addActionListener(event -> onSaveClick());

        JButton saveAsButton = new JButton(new ImageIcon("gui/resources/save_as.png")); // add image
        saveAsButton.setActionCommand("Save to a specific file with a name");
        saveAsButton.setToolTipText("Save to a specific file with a name");
        toolbar.add(saveAsButton);
        saveAsButton.addActionListener(event -> onSaveAsClick());

        toolbar.add(Box.createHorizontalStrut(25));

        JButton customer = new JButton(new ImageIcon("gui/resources/add_customer.png"));
        customer.setActionCommand("Insert Customer");
        customer.setToolTipText("Provide a name and email for a new customer");
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

        JButton order = new JButton(new ImageIcon("gui/resources/add_order.png")); // add path
        order.setActionCommand("Insert Order");
        order.setToolTipText("Insert a new order");
        toolbar.add(order);
        order.addActionListener(event -> onInsertOrderClick());

        toolbar.add(Box.createHorizontalStrut(25));

        JButton vCustomer = new JButton(new ImageIcon("gui/resources/view_customers.png"));
        vCustomer.setActionCommand("View Customer");
        vCustomer.setToolTipText("View the list of customers");
        toolbar.add(vCustomer);
        vCustomer.addActionListener(event -> onViewClick(Record.CUSTOMER));

        JButton vOption = new JButton(new ImageIcon("gui/resources/view_options.png"));
        vOption.setActionCommand("View Option");
        vOption.setToolTipText("View the list of options");
        toolbar.add(vOption);
        vOption.addActionListener(event -> onViewClick(Record.OPTION));

        JButton vComputer = new JButton(new ImageIcon("gui/resources/view_computers.png"));
        vComputer.setActionCommand("View Computers");
        vComputer.setToolTipText("View the list of computers");
        toolbar.add(vComputer);
        vComputer.addActionListener(event -> onViewClick(Record.COMPUTER));

        JButton vOrder = new JButton(new ImageIcon("gui/resources/view_orders.png")); // add image path
        vOrder.setActionCommand("View Orders");
        vOrder.setToolTipText("View the list of orders");
        toolbar.add(vOrder);
        vOrder.addActionListener(event -> onViewClick(Record.ORDER));

        toolbar.addSeparator();

        getContentPane().add(toolbar, BorderLayout.PAGE_START);

        // /////////////////////////// ////////////////////////////////////////////
        // D I S P L A Y
        // Provide a text entry box to show the remaining computers
        display = new JLabel();
        display.setVerticalAlignment(JLabel.TOP);
        display.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(display, BorderLayout.CENTER);
        
        // Make everything in the JFrame visible
        setVisible(true);
        store = new Store("KIMEX 243");
    }
    
    // Listeners

    protected void onNewClick()
    {
        int newStore = JOptionPane.showConfirmDialog(this, "All saved data will be wiped, please confirm", "Confirm Create New Store", JOptionPane.YES_NO_OPTION);
        if(newStore == JOptionPane.YES_OPTION)
        {
            String name = JOptionPane.showInputDialog(this, "Store Name", "Input", JOptionPane.QUESTION_MESSAGE);
            store = new Store(name);
            MainWin newWindow = new MainWin(store.name());
            newWindow.setVisible(true);
        }
       
    }

    protected void onOpenClick()
    {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter elsaFiles = new FileNameExtensionFilter("Elsa files", "elsa");
        fc.addChoosableFileFilter(elsaFiles);
        fc.setFileFilter(elsaFiles);

        int result = fc.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            filename = fc.getSelectedFile();

            try (BufferedReader br = new BufferedReader(new FileReader(filename)))
            {
                store = new Store(br);
                //MainWin newWindow = new MainWin(store.name());
                //newWindow.setVisible(true);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Unable to open/load " + filename + '\n' + e, "Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    protected void onSaveClick()
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            store.save(bw);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Unable to open/write " + filename + '\n' + e, "Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected void onSaveAsClick()
    {
        final JFileChooser fc = new JFileChooser(filename);
        FileFilter elsaFiles = new FileNameExtensionFilter("Elsa files", "elsa");
        fc.addChoosableFileFilter(elsaFiles);
        fc.setFileFilter(elsaFiles);

        int result = fc.showSaveDialog(this);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            filename = fc.getSelectedFile();
            if(!filename.getAbsolutePath().endsWith(".elsa"))
            {
                filename = new File(filename.getAbsolutePath() + ".elsa");
            }
            onSaveClick();
        }
    }

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
            onViewClick(Record.CUSTOMER);
        }
        catch(NullPointerException e) 
        {

        }
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(this, e,"Customer Not Created", JOptionPane.ERROR_MESSAGE);
        }
    }

    protected  void onInsertOptionClick()
    {
        try 
        { 
            store.add(new Option(JOptionPane.showInputDialog(this, "Option name", "New Option", JOptionPane.QUESTION_MESSAGE),
                (long) (100.0 * Double.parseDouble(JOptionPane.showInputDialog(this, "Option cost", "New Option", JOptionPane.QUESTION_MESSAGE)))));

            onViewClick(Record.OPTION);
        } 
        catch(NullPointerException e) 
        {

        } 
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(this, e, 
                "Customer Not Created", JOptionPane.ERROR_MESSAGE);
        }

        /*try
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
        }*/
    }

    protected void onInsertComputerClick()
    {
        try 
        { 
            Computer c = new Computer(JOptionPane.showInputDialog(this, "Computer name", "New Computer", JOptionPane.QUESTION_MESSAGE),
                JOptionPane.showInputDialog(this, "Computer model", "New Computer", JOptionPane.QUESTION_MESSAGE));

            JComboBox<Object> cb = new JComboBox<>(store.options());
            int optionsAdded = 0; // Don't add computers with no options

            while(true) 
            {
                int button = JOptionPane.showConfirmDialog(this, cb, "Another Option?", JOptionPane.YES_NO_OPTION);

                if(button != JOptionPane.YES_OPTION) break;
                c.addOption((Option) cb.getSelectedItem());
                ++optionsAdded;
            }

            if(optionsAdded > 0) 
            {
                store.add(c);
                onViewClick(Record.COMPUTER);
            }
        } 
        catch(NullPointerException e) 
        {

        } 
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(this, e, "Computer Not Created", JOptionPane.ERROR_MESSAGE);
        }    

        /*String name = JOptionPane.showInputDialog(this, "Computer name", "New Computer", JOptionPane.QUESTION_MESSAGE);
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
        }*/
    }

    protected void onInsertOrderClick()
    {

    }

    protected void onViewClick(Record record)
    {
        String header = null;
        Object[] list = null;

        if(record == Record.CUSTOMER) 
        {
            header = "Our Beloved Customers";
            list = store.customers();
        }
        if(record == Record.OPTION) 
        {
            header = "Options for our SuperComputers";
            list = store.options();
        }
        if(record == Record.COMPUTER) 
        {
            header = "Computers for Sale - Cheap!";
            list = store.computers();
        }              
        if(record == Record.ORDER) 
        {
            header = "Orders Placed to Date";
            list = store.orders();
        }
                        
        StringBuilder sb = new StringBuilder("<html><p><font size=+2>" + header + "</font></p><br/>\n<ol>\n");
        for(Object i : list) sb.append("<li>" + i.toString().replaceAll("<","&lt;")
                                                            .replaceAll(">", "&gt;")
                                                            .replaceAll("\n", "<br/>") + "</li>\n");
        sb.append("</ol></html>");
        display.setText(sb.toString());

        /*String header = "";
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
                        "</li><li></li></font></ol></html>"); */
    }

    public class Canvas extends JPanel
    {
        private BufferedImage image;
        public Canvas()
        {
            setBackground(Color.WHITE);
            String imageFile = "gui/resources/buy.png";

            try 
            {
                image = ImageIO.read(new File(imageFile));
            } 
            catch (IOException e) 
            {
                throw new RuntimeException("Unable to load image from " + imageFile, e);
            }
        }
        public Dimension getPreferredSize()
        {
            return new Dimension(250,200);
        }

        @Override
        public void paintComponent(Graphics graphics)
        {
            super.paintComponent(graphics);
            Graphics2D g = (Graphics2D) graphics.create();
            g.setStroke(new  BasicStroke(3));
            g.drawString("ELSA, Cheap Computers!", 25, 20);

            int x = (getBounds().width  - image.getWidth())  / 2;
            int y = (getBounds().height - image.getHeight()) / 2;
            g.drawImage(image, x, y, this);

            Rectangle size = getBounds();                  // get canvas size
            final int oneThirdX  = 333 * size.width  / 1000;
            final int twoThirdsX = 666 * size.width  / 1000;
            final int halfX      = 500 * size.width  / 1000;
            final int oneThirdY  = 333 * size.height / 1000;
            final int twoThirdsY = 666 * size.height / 1000;
            //final int halfY      = 500 * size.height / 1000;

            g.setColor(Color.RED);                   // red with constant
            g.drawLine(oneThirdX, twoThirdsY, twoThirdsX, twoThirdsY); // --
        
            g.setColor(new Color(0, 255, 0));        // green with int
            g.drawLine(oneThirdX, twoThirdsY,  halfX, oneThirdY);      // /
        
            g.setColor(new Color(0.0f, 0.0f, 1.0f)); // blue with double
            g.drawLine(twoThirdsX, twoThirdsY, halfX, oneThirdY);      // 
                
        }
    }

    protected void onAboutClick() // Display About dialog
    {    

        JDialog about = new JDialog(this, "Elsa");
        about.setLayout(new BoxLayout(about.getContentPane(), BoxLayout.PAGE_AXIS));
        Canvas logo = new Canvas();
        logo.setAlignmentX(Canvas.LEFT_ALIGNMENT);
        about.add(logo);

        JPanel text = new JPanel();
        text.setLayout(new BoxLayout(text, BoxLayout.PAGE_AXIS));

        JLabel title = new JLabel("<html>"
          + "<p><font size=+4>ELSA</font></p>"
          + "</html>",
          SwingConstants.CENTER);
        text.add(title);

        JLabel subtitle = new JLabel("<html>"
          + "<p>Exceptional Laptops and Supercomputers Always</p>"
          + "</html>",
          SwingConstants.CENTER);
        text.add(subtitle);

        JLabel version = new JLabel("<html>"
          + "<p>Version 0.3</p>"
          + "</html>",
          SwingConstants.CENTER);
        text.add(version);

        JLabel artists = new JLabel("<html>"
        + "<br/><p>Copyright 2023 by Marcia K. Kimenyembo</p>"
        + "<p>Licensed under Gnu GPL 3.0</p><br/>"

        + "<br/><p><font size=-2>Add Customer icon based on work by Freepik per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/add-user</font></p>"

        + "<br/><p><font size=-2>View Customers icon based on work by Flat Icons per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/discovery</font></p>"

        + "<br/><p><font size=-2>Add Option icon based on work by Vectorslab per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/module</font></p>"

        + "<br/><p><font size=-2>View Options icon based on work by Flat Icons per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/embedded</font></p>"

        + "<br/><p><font size=-2>Add Computer icon based on work by Paul J. per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/add-file</font></p>"

        + "<br/><p><font size=-2>View Computers icon based on work by Freepik per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/network</font></p>"

        + "<br/><p><font size=-2>Add Order icon based on work by Vectorslab per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/shipping-and-delivery</font></p>"

        + "<br/><p><font size=-2>View Orders icon based on work by Freepik per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/order</font></p>"

        + "<br/><p><font size=-2>New icon based on work by Pixartist per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/tabs</font></p>"

        + "<br/><p><font size=-2>Open icon based on work by Flowicon per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/email</font></p>"

        + "<br/><p><font size=-2>Save icon based on work by Freepik per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/archive</font></p>"

        + "<br/><p><font size=-2>Save as icon based on work by Freepik per the Flaticon License</font></p>"
        + "<p><font size=-2>https://www.flaticon.com/free-icons/save</font></p>"

        + "</html>");
        text.add(artists);
        about.add(text);

        JPanel panel = new JPanel();
        JButton ok = new JButton("OK");
        ok.addActionListener(event -> about.setVisible(false));
        panel.add(ok);
        about.add(panel);

        about.add(Box.createVerticalStrut(10));

        about.pack();
        about.setVisible(true);
          
        //JOptionPane.showMessageDialog(this, new Object[]{title, subtitle, version, artists},"ELSA", JOptionPane.PLAIN_MESSAGE);
    }
}
