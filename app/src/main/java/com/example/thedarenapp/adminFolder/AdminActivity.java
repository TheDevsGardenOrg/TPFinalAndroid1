package com.example.thedarenapp.adminFolder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.thedarenapp.MainActivity;
import com.example.thedarenapp.R;
import com.example.thedarenapp.emailData.CustomAdapter;
import com.example.thedarenapp.emailData.EmailSendHandler;
import com.example.thedarenapp.loginPageActivity;
import com.example.thedarenapp.userJava.Person;
import com.example.thedarenapp.userJava.pageCourriel;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ListView listViewUsers;
    private userCustomAdapter userAdapter;
    private List<userTemplate> userList;
    PopupWindow popUp;
    boolean click = true;
    EmailSendHandler CourrielWriter = new EmailSendHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        try {
            listViewUsers = findViewById(R.id.listViewUsers);
            userList = loadAllUsers(); // Update this method to return List<userTemplate>
            userAdapter = new userCustomAdapter(this, userList);
            listViewUsers.setAdapter(userAdapter);

            listViewUsers.setOnItemClickListener((parent, view, position, id) -> {
                userTemplate selectedUser = userAdapter.getItem(position);
                if (selectedUser != null) {
                    showUserInfoDialog(selectedUser);
                }
            });


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

    /*private List<userTemplate> loadUsers() {
        List<userTemplate> users = new ArrayList<>();

        users.add(new userTemplate("user1@example.com", "John", "Doe", "Engineer"));
        users.add(new userTemplate("user2@example.com", "Jane", "Doe", "Doctor"));
        users.add(new userTemplate("user3@example.com", "Alice", "Johnson", "Teacher"));
        users.add(new userTemplate("user4@example.com", "Bob", "Smith", "Driver"));

        // Add more users as needed

        return users;
    }*/


    private List<userTemplate> loadAllUsers() {
        List<userTemplate> userTemplates = new ArrayList<>();
        List<Person> persons = adminDataHandler.loadAllUsers(this);

        for (Person person : persons) {
            userTemplate user = new userTemplate(
                    person.getEmail(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getProfession()
            );
            userTemplates.add(user);
        }
        Log.d("AdminActivity", "Loaded " + persons.size() + " persons");
        Log.d("AdminActivity", "Loaded " + userTemplates.size() + " users");
        return userTemplates;
    }

    // Dummy function to show where you would load your users from
    /*private List<userTemplate> loadUsers() {
        // Load your userTemplates from wherever they are stored
        // For example:
        return new ArrayList<>();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    private void showUserInfoDialog(userTemplate user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informations de l'utilisateur");
        builder.setMessage(
                "Prénom: " + user.getFirstName() + "\n" +
                        "Nom: " + user.getLastName() + "\n" +
                        "Email: " + user.getEmail() + "\n" +
                        "Profession: " + user.getProfession()
        );
        builder.setPositiveButton("OK", null);
        builder.show();
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