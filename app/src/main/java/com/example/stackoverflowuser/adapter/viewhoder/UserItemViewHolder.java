package com.example.stackoverflowuser.adapter.viewhoder;

import android.graphics.Color;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.module.AppGlideModule;
import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.common.AppGlideModulConfig;
import com.example.stackoverflowuser.common.GlideApp;
import com.example.stackoverflowuser.data.local.entity.UserEntity;

public class UserItemViewHolder extends RecyclerView.ViewHolder {
    private UserEntity currentUser;

    private TextView txtName;
    private ImageView ivAvatar;
    private ImageView ivBookMarked;
    private TextView txtLastAccessDate;
    private TextView txtLocation;

    public UserItemViewHolder(@NonNull ConstraintLayout itemView,
                              MutableLiveData<UserEntity> itemClickedLiveData,
                              MutableLiveData<UserEntity> bookmarkedClickLiveData) {
        super(itemView);
        txtName = itemView.findViewById(R.id.txtName);
        ivAvatar = itemView.findViewById(R.id.ivAvatar);
        ivBookMarked = itemView.findViewById(R.id.ivBookMarked);
        txtLastAccessDate = itemView.findViewById(R.id.txtLastAccessDate);
        txtLocation = itemView.findViewById(R.id.txtLocation);
        ConstraintLayout userConstraintLayout = itemView.findViewById(R.id.userLayout);
        setOnItemClickListener(userConstraintLayout, itemClickedLiveData);
        setOnBookmarkedClickListener(ivBookMarked, bookmarkedClickLiveData);
    }

    public void bindTo(UserEntity userEntity) {
        if (userEntity != null) {
            currentUser = userEntity;
            txtName.setText(userEntity.getDisplayName());
            txtLocation.setText(userEntity.getLocation());
            txtLastAccessDate.setText(userEntity.getLastAccessDateString());
            setIvBookMarkedColor(ivBookMarked, currentUser.isBookmarked());

            // TODO: load Avatar here
            GlideApp.with(txtName.getContext())
                    .load(userEntity.getProfileImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .into(ivAvatar);
        }
    }

    private void setOnItemClickListener (ConstraintLayout itemView, MutableLiveData<UserEntity> itemClickedLiveData) {
        itemView.setOnClickListener(v -> {
            itemClickedLiveData.postValue(currentUser);
        });
    }

    private void setOnBookmarkedClickListener (ImageView ivBookMarked, MutableLiveData<UserEntity> bookmarkedClickLiveData) {
        ivBookMarked.setOnClickListener(v -> {
            currentUser.toggleBookmarked();     // toggle bookmarked user
            bookmarkedClickLiveData.postValue(currentUser);
            setIvBookMarkedColor(ivBookMarked, currentUser.isBookmarked());
        });
    }

    private void setIvBookMarkedColor(ImageView imv, boolean isBookmarked) {
        if (isBookmarked) {
            imv.setColorFilter(Color.YELLOW);
        } else {
            imv.setColorFilter(0xf1f3f4);
        }
    }
}