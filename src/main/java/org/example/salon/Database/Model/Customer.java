package org.example.salon.Database.Model;

public class Customer extends User{


    public Customer(String username, String fullname, String email, String password, String phone) {
        super(username, fullname, email, password, phone, "customer");
    }

}