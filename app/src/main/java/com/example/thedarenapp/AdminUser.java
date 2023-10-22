package com.example.thedarenapp;

public class AdminUser extends User {
    private static final long serialVersionUID = 1L;

    private String role;

    // constructor
    public AdminUser(String email, String password, String role) {
        super(email, password);
        this.role = role;
    }

    // getters
    public String getRole() {
        return role;
    }

    // setters
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AdminUser{" +
                "username='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
