package com.example.thedarenapp;

import android.content.Context;
import android.util.Log;

import com.example.thedarenapp.userJava.Address;
import com.example.thedarenapp.userJava.Person;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileHelper {

    private static final String FILE_NAME = "usersDatabase.txt";

    public static boolean saveUser(Person person, Context context) {
        String dataLine = personToDataLine(person);
        try {
            FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.println(dataLine);
            pw.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Person loadUser(String email, Context context) {
        try {
            Log.d("FileHelper", "Attempting to load user with email: " + email);

            FileInputStream fis = context.openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                Person person = dataLineToPerson(line);
                if (person != null && person.getEmail().equals(email)) {
                    return person;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String personToDataLine(Person person) {
        return person.getFirstName() + "," +
                person.getLastName() + "," +
                person.getEmail() + "," +
                person.getPassword() + "," +
                person.getBirthday() + "," +
                addressToDataLine(person.getAddress()) + "," +
                person.getProfession();
    }

    private static Address dataLineToAddress(String dataLine) {
        String[] parts = dataLine.split(",");
        if (parts.length == 5) {
            return new Address(parts[0], parts[1], parts[2], parts[3], parts[4]);
        }
        return null;
    }

    private static Person dataLineToPerson(String dataLine) {
        String[] parts = dataLine.split(",");
        if (parts.length == 7) {
            Person person = new Person(
                    parts[2], // Email
                    parts[3],  // Password
                    parts[0], // First Name
                    parts[1], // Last Name
                    parts[4], // Date of Birth
                    dataLineToAddress(parts[5]), // Address
                    parts[6] // Profession
            );
            Log.d("FileHelper", "Converted to Person: " + person.getEmail());
            return person;
        }
        return null;
    }

    private static String addressToDataLine(Address address) {
        return address.getPropertyNumber() + "," +
                address.getStreetName() + "," +
                address.getProvince() + "," +
                address.getPostalCode() + "," +
                address.getCountry();
    }
}