package com.example.thedarenapp.adminFolder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.thedarenapp.R;
import com.example.thedarenapp.userJava.Person;
import java.util.List;

public class userListAdapter extends ArrayAdapter<Person> {
    private Context mContext;
    private List<Person> mPersons;

    public userListAdapter(Context context, List<Person> persons) {
        super(context, 0, persons);
        mContext = context;
        mPersons = persons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.admin_user_list_view, parent, false);
        }

        Person person = mPersons.get(position);

        // Default icon for the moment
        ImageView iconView = convertView.findViewById(R.id.icon);
        iconView.setImageResource(R.drawable.ic_action_name);

        // Email of user
        TextView emailView = convertView.findViewById(R.id.courrielNameAV);
        emailView.setText(person.getEmail());

        // First name of user
        TextView firstNameView = convertView.findViewById(R.id.prenomAV);
        firstNameView.setText(person.getFirstName());

        // Last name of user
        TextView lastNameView = convertView.findViewById(R.id.nomAV);
        lastNameView.setText(person.getLastName());

        // Profession of user
        TextView professionView = convertView.findViewById(R.id.professionAV);
        professionView.setText(person.getProfession());

        return convertView;
    }
}