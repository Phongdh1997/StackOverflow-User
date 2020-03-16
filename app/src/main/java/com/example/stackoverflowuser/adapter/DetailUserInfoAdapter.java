package com.example.stackoverflowuser.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.adapter.viewhoder.DetailUserInfoItemViewHolder;
import com.example.stackoverflowuser.data.local.entity.DetailUserInfoEntity;

public class DetailUserInfoAdapter extends
        PagedListAdapter<DetailUserInfoEntity, DetailUserInfoItemViewHolder> {

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
        DetailUserInfoEntity item = getItem(position);
        holder.bindTo(item);
    }


    private static DiffUtil.ItemCallback<DetailUserInfoEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<DetailUserInfoEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull DetailUserInfoEntity oldItem, @NonNull DetailUserInfoEntity newItem) {
                    return false;
                }

                @Override
                public boolean areContentsTheSame(DetailUserInfoEntity oldConcert,
                                                  @Nullable DetailUserInfoEntity newConcert) {
                    return oldConcert.equals(newConcert);
                }
            };
}