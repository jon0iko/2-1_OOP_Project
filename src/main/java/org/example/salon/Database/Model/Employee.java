package org.example.salon.Database.Model;


public class Employee extends User{
    
    public Employee(String username, String fullname, String email, String password, String phone) {
        super(username, fullname, email, password, phone, "employee");
    }
}
