package com.example.thedarenapp.userJava.userData;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;
import android.widget.Toast;

import com.example.thedarenapp.R;

public class ModificationProfil extends AppCompatActivity {
    private EditText[] editTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modification_profil);

        // On mets champs EditText dans un tableau
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

        try {
            FileInputStream fis = new FileInputStream(filePathUser);
            Scanner scanner = new Scanner(fis);

            if (scanner.hasNextLine()) {
                final String line = scanner.nextLine();

                //Quand on clique sur le btn
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //crée une copie de la ligne dans le fichier txt
                        String updatedLine = line;
                        // Parcourir tous les champs EditText et obtiens les nouvelles valeurs
                        for (int i = 0; i < editTexts.length; i++) {
                            // Obtiens la nouvelle valeur du champ EditText
                            String newFieldValue = editTexts[i].getText().toString();
                            //verification que la valeur n'est pas vide
                            if (!newFieldValue.isEmpty()) {
                                //on obtient les champs correspondant
                                String fieldName = getFieldName(i);
                                // Remplacer la valeur existante du champ dans la ligne par la nouvelle valeur
                                updatedLine = updatedLine.replaceAll(fieldName + "='[^']*'", fieldName + "='" + newFieldValue + "'");
                            }
                        }

                        try {
                            scanner.close();
                            fis.close();

                            FileOutputStream fos = new FileOutputStream(filePathUser);
                            fos.write(updatedLine.getBytes());
                            fos.close();

                            //notification que c'est modifié
                            Toast.makeText(ModificationProfil.this, "Modifications enregistrées avec succès", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                // affichage dans les placeholder
                for (int i = 0; i < editTexts.length; i++) {
                    // Obtenir la valeur du champ à partir de la ligne
                    String fieldValue = extractFieldValue(getFieldName(i), line);
                    // Si la valeur n'est pas vide, l'afficher comme une indication dans le champ EditText
                    if (!fieldValue.isEmpty()) {
                        editTexts[i].setHint(fieldValue);
                    }
                }

                scanner.close();
                fis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Fonction pour extraire une valeur de champ d'une ligne
    private String extractFieldValue(String fieldName, String line) {
        int fieldIndex = line.indexOf(fieldName + "='");
        if (fieldIndex >= 0) {
            int valueStartIndex = fieldIndex + fieldName.length() + 2; // 2 est la longueur de "='"
            int valueEndIndex = line.indexOf("'", valueStartIndex);
            if (valueEndIndex > valueStartIndex) {
                return line.substring(valueStartIndex, valueEndIndex);
            }
        }
        return "";
    }

    // Fonction pour obtenir le nom du champ à partir de l'indice
    private String getFieldName(int index) {
        switch (index) {
            case 0:
                return "firstName";
            case 1:
                return "lastName";
            case 2:
                return "email";
            case 3:
                return "profession";
            case 4:
                return "birthday";
            case 5:
                return "propertyNumber";
            case 6:
                return "streetName";
            case 7:
                return "postalCode";
            case 8:
                return "province";
            default:
                return "";
        }
    }
}