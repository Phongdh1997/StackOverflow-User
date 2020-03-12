package com.example.stackoverflowuser.model;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.repository.local.entity.UserEntity;

public class UserPagedListResult {
    private LiveData<PagedList<UserEntity>> userPagedListLiveData;
    private LiveData<Boolean> networkErrorLiveData;

    public UserPagedListResult(LiveData<PagedList<UserEntity>> userPagedListLiveData, LiveData<Boolean> networkError) {
        this.userPagedListLiveData = userPagedListLiveData;
        this.networkErrorLiveData = networkError;
    }

    public LiveData<PagedList<UserEntity>> getUserPagedListLiveData() {
        return userPagedListLiveData;
    }

    public LiveData<Boolean> getNetworkErrorLiveData() {
        return networkErrorLiveData;
    }
}
