package com.example.thedarenapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thedarenapp.DataHandler.EmailUtils;
import com.example.thedarenapp.R;
import com.example.thedarenapp.DataHandler.EmailUtils;

public class emailDraftActivity extends AppCompatActivity {
    EditText editRecipient, editTextName, editTextMessage;
    EmailUtils emailUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_write);

        // Initialize EmailUtils
        emailUtils = new EmailUtils(this);

        // Get the recipient email address from the intent
        String recipientEmail = getIntent().getStringExtra("recipient_email");

        // Set the recipient email address if it was provided
        if (recipientEmail != null && !recipientEmail.isEmpty()) {
            editRecipient = findViewById(R.id.recipient);
            editRecipient.setText(recipientEmail);
        }

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        editTextName = findViewById(R.id.sujet);
        editTextMessage = findViewById(R.id.message);

        Button soumission = findViewById(R.id.sendButton);
        soumission.setOnClickListener((view) -> {
            String subject = editTextName.getText().toString();
            String content = editTextMessage.getText().toString();
            String to_email = editRecipient.getText().toString();

            if (subject.isEmpty() || content.isEmpty() || to_email.isEmpty()) {
                Toast.makeText(emailDraftActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                emailUtils.sendEmail(subject, content, to_email);
                emailUtils.saveEmailToDatabase(subject, content, to_email);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}