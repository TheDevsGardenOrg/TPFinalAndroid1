package com.example.thedarenapp.adminFolder;

import android.content.Context;
import android.widget.Toast;
import com.example.thedarenapp.userJava.Address;
import com.example.thedarenapp.userJava.Person;
import com.example.thedarenapp.userJava.User;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class adminDataHandler {
    private static final String FILE_NAME = "usersDatabase.txt";

    /*Person{firstName='Koukou', lastName='5435435435',
            email='IMsoHot@hotmail.com', birthday='1996-10-26',
            address=Address{propertyNumber='256', streetName='dada',
            province='Quebec', postalCode='J0P3P3', country='Cacnada'},
        profession='Rich motherfucker'}*/

    public static boolean saveUser(Person person, Context context) {
        String dataLine = userToDataLine(person);
        try {
            FileOutputStream fos = context.openFileOutput("usersDatabase.txt", Context.MODE_APPEND);
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
            return users;
        } else {
            try {
                FileInputStream fis = context.openFileInput(FILE_NAME);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = reader.readLine()) != null) {
                    Person person = dataLineToPerson(line);
                    if (person != null) {
                        users.add(person);
                    }
                }
                reader.close();
            } catch (IOException var7) {
                Toast.makeText(context, var7.getMessage(), Toast.LENGTH_SHORT).show();
            }

            return users;
        }
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
        return person.getFirstName() + ";" + person.getLastName() + ";" + person.getEmail() + ";" +
                person.getPassword() + ";" + person.getBirthday() + ";" +
                person.getProfession() + ";" + address.getPropertyNumber() + ";" +
                address.getStreetName() + ";" + address.getProvince() + ";" +
                address.getPostalCode() + ";" + address.getCountry();
    }

    private static Person dataLineToPerson(String dataLine) {
        String[] parts = dataLine.split(";");
        if (parts.length == 11) {
            String firstName = parts[0];
            String lastName = parts[1];
            String email = parts[2];
            String password = parts[3];
            String birthday = parts[4];
            String profession = parts[5];
            String propertyNumber = parts[6];
            String streetName = parts[7];
            String province = parts[8];
            String postalCode = parts[9];
            String country = parts[10];

            // Create an Address object
            Address address = new Address(propertyNumber, streetName, province, postalCode, country);

            // Return a new Person object
            return new Person(firstName, lastName, email,password, birthday, address, profession);
        } else {
            return null;
        }
    }
}