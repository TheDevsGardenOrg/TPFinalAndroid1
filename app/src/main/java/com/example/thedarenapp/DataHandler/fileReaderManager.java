package com.example.thedarenapp.DataHandler;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.example.thedarenapp.Data.Address;
import com.example.thedarenapp.Data.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class fileReaderManager {
    private static final String FILE_NAME = "UserFile.txt";

    public static boolean saveUser(Person person, Context context) {
        String dataLine = userToDataLine(person);
        try {
            FileOutputStream fos = context.openFileOutput("UserFile.txt", Context.MODE_APPEND);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.println(dataLine);
            pw.close();
            Toast.makeText(context, "User saved!", Toast.LENGTH_SHORT).show();
            return true;
        } catch (IOException var5) {
            var5.printStackTrace();
            Toast.makeText(context, "Failed to save user!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static Person loadUser(String email, Context context) {
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                Person person = dataLineToPerson(line);
                if (person != null && person.getEmail().equals(email)) {
                    reader.close();
                    return person;
                }
            }
            reader.close();
        } catch (IOException var6) {
            var6.printStackTrace();
            Toast.makeText(context, "Failed to load user!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    public static List<Person> loadAllUsers(Context context) {
        List<Person> users = new ArrayList<>();
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (!file.exists()) {
            Log.d(TAG, "File not found: " + FILE_NAME);
            return users;
        } else {
            try {
                FileInputStream fis = context.openFileInput(FILE_NAME);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = reader.readLine()) != null) {
                    Log.d(TAG, "Reading line: " + line);
                    Person person = dataLineToPerson(line);
                    if (person != null) {
                        Log.d(TAG, "Loaded user: " + person);
                        users.add(person);
                    } else {
                        Log.d(TAG, "Failed to create person object from line");
                    }
                }
                reader.close();
            } catch (IOException e) {
                Log.e(TAG, "Failed to load users", e);
                Toast.makeText(context, "Failed to load users!", Toast.LENGTH_SHORT).show();
            }

            return users;
        }
    }

    public static List<String> loadAllUsersFromText(Context context) {
        List<String> userStrings = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                userStrings.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userStrings;
    }

    public static boolean deleteUser(String email, Context context) {
        List<Person> users = loadAllUsers(context);
        List<Person> remainingUsers = new ArrayList<>();
        boolean deleted = false;

        for (Person user : users) {
            if (!user.getEmail().equals(email)) {
                remainingUsers.add(user);
            } else {
                deleted = true;
            }
        }

        if (deleted) {
            try {
                FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
                for (Person user : remainingUsers) {
                    pw.println(userToDataLine(user));
                }
                pw.close();
                Toast.makeText(context, "User deleted!", Toast.LENGTH_SHORT).show();
            } catch (IOException var9) {
                var9.printStackTrace();
                Toast.makeText(context, "Failed to delete user!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return deleted;
    }

    private static String userToDataLine(Person person) {
        Address address = person.getAddress();
        return person.getEmail() + ";" + person.getPassword()+ ";" + person.getFirstName() + ";" +
                person.getLastName() + ";" + person.getBirthday() + ";" + address.getPropertyNumber() + ";" +
                address.getStreetName() + ";" + address.getProvince() + ";" +
                address.getPostalCode() + ";" + address.getCountry() + ";" + person.getProfession();
    }
    // Create an Address object
    private static Person dataLineToPerson(String dataLine) {
        String[] parts = dataLine.split(";");
        if (parts.length == 11) {
            String email = parts[0];
            String password = parts[1];
            String firstName = parts[2];
            String lastName = parts[3];
            String birthday = parts[4];
            String  propertyNumber = parts[5];
            String streetName = parts[6];
            String province = parts[7];
            String postalCode = parts[8];
            String country = parts[9];
            String profession = parts[10];
            // Create an Address object
            Address address = new Address(propertyNumber, streetName, province, postalCode, country);

            // Return a new Person object
            return new Person(email, password, firstName, lastName, birthday, address, profession);
        } else {
            return null;
        }
    }
}