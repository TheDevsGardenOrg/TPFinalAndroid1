package com.example.thedarenapp.adminFolder;

public class userTemplate {
    private String email;
    private String firstName;
    private String lastName;
    private String profession;

    public userTemplate() {
        // Default constructor
    }

    public userTemplate(String email, String firstName, String lastName, String profession) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
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
                '}';
    }
}