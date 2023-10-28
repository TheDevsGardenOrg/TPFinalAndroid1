package com.example.thedarenapp.DataHandler;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.thedarenapp.Data.Email;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class EmailUtils {
    private Context context;
    private Date myDate;

    public EmailUtils(Context context) {
        this.context = context;
        this.myDate = new Date();
    }

    public void sendEmail(String subject, String content, String to_email) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{to_email});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, content);
        intent.setType("message/rfc822");
        context.startActivity(Intent.createChooser(intent, "Choose email client:"));
    }

    public void saveEmailToDatabase(String subject, String content, String to_email) {
        String data = constituerDonnees(subject, content, to_email);
        String nomFichier = "emailDatabase.txt";
        try {
            FileOutputStream fos = context.openFileOutput(nomFichier, Context.MODE_APPEND);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.print(data);
            pw.close();
            Toast.makeText(context, "Email saved!", Toast.LENGTH_SHORT).show();
        } catch (java.io.IOException e) {
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String constituerDonnees(String subject, String content, String to_email) {
        Email email = new Email(to_email, subject, content, myDate);
        return email.toString();
    }
}