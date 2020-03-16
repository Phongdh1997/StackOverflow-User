package com.example.stackoverflowuser.model;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class PagedListResult<T> {
    private LiveData<PagedList<T>> userPagedListLiveData;
    private LiveData<String> networkStateLiveData;

    public PagedListResult(LiveData<PagedList<T>> userPagedListLiveData, LiveData<String> networkState) {
        this.userPagedListLiveData = userPagedListLiveData;
        this.networkStateLiveData = networkState;
    }

    public LiveData<PagedList<T>> getPagedListLiveData() {
        return userPagedListLiveData;
    }

    public LiveData<String> getNetworkStateLiveData() {
        return networkStateLiveData;
    }
}
