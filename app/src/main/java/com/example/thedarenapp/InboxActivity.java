package com.example.thedarenapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.thedarenapp.databinding.ActivityInboxBinding;

import java.util.ArrayList;
import java.util.List;

public class InboxActivity extends AppCompatActivity {
    private ArrayList<String> noms = new ArrayList<String>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        mListView = findViewById(R.id.listView);
        EmailReadHandler emailReadHandler = new EmailReadHandler();
        ArrayList<Email> emails = emailReadHandler.readFileAndSaveInstances();
        EmailAdapter adapter = new EmailAdapter(this, emails);
        mListView.setAdapter(adapter);
    }


}