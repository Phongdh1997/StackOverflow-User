package com.example.stackoverflowuser.data.local.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.stackoverflowuser.data.local.entity.UserEntity;

import java.util.List;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity userEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> userEntities);

    @Query("SELECT * FROM user ORDER BY userId ASC")
    DataSource.Factory<Integer, UserEntity> getUsers();

    @Update
    void updateUser(UserEntity userEntity);
}
