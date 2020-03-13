package com.example.stackoverflowuser.repository.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.stackoverflowuser.repository.local.dao.UserDao;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;

import java.util.concurrent.Executors;

@Database(entities = {UserEntity.class}, exportSchema = false, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public static AppDatabase getInstance(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "app-database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appDatabase;
    }

    public abstract UserDao userDao();
}
