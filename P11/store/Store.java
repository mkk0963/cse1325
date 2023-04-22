package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Store 
{
    // ///////////////////////////////////////////////////////////
    // Fields
    
    private String name;

    TreeSet<Customer> customers = new TreeSet<>();
    HashSet<Option> options = new HashSet<>();
    HashSet<Computer> computers = new HashSet<>();
    HashSet<Order> orders = new HashSet<>();

    public Store(String name) 
    {
        this.name = name;
    }

    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
        save(bw, customers);
        save(bw, options);
        save(bw, computers);
        save(bw, orders);
    }

    public Store(BufferedReader br) throws IOException
    {
        this.name = br.readLine();

        int numCust = Integer.parseInt(br.readLine());
        while(numCust-- > 0) customers.add(new Customer(br));

        int numOpt = Integer.parseInt(br.readLine());
        while(numOpt-- > 0) options.add(new Option(br));

        int numComp = Integer.parseInt(br.readLine());
        while(numComp-- > 0) computers.add(new Computer(br));

        int numOrd = Integer.parseInt(br.readLine());
        while(numOrd-- > 0) orders.add(new Order(br));
    }

    private <T extends Saveable> void save(BufferedWriter bw, Set<T> set) throws IOException 
    {
        bw.write("" + set.size() + '\n');
        for (var element : set) 
        {
            element.save(bw);
        }
    }

    public String name() 
    {
        return this.name;
    }
    
    // ///////////////////////////////////////////////////////////
    // Customers
    public void add(Customer customer) 
    {
        if(!customers.contains(customer)) customers.add(customer);
    }
    public Object[] customers() 
    {
        return this.customers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Options
    public void add(Option option) 
    {
        if(!options.contains(option)) options.add(option);
    }
    public Object[] options() 
    {
        return this.options.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Computers
    public void add(Computer computer) 
    {
        if(!computers.contains(computer)) computers.add(computer);
    }
    public Object[] computers() 
    {
        return this.computers.toArray();
    }
    
    // ///////////////////////////////////////////////////////////
    // Orders
    public void add(Order order) 
    {
        if(!orders.contains(order)) orders.add(order);
    }
    public Object[] orders() 
    {
        return this.orders.toArray();
    }
}
