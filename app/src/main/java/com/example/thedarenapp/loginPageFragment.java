package com.example.thedarenapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.example.thedarenapp.adminFolder.adminDataHandler;
import com.example.thedarenapp.userJava.InboxActivity;
import com.example.thedarenapp.userJava.User;
import com.example.thedarenapp.userJava.registerPage;

public class loginPageFragment extends Fragment {

    private EditText emailEditText;
    private EditText passwordEditText;
    private NavController navController;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login_page, container, false);

        //This part is to log in actual data
        this.emailEditText = view.findViewById(R.id.inputCourriel);
        this.passwordEditText = view.findViewById(R.id.inputMotPass);
        Button loginButton = view.findViewById(R.id.connexionBTN);
        loginButton.setOnClickListener((v) -> {
            this.loginUser();
        });

        TextView yoyo = view.findViewById(R.id.inscrireLabel);
        yoyo.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Inscription clicked!", Toast.LENGTH_SHORT).show();
            //startActivity(new Intent(getActivity(), registerPage.class));
            //action_loginPageFragment2_to_inboxActivity2

            navController.navigate(R.id.action_loginPageFragment2_to_inboxActivity2);

        });

        return view;
    }

    private void loginUser() {
        Log.d("LoginActivity", "Starting login process");
        String inputEmail = this.emailEditText.getText().toString().trim();
        String inputPassword = this.passwordEditText.getText().toString().trim();
        String adminEmail = "admin@example.com";
        String adminPassword = "admin1234";
        //String userEmail = "user@example.com";
        //String userPassword = "user1234";

        RadioGroup accountTypeRadioGroup = getView().findViewById(R.id.accountTypeRadioGroup);
        int selectedAccountType = accountTypeRadioGroup.getCheckedRadioButtonId();

        //USER PART
        if (selectedAccountType == R.id.userButton) {
            User user = adminDataHandler.loadUser(inputEmail, getActivity());
            if (user != null && user.getPassword().equals(inputPassword)) {
                Log.d("LoginActivity", "Login successful as User");
                Intent userActivityIntent = new Intent(getActivity(), InboxActivity.class);
                Toast.makeText(getActivity(),"Connexion vers la nouvelle page...",Toast.LENGTH_SHORT).show();
                startActivity(userActivityIntent);
                getActivity().finish();
            } else {
                Log.d("LoginActivity", "Login failed as User");
                Toast.makeText(getActivity(), "User login failed.", Toast.LENGTH_SHORT).show();
            }

            //ADMIN PART
        } else if (selectedAccountType == R.id.adminButton) {
            if (adminEmail.equalsIgnoreCase(inputEmail) && adminPassword.equals(inputPassword)) {
                Log.d("LoginActivity", "Login successful as Admin");
                //Doit mettre une page admin
                Intent adminActivityIntent = new Intent(getActivity(), InboxActivity.class);
                Toast.makeText(getActivity(),"Connexion vers la nouvelle page...",Toast.LENGTH_SHORT).show();
                startActivity(adminActivityIntent);
                getActivity().finish();
            } else {
                Log.d("LoginActivity", "Login failed as Admin");
                Toast.makeText(getActivity(), "Admin login failed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}