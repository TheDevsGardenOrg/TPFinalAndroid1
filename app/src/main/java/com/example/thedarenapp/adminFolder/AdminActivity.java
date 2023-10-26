package com.example.thedarenapp.adminFolder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thedarenapp.R;
import com.example.thedarenapp.emailData.Email;
import com.example.thedarenapp.emailData.EmailAdapter;
import com.example.thedarenapp.emailData.EmailReadHandler;
import com.example.thedarenapp.userJava.Person;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {


    private ArrayList<String> noms = new ArrayList<String>();
    private ListView userListeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);


        userListeView = findViewById(R.id.listViewUsers);
        //I need a handler for users
        EmailReadHandler emailReadHandler = new EmailReadHandler();

        ArrayList<Email> emails = emailReadHandler.readFileAndSaveInstances();

        //EmailAdapter adapter = new EmailAdapter(this, emails);
        //mListView.setAdapter(adapter);
        //mListView = findViewById(R.id.listView);
        //EmailReadHandler emailReadHandler = new EmailReadHandler();
        //ArrayList<Email> emails = emailReadHandler.readFileAndSaveInstances();

        EmailAdapter adapter = new EmailAdapter(this, emails);
        userListeView.setAdapter(adapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }


    //The buttons options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_ajouterUser) {
            ajouterUser();
            return true;
        } else if (id == R.id.action_supprimerUser) {
            supprimerUser();
            return true;
        } else if (id == R.id.action_mail) {
            logout();
            return true;
        } else if (id == R.id.action_consulterUser) {
            consulterUser();
            return true;
        } else if (id == R.id.action_consulterUserInscrit) {
            consulterUserInscrit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showUserInfoDialog(Person person) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informations de l'utilisateur");
        builder.setMessage(
                "Prénom: " + person.getFirstName() + "\n" +
                        "Nom: " + person.getLastName() + "\n" +
                        "Email: " + person.getEmail() + "\n" +
                        // ... ajoutez d'autres informations ici ...
                        "Profession: " + person.getProfession()
        );
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void ajouterUser() {
        Toast.makeText(this, "Ajouter utilisateur sélectionné", Toast.LENGTH_SHORT).show();
    }

    private void supprimerUser() {
        Toast.makeText(this, "Supprimer utilisateur sélectionné", Toast.LENGTH_SHORT).show();
    }

    private void consulterUser() {
        Toast.makeText(this, "Voir utilisateur sélectionné", Toast.LENGTH_SHORT).show();
    }

    private void consulterUserInscrit() {
        Toast.makeText(this, "Voir info utilisateur sélectionné", Toast.LENGTH_SHORT).show();
    }

    private void logout() {
        Toast.makeText(this, "Envoyer mail sélectionné", Toast.LENGTH_SHORT).show();
    }
}