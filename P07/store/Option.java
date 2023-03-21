package store;

public class Option // inheritance is in the future!!
{
    protected String name;
    protected long cost;

    public Option(String name, long cost)
    {
        this.name = name;
        this.cost = cost;

        if(cost < 0)
        {
            throw new IllegalArgumentException( " ⚠️ " + cost + " cannot be negative ⚠️ ");
        }
    }

    public long cost()
    {
        return cost;
    }

    @Override
    public String toString()
    {
        return name + " ($" + (cost/100.0) + ") ";
    }


    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if((o == null) ||  !(o instanceof Option))
        {
            return false;
        }
        if(o instanceof Long)
        {
            return cost == (Long)o;
        }
        Option options = (Option)o;
        return name.equals(options.name) && Long.valueOf(cost).equals(options.cost);
    
    }
}
