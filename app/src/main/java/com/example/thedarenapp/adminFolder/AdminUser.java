package com.example.thedarenapp.adminFolder;

import com.example.thedarenapp.userJava.User;

public class AdminUser extends User {
    private static final long serialVersionUID = 1L;

    private Boolean role;

    // constructor
    public AdminUser(String email, String password, Boolean role) {
        super(email, password);
        this.role = role;
    }

    // getters
    public Boolean getRole() {
        return role;
    }

    // setters
    public void setRole(Boolean role) {
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
