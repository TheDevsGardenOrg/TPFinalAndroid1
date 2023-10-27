package com.example.thedarenapp.Views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.example.thedarenapp.Adapters.UserAdapter;
import com.example.thedarenapp.Data.User;
import com.example.thedarenapp.DataHandler.adminDataHandler;
import com.example.thedarenapp.DataHandler.userTemplate;
import com.example.thedarenapp.Data.Address;
import com.example.thedarenapp.Data.Person;
import com.example.thedarenapp.R;

import java.util.ArrayList;
import java.util.List;

public class AdminPageActivity extends AppCompatActivity {

    private ListView listViewUsers;
    private UserAdapter userAdapter;  // Change to UserAdapter
    private List<userTemplate> userList;

    private ListView listView;
    private ArrayAdapter<User> adapter;

    PopupWindow popUp;
    boolean click = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity);

        try {
            listViewUsers = findViewById(R.id.listViewUsers);
            userList = loadAllUsers(); // Update this method to return List<userTemplate>
            userAdapter = new UserAdapter(this, userList);  // Use UserAdapter
            listViewUsers.setAdapter(userAdapter);

            listViewUsers.setOnItemClickListener((parent, view, position, id) -> {
                userTemplate selectedUser = userAdapter.getItem(position);
                if (selectedUser != null) {
                    showUserInfoDialog(selectedUser);
                }
            });

            Button soumission = findViewById(R.id.adminNewEmail);
            soumission.setOnClickListener((view -> {
                Intent login = new Intent(AdminPageActivity.this, emailDraftActivity.class);
                startActivity(login);
            }));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private List<userTemplate> loadAllUsers() {
        List<userTemplate> userTemplates = new ArrayList<>();
        List<Person> persons = adminDataHandler.loadAllUsers(this);

        for (Person person : persons) {
            Address address = person.getAddress();  // Assuming getAddress() returns an Address object
            userTemplate user = new userTemplate(
                    person.getEmail(),
                    person.getFirstName(),
                    person.getLastName(),
                    person.getProfession(),
                    address
            );
            userTemplates.add(user);
        }
        Log.d("AdminPageActivity", "Loaded " + persons.size() + " persons");
        Log.d("AdminPageActivity", "Loaded " + userTemplates.size() + " users");
        return userTemplates;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    private void showUserInfoDialog(final userTemplate user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Informations de l'utilisateur");

        Address userAddress = user.getAddress();
        StringBuilder message = new StringBuilder();

        if (userAddress != null) {
            message.append("Prénom: ").append(user.getFirstName()).append("\n")
                    .append("Nom: ").append(user.getLastName()).append("\n")
                    .append("Email: ").append(user.getEmail()).append("\n")
                    .append("Profession: ").append(user.getProfession()).append("\n")
                    .append("Numéro Civil: ").append(userAddress.getPropertyNumber()).append("\n")
                    .append("Rue: ").append(userAddress.getStreetName()).append("\n")
                    .append("Code Postal: ").append(userAddress.getPostalCode()).append("\n")
                    .append("Province: ").append(userAddress.getProvince()).append("\n")
                    .append("Pays: ").append(userAddress.getCountry());
        } else {
            message.append("Les informations de l'adresse ne sont pas disponibles.");
        }

        builder.setMessage(message.toString());

        // Setting positive button for "Close" action
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // This will close the dialog
            }
        });

        // Setting negative button for "Delete" action
        builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Call your method to delete the user here
                supprimerUser(user);
            }
        });

        // Display the AlertDialog
        builder.show();
    }

    //The buttons options
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_ajouterUser) {
            ajouterUser();
            return true;
        } else if (id == R.id.action_mail) {
            logout();
            return true;
        } else if (id == R.id.déconnecterOption) {
            consulterUserInscrit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void ajouterUser() {
        Intent logout = new Intent(this, RegisterPageActivity.class);
        startActivity(logout);
    }



    private void supprimerUser(final userTemplate userToDelete) {
        AlertDialog.Builder confirmationDialog = new AlertDialog.Builder(this);
        confirmationDialog.setTitle("Confirmation");
        confirmationDialog.setMessage("Are you sure you want to delete this user?");
        confirmationDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (adminDataHandler.deleteUser(userToDelete.getEmail(), AdminPageActivity.this)) {
                    userList.remove(userToDelete);  // Assuming userList is a List of userTemplate objects
                    userAdapter.notifyDataSetChanged();  // Notify the adapter about the data change
                    Toast.makeText(AdminPageActivity.this, "Utilisateur supprimé", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AdminPageActivity.this, "Échec de la suppression de l'utilisateur", Toast.LENGTH_SHORT).show();
                }
            }
        });
        confirmationDialog.setNegativeButton("No", null);
        confirmationDialog.show();
    }


    private void consulterUser() {
        Toast.makeText(this, "Voir utilisateur sélectionné", Toast.LENGTH_SHORT).show();
    }

    private void consulterUserInscrit() {
        Toast.makeText(this, "Déconnexion en cours...", Toast.LENGTH_SHORT).show();
        Intent logout = new Intent(AdminPageActivity.this, LoginPageActivity.class);
        startActivity(logout);
    }

    private void logout() {
        Toast.makeText(this, "Envoyer mail sélectionné", Toast.LENGTH_SHORT).show();
    }

}