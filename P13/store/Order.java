package store;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

public class Order implements Saveable
{
    private static long nextOrderNumber = 0;
    private long orderNumber = nextOrderNumber++;
    private Customer customer;
    private ArrayList<Computer> computers = new ArrayList<>();

    public Order(Customer customer)
    {
        this.customer = customer;
    }

    @Override
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write("" + nextOrderNumber + '\n');
        bw.write("" + orderNumber + '\n');
        customer.save(bw);

        bw.write("" + computers.size() + '\n');
        for(Computer comp : computers) comp.save(bw);
    }


    public Order(BufferedReader br) throws IOException
    {
        nextOrderNumber = Long.parseLong(br.readLine());
        this.orderNumber = Long.parseLong(br.readLine());
        this.customer = new Customer(br);

        int numComp = Integer.parseInt(br.readLine());
        while(numComp-- > 0) computers.add(new Computer(br));
    }

    public void addComputer(Computer computer)
    {
        computers.add(computer);
    }

    public long cost()
    {
        long total =  0;
        
        for(Computer index : computers)
        {
            total += index.cost();
        }
        return total;
    }

    @Override
    public String toString()
    {
        StringBuilder order = new StringBuilder("Order " + orderNumber + " for " + customer);
        for(Computer index : computers)
        {
            order.append(index);
        }
        order.append("Total cost: $" + cost()/100.0);

        return order.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if((o == null) ||  !(o instanceof Order))
        {
            return false;
        }
        Order orders = (Order)o;
        return customer.equals(orders.customer) && computers.equals(orders.computers);
        
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(customer, computers);
    }
}
