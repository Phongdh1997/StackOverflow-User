package com.example.stackoverflowuser.repository;

import androidx.lifecycle.LiveData;

import com.example.stackoverflowuser.model.User;

import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void loadNewUsers(int page, int pageSize) {

    }

    @Override
    public LiveData<List<User>> loadUsers() {
        return null;
    }

    private void fetchUser() {

    }
}
