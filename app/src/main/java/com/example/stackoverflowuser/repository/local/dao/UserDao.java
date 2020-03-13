package com.example.stackoverflowuser.repository.local.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.stackoverflowuser.repository.local.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity userEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> userEntities);

    @Query("SELECT * FROM user ORDER BY userId ASC")
    DataSource.Factory<Integer, UserEntity> getUsers();
}
