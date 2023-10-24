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
import java.util.List;

public class InboxActivity extends AppCompatActivity {
    private ArrayList<String> noms = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        ListView listView = (ListView) findViewById(R.id.nomsViews);

//        List<ListItem> items = new ArrayList<>();
//        items.add(new ListItem("Texte 1", R.drawable.icon1));
//        CustomAdapter adapter = new CustomAdapter(this, items);
//        listView.setAdapter(adapter);

        List<Email> emails = new ArrayList<>();
        //emails.add(new Email("subject", "message"));
        //this works ok great


        //noms.add("yoyoyo");
       // noms.add("2144t");
       // noms.add("fdsfsd");
        //noms.add("jhgjhbmnb");

        //Créer adaptateur
 //        ArrayAdapter<String> adaptateurNoms = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noms);

        //customadapter
        CustomAdapter adapter = new CustomAdapter(this, emails);

        listView.setAdapter(adapter);
    }
}