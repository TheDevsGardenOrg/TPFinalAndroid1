package com.example.thedarenapp;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.thedarenapp.databinding.ActivityInboxBinding;

import java.util.ArrayList;

public class InboxActivity extends AppCompatActivity {
    private ArrayList<String> noms = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);


        noms.add("yoyoyo");
        noms.add("2144t");
        noms.add("fdsfsd");
        noms.add("jhgjhbmnb");

        ListView listView = (ListView) findViewById(R.id.nomsViews);

        //Créer adaptateur
        ArrayAdapter<String> adaptateurNoms = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noms);

        listView.setAdapter(adaptateurNoms);
    }
}