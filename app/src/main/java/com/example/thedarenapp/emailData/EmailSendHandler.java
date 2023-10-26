package com.example.thedarenapp.emailData;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thedarenapp.R;
import com.example.thedarenapp.userJava.InboxActivity;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class EmailSendHandler extends AppCompatActivity {
    private Email email = null;
    Date myDate = new Date();
    private Context mContext;

    private String yaya = "";

    public void EmailReadHandler(Context context) {
        mContext = context;
    }
    EditText editRecipient, editTextName, editTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_email_write);
            // calling the action bar
            ActionBar actionBar = getSupportActionBar();
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);



            Button soumission = (Button) findViewById(R.id.sendButton);
            soumission.setOnClickListener((view -> {
                editRecipient = findViewById(R.id.recipient);
                editTextName = findViewById(R.id.sujet);
                editTextMessage = findViewById(R.id.message);
                String subject,content, to_email;
                subject = editTextName.getText().toString();
                content = editTextMessage.getText().toString();
                to_email = editRecipient.getText().toString();
                if (subject.equals("") && content.equals("") && to_email.equals("")) {
                    Toast.makeText(EmailSendHandler.this, "All fields are required",Toast.LENGTH_SHORT).show();
                } else {
                    sendEmail(subject,content,to_email);
                }
            }));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, log it, or display an error message.
        }
    }

    public void sendEmail(String subject, String content, String to_email) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");

        enregisterDonnees();
        startActivity(new Intent(EmailSendHandler.this, InboxActivity.class));

        // Fermez l'activité actuelle
        //finish();

        // Revenez à l'activité précédente (si elle existe)
        //onBackPressed();

        // Affichez la boîte de dialogue de sélection du client de messagerie
        //startActivity(Intent.createChooser(intent, "Choose email client:"));
    }


    //write email to database
    public void enregisterDonnees() {
        String datas = constituerDonnees();
        //String dateString = myDate.toString();


        String nomFichier = "emailDatabase.txt";
        try {
            FileOutputStream fos = openFileOutput(nomFichier, MODE_APPEND);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.print(datas);
            pw.close();
            CharSequence text = "enregistré!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
            //startActivity(new Intent(EmailSendHandler.this, InboxActivity.class));
        } catch (java.io.IOException e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }




    private String constituerDonnees() {
        String retval = "";

       try {
           email = new Email();
           // data
           EditText subject = ((EditText) findViewById(R.id.sujet));
           EditText message = ((EditText) findViewById(R.id.message));
           EditText recipient = ((EditText) findViewById(R.id.recipient));


           //set the values to the class
           email.setSujet(subject.getText().toString());
           email.setMessage(message.getText().toString());
           email.setRecipient(recipient.getText().toString());


           email.setDate(myDate);
           retval = email.toString();
           email = null;
       } catch (Exception e) {
           e.printStackTrace();
       }
        return retval;
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}