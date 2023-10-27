package com.example.thedarenapp.adminFolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thedarenapp.R;

import java.util.List;

public class userCustomAdapter extends ArrayAdapter<userTemplate> {

    public userCustomAdapter(Context context, List<userTemplate> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.admin_user_list_view, parent, false);
        }

        userTemplate user = getItem(position);

        if (user != null) {
            // Access the ImageView and TextViews in the layout
            ImageView iconView = convertView.findViewById(R.id.iconAdminAV);
            TextView emailText = convertView.findViewById(R.id.courrielNameAV);
            TextView firstNameText = convertView.findViewById(R.id.prenomAV);
            TextView lastNameText = convertView.findViewById(R.id.nomAV);
            TextView professionText = convertView.findViewById(R.id.professionAV);

            // Set the content for the views
            if (iconView != null) {
                iconView.setImageResource(R.drawable.ic_action_name); // You might want to change the icon
            }
            if (emailText != null) {
                emailText.setText(user.getEmail());
            }
            if (firstNameText != null) {
                firstNameText.setText(user.getFirstName());
            }
            if (lastNameText != null) {
                lastNameText.setText(user.getLastName());
            }
            if (professionText != null) {
                professionText.setText(user.getProfession());
            }
        }

        return convertView;
    }
}