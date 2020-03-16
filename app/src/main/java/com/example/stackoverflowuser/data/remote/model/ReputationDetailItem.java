package com.example.stackoverflowuser.data.remote.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReputationDetailItem {
    @SerializedName("reputation_history_type")
    @Expose
    private String reputationHistoryType;
    @SerializedName("reputation_change")
    @Expose
    private long reputationChange;
    @SerializedName("post_id")
    @Expose
    private long postId;
    @SerializedName("creation_date")
    @Expose
    private long creationDate;
    @SerializedName("user_id")
    @Expose
    private long userId;

    public String getReputationHistoryType() {
        return reputationHistoryType;
    }

    public void setReputationHistoryType(String reputationHistoryType) {
        this.reputationHistoryType = reputationHistoryType;
    }

    public long getReputationChange() {
        return reputationChange;
    }

    public void setReputationChange(long reputationChange) {
        this.reputationChange = reputationChange;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(long creationDate) {
        this.creationDate = creationDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @NonNull
    @Override
    public String toString() {
        return reputationHistoryType + ", " + reputationChange + ", " + userId;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ReputationDetailItem) {
            ReputationDetailItem otherObj = (ReputationDetailItem) obj;
            return postId == otherObj.getPostId();
        }
        return false;
    }
}
