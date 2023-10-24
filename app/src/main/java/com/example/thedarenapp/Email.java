package com.example.thedarenapp;

import java.util.Date;

public class Email {
    private String sender;
    private String recipient;
    private String sujet;
    private String message;
    private Date date;
    public Email(){};
    public Email(String sender, String recipient, String sujet, String message, Date date){
        this.sender = sender;
        this.recipient = recipient;
        this.sujet = sujet;
        this.message = message;
        this.date = date;
    }

    //getters
    public String getSujet (){
        return this.sujet;
    }
    public String getMessage (){
        return this.message;
    }
    public String getSender() {return this.sender;}
    public String getRecipient() {return this.recipient;}
    public Date getDate() {return this.date;}

    //setters
    public void setSujet (String sujet) { this.sujet = sujet; }
    public void setMessage (String message) { this.message = message; }
    public void setSender (String sender) { this.sender = sender; }
    public void setRecipient (String recipient) { this.recipient = recipient; }
    public void setDate (Date date) { this.date = date; }

    //tostring
    @Override
    public String toString(){
        StringBuilder retval = new StringBuilder();
        retval.append(""+sender+";");
        retval.append(recipient+";");
        retval.append(sujet+";");
        retval.append(message+";");
        retval.append(date+";");
        retval.append("\n");
        return retval.toString();
    }

}
