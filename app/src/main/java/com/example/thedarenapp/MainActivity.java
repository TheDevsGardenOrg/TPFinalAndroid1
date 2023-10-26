package com.example.thedarenapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.thedarenapp.adminFolder.AdminUser;
import com.example.thedarenapp.emailData.EmailSendHandler;
import com.example.thedarenapp.userJava.InboxActivity;
import com.example.thedarenapp.userJava.activeUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //main is admin validation
        new AdminUser( "admin@thedarenapp.com", "password", true);

        System.out.println("If you see the same value, then singleton was reused (yay!)" + "\n" +
                "If you see different values, then 2 singletons were created (booo!!)" + "\n\n" +
                "RESULT:" + "\n");
        activeUser singleton = activeUser.getInstance("ibel@gmail.com");
        activeUser anotherSingleton = activeUser.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);


        //Where the app is launching
        launchActivityLogin();
    }

    public void launchActivityInbox(){
        Intent myInbox = new Intent(MainActivity.this, InboxActivity.class);
        startActivity(myInbox);
    }

    public void launchActivitySendMail(){
        Intent mailDraft = new Intent(MainActivity.this, EmailSendHandler.class);
        startActivity(mailDraft);
    }

    public void launchActivityLogin(){
        Intent login = new Intent(MainActivity.this, loginPageActivity.class);
        startActivity(login);
    }

}
//
