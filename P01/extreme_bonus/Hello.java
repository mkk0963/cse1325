public class Hello
{
    public static void main(String[] args)
    {
        String name = System.getProperty("user.name");
        System.out.printf("Hello, %s!", name);
    }
}