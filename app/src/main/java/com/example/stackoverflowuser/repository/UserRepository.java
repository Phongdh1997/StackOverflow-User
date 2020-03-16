package com.example.stackoverflowuser.repository;

import com.example.stackoverflowuser.annotation.UserLoadType;
import com.example.stackoverflowuser.model.PagedListResult;
import com.example.stackoverflowuser.data.local.entity.UserEntity;

public interface UserRepository {
    PagedListResult<UserEntity> loadUsers(@UserLoadType String loadType);
    void updateUser(UserEntity userEntity);
}
