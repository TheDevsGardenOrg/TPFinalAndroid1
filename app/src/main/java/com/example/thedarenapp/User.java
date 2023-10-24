package com.example.thedarenapp;
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String email;
    private String password;

    // constructor
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    // setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}