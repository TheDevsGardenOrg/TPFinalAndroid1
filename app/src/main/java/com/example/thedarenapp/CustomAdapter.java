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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_tem, parent, false);
        }

        ListItem item = getItem(position);

        TextView textView = convertView.findViewById(R.id.text);
        ImageView imageView = convertView.findViewById(R.id.icon);

        textView.setText(item.getText());
        imageView.setImageResource(item.getIconResId());

        return convertView;
    }
}
