public class Pet 
{
    private String name;
    private double age;
    private Type type;
    final static double humanLifespan = 80;

    public Pet (String name, double age, Type type)
    {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public double humanAge()
    {
        double humanAge = humanLifespan * age / type.lifespan();
        return humanAge;
    }

    @Override
    public String toString()
    {
        return name + " is a " + type + " of age " + age + " or " + humanAge() + " in human-equivalent years";
    }
}