import java.util.ArrayList;

public class Store
{
    private static ArrayList<Product> products = new ArrayList<>();
    //private static ArrayList<Product> shoppingCart = new ArrayList<>();

    public static void main(String[] args)
    {
        System.out.println("========================");
        System.out.println("  Welcome to the Store");
        System.out.println("========================");
    
        products.add(new Taxfree("Milk", 2.851));
        products.add(new Taxfree("Bread", 1.991));
        products.add(new Taxfree("Cheese", 0.851));
        products.add(new Taxfree("Eggs", 6.951));

        products.add(new Taxed("Ice cream", 4.951));
        products.add(new Taxed("Poptarts", 3.491));
        products.add(new Taxed("Oreos", 5.991));
        
        for(int i = 0; i < products.size(); ++i)
        {
            System.out.printf("%d)", i);
            System.out.println(products.get(i));
        }
        
    }
}
