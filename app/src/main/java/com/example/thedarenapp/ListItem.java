package com.example.thedarenapp;

public class ListItem {

    private  String text;
    private int iconResId;

    public ListItem(String text, int iconResId) {
        this.text =  text;
        this.iconResId = iconResId;
    }

    public String getText(){
        return text;
    }

    public int getIconResId(){
        return iconResId;
    }

}
