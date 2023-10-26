package com.example.thedarenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thedarenapp.userJava.InboxActivity;
import com.example.thedarenapp.userJava.User;
import com.example.thedarenapp.userJava.registerPage;

public class loginPageActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);


        //This part is to log in actual data
        this.emailEditText = (EditText)this.findViewById(R.id.inputCourriel);
        this.passwordEditText = (EditText)this.findViewById(R.id.inputMotPass);
        Button loginButton = (Button)this.findViewById(R.id.connexionBTN);
        loginButton.setOnClickListener((v) -> {
            this.loginUser();
        });



        TextView yoyo = findViewById(R.id.inscrireLabel);
        yoyo.setOnClickListener(v -> {
            Toast.makeText(loginPageActivity.this, "Inscription clicked!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(loginPageActivity.this, registerPage.class));
        });
    }





    private void loginUser() {
        Log.d("LoginActivity", "Starting login process");
        String inputEmail = this.emailEditText.getText().toString().trim();
        String inputPassword = this.passwordEditText.getText().toString().trim();
        String adminEmail = "admin@example.com";
        String adminPassword = "admin1234";
        //String userEmail = "user@example.com";
        //String userPassword = "user1234";

        RadioGroup accountTypeRadioGroup = (RadioGroup)this.findViewById(R.id.accountTypeRadioGroup);
        int selectedAccountType = accountTypeRadioGroup.getCheckedRadioButtonId();

        //USER PART
        if (selectedAccountType == R.id.userButton) {
            User user = FileHelper.loadUser(inputEmail, this);
            if (user != null && user.getPassword().equals(inputPassword)) {
                Log.d("LoginActivity", "Login successful as User");
                Intent userActivityIntent = new Intent(this, InboxActivity.class);
                Toast.makeText(this,"Connexion vers la nouvelle page...",Toast.LENGTH_SHORT).show();
                this.startActivity(userActivityIntent);
                this.finish();
            } else {
                Log.d("LoginActivity", "Login failed as User");
                Toast.makeText(this, "User login failed.", 0).show();
            }


        //ADMIN PART
        } else if (selectedAccountType == R.id.adminButton) {
            if (adminEmail.equalsIgnoreCase(inputEmail) && adminPassword.equals(inputPassword)) {
                Log.d("LoginActivity", "Login successful as Admin");
                        //Doit mettre une page admin
                Intent adminActivityIntent = new Intent(this, InboxActivity.class);
                Toast.makeText(this,"Connexion vers la nouvelle page...",Toast.LENGTH_SHORT).show();
                this.startActivity(adminActivityIntent);
                this.finish();
            } else {
                Log.d("LoginActivity", "Login failed as Admin");
                Toast.makeText(this, "Admin login failed.", 0).show();
            }
        }

    }



}
