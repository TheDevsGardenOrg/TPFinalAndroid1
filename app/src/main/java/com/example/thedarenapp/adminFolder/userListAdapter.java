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

public class userListAdapter extends ArrayAdapter<userTemplate> {
    private Context mContext;
    private List<userTemplate> mUsers;

    public userListAdapter(Context context, List<userTemplate> users) {
        super(context, 0, users);
        mContext = context;
        mUsers = users;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.admin_user_list_view, parent, false);
        }

        userTemplate user = mUsers.get(position);

        // Default icon for the moment
        ImageView iconView = convertView.findViewById(R.id.iconAdminAV);
        iconView.setImageResource(R.drawable.ic_action_name);

        // Email of user
        TextView emailView = convertView.findViewById(R.id.courrielNameAV);
        emailView.setText(user.getEmail());

        // First name of user
        TextView firstNameView = convertView.findViewById(R.id.prenomAV);
        firstNameView.setText(user.getFirstName());

        // Last name of user
        TextView lastNameView = convertView.findViewById(R.id.nomAV);
        lastNameView.setText(user.getLastName());

        // Profession of user
        TextView professionView = convertView.findViewById(R.id.professionAV);
        professionView.setText(user.getProfession());

        return convertView;
    }
}