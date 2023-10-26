package com.example.thedarenapp.adminFolder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.thedarenapp.MainActivity;
import com.example.thedarenapp.R;
import com.example.thedarenapp.emailData.EmailSendHandler;
import com.example.thedarenapp.loginPageActivity;
import com.example.thedarenapp.userJava.Person;
import com.example.thedarenapp.userJava.pageCourriel;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    private ListView userListeView;
    private userCustomAdapter adapter;
    private ArrayList<Person> persons;
    PopupWindow popUp;
    boolean click = true;
    EmailSendHandler CourrielWriter = new EmailSendHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        try {
            userListeView = findViewById(R.id.listViewUsers);

            // Initialize your list of persons. This should come from your user data source.
            persons = loadUsers(); // Implement loadUsers to load your users

            adapter = new userCustomAdapter(this, persons);
            userListeView.setAdapter(adapter);

            Button soumission = findViewById(R.id.adminNewEmail);
            soumission.setOnClickListener((view -> {
                Intent login = new Intent(AdminActivity.this, pageCourriel.class);
                startActivity(login);
            }));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }



    // Dummy function to show where you would load your users from
    private ArrayList<Person> loadUsers() {
        return new ArrayList<>(adminDataHandler.loadAllUsers(this));
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