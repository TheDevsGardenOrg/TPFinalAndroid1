package com.example.thedarenapp;

import androidx.appcompat.app.AppCompatActivity;

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



public class MainActivity extends AppCompatActivity {

    private Email trip = new Email();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            int theYear = ((DatePicker)findViewById(R.id.datePicker)).getYear();
            int theMonth = ((DatePicker)findViewById(R.id.datePicker)).getMonth();
            int theDay = ((DatePicker)findViewById(R.id.datePicker)).getDayOfMonth();

            EditText subject = ((EditText)findViewById(R.id.subjectText));
            EditText message = ((EditText)findViewById(R.id.messageText));

        //set the values to the class
            trip.setJour(theDay);
            trip.setMois(theMonth);
            trip.setAnnee(theYear);

            trip.setSujet((subject).getText().toString());
            trip.setMessage(message.getText().toString());

        return retval;
    }

}