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
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        mListView = findViewById(R.id.listView);
        EmailReadHandler emailReadHandler = new EmailReadHandler();
        ArrayList<Email> emails = emailReadHandler.readFileAndSaveInstances();

        EmailAdapter adapter = new EmailAdapter(this, emails);
        mListView.setAdapter(adapter);
    }


}

//package com.example.oct18adaptateurs;
//
//        import androidx.appcompat.app.AppCompatActivity;
//
//        import android.media.Image;
//        import android.os.Bundle;
//        import android.widget.ArrayAdapter;
//        import android.widget.ImageView;
//        import android.widget.ListView;
//
//        import java.util.ArrayList;
//        import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        ListView listView = findViewById(R.id.listView);
//
//        List<ListItem> items = new ArrayList<>();
//        items.add(new ListItem("Texte 1", R.drawable.icon1));
//        items.add(new ListItem("Texte 2", R.drawable.icon2));
//        items.add(new ListItem("Texte 3", R.drawable.icon3));
//
//        CustomAdapter adapter = new CustomAdapter(this, items);
//        listView.setAdapter(adapter);
//    }
//}
//
//
