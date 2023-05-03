package store;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

public class Computer implements Saveable
{
    private String name;
    private String model;
    private ArrayList<Option> options = new ArrayList<>();
    private boolean deprecated = false;
    
    public Computer(String name, String model)
    {
        this.name = name;
        this.model = model;
    }

    public boolean isDeprecated() 
    {
        return deprecated;
    }
    
    public void setDeprecated(boolean deprecated) 
    {
        this.deprecated = deprecated;
    }

    @Override
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
        bw.write(model + '\n');

        bw.write("" + options.size() + '\n');
        for(Option opt : options) opt.save(bw);
    }

    public Computer(BufferedReader br) throws IOException
    {
        this.name = br.readLine();
        this.model = br.readLine();

        int numOpt = Integer.parseInt(br.readLine());
        while(numOpt-- > 0) options.add(new Option(br));
    }

    public void addOption(Option option)
    {
        if(option.isDeprecated())
        {
            throw new IllegalArgumentException("Deprecated options cannot be added to existing computers");
        }
        options.add(option);
    }

    public long cost()
    {
        long sum = 0;

        for(int index = 0; index < options.size(); index++)
        {
            sum += options.get(index).cost();
        }

        return sum;
    }

    @Override
    public String toString()
    {
        StringBuilder computer = new StringBuilder(name + " (" + model + ") ");
        for(Option index : options)
        {
            computer.append(index);
        }

        return computer.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if((o == null) ||  !(o instanceof Computer))
        {
            return false;
        }
        Computer computers = (Computer)o;
        return name.equals(computers.name) && options.equals(computers.options);

        //  Improve!! not sure if this is correct. 
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, model, options);
    }
}