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

public class userCustomAdapter extends ArrayAdapter<Person> {

    public userCustomAdapter(Context context, List<Person> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.admin_user_list_view, parent, false);
        }

        Person person = getItem(position);

        ImageView iconView = convertView.findViewById(R.id.icon);
        TextView emailView = convertView.findViewById(R.id.courrielNameAV);
        TextView firstNameView = convertView.findViewById(R.id.prenomAV);
        TextView lastNameView = convertView.findViewById(R.id.nomAV);
        TextView professionView = convertView.findViewById(R.id.professionAV);

        iconView.setImageResource(R.drawable.ic_action_name);
        emailView.setText(person.getEmail());
        firstNameView.setText(person.getFirstName());
        lastNameView.setText(person.getLastName());
        professionView.setText(person.getProfession());

        return convertView;
    }
}
