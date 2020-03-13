package com.example.stackoverflowuser.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;

public class UserPagedListAdapter
        extends PagedListAdapter<UserEntity, UserPagedListAdapter.UserItemViewHolder> {

    public UserPagedListAdapter() {
        super(DIFF_CALLBACK);
    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {
        public TextView txtName;
        public ImageView ivAvatar;

        public UserItemViewHolder(@NonNull ConstraintLayout itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
        }
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout recyclerViewUserItemLayout = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_user_item_layout, parent, false);
        return new UserPagedListAdapter.UserItemViewHolder(recyclerViewUserItemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        UserEntity item = getItem(position);
        if (item != null) {
            holder.txtName.setText(item.displayName);
        }
    }

    private static DiffUtil.ItemCallback<UserEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UserEntity>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(UserEntity oldConcert, UserEntity newConcert) {
                    return oldConcert.userId == newConcert.userId;
                }

                @Override
                public boolean areContentsTheSame(UserEntity oldConcert,
                                                  @Nullable UserEntity newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
}
