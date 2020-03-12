package com.example.stackoverflowuser.repository.local.entity;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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
        return super.equals(obj);
    }
}
