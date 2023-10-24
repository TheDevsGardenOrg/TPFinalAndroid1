package com.example.thedarenapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class registerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_page);



}

    public void launchActivityInbox(){
        Intent myInbox = new Intent(registerPage.this, InboxActivity.class);
        startActivity(myInbox);
    }

    public void launchActivitySendMail(){
        Intent mailDraft = new Intent(registerPage.this, EmailSendHandler.class);
        startActivity(mailDraft);
    }

    public void launchActivityLogin(){
        Intent login = new Intent(registerPage.this, loginPage.class);
        startActivity(login);
    }

}