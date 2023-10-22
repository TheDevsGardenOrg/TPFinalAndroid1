package com.example.thedarenapp;

public class Email {
    private String sujet;
    private String message;

    //constructor par defaut
    public Email(){}

    //getters

    //setters
    public void setSujet (String sujet) {
        this.sujet = sujet;
    }
    public void setMessage (String message) {
        this.sujet = message;
    }

    //tostring
    @Override
    public String toString(){
        StringBuilder retval = new StringBuilder();

        retval.append("+"+sujet);
        retval.append("+"+message);

        return retval.toString();
    }

}
