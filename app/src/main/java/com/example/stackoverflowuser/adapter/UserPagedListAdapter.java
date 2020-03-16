package com.example.stackoverflowuser.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.adapter.viewhoder.UserItemViewHolder;
import com.example.stackoverflowuser.data.local.entity.UserEntity;

public class UserPagedListAdapter
        extends PagedListAdapter<UserEntity, UserItemViewHolder> {

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

    @NonNull
    @Override
    public UserItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout recyclerViewUserItemLayout = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_view_user_item_layout, parent, false);
        return new UserItemViewHolder(
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
