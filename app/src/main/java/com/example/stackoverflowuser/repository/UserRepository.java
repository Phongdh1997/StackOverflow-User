package com.example.stackoverflowuser.repository;

import com.example.stackoverflowuser.model.UserPagedListResult;
import com.example.stackoverflowuser.data.local.entity.UserEntity;

public interface UserRepository {
    UserPagedListResult loadUsers();
    void updateUser(UserEntity userEntity);
}
