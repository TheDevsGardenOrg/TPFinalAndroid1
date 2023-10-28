package com.example.thedarenapp.DataHandler;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thedarenapp.Data.Email;
import com.example.thedarenapp.R;

import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class EmailReadHandler extends AppCompatActivity {
    private Email email = null;
    ArrayList<Email> emails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_emails);
        try {
            readFileAndSaveInstances();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Email> readFileAndSaveInstances() {
        //String fileName = "emailDatabase.txt";
        String filePath = "/data/data/com.example.thedarenapp/files/emailDatabase.txt";
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
        Email retEmail = new Email(values[0], values[1], values[2], convertStringToDate(values[3]));
        return retEmail;
    }
    private Date convertStringToDate(String dateString) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
