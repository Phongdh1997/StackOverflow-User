package com.example.stackoverflowuser.repository;

import androidx.lifecycle.LiveData;

import com.example.stackoverflowuser.model.User;

import java.util.List;

public interface UserRepository {
    void loadNewUsers(int page, int pageSize);
    LiveData<List<User>> loadUsers();
}
