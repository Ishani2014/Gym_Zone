package com.example.myapplication3345;

import com.google.firebase.database.Exclude;
import java.io.Serializable;

public class Employee implements Serializable
{
    @Exclude
    private String key;
    private String name;
    private String position;
    private String email;
    private String address;
    private String plan;
    public Employee(){}
    public Employee(String name, String position, String email,String address, String plan)
    {
        this.name = name;
        this.position = position;
        this.email = email;
        this.address = address;
        this.plan = plan;
    }

    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getPosition()
    {
        return position;
    }
    public void setPosition(String position)
    {
        this.position = position;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getPlan()
    {
        return plan;
    }
    public void setPlan(String plan)
    {
        this.plan = plan;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }
}
