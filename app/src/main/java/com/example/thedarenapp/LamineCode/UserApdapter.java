package com.example.thedarenapp.LamineCode;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lab123.R.id;
import com.example.lab123.R.layout;
import com.example.thedarenapp.userJava.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private final List<User> userList;
    private final OnUserClickListener onUserClickListener;

    public UserAdapter(List<User> userList, OnUserClickListener onUserClickListener) {
        this.userList = userList;
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout.item_user, parent, false);
        return new UserViewHolder(view, this.onUserClickListener);
    }

    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = (User)this.userList.get(position);
        holder.userEmailTextView.setText(user.getEmail());
    }

    public int getItemCount() {
        return this.userList.size();
    }

    public interface OnUserClickListener {
        void onUserClick(int var1);
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        final TextView userEmailTextView;

        UserViewHolder(@NonNull View itemView, OnUserClickListener onUserClickListener) {
            super(itemView);
            this.userEmailTextView = (TextView)itemView.findViewById(id.userEmailTextView);
            itemView.setOnClickListener((v) -> {
                if (onUserClickListener != null) {
                    int position = this.getAbsoluteAdapterPosition();
                    if (position != -1) {
                        onUserClickListener.onUserClick(position);
                    }
                }

            });
        }
    }
}
