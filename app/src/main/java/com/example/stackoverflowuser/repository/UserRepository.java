package com.example.stackoverflowuser.repository;

import com.example.stackoverflowuser.model.UserPagedListResult;

public interface UserRepository {
    UserPagedListResult loadUsers();
}
