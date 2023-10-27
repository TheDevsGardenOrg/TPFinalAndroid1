package com.example.thedarenapp.DataHandler;

import android.content.Context;
import android.content.Intent;

import com.example.thedarenapp.Views.AdminPageActivity;
import com.example.thedarenapp.Views.UserInboxActivity;
import com.example.thedarenapp.Views.LoginPageActivity;
import com.example.thedarenapp.Views.ModificationPageActivity;
import com.example.thedarenapp.Views.RegisterPageActivity;
import com.example.thedarenapp.Views.emailDraftActivity;

public class ActivityManager {

    private final Context context;

    public ActivityManager(Context context) {
        this.context = context;
    }

    public void launchActivityInbox(){
        Intent myInbox = new Intent(context, UserInboxActivity.class);
        context.startActivity(myInbox);
    }

    public void launchActivitySendMail(){
        Intent mailDraft = new Intent(context, emailDraftActivity.class);
        context.startActivity(mailDraft);
    }

    public void launchActivityLogin(){
        Intent login = new Intent(context, LoginPageActivity.class);
        context.startActivity(login);
    }

    public void launchActivityAdmin(){
        Intent admin = new Intent(context, AdminPageActivity.class);
        context.startActivity(admin);
    }

    public void launchActivityMProfile(){
        Intent profile = new Intent(context, ModificationPageActivity.class);
        context.startActivity(profile);
    }

    public void launchRegisterPage(){
        Intent registerPage = new Intent(context, RegisterPageActivity.class);
        context.startActivity(registerPage);
    }
}
