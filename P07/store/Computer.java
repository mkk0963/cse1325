package store;

import java.util.ArrayList;

public class Computer
{
    private String name;
    private String model;
    private ArrayList<Option> options = new ArrayList<>();
    
    public Computer(String name, String model)
    {
        this.name = name;
        this.model = model;
    }

    public void addOption(Option option)
    {
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

    public String toString()
    {
        StringBuilder computer = new StringBuilder(name + " (" + model + ") ");
        for(Option index : options)
        {
            computer.append(index);
        }

        return computer.toString();
    }

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
}