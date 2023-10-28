package com.example.thedarenapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import android.widget.Toast;

import com.example.thedarenapp.R;

public class ModificationPageActivity extends AppCompatActivity {
    private EditText[] editTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_profil);

        editTexts = new EditText[9];
        editTexts[0] = findViewById(R.id.name);
        editTexts[1] = findViewById(R.id.lastname);
        editTexts[2] = findViewById(R.id.email);
        editTexts[3] = findViewById(R.id.profession);
        editTexts[4] = findViewById(R.id.birthday);
        editTexts[5] = findViewById(R.id.propretyNumber);
        editTexts[6] = findViewById(R.id.streetName);
        editTexts[7] = findViewById(R.id.postaleCode);
        editTexts[8] = findViewById(R.id.province);

        Button saveButton = findViewById(R.id.saveButton);

        final String filePathUser = "/data/data/com.example.thedarenapp/files/UserFile.txt";

        //Set l'action de bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile Modification");
        actionBar.setDisplayHomeAsUpEnabled(true);

        try {
            FileInputStream fis = new FileInputStream(filePathUser);
            Scanner scanner = new Scanner(fis);

            if (scanner.hasNextLine()) {
                final String line = scanner.nextLine();

                String[] fieldValues = line.split(";");
                if (fieldValues.length >= 9) {
                    for (int i = 0; i < editTexts.length; i++) {
                        editTexts[i].setHint(fieldValues[i]);
                    }
                }

                saveButton.setOnClickListener(v -> {
                    StringBuilder updatedLine = new StringBuilder();
                    for (int i = 0; i < editTexts.length; i++) {
                        String newFieldValue = editTexts[i].getText().toString();
                        if (!newFieldValue.isEmpty()) {
                            updatedLine.append(newFieldValue);
                        } else {
                            updatedLine.append(fieldValues[i]);
                        }
                        if (i < editTexts.length - 1) {
                            updatedLine.append(";");
                        }
                    }

                    try {
                        scanner.close();
                        fis.close();

                        FileOutputStream fos = new FileOutputStream(filePathUser);
                        fos.write(updatedLine.toString().getBytes());
                        fos.close();

                        Toast.makeText(ModificationPageActivity.this, "Modifications enregistrées avec succès", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                scanner.close();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
