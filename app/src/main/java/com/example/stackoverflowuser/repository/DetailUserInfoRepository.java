package com.example.stackoverflowuser.repository;

import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.data.remote.model.ReputationDetailItem;
import com.example.stackoverflowuser.model.PagedListResult;

public interface DetailUserInfoRepository {
    PagedListResult<ReputationDetailItem> loadDetailUserInfos(UserEntity user);
}
