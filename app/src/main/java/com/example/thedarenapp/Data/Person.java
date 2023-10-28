package com.example.thedarenapp.Data;

import androidx.annotation.NonNull;

public class Person extends User {
    private String firstName;
    private String lastName;
    private String birthday;
    private Address address; // Use the Address class instead of individual address fields
    private String profession;
    private String email;

    // constructor

    public Person(String email, String password, String firstName, String lastName, String birthday, Address address, String profession) {
        super(email,password);
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address; // Set the Address object
        this.profession = profession;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address;
    }

    public String getProfession() {
        return profession;
    }

    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
        ;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return getEmail() + ';' +
                getPassword() + ';' +
                firstName + ';' +
                lastName + ';' +
                birthday +';' +
                address +';' +// Use the Address class for the address field
                profession + ';';
    }
}