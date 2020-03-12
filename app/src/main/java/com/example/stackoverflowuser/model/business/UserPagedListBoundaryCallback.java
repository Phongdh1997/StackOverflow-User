package com.example.stackoverflowuser.model.business;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.repository.local.dao.UserDao;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;
import com.example.stackoverflowuser.repository.remote.service.UserService;

public class UserPagedListBoundaryCallback extends PagedList.BoundaryCallback<UserEntity> {

    private int lastRequestedPage = 1;

    // avoid triggering multiple requests in the same time
    private boolean isRequestInProgress = false;
    private MutableLiveData<Boolean> networkError = new MutableLiveData<>();
    private UserService userService;
    private UserDao userDao;

    public LiveData<Boolean> getNetworkError() {
        return networkError;
    }

    public UserPagedListBoundaryCallback(UserService userService, UserDao userDao) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @Override
    public void onZeroItemsLoaded() {
        requestAndSaveData();
        Log.e("On Zero loaded", "test");
    }

    @Override
    public void onItemAtEndLoaded(@NonNull UserEntity itemAtEnd) {
        requestAndSaveData();
        Log.e("On end loaded", "test");
    }

    private void requestAndSaveData() {

    }
}
