package com.example.stackoverflowuser.repository.remote;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.stackoverflowuser.repository.remote.dao.UserDao;
import com.example.stackoverflowuser.repository.remote.entity.UserEntity;

@Database(entities = {UserEntity.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "app-database").build();
        }
        return appDatabase;
    }

    public abstract UserDao userDao();
}
