package com.example.thedarenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Email> {

    public CustomAdapter(Context context, List<Email> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_emails, parent, false);
        }//this is where it gets sketchy

        Email item = getItem(position);
        //sender,receiver,subject,message,date
        //ibel@gmail.com;orange@outlook.com;this is a subject;This si a string;Tue Oct 24 11:26:07 EDT 2023;

        TextView sender = convertView.findViewById(R.id.senderNameText);
        TextView sujet = convertView.findViewById(R.id.subjectText);
        TextView message = convertView.findViewById(R.id.messageText);
//        ImageView imageView = convertView.findViewById(R.id.icon);

        //maybe loop

        sender.setText(item.getSender());
        sujet.setText(item.getSujet());
        message.setText(item.getMessage());
//        imageView.setImageResource(item.getIconResId());

        return convertView;
    }
}
