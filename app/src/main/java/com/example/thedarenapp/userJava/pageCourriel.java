package com.example.thedarenapp.userJava;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thedarenapp.MainActivity;
import com.example.thedarenapp.adminFolder.AdminActivity;
import com.example.thedarenapp.emailData.EmailSendHandler;
import com.example.thedarenapp.R;

public class pageCourriel extends AppCompatActivity {
    PopupWindow popUp;
    boolean click = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_write);

        // Get the recipient email address from the intent
        String recipientEmail = getIntent().getStringExtra("recipient_email");

        // Set the recipient email address if it was provided
        if (recipientEmail != null && !recipientEmail.isEmpty()) {
            EditText recipientEditText = findViewById(R.id.recipient);
            recipientEditText.setText(recipientEmail);
        }



        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button soumission = findViewById(R.id.sendButton);
        soumission.setOnClickListener((view -> {
            EditText sujet = findViewById(R.id.sujet);
            String sujetTitre = sujet.getText().toString();

            EditText courriel = findViewById(R.id.recipient);
            String courrielEnvoie = courriel.getText().toString();

            EditText contenu = findViewById(R.id.message);
            String contenuMessage = contenu.getText().toString();

            if (sujetTitre.equals("") || courrielEnvoie.equals("") || contenuMessage.equals("")) {

                Toast.makeText(this, "Les champs doivent être remplis!", Toast.LENGTH_SHORT).show();
            } else {
                sendEmail( sujetTitre, courrielEnvoie, contenuMessage);
            }
        }));
    }

    public void sendEmail(String subject,String to_email, String content) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");

        Toast.makeText(pageCourriel.this, "Courriel envoyé!",Toast.LENGTH_SHORT).show();

        // Fermez l'activité actuelle
        //finish();

        // Revenez à l'activité précédente (si elle existe)
        //onBackPressed();

        // Affichez la boîte de dialogue de sélection du client de messagerie
        startActivity(Intent.createChooser(intent, "Choose email client:"));
       // startActivity(new Intent(pageCourriel.this, InboxActivity.class));
    }

//Here it's an event listener
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
