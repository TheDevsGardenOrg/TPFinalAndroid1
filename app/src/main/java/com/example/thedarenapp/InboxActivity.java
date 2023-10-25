package com.example.thedarenapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.thedarenapp.databinding.ActivityInboxBinding;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


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

        //EmailAdapter adapter = new EmailAdapter(this, emails);
        //mListView.setAdapter(adapter);
        //mListView = findViewById(R.id.listView);
        //EmailReadHandler emailReadHandler = new EmailReadHandler();
        //ArrayList<Email> emails = emailReadHandler.readFileAndSaveInstances();

        EmailAdapter adapter = new EmailAdapter(this, emails);
        mListView.setAdapter(adapter);


        Button newEmail = findViewById(R.id.newEmail);
        newEmail.setOnClickListener((view -> {
            startActivity(new Intent(InboxActivity.this, EmailSendHandler.class));
        }));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.action_parametres) {
            Toast.makeText(this,"Les paramètres",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_mail) {
            Toast.makeText(this,"Envoyer sur la page Courriel",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InboxActivity.this, EmailSendHandler.class));

            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

}


