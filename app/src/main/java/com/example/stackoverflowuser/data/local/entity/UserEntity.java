package com.example.stackoverflowuser.data.local.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.stackoverflowuser.data.remote.model.UserResultGSON;
import com.example.stackoverflowuser.util.DateStringConverter;

import java.io.Serializable;

@Entity (tableName = "user")
public class UserEntity implements Serializable {

    public UserEntity() {}

    @Ignore
    public UserEntity(long userId, String displayName, String profileImage, long reputation) {
        this.userId = userId;
        this.displayName = displayName;
        this.profileImage = profileImage;
        this.reputation = reputation;
    }

    @Ignore
    public UserEntity(UserResultGSON.UserItem userItem) {
        this.userId = userItem.getUserId();
        this.displayName = userItem.getDisplayName();
        this.profileImage = userItem.getProfileImage();
        this.reputation = userItem.getReputation();
        this.location = userItem.getLocation();
        this.lastAccessDate = userItem.getLastAccessDate();
    }

    @PrimaryKey
    private long userId;

    @ColumnInfo(name = "display_name")
    private String displayName;

    @ColumnInfo(name = "profile_image")
    private String profileImage;

    @ColumnInfo(name = "reputation")
    private long reputation;

    @ColumnInfo(name = "is_bookmarked")
    private boolean isBookmarked = false;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "last_access_date")
    private long lastAccessDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public long getReputation() {
        return reputation;
    }

    public void setReputation(long reputation) {
        this.reputation = reputation;
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }

    public void toggleBookmarked() {
        isBookmarked = !isBookmarked;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getLastAccessDate() {
        return lastAccessDate;
    }

    public void setLastAccessDate(long lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    public String getLastAccessDateString() {
        return DateStringConverter.timeToDateString(lastAccessDate);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof UserEntity) {
            UserEntity otherUserEntity = (UserEntity) obj;
            return  userId == otherUserEntity.userId &&
                    displayName.equals(otherUserEntity.displayName) &&
                    profileImage.equals(otherUserEntity.profileImage) &&
                    reputation == otherUserEntity.reputation;
        }
        return false;
    }
}
