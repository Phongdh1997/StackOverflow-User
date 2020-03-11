package com.example.stackoverflowuser.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackoverflowuser.model.User;

import java.util.List;

public class UserRecyclerViewAdapter
        extends RecyclerView.Adapter<UserRecyclerViewAdapter.UserItemViewHolder> {

    private List<User> userList;

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {
        public UserItemViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public UserRecyclerViewAdapter (List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return (userList != null) ? userList.size() : 0;
    }

}
