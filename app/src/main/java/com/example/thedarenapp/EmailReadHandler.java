package com.example.thedarenapp;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class EmailReadHandler extends AppCompatActivity {
    private Email email = null;
    ArrayList<Email> emails = new ArrayList<>(); //global variable


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_emails);
        try {
            readFileAndSaveInstances();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Email> readFileAndSaveInstances() {
        String fileName = "emailDatabase.txt";

        // problem to fix later 2 hours died here, do not attempt
        String filePath = "/data/data/com.example.thedarenapp/files/emailDatabase.txt";
        //File file = new File(getFilesDir(), fileName);
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Scanner scanner = new Scanner(fis);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Email email = parseEmail(line);
                emails.add(email);
            }
            scanner.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return emails;
    }

    private Email parseEmail(String line) {
        String values[] = line.split(";");
        Email retEmail = new Email(values[0], values[1], values[2], values[3], convertStringToDate(values[4]));
        return retEmail;
    }

    private Date convertStringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

//call the custom adapter and use it to populate the list

}
