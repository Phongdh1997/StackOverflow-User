package com.example.stackoverflowuser.adapter.viewhoder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.data.remote.model.ReputationDetailItem;
import com.example.stackoverflowuser.util.DateStringConverter;

public class DetailUserInfoItemViewHolder extends RecyclerView.ViewHolder {

    private TextView txtReputationHistoryType;
    private TextView txtPostId;
    private TextView txtReputationChange;
    private TextView txtCreationDate;

    public DetailUserInfoItemViewHolder(@NonNull View itemView) {
        super(itemView);

        txtReputationHistoryType = itemView.findViewById(R.id.txtReputationHistoryType);
        txtPostId = itemView.findViewById(R.id.txtPostId);
        txtReputationChange = itemView.findViewById(R.id.txtReputationChange);
        txtCreationDate = itemView.findViewById(R.id.txtCreationDate);
    }

    public void bindTo(ReputationDetailItem item) {
        txtReputationHistoryType.setText(item.getReputationHistoryType());
        txtPostId.setText(String.valueOf(item.getPostId()));
        txtReputationChange.setText(String.valueOf(item.getReputationChange()));
        txtCreationDate.setText(DateStringConverter.timeToDateString(item.getCreationDate()));
    }
}