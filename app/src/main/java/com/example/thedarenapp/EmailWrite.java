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
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
public class EmailWrite extends AppCompatActivity {

    private Email trip = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_write);

        Button soumission = (Button) findViewById(R.id.sendButton);
        soumission.setOnClickListener((view -> {
            DatePicker datePicker = findViewById(R.id.datePicker);
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth() + 1;
            int year = datePicker.getYear();
            enregisterDonnees();
        }));


    }

    private void enregisterDonnees() {
        String datas = constituerDonnees();
        int lemois = ((DatePicker)findViewById(R.id.datePicker)).getMonth();
        int lAnnee = ((DatePicker)findViewById(R.id.datePicker)).getYear();

        String nomFichier = "B"+lAnnee+lemois;
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
        }
    }

    private String constituerDonnees() {
        String retval = "";

        trip = new Email();
        // data

        EditText subject = ((EditText)findViewById(R.id.subjectText));
        EditText message = ((EditText)findViewById(R.id.messageText));

        //set the values to the class

        trip.setSujet((subject).getText().toString());
        trip.setMessage(message.getText().toString());
        retval = trip.toString();
        trip = null;

        return retval;
    }

}