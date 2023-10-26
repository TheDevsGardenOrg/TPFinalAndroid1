package com.example.thedarenapp.userJava;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thedarenapp.emailData.EmailSendHandler;
import com.example.thedarenapp.R;

public class pageCourriel extends AppCompatActivity {
    PopupWindow popUp;
    boolean click = true;
    EmailSendHandler CourrielWriter = new EmailSendHandler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_write);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button soumission = findViewById(R.id.sendButton);
        soumission.setOnClickListener((view -> {
            EditText sujet = findViewById(R.id.sujet);
            String sujetTitre = sujet.getText().toString().toUpperCase();

            EditText courriel = findViewById(R.id.recipient);
            String courrielEnvoie = courriel.getText().toString().toUpperCase();

            EditText contenu = findViewById(R.id.message);
            String contenuMessage = contenu.getText().toString().toUpperCase();

            if (sujetTitre.equals("") || courrielEnvoie.equals("") || contenuMessage.equals("")) {

                Toast.makeText(this, "Les champs doivent être remplis!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                CourrielWriter.enregisterDonnees();
                //showPopup(view, sujetTitre, courrielEnvoie, contenuMessage);
                // Redirect to MainActivity after showing the Popup
                /*startActivity(new Intent(pageCourriel.this, MainActivity.class));
                messageEntre = (TextView) findViewById(R.id.message);
                btnRepondre = (Button) findViewById(R.id.repondreMessage);
                * */

            }
        }));
    }

    /*private void showPopup(View view, String sujet, String courriel, String contenu) {
        // Inflate the popup_layout.xml
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.popup_layout, null);

        // Set the content to the popup
        TextView tv = popupView.findViewById(R.id.tv);
        tv.setText("\nVoici le contenu de votre message : " +"\nSujet: " + sujet + "\nCourriel: " + courriel + "\nContenu: " + contenu);
        // Create the popup window
        popUp = new PopupWindow(this);
        popUp.setContentView(popupView);
        popUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        popUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popUp.setFocusable(true);
        popUp.setOnDismissListener(() -> {
            startActivity(new Intent(pageCourriel.this, InboxActivity.class));
        });

        // Close the popup and redirect when the Close button is pressed
        Button closePopupButton = popupView.findViewById(R.id.closePopupButton);
        closePopupButton.setOnClickListener(v -> popUp.dismiss());

        // Displaying the popup at the specified location, + offsets
        popUp.showAtLocation(view, Gravity.CENTER, 0, 0);
    }*/
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
