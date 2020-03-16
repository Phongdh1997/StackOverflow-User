package com.example.stackoverflowuser.model;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

public class PagedListResult<T> {
    private LiveData<PagedList<T>> userPagedListLiveData;
    private LiveData<String> networkStateLiveData;

    public PagedListResult(LiveData<PagedList<T>> userPagedListLiveData,
                           @Nullable LiveData<String> networkState) {
        this.userPagedListLiveData = userPagedListLiveData;
        if (networkState == null) {
            networkStateLiveData = new MutableLiveData<>();
        } else {
            networkStateLiveData = networkState;
        }
    }

    public LiveData<PagedList<T>> getPagedListLiveData() {
        return userPagedListLiveData;
    }

    public LiveData<String> getNetworkStateLiveData() {
        return networkStateLiveData;
    }
}
