package com.example.stackoverflowuser.repository;

import com.example.stackoverflowuser.data.local.entity.DetailUserInfoEntity;
import com.example.stackoverflowuser.model.DetailUserInfoListResult;

public interface DetailUserInfoRepository {
    DetailUserInfoListResult loadUsers();
    void updateUser(DetailUserInfoEntity detailUserInfoEntity);
}
