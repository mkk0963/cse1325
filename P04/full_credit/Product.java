public abstract class Product
{
    protected double cost;
    protected String name;

    public Product(String name, double cost)
    {
        this.name = name;
        this.cost = cost;

        if(cost < 0)
        {
            throw new RuntimeException("cost cannot be negative!");
        }
    }

    public abstract double price();

    @Override
    public String toString()
    {
        // finish building a better  string that matches the output
        StringBuilder items = new StringBuilder(name + " ($");
        items.append(String.format("%.2f) \t\t\t $%2.2f ", cost, price()));
   
        return items.toString();
    }
}