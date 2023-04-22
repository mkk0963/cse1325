package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

public class Option implements Saveable // inheritance is in the future!!
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

    @Override
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
        bw.write("" + cost + '\n');
    }

    public Option(BufferedReader br) throws IOException
    {
        this.name = br.readLine();
        this.cost = Long.parseLong(br.readLine());
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

    @Override
    public int hashCode()
    {
        return Objects.hash(name, cost);
    }
}
