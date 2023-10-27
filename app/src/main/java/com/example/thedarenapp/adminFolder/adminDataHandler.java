package com.example.thedarenapp.adminFolder;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
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
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class adminDataHandler {
    private static final String FILE_NAME = "UserFile.txt";
    public static List<userTemplate> listOfUsers = new ArrayList<>();


    /*Person{firstName='Koukou', lastName='5435435435',
            email='IMsoHot@hotmail.com', birthday='1996-10-26',
            address=Address{propertyNumber='256', streetName='dada',
            province='Quebec', postalCode='J0P3P3', country='Cacnada'},
        profession='Rich motherfucker'}*/

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
                    Log.d(TAG, "YAYAYAYAYAYAYAYAYAYAY" + null);
                    Log.d(TAG, "Reading line: " + line);
                    Person person = dataLineToPerson(line);
                    if (person != null) {
                        Log.d(TAG, "Loaded user: " + person);
                        users.add(person);
                        //listOfUsers.add(new userTemplate(person.getEmail(),person.getFirstName(), person.getLastName(), person.getProfession()));
                       // Log.d(TAG, "LEt's see here  " + listOfUsers);

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
        return person.getFirstName() + "," + person.getLastName() + "," + person.getEmail() + "," +
                person.getPassword() + "," + person.getBirthday() + "," +
                person.getProfession() + "," + address.getPropertyNumber() + "," +
                address.getStreetName() + "," + address.getProvince() + "," +
                address.getPostalCode() + "," + address.getCountry();
    }


    /*Person{firstName='Koukou', lastName='5435435435',
            email='IMsoHot@hotmail.com', birthday='1996-10-26',
            address=Address{propertyNumber='256', streetName='dada',
            province='Quebec', postalCode='J0P3P3', country='Cacnada'},
        profession='Rich motherfucker'}*/



    private static Person dataLineToPerson(String dataLine) {
        Pattern pattern = Pattern.compile("Person\\{firstName='(.*?)', lastName='(.*?)', email='(.*?)',password='(.*?)', birthday='(.*?)', " +
                "address=Address\\{propertyNumber='(.*?)', streetName='(.*?)', province='(.*?)', postalCode='(.*?)', country='(.*?)'\\}, " +
                "profession='(.*?)'\\}");

        Matcher matcher = pattern.matcher(dataLine);
        if (matcher.find()) {
            String firstName = matcher.group(1);
            String lastName = matcher.group(2);
            String email = matcher.group(3);
            String password = matcher.group(4);
            String birthday = matcher.group(5);
            String propertyNumber = matcher.group(6);
            String streetName = matcher.group(7);
            String province = matcher.group(8);
            String postalCode = matcher.group(9);
            String country = matcher.group(10);
            String profession = matcher.group(11);

            // Create an Address object
            Address address = new Address(propertyNumber, streetName, province, postalCode, country);

            // Return a new Person object
            return new Person(firstName, lastName, email,password, birthday, address, profession);
        } else {
            return null;
        }
    }
}