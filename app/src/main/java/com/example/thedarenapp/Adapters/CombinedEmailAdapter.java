package com.example.thedarenapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thedarenapp.Data.Email;
import com.example.thedarenapp.R;

import java.util.List;

public class CombinedEmailAdapter extends ArrayAdapter<Email> {

    private Context mContext;
    private List<Email> mEmails;

    public CombinedEmailAdapter(Context context, List<Email> emails) {
        super(context, 0, emails);
        mContext = context;
        mEmails = emails;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.user_list_emails, parent, false);
        }

        Email email = mEmails.get(position);

        // Set the icon image here
        ImageView iconView = convertView.findViewById(R.id.icon);
        iconView.setImageResource(R.drawable.ic_action_name);

        // Email you wrote to
        TextView recipientView = convertView.findViewById(R.id.recipientText);
        recipientView.setText(email.getRecipient());

        // Name of the email
        TextView subjectView = convertView.findViewById(R.id.subjectText);
        subjectView.setText(email.getSujet());

        // Contenu du message que l'utilisateur a écrit
        TextView messageView = convertView.findViewById(R.id.messageText);
        messageView.setText(email.getMessage());

        // The time of the message sent
        TextView timeView = convertView.findViewById(R.id.timeText);
        timeView.setText(email.getDateString());

        return convertView;
    }
}
