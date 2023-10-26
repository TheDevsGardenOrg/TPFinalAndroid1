package com.example.thedarenapp.userJava;

import android.os.Bundle;

import com.example.thedarenapp.emailData.Email;
import com.example.thedarenapp.emailData.EmailAdapter;
import com.example.thedarenapp.emailData.EmailReadHandler;
import com.example.thedarenapp.emailData.EmailSendHandler;
import com.example.thedarenapp.R;
import com.example.thedarenapp.loginPageActivity;
import com.example.thedarenapp.userJava.userData.ModificationProfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class InboxActivity extends AppCompatActivity {
    private ArrayList<String> noms = new ArrayList<String>();
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragment_inbox);


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
            Toast.makeText(this,"Déconnexion en cours...",Toast.LENGTH_SHORT).show();

            //Ici on peut set l'activité du User à null au moment du la déconnexion
            startActivity(new Intent(InboxActivity.this, loginPageActivity.class));
            return true;
        }
        else if (id == R.id.action_mail) {
            Toast.makeText(this,"Envoyer sur la page d'utilisateur",Toast.LENGTH_SHORT).show();
            Intent login = new Intent(InboxActivity.this, ModificationProfil.class);
            return true;
        }
        return  super.onOptionsItemSelected(item);
    }

}


