package com.example.thedarenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class loginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        TextView yoyo = findViewById(R.id.inscrireLabel);
        yoyo.setOnClickListener(v -> {
            Toast.makeText(loginPage.this, "Inscription clicked!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(loginPage.this, registerPage.class));
        });
    }
}
