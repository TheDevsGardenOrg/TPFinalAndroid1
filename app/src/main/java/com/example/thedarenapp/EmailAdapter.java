package com.example.thedarenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class EmailAdapter extends ArrayAdapter<Email> {
    private Context mContext;
    private List<Email> mEmails;

    public EmailAdapter(Context context, List<Email> emails) {
        super(context, 0, emails);
        mContext = context;
        mEmails = emails;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_emails, parent, false);
        }

        Email email = mEmails.get(position);

        ImageView iconView = convertView.findViewById(R.id.icon);
        // Set the icon image here

        TextView senderNameView = convertView.findViewById(R.id.senderNameText);
        senderNameView.setText(email.getSender());

        TextView subjectView = convertView.findViewById(R.id.subjectText);
        subjectView.setText(email.getSujet());

        TextView messageView = convertView.findViewById(R.id.messageText);
        messageView.setText(email.getMessage());

        return convertView;
    }
}
