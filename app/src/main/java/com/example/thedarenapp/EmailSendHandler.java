package com.example.thedarenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EmailSendHandler extends AppCompatActivity {
    private Email email = null;
    Date myDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_email_write);
            Button soumission = (Button) findViewById(R.id.sendButton);
            soumission.setOnClickListener((view -> {
                enregisterDonnees();
            }));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, log it, or display an error message.
        }
    }
    //write email to database
    private void enregisterDonnees() {
        String datas = constituerDonnees();
        String dateString = myDate.toString();

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
           String sender = "ibel@gmail.com"; //has to be changed to the current logged in user
           EditText recipient = ((EditText) findViewById(R.id.recipient));

           //set the values to the class
           email.setSujet(subject.getText().toString());
           email.setMessage(message.getText().toString());
           email.setSender(sender);
           email.setRecipient(recipient.getText().toString());
           email.setDate(myDate);
           retval = email.toString();
           email = null;

       } catch (Exception e) {
           e.printStackTrace();
       }
        return retval;
    }


}