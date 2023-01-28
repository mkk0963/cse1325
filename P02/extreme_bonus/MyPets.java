//import java.util.ArrayList;
import java.util.Scanner;

public class MyPets
{
    public static void main(String[] args)
    {
        //ArrayList<Pet> pet = new ArrayList<Pet>();

        Scanner in = new Scanner(System.in);

        System.out.print("Enter your pet's name: ");
        String name = in.nextLine();

        System.out.print("What kind of pet is it? ");
        String kind = in.nextLine();
        Type type = Type.valueOf(kind.substring(0,1).toUpperCase() + kind.substring(1));

        System.out.print("Enter your pet's age: ");
        double age = in.nextDouble();

        Pet[] pet = new Pet[4];
        pet[0] = new Pet(name, age, type);
        pet[1] = new Pet("Motema", 6, Type.Monkey);
        pet[2] = new Pet("Mboka", 2, Type.Snake);
        pet[3] = new Pet("Congo", 3, Type.Leopard);

        for(Pet index : pet)
        {
            System.out.println(index);
        }
    }
}