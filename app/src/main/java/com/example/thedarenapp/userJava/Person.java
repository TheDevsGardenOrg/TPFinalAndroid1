package com.example.thedarenapp.userJava;

public class Person extends User {
    private String firstName;
    private String lastName;
    private String birthday;
    private Address address; // Use the Address class instead of individual address fields
    private String profession;
    private String email;

    // constructor

    public Person(String email, String password, String firstName, String lastName, String birthday, Address address, String profession) {
        super(email, password);
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
        this.setEmail(email);
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

    public String toString() {
        return firstName + ';' + lastName + ';' +
                getEmail() + ';' +
                birthday + ';' +
                address + ';' +
                profession;
    }
}