package com.example.stackoverflowuser.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.adapter.viewhoder.DetailUserInfoItemViewHolder;
import com.example.stackoverflowuser.data.remote.model.ReputationDetailItem;

public class DetailUserInfoAdapter extends
        PagedListAdapter<ReputationDetailItem, DetailUserInfoItemViewHolder> {

    public DetailUserInfoAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public DetailUserInfoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ConstraintLayout rvDetailUserInfoItemLayout = (ConstraintLayout) LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.rv_detail_user_info_item_layout, parent, false);
        return new DetailUserInfoItemViewHolder(rvDetailUserInfoItemLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailUserInfoItemViewHolder holder, int position) {
        ReputationDetailItem item = getItem(position);
        holder.bindTo(item, position);
    }


    private static DiffUtil.ItemCallback<ReputationDetailItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ReputationDetailItem>() {
                @Override
                public boolean areItemsTheSame(@NonNull ReputationDetailItem oldItem, @NonNull ReputationDetailItem newItem) {
                    return oldItem.getPostId() == newItem.getPostId();
                }

                @Override
                public boolean areContentsTheSame(ReputationDetailItem oldConcert,
                                                  @Nullable ReputationDetailItem newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
}