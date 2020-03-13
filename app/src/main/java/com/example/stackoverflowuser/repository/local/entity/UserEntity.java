package com.example.stackoverflowuser.repository.local.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.stackoverflowuser.repository.remote.model.UserResultGSON;

@Entity (tableName = "user")
public class UserEntity {

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
    }

    @PrimaryKey
    public long userId;

    @ColumnInfo(name = "display_name")
    public String displayName;

    @ColumnInfo(name = "profile_image")
    public String profileImage;

    @ColumnInfo(name = "reputation")
    public long reputation;

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
