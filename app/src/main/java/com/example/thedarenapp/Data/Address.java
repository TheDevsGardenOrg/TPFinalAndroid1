package com.example.thedarenapp.Data;

public class Address {
    private String propertyNumber;
    private String streetName;
    private String province;
    private String postalCode;
    private String country;

    public Address(String propertyNumber, String streetName, String province, String postalCode, String country) {
        this.propertyNumber = propertyNumber;
        this.streetName = streetName;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
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


    // setters
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


    @Override
    public String toString() {
        return propertyNumber + ';' +
                streetName + ';' +
                province + ';' +
                postalCode + ';' +
                country;
    }
}