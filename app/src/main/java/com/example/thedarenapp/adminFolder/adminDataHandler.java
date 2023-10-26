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

    public static User loadUser(String email, Context context) {
        try {
            FileInputStream fis = context.openFileInput("usersDatabase.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                User user = dataLineToUser(line);
                if (user != null && user.getEmail().equals(email)) {
                    return user;
                }
            }
            reader.close();
        } catch (IOException var6) {
            var6.printStackTrace();
            Toast.makeText(context, "Failed to load user!", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    public static List<User> loadAllUsers(Context context) {
        List<User> users = new ArrayList<>();
        File file = new File(context.getFilesDir(), "usersDatabase.txt");
        if (!file.exists()) {
            return users;
        } else {
            try {
                FileInputStream fis = context.openFileInput("usersDatabase.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String line;
                while ((line = reader.readLine()) != null) {
                    User user = dataLineToUser(line);
                    if (user != null) {
                        users.add(user);
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
        List<User> users = loadAllUsers(context);
        List<User> remainingUsers = new ArrayList<>();
        boolean deleted = false;
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (!user.getEmail().equals(email)) {
                remainingUsers.add(user);
            } else {
                deleted = true;
            }
        }

        if (deleted) {
            try {
                FileOutputStream fos = context.openFileOutput("usersDatabase.txt", Context.MODE_PRIVATE);
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
                for (User user : remainingUsers) {
                    pw.println(userToDataLine((Person) user));
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

    private static User dataLineToUser(String dataLine) {
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

            return new Person(email, password, firstName, lastName, birthday, address, profession);
        } else {
            return null;
        }
    }
}
