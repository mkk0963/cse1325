import java.util.ArrayList;
import java.util.Scanner;

public class MyPets
{
    public static void main(String[] args)
    {
        ArrayList<Pet> pet = new ArrayList<Pet>(1);
        Scanner in = new Scanner(System.in);

        System.out.print("Would you like to add a pet? press y(yes) to add OR n(no) to quit: ");
        char yes = in.nextLine().toLowerCase().charAt(0);

        while(yes == 'y')
        {
            System.out.println();
            System.out.print("Enter your pet's name: ");
            String name = in.nextLine();
    
            System.out.println();
            System.out.print("What kind of pet is it? Snake, Leopard, Monkey, or Okapi only: ");
            String kind = in.nextLine();
            Type type = Type.valueOf(kind.substring(0,1).toUpperCase() + kind.substring(1).toLowerCase());
    
            System.out.println();
            System.out.print("Enter your pet's age: ");
            String tempAge = in.nextLine();
            double age = Double.valueOf(tempAge);


            pet.add(new Pet(name, age, type));

            System.out.println();
            System.out.print("press y(yes) to add another pet OR n(no) to quit: ");
            yes = in.nextLine().toLowerCase().charAt(0);
        }

        System.out.println();
        for(Pet index : pet)
        {
            System.out.println(index);
        }

        System.out.println();
        System.out.printf("The size of the array is %d", pet.size());
    }
}