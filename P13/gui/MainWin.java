package gui;

import javax.swing.JFrame;           // for main window
import javax.swing.JOptionPane;      // for standard dialogs
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
import javax.swing.ButtonGroup;
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
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
//import java.awt.Color;               // the color of widgets, text, or borders
import java.awt.Font;                // rich text in a JLabel or similar widget
//import java.awt.image.BufferedImage; // holds an image loaded from a file

import store.Customer;
import store.Option;
import store.Order;
import store.Computer;
import store.Cpu;
import store.Store;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;

enum Record
{
    CUSTOMER, OPTION, COMPUTER, ORDER;
}

public class MainWin extends JFrame 
{
    private Store store;
    private JLabel display;
    private File filename;
    private JLabel status;

    public MainWin(String title) 
    {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        
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
        JMenu viewDetails = new JMenu("Details");
        JMenuItem viewCust = new JMenuItem("Customers");
        JMenuItem viewOpt = new JMenuItem("Options");
        JMenuItem viewComp = new JMenuItem("Computers");
        JMenuItem viewOrd = new JMenuItem("Orders");
        JMenuItem viewDCust = new JMenuItem("Customers");

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
        viewDCust.addActionListener(event -> onViewCustomerDetailsClick());

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
        viewDetails.add(viewDCust);
        view.add(viewDetails);
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

        toolbar.add(Box.createHorizontalStrut(50));
        
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
        
        // Make the main window area scrollable
        JScrollPane scroll = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(800, 400));
        add(scroll, BorderLayout.CENTER);
        //add(display, BorderLayout.CENTER);

        // /////////////////////////// ////////////////////////////////////////////
        // S T A T U S  B A R

        status = new JLabel();
        status.setFont(new Font("SansSerif", Font.PLAIN, 12));
        getContentPane().add(status, BorderLayout.PAGE_END);
        
        // Make everything in the JFrame visible
        setVisible(true);
        store = new Store("KIMEX 243");
    }
    
    // Listeners

    private void setStatus(String message)
    {
        status.setText(message);
    }

    protected void onViewCustomerDetailsClick() 
    {
        setStatus("");
        Object[] customersArr = store.customers();
        JComboBox<Object> customerComboBox = new JComboBox<>(customersArr);
        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Customer:"));
        panel.add(customerComboBox);
    
        int result = JOptionPane.showConfirmDialog(this, panel, "Customer Details", JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) 
        {
            Customer selectedCustomer = (Customer) customerComboBox.getSelectedItem();
            if (selectedCustomer != null) 
            {
                display.setIcon(new ImageIcon(selectedCustomer.getImageFilename()));
                display.setText(selectedCustomer.toString());
            } else 
            {
                //display.setIcon(null);
            }
        }
    }

    protected void onNewClick()
    {
        setStatus("");

        int newStore = JOptionPane.showConfirmDialog(this, "All saved data will be wiped, please confirm", "Confirm Create New Store", JOptionPane.YES_NO_OPTION);
        if(newStore == JOptionPane.YES_OPTION)
        {
            String name = JOptionPane.showInputDialog(this, "Store Name", "Input", JOptionPane.QUESTION_MESSAGE);
            store = new Store(name);
            setStatus("New store created");
            //MainWin newWindow = new MainWin(store.name());
            //newWindow.setVisible(true);
            //onViewClick(Record.CUSTOMER);
        }
       
    }

    protected void onOpenClick()
    {
        setStatus("");

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
                setStatus("Opened " + filename.getName());
                //MainWin newWindow = new MainWin(store.name());
                //newWindow.setVisible(true);
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(this, "Unable to open/load " + filename + '\n' + e, "Failed", JOptionPane.ERROR_MESSAGE);
                setStatus("Failed to open " + filename.getName());
            }
        }
    }

    protected void onSaveClick()
    {
        setStatus("");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            store.save(bw);
            setStatus("Saved " + filename.getName());
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Unable to save/write " + filename + '\n' + e, "Failed", JOptionPane.ERROR_MESSAGE);
            setStatus("Failed to save " + filename.getName());
        }
    }

    protected void onSaveAsClick()
    {
        setStatus("");

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
            setStatus("Saved file as " + filename.getName());
        }
    }

    protected void onQuitClick() // Exit the game
    {
        setStatus("");
        System.exit(0);
    }  

    protected void onInsertCustomerClick()
    {
        setStatus("");

        try
        {
            JPanel panel = new JPanel(new GridLayout(5, 10));

            JTextField nameField = new JTextField();
            panel.add(new JLabel("Name"));
            panel.add(nameField);

            JTextField emailField = new JTextField();
            panel.add(new JLabel("Email"));
            panel.add(emailField);

            JTextField imageFilenameField = new JTextField();
            panel.add(new JLabel("Photo"));
            panel.add(imageFilenameField);

            JButton chooseImageButton = new JButton("Choose Image");
            panel.add(chooseImageButton);

            chooseImageButton.addActionListener(event -> 
            {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(this);

                if (result == JFileChooser.APPROVE_OPTION) 
                {
                    File selectedFile = fileChooser.getSelectedFile();
                    imageFilenameField.setText(selectedFile.getName());
                }
            });
    
            int result = JOptionPane.showConfirmDialog(this, panel, "New Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/resources/add_customer.png"));
    
            if (result == JOptionPane.OK_OPTION) 
            {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String imageFilename = imageFilenameField.getText().trim();
                store.add(new Customer(name, email, imageFilename));
                onViewClick(Record.CUSTOMER);
                setStatus("Created Customer " + name + " with email: " + email);
            }
        }
        catch(NullPointerException e)
        {

        }
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(this, e,"Customer Not Created", JOptionPane.ERROR_MESSAGE);
            setStatus("Failed to create a new customer");
        }
    }

    protected  void onInsertOptionClick()
    {
        setStatus("");

        try
        {
            JPanel panel = new JPanel(new GridLayout(5,10));

            ButtonGroup group = new ButtonGroup();
            JRadioButton cpu = new JRadioButton("CPU");
            JRadioButton other = new JRadioButton("Other");
            group.add(cpu);
            group.add(other);
            panel.add(cpu);
            panel.add(other);

            JTextField nameField = new JTextField();
            panel.add(new JLabel("Option name"));
            panel.add(nameField);

            JTextField costField = new JTextField();
            panel.add(new JLabel("Option cost"));
            panel.add(costField);

            JLabel label = new JLabel("GHz");
            JTextField ghzField = new JTextField();
            panel.add(label);
            panel.add(ghzField);

            int result = JOptionPane.showConfirmDialog(this, panel, "New Option", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/resources/add_option.png"));
            
            if(result == JOptionPane.OK_OPTION)
            {
                String name = nameField.getText().trim();
                long cost = (long) (100.0 * Double.parseDouble(costField.getText().trim()));

                if(cpu.isSelected())
                {
                    double gigaHz = Double.parseDouble(ghzField.getText().trim());
                    store.add(new Cpu(name, cost, gigaHz));
                }
                else
                {
                    store.add(new Option(name, cost));
                }
        
                onViewClick(Record.OPTION);
                setStatus("Created Option " + name);
            }
        }
        catch(NullPointerException e)
        {

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e, 
                "Option not created", JOptionPane.ERROR_MESSAGE);
            
                setStatus("Failed to create a new option");
        }
    }

    protected void onInsertComputerClick()
    {
        setStatus("");
        try
        {
            JPanel panel = new JPanel(new GridLayout(5,10));

            JTextField nameField = new JTextField();
            panel.add(new JLabel("Computer name"));
            panel.add(nameField);

            JTextField modelField = new JTextField();
            panel.add(new JLabel("Computer model"));
            panel.add(modelField);

            JComboBox<Object> cb = new JComboBox<>(store.options());
            int optionsAdded = 0;
            int result = JOptionPane.showConfirmDialog(this, panel, "New Computer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("gui/resources/add_computer.png"));

            if(result == JOptionPane.OK_OPTION)
            {
                String name = nameField.getText().trim();
                String model = modelField.getText().trim();
                Computer c = new Computer(name, model);

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
                    setStatus("Created Computer " + name + " with model: " + model);
                }
            }
        }
        catch(NullPointerException e)
        {

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, e, "Computer Not Created", JOptionPane.ERROR_MESSAGE);
            setStatus("Failed to create a new computer");
        }
    }

    protected void onInsertOrderClick()
    {
        setStatus("");
        Object[] customersArr = store.customers();
        Customer customer = null;

        if(customersArr.length == 0)
        {
            onInsertCustomerClick();
            return;
        }
        else if(customersArr.length == 1)
        {
            customer = (Customer) customersArr[0];
        }
        else 
        {
            JComboBox<Object> custBox = new JComboBox<>(customersArr);
            JPanel custJPanel = new JPanel(new GridLayout(2, 2));
            custJPanel.add(new JLabel("Order for which Customer?"));
            custJPanel.add(custBox);

            int result = JOptionPane.showConfirmDialog(this, custJPanel, "Select a Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.CANCEL_OPTION)
            {
                return;
            }

            customer = (Customer) custBox.getSelectedItem();
        }

        Order order = new Order(customer);

        int orderPlaced = 0;
        while(true)
        {
            Object[] computersArr = store.computers();
            JComboBox<Object> compBox = new JComboBox<>(computersArr);
            JPanel compPanel = new JPanel(new GridLayout(2, 2));
            compPanel.add(new JLabel(order.toString()));
            compPanel.add(compBox);

            int result = JOptionPane.showConfirmDialog(this, compPanel, "Current Order", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if(result == JOptionPane.CANCEL_OPTION)
            {
                break;
            }
            
           
            Computer computer = (Computer) compBox.getSelectedItem();
            order.addComputer(computer);
            result = JOptionPane.showConfirmDialog(this, "Do you want to place the order?", "Place Order", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(result == JOptionPane.YES_OPTION)
            {
                if(computersArr.length == 0)
                {
                    JOptionPane.showMessageDialog(this, "Order not placed. No computers available", "Error", JOptionPane.ERROR_MESSAGE);
                    setStatus("Failed to place a new order");
                    break;
                }
                else
                {
                    store.add(order);
                    ++orderPlaced;
                    setStatus("Created " + order);
                    //break;
                }
            }
            else
            {
                break;
            }
        } 

        if(orderPlaced > 0)
        {
            onViewClick(Record.ORDER);
        }
    }

    protected void onViewClick(Record record)
    {
        setStatus("");
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
        //display.setIcon(null);
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
        setStatus("");
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
          + "<p>Version 4.0</p>"
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
