package com.example.thedarenapp.adminFolder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab123.R.id;
import com.example.lab123.R.layout;
import com.example.thedarenapp.FileHelper;
import com.example.thedarenapp.userJava.User;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<User> adapter;
    private List<User> users;

    public AdminActivity() {
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(layout.activity_admin);
        this.listView = (ListView)this.findViewById(id.listView);
        this.users = FileHelper.loadAllUsers(this);
        this.adapter = new ArrayAdapter(this, 17367043, this.users);
        this.listView.setAdapter(this.adapter);
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User selectedUser = (User)AdminActivity.this.users.get(position);
                AdminActivity.this.showActionDialog(selectedUser);
            }
        });
    }

    private void showActionDialog(final User user) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Action").setItems(new String[]{"View", "Modify", "Delete"}, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        AdminActivity.this.viewUser(user);
                        break;
                    case 1:
                        AdminActivity.this.modifyUser(user);
                        break;
                    case 2:
                        AdminActivity.this.deleteUser(user);
                }

            }
        });
        builder.create().show();
    }

    private void viewUser(User user) {
        Toast.makeText(this, user.getEmail(), 0).show();
    }

    private void modifyUser(User user) {
    }

    private void deleteUser(User user) {
        if (FileHelper.deleteUser(user.getEmail(), this)) {
            this.users.remove(user);
            this.adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Failed to delete user", 0).show();
        }

    }
}
