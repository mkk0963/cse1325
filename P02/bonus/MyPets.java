public class MyPets
{
    public static void main(String[] args)
    {
        Pet[] pet = new Pet[4];
        pet[0] = new Pet("Mokonzi", 5.2, Type.Okapi);
        pet[1] = new Pet("Motema", 6, Type.Monkey);
        pet[2] = new Pet("Mboka", 2.5, Type.Snake);
        pet[3] = new Pet("Congo", 3.8, Type.Leopard);

        for(Pet index : pet)
        {
            System.out.println(index);
        }
    }
}