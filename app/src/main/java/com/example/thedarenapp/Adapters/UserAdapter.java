package com.example.thedarenapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thedarenapp.Data.Person;
import com.example.thedarenapp.Data.User;
import com.example.thedarenapp.DataHandler.userTemplate;
import com.example.thedarenapp.R;
import com.example.thedarenapp.Views.emailDraftActivity;

import java.util.List;

public class UserAdapter extends ArrayAdapter<userTemplate> {

    private Context mContext;
    private List<userTemplate> mUsers;

    private List<Person> userList;

    public UserAdapter(Context context, List<userTemplate> users) {
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

        //Setting up the icon clickable
        ImageView emailIcon = convertView.findViewById(R.id.BtnSendUser);
        emailIcon.setImageResource(R.drawable.ic_action_email);
        emailIcon.setOnClickListener(v -> {
            Toast.makeText(mContext, "Téléchargement de la page en cours..", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(mContext, emailDraftActivity.class);
            intent.putExtra("recipient_email", user.getEmail());
            mContext.startActivity(intent);
        });

        return convertView;
    }

    public void updateUsers(List<userTemplate> newUsers) {
        this.mUsers = newUsers;
        notifyDataSetChanged();
    }

}