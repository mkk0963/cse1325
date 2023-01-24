import java.util.Scanner;

public class Hello
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("What is your name? ");
        String name = in.nextLine();
        System.out.printf("Hello, %s!", name); // alternative : System.out.print("Hello, " + name + "!");
        in.close(); // to remove the "ressource leak: 'in' is never closed" warning
    }
}