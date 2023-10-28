package com.example.thedarenapp.DataHandler;

import com.example.thedarenapp.Data.Address;

public class userTemplate {
    private String email;
    private String firstName;
    private String lastName;
    private String profession;
    private Address address; // Assuming Address is another class you've defined

    public userTemplate(String email, String firstName, String lastName, String profession, Address address) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
        this.address = address;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getProfession() {
        return profession;
    }
    public Address getAddress() {
        return address;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "userTemplate{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profession='" + profession + '\'' +
                ", address=" + address +
                '}';
    }
}