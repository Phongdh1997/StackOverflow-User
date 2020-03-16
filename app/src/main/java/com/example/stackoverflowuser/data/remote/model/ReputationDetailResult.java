package com.example.stackoverflowuser.data.remote.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReputationDetailResult {
    @SerializedName(value = "items")
    @Expose
    private List<ReputationDetailItem> items;
    @SerializedName(value = "has_more")
    @Expose
    private boolean hasMore;
    @SerializedName("quota_max")
    @Expose
    private long quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    private long quotaRemaining;

    public List<ReputationDetailItem> getItems() {
        return items;
    }

    public void setItems(List<ReputationDetailItem> items) {
        this.items = items;
    }

    public boolean getHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    public long getQuotaMax() {
        return quotaMax;
    }

    public void setQuotaMax(long quotaMax) {
        this.quotaMax = quotaMax;
    }

    public long getQuotaRemaining() {
        return quotaRemaining;
    }

    public void setQuotaRemaining(long quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

    @NonNull
    @Override
    public String toString() {
        return "size " + items.size() + ", have more " + hasMore;
    }
}
