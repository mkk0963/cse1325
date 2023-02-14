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
        StringBuilder items = new StringBuilder();
        items.append(String.format("%-10s($%.2f)\t\t\t$  %.2f ", name, cost, price()));

        return items.toString();
    }
}