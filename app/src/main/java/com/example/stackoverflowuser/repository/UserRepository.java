package com.example.stackoverflowuser.repository;

import com.example.stackoverflowuser.common.UserLoadType;
import com.example.stackoverflowuser.model.UserPagedListResult;
import com.example.stackoverflowuser.data.local.entity.UserEntity;

public interface UserRepository {
    UserPagedListResult loadUsers(@UserLoadType String loadType);
    void updateUser(UserEntity userEntity);
}
