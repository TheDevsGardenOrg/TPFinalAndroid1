package com.example.thedarenapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thedarenapp.Adapters.CombinedEmailAdapter;
import com.example.thedarenapp.Data.Email;
import com.example.thedarenapp.DataHandler.EmailReadHandler;
import com.example.thedarenapp.R;

import java.util.ArrayList;

public class UserInboxActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_inbox);

        mListView = findViewById(R.id.listView);
        EmailReadHandler emailReadHandler = new EmailReadHandler();
        ArrayList<Email> emails = emailReadHandler.readFileAndSaveInstances();

        CombinedEmailAdapter adapter = new CombinedEmailAdapter(this, emails);
        mListView.setAdapter(adapter);



        Button newEmail = findViewById(R.id.newEmail);
        newEmail.setOnClickListener((view -> {
            Intent sendEmail = new Intent(this, emailDraftActivity.class);
            sendEmail.putExtra("recipient_email", "Entrez votre courriel");
            startActivity(sendEmail);
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

        if (id == R.id.action_parametres) {
            Toast.makeText(this, "Déconnexion en cours...", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserInboxActivity.this, LoginPageActivity.class));
            return true;
        } else if (id == R.id.iconPerson) {
            Toast.makeText(this, "Envoyer sur la page d'utilisateur", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserInboxActivity.this, ModificationPageActivity.class));
            return true;
        } else if (id == R.id.action_envoyerAdmin) {
            Intent intent = new Intent(this, emailDraftActivity.class);
            intent.putExtra("recipient_email", "admin@example.com");
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}