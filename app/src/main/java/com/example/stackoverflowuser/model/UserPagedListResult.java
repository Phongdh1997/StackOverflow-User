package com.example.stackoverflowuser.model;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.data.local.entity.UserEntity;

public class UserPagedListResult {
    private LiveData<PagedList<UserEntity>> userPagedListLiveData;
    private LiveData<String> networkStateLiveData;

    public UserPagedListResult(LiveData<PagedList<UserEntity>> userPagedListLiveData, LiveData<String> networkState) {
        this.userPagedListLiveData = userPagedListLiveData;
        this.networkStateLiveData = networkState;
    }

    public LiveData<PagedList<UserEntity>> getUserPagedListLiveData() {
        return userPagedListLiveData;
    }

    public LiveData<String> getNetworkStateLiveData() {
        return networkStateLiveData;
    }
}
