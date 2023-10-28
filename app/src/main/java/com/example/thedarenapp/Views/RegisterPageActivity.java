package com.example.thedarenapp.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thedarenapp.Data.Address;
import com.example.thedarenapp.Data.Person;
import com.example.thedarenapp.DataHandler.fileReaderManager;
import com.example.thedarenapp.R;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class RegisterPageActivity extends AppCompatActivity {

    AdminPageActivity refresh = new AdminPageActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.user_registration);

            Button soumission = findViewById(R.id.btnSoumettre);
            soumission.setOnClickListener((view -> enregistrerInformations()));

            Button retour = findViewById(R.id.btnRetour);
            retour.setOnClickListener((view ->startActivity(new Intent(RegisterPageActivity.this, LoginPageActivity.class))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enregistrerInformations() {

        // Read input fields
        EditText prenom = findViewById(R.id.prenomInput);
        EditText nom = findViewById(R.id.nomInput);
        EditText courriel = findViewById(R.id.courrielAccountInput);
        EditText motPass = findViewById(R.id.motPassInput);
        DatePicker datePicker = findViewById(R.id.datePicker);
        EditText numeroCivil = findViewById(R.id.numeroCivilInput);
        EditText rue = findViewById(R.id.rueInput);
        EditText codePostal = findViewById(R.id.codePostalInput);
        EditText province = findViewById(R.id.provinceInput);
        EditText pays = findViewById(R.id.paysInput);
        EditText profession = findViewById(R.id.professionInput);

        String prenomText = prenom.getText().toString();
        String nomText = nom.getText().toString();
        String courrielText = courriel.getText().toString();
        String selectedDate = getSelectedDate(datePicker);
        String numeroCivilText = numeroCivil.getText().toString();
        String rueText = rue.getText().toString();
        String codePostalText = codePostal.getText().toString();
        String provinceText = province.getText().toString();
        String paysText = pays.getText().toString();
        String professionText = profession.getText().toString();
        String motPassText = motPass.getText().toString();



        Address address = new Address(
                numeroCivilText,
                rueText,
                provinceText,
                codePostalText,
                paysText
        );

        Person person = new Person(
                courrielText,
                motPassText,
                prenomText,
                nomText,
                selectedDate,
                address,
                professionText
        );
        writePersonToFile(person);


        //Retrouver la session précédente
        setResult(Activity.RESULT_OK);
        //Remettre à jour les données
        fileReaderManager.loadAllUsersFromText(this);
        refresh.loadAllUserTemplates();
        finish();
    }

    private String getSelectedDate(DatePicker datePicker) {
        int year = datePicker.getYear();
        int month = datePicker.getMonth() + 1;
        int dayOfMonth = datePicker.getDayOfMonth();
        return String.format("%04d-%02d-%02d", year, month, dayOfMonth);
    }

    private void writePersonToFile(Person person) {
        String datas = person.toString();
        String nomFichier = "UserFile.txt";
        try {
            FileOutputStream fos = openFileOutput(nomFichier, MODE_APPEND);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.println(datas);
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
}