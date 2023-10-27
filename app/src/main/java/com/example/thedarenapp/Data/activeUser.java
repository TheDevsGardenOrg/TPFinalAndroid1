package com.example.thedarenapp.Data;

public class activeUser {
    private static activeUser instance;
    public String value;

    private activeUser (String value) {
        // The following code emulates slow initialization.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        this.value = value;
    }

    public static activeUser getInstance(String value) {
        if (instance == null) {
            instance = new activeUser(value);
        }
        return instance;
    }

    public static void eraseActiveUser() {
        instance = null;
    }

}

