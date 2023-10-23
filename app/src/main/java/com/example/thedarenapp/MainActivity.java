package com.example.thedarenapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView yoyo = findViewById(R.id.inscrireLabel);
        yoyo.setOnClickListener(v -> {

            Toast.makeText(MainActivity.this, "Inscription clicked!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, inscriptionPage.class));
        });
    }
}