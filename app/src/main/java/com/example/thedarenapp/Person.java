package com.example.thedarenapp;

public class Person extends User {
    private String firstName;
    private String lastName;
    private String birthday;
    private String propertyNumber;
    private String streetName;
    private String province;
    private String postalCode;
    private String country;
    private String profession;

    // constructor
    public Person(String email, String password, String firstName, String lastName, String birthday, String propertyNumber, String streetName, String province, String postalCode, String country, String profession) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.propertyNumber = propertyNumber;
        this.streetName = streetName;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
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
        return getEmail();
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getProvince() {
        return province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
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

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + getEmail() + '\'' +
                ", birthday=" + birthday +
                ", propertyNumber='" + propertyNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", profession='" + profession + '\'' +
                '}';
    }
}
