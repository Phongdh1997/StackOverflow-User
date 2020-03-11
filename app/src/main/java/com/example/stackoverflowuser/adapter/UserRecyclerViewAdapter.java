package com.example.stackoverflowuser.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.model.User;

import java.util.List;

public class UserRecyclerViewAdapter
        extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserItemViewHolder> {

    private List<User> userList;

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public ImageView ivAvatar;

        public UserItemViewHolder(@NonNull ConstraintLayout itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
        }
    }

    public UserRecyclerViewAdapter (List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout recyclerViewUserItemLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_user_item_layout, parent, false);
        return new UserItemViewHolder(recyclerViewUserItemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        User userItem = userList.get(position);
        holder.txtName.setText(userItem.getName());

        // TODO: set avatar here
    }

    @Override
    public int getItemCount() {
        Log.e("tes", "" + ((userList != null) ? userList.size() : 0));
        return (userList != null) ? userList.size() : 0;
    }

}
