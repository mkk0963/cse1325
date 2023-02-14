import java.util.ArrayList;
import java.util.Scanner;

public class Store
{
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<Product> shoppingCart = new ArrayList<>();

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        Taxed.setTaxRate(0.0825);

        products.add(new Taxfree("Beans", 2.850));
        products.add(new Taxfree("Rice", 3.320));
        products.add(new Taxfree("Water", 1.500));
        products.add(new Taxfree("Eggs", 6.950));

        products.add(new Taxed("Detergent", 4.95));
        products.add(new Taxed("Haribo", 3.49));
        products.add(new Taxed("Oreos", 5.99));

        while(true)
        {
           
            System.out.println("========================");
            System.out.println("  Welcome to the Store");
            System.out.println("========================");
            System.out.println();

            for(int i = 0; i < products.size(); i++)
            {
                System.out.printf("%d) ", i);
                System.out.println(products.get(i));
            }
            System.out.println();

            if(!(shoppingCart.isEmpty()))
            {
                double total = 0.0;
            
                System.out.println("Current Order");
                System.out.println("-------------");
                System.out.println();

                for(int index = 0; index < shoppingCart.size(); index++)
                {
                    System.out.println(shoppingCart.get(index));
                    total += shoppingCart.get(index).price();
                }

                System.out.printf("Total price: $%.2f", total);
                System.out.println();
            }

            System.out.println("Buy which product?");
            int item = in.nextInt(); in.nextLine();
            System.out.println();

            try
            {
                if(item >= 0)
                {
                    shoppingCart.add(products.get(item));
                }
                else
                {
                 break;
                } 
            }
            catch(IndexOutOfBoundsException e)
            {
                System.out.println(" ⚠️  This product is not on the Store's list. Try again. ⚠️");
                System.out.println();
            }
        }
    }
}
