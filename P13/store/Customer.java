package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Objects;

public class Customer implements Comparable<Customer>, Saveable
{
    private String name;
    private String email;
    
    public Customer(String name, String email)
    {
        this.name = name;
        this.email = email;

        int index1 = email.indexOf("@"); // returns -1 if not found
        int index2 = email.indexOf(".", index1);

        if(index1 == -1 & index2 == -1)
        {
            throw new IllegalArgumentException(" ⚠️ " + email + " is not a valid email ⚠️ ");
        }
    }

    @Override
    public void save(BufferedWriter bw) throws IOException
    {
        bw.write(name + '\n');
        bw.write(email + '\n');
    }

    public Customer(BufferedReader br) throws IOException
    {
        this.name = br.readLine();
        this.email = br.readLine();
    }

    @Override
    public String toString()
    {
        return name + " (" + email + ") ";
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o)
        {
            return true;
        }
        if((o == null) ||  !(o instanceof Customer))
        {
            return false;
        }
        Customer customers = (Customer)o;
        return email.equals(customers.email);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, email);
    }

    @Override
    public int compareTo(Customer cust) 
    {
        int result = this.name.compareTo(cust.name);

        if (result != 0) 
        {
            return result;
        } 
        else 
        {
            return this.email.compareTo(cust.email);
        }
    }
}