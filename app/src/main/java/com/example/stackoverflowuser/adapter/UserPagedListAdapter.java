package com.example.stackoverflowuser.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;

public class UserPagedListAdapter
        extends PagedListAdapter<UserEntity, UserPagedListAdapter.UserItemViewHolder> {

    // obseverable for item clicked on recycleView
    private final MutableLiveData<UserEntity> itemClickedLiveData = new MutableLiveData<>();

    // obseverable for bookmarked clicked on recycleView
    private final MutableLiveData<UserEntity> bookmarkedClickedLiveData = new MutableLiveData<>();

    public UserPagedListAdapter() {
        super(DIFF_CALLBACK);
    }

    public LiveData<UserEntity> getItemClickedLiveData() {
        return itemClickedLiveData;
    }

    public LiveData<UserEntity> getBookmarkedClickedLiveData() {
        return bookmarkedClickedLiveData;
    }

    public static class UserItemViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private ImageView ivAvatar;
        private ImageView ivBookMarked;
        private UserEntity currentUser;

        public UserItemViewHolder(@NonNull ConstraintLayout itemView,
                                  MutableLiveData<UserEntity> itemClickedLiveData,
                                  MutableLiveData<UserEntity> bookmarkedClickLiveData) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            ivAvatar = itemView.findViewById(R.id.ivAvatar);
            ivBookMarked = itemView.findViewById(R.id.ivBookMarked);
            ConstraintLayout userConstraintLayout = itemView.findViewById(R.id.userLayout);
            setOnItemClickListener(userConstraintLayout, itemClickedLiveData);
            setOnBookmarkedClickListener(ivBookMarked, bookmarkedClickLiveData);
        }

        public void bindTo(UserEntity userEntity) {
            if (userEntity != null) {
                currentUser = userEntity;
                txtName.setText(userEntity.getDisplayName());
                if (currentUser.isBookmarked()) {
                    ivBookMarked.setColorFilter(Color.YELLOW);
                } else {
                    ivBookMarked.setColorFilter(0xf1f3f4);
                }

                // TODO: load Avatar here
            }
        }

        private void setOnItemClickListener (ConstraintLayout itemView, MutableLiveData<UserEntity> itemClickedLiveData) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickedLiveData.postValue(currentUser);
                }
            });
        }

        private void setOnBookmarkedClickListener (ImageView ivBookMarked, MutableLiveData<UserEntity> bookmarkedClickLiveData) {
            ivBookMarked.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bookmarkedClickLiveData.postValue(currentUser);
                    ivBookMarked.setColorFilter(Color.YELLOW);
                }
            });
        }
    }

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout recyclerViewUserItemLayout = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_view_user_item_layout, parent, false);
        return new UserPagedListAdapter.UserItemViewHolder(
                recyclerViewUserItemLayout,
                itemClickedLiveData,
                bookmarkedClickedLiveData);
    }

    @Override
    public void onBindViewHolder(@NonNull UserItemViewHolder holder, int position) {
        UserEntity userEntity = getItem(position);
        holder.bindTo(userEntity);
    }

    private static DiffUtil.ItemCallback<UserEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<UserEntity>() {
                // Concert details may have changed if reloaded from the database,
                // but ID is fixed.
                @Override
                public boolean areItemsTheSame(UserEntity oldConcert, UserEntity newConcert) {
                    return oldConcert.getUserId() == newConcert.getUserId();
                }

                @Override
                public boolean areContentsTheSame(UserEntity oldConcert,
                                                  @Nullable UserEntity newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
}
