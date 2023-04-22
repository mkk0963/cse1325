package store;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

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
}