package com.example.stackoverflowuser.repository.local.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.stackoverflowuser.repository.local.entity.UserEntity;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity userEntity);

    @Query("SELECT * FROM user ORDER BY userId DESC")
    DataSource.Factory<Integer, UserEntity> getUsers();
}
