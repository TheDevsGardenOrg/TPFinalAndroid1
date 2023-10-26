package com.example.thedarenapp.adminFolder;

import android.content.Context;
import android.widget.Toast;

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

    public FileHelper() {
    }

    public static boolean saveUser(User user, Context context) {
        String dataLine = userToDataLine(user);

        try {
            FileOutputStream fos = context.openFileOutput("usersDatabase.txt", 32768);
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
            pw.println(dataLine);
            pw.close();
            Toast.makeText(context, "User saved!", 0).show();
            return true;
        } catch (IOException var5) {
            var5.printStackTrace();
            Toast.makeText(context, "Failed to save user!", 0).show();
            return false;
        }
    }

    public static User loadUser(String email, Context context) {
        try {
            FileInputStream fis = context.openFileInput("usersDatabase.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while((line = reader.readLine()) != null) {
                User user = dataLineToUser(line);
                if (user != null && user.getEmail().equals(email)) {
                    return user;
                }
            }

            reader.close();
        } catch (IOException var6) {
            var6.printStackTrace();
            Toast.makeText(context, "Failed to load user!", 0).show();
        }

        return null;
    }

    public static List<User> loadAllUsers(Context context) {
        List<User> users = new ArrayList();
        File file = new File(context.getFilesDir(), "usersDatabase.txt");
        if (!file.exists()) {
            return users;
        } else {
            try {
                FileInputStream fis = context.openFileInput("usersDatabase.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

                String line;
                while((line = reader.readLine()) != null) {
                    User user = dataLineToUser(line);
                    if (user != null) {
                        users.add(user);
                    }
                }

                reader.close();
            } catch (IOException var7) {
                Toast.makeText(context, var7.getMessage(), 0).show();
            }

            return users;
        }
    }

    public static boolean deleteUser(String email, Context context) {
        List<User> users = loadAllUsers(context);
        List<User> remainingUsers = new ArrayList();
        boolean deleted = false;
        Iterator var5 = users.iterator();

        while(var5.hasNext()) {
            User user = (User)var5.next();
            if (!user.getEmail().equals(email)) {
                remainingUsers.add(user);
            } else {
                deleted = true;
            }
        }

        if (deleted) {
            try {
                FileOutputStream fos = context.openFileOutput("usersDatabase.txt", 0);
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(fos));
                Iterator var7 = remainingUsers.iterator();

                while(var7.hasNext()) {
                    User user = (User)var7.next();
                    pw.println(userToDataLine(user));
                }

                pw.close();
                Toast.makeText(context, "User deleted!", 0).show();
            } catch (IOException var9) {
                var9.printStackTrace();
                Toast.makeText(context, "Failed to delete user!", 0).show();
                return false;
            }
        }

        return deleted;
    }

    private static String userToDataLine(User user) {
        return user.getFirstName() + "," + user.getLastName() + "," + user.getEmail() + "," + user.getPassword() + "," + user.getDateOfBirth() + "," + user.getAddress() + "," + user.getProfession();
    }

    private static User dataLineToUser(String dataLine) {
        String[] parts = dataLine.split(",");
        return parts.length == 7 ? new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]) : null;
    }
}

