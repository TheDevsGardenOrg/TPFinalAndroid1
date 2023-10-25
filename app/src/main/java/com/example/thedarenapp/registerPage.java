package com.example.thedarenapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class registerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.register_page);
            Button soumission = (Button) findViewById(R.id.btnSoumettre);
            soumission.setOnClickListener((view -> {
                enregistrerInformations();
            }));
            Button retour = (Button) findViewById(R.id.btnRetour);
            retour.setOnClickListener((view -> {
                launchActivityLogin();
            }));
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception, log it, or display an error message.
        }
    }

    public void enregistrerInformations() {

        // Read input fields
        EditText prenom = findViewById(R.id.prenomInput);
        EditText nom = findViewById(R.id.nomInput);
        EditText courriel = findViewById(R.id.courrielAccountInput);
        EditText numeroCivil = findViewById(R.id.numeroCivilInput);
        EditText rue = findViewById(R.id.rueInput);
        EditText codePostal = findViewById(R.id.codePostalInput);
        EditText province = findViewById(R.id.provinceInput);
        EditText pays = findViewById(R.id.paysInput);
        EditText profession = findViewById(R.id.professionInput);
        EditText motPass = findViewById(R.id.motPassInput);

        String prenomText = prenom.getText().toString();
        String nomText = nom.getText().toString();
        String courrielText = courriel.getText().toString();
        String dateNaissanceText = "yoyoyoy";
        String numeroCivilText = numeroCivil.getText().toString();
        String rueText = rue.getText().toString();
        String codePostalText = codePostal.getText().toString();
        String provinceText = province.getText().toString();
        String paysText = pays.getText().toString();
        String professionText = profession.getText().toString();
        String motPassText = motPass.getText().toString();


            // Create a Person object with the user's information
        Person person = new Person(
                courrielText, // Email
                motPassText,  // Password
                prenomText,
                nomText,
                dateNaissanceText,
                numeroCivilText,
                rueText,
                provinceText,
                codePostalText,
                paysText,
                professionText
        );

            // Write the person's information to a file
            writePersonToFile(person);

            // Fields are filled, you can proceed to the next activity
            launchActivityLogin();
    }


    private void writePersonToFile(Person person) {
        String datas = person.toString();
        String nomFichier = "UserFile.txt"; // Change the file name as needed
        try {
            FileOutputStream fos = openFileOutput(nomFichier, MODE_APPEND);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.println(datas); // Use println to append a new line
            pw.close();
            CharSequence text = "enregistré!";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, text, duration);
            toast.show();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void launchActivityLogin() {
        Intent login = new Intent(registerPage.this, loginPage.class);
        startActivity(login);
    }
}