import java.util.ArrayList;

public class Order
{
    private static long nextOrderNumber = 0;
    private long orderNumber = nextOrderNumber++;
    private Customer customer;
    private ArrayList<Computer> computers = new ArrayList<>();

    public Order(Customer customer)
    {
        this.customer = customer;
    }

    public void addComputer(Computer computer)
    {
        computers.add(computer);
    }

    public String toString()
    {
        StringBuilder order = new StringBuilder("Order " + orderNumber + " for " + customer);
        for(Computer index : computers)
        {
            order.append(index);
        }

        return order.toString();
    }

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

}
