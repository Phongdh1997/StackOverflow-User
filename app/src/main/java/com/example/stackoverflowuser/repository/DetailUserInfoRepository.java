package com.example.stackoverflowuser.repository;

import com.example.stackoverflowuser.data.local.entity.DetailUserInfoEntity;
import com.example.stackoverflowuser.model.PagedListResult;

public interface DetailUserInfoRepository {
    PagedListResult<DetailUserInfoEntity> loadUsers();
    void updateUser(DetailUserInfoEntity detailUserInfoEntity);
}
