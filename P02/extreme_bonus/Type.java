public enum Type
{
    Snake(30), Leopard(20), Monkey(40), Okapi(20); 

    private double lifespan;

    private Type(double lifespan)
    {
        this.lifespan = lifespan;
    }

    public double lifespan()
    {
        return lifespan;
    }
}
