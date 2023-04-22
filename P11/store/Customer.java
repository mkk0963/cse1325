package store;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Customer
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
}