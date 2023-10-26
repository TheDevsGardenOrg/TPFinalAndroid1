package com.example.thedarenapp.userJava;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thedarenapp.R;
import com.example.thedarenapp.emailData.Email;
import com.example.thedarenapp.emailData.EmailAdapter;
import com.example.thedarenapp.emailData.EmailReadHandler;
import com.example.thedarenapp.emailData.EmailSendHandler;
import com.example.thedarenapp.loginPageActivity;
import com.example.thedarenapp.userJava.userData.ModificationProfil;

import java.util.ArrayList;

public class InboxFragment extends Fragment {

    private ArrayList<String> noms = new ArrayList<String>();
    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_inbox, container, false);

        mListView = view.findViewById(R.id.listView);
        EmailReadHandler emailReadHandler = new EmailReadHandler();
        ArrayList<Email> emails = emailReadHandler.readFileAndSaveInstances();

        EmailAdapter adapter = new EmailAdapter(getActivity(), emails);
        mListView.setAdapter(adapter);

        Button newEmail = view.findViewById(R.id.newEmail);
        newEmail.setOnClickListener((v) -> {
            startActivity(new Intent(getActivity(), EmailSendHandler.class));
        });

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_parametres) {
            Toast.makeText(getActivity(), "Déconnexion en cours...", Toast.LENGTH_SHORT).show();

            //Ici on peut set l'activité du User à null au moment du la déconnexion
            startActivity(new Intent(getActivity(), loginPageActivity.class));
            return true;
        } else if (id == R.id.action_mail) {
            Toast.makeText(getActivity(), "Envoyer sur la page d'utilisateur", Toast.LENGTH_SHORT).show();
            Intent login = new Intent(getActivity(), ModificationProfil.class);
            startActivity(login);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}