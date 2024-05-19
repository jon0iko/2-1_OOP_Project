package org.example.salon.Database.Model;


public class User {
    private int UserID;
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String phone;
    private String role;
    private int points;

    public User(String userName, String fullname, String email, String password, String phone, String role) {
        this.username = userName;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public User(int id, String userName, String fullname, String email, String phone, String role) {
        this.username = userName;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.UserID = id;
    }

    public User(int id, String userName, String fullname, String email, String phone, String role, int points) {
        this.username = userName;
        this.fullname = fullname;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.UserID = id;
        this.points = points;
    }

    public int getUserID() {
        return UserID;
    }

    public String getUserName() {
        return username;
    }

    public String getFullName() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getRole() {
        return role;
    }

    public int getPoints() {
        return points;
    }



    public void setUserName(String name) {
        this.username = name;
    }

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void addPoints(int points) {
        this.points += points;
    }

}
