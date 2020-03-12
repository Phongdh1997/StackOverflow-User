package com.example.stackoverflowuser.repository.remote.model;

import android.content.ClipData;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResultGSON {
    @SerializedName("items")
    @Expose
    private List<UserItem> items;
    @SerializedName("has_more")
    @Expose
    private Boolean hasMore;
    @SerializedName("quota_max")
    @Expose
    private Long quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    private Long quotaRemaining;

    public List<UserItem> getItems() {
        return items;
    }

    public Boolean getHasMore() {
        return hasMore;
    }

    public static class UserItem {
        @SerializedName("reputation")
        @Expose
        private Long reputation;
        @SerializedName("user_id")
        @Expose
        private Long userId;
        @SerializedName("profile_image")
        @Expose
        private String profileImage;
        @SerializedName("display_name")
        @Expose
        private String displayName;

        public Long getReputation() {
            return reputation;
        }

        public Long getUserId() {
            return userId;
        }

        public String getProfileImage() {
            return profileImage;
        }

        public String getDisplayName() {
            return displayName;
        }

        @NonNull
        @Override
        public String toString() {
            return "display_name: " + displayName;
        }
    }

    @NonNull
    @Override
    public String toString() {
        return "has_more " + hasMore + ", quota_max " + quotaMax + ", quota_remaining" + quotaRemaining;
    }
}
