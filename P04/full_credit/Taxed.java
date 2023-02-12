public class Taxed extends Product
{
    private static double salesTaxRate = 0.0;

    public Taxed(String name, double cost)
    {
        super(name, cost);
    }

    public static void setTaxRate(double salesTax)
    {
        salesTaxRate = salesTax;
    }

    @Override
    public double price()
    {
        return cost * (1+salesTaxRate);
    }
}