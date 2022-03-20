package com.example.myapplication3345;

import java.io.Serializable;

public class ContactModel implements Serializable {
    String name,number,email,address;

    public  ContactModel(String name,String number,String email,String address){
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
    }
}
