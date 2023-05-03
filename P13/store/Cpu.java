package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

public class Cpu extends Option 
{
    private double gigaHz;

    public Cpu(String name, long cost, double gigaHz) 
    {
        super(name, cost);
        this.gigaHz = gigaHz;
    }

    public double getGigaHz() 
    {
        return gigaHz;
    }

    public void setGigaHz(double gigaHz) 
    {
        this.gigaHz = gigaHz;
    }

    @Override
    public void save(BufferedWriter bw) throws IOException 
    {
        bw.write("CPU" + '\n');
        super.save(bw);
        bw.write("" + gigaHz + '\n');
    }

    public Cpu(BufferedReader br) throws IOException 
    {
        super(br);
		this.gigaHz = Double.parseDouble(br.readLine());
    }

    @Override
    public String toString() 
    {
        return super.toString() + gigaHz + " GHz";
    }

    @Override
    public boolean equals(Object o) 
    {
        if(this == o) 
        {
            return true;
        }
        if((o == null) || !(o instanceof Cpu)) 
        {
            return false;
        }
        if(!super.equals(o)) 
        {
            return false;
        }
        Cpu cpu = (Cpu)o;
        return Double.compare(cpu.gigaHz, gigaHz) == 0;
        }

    @Override
    public int hashCode() 
    {
        return Objects.hash(super.hashCode(), gigaHz);
    }
}
