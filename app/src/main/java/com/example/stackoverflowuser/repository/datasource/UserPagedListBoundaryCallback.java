package com.example.stackoverflowuser.repository.datasource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.common.NetworkStateValue;
import com.example.stackoverflowuser.common.UserPagedListConfig;
import com.example.stackoverflowuser.data.local.dao.UserDao;
import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.data.remote.model.UserResultGSON;
import com.example.stackoverflowuser.data.remote.service.UserService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Response;

public class UserPagedListBoundaryCallback extends PagedList.BoundaryCallback<UserEntity> {

    private int lastRequestedPage = 1;
    private boolean isRequestInProgress = false;    // avoid triggering multiple requests in the same time
    private boolean isHasMore = true;   // show if has more page for next loading
    private MutableLiveData<String> networkState = new MutableLiveData<>();
    private UserService userService;
    private UserDao userDao;

    public LiveData<String> getNetworkStateLiveData() {
        return networkState;
    }

    public UserPagedListBoundaryCallback(UserService userService, UserDao userDao) {
        this.userDao = userDao;
        this.userService = userService;
    }

    @Override
    public void onZeroItemsLoaded() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                requestAndSaveData();
            }
        }).start();
        Log.e("On Zero loaded", "load page " + lastRequestedPage);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull UserEntity itemAtEnd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                requestAndSaveData();
            }
        }).start();
        Log.e("On end loaded", "load page " + lastRequestedPage);
    }

    private void requestAndSaveData() {
        if (!isRequestInProgress && isHasMore) {
            networkState.postValue(NetworkStateValue.LOADING);
            isRequestInProgress = true;
            try {
                Response<UserResultGSON> response = userService.getStackOverflowUsers(
                        lastRequestedPage,
                        UserPagedListConfig.NETWORK_PAGE_SIZE)
                        .execute();
                UserResultGSON userResultGSON = response.body();
                if (response.code() == 200 && userResultGSON != null) {
                    List<UserResultGSON.UserItem> userList = userResultGSON.getItems();
                    isHasMore = userResultGSON.getHasMore();
                    lastRequestedPage++;
                    saveDataToDB(userList);
                } else {
                    networkState.postValue(NetworkStateValue.ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
                networkState.postValue(NetworkStateValue.ERROR);
            }
            isRequestInProgress = false;
        }
    }

    private void saveDataToDB(List<UserResultGSON.UserItem> userList) {
        if (userList == null) return;
        List<UserEntity> userEntityList = new ArrayList<>();
        for (UserResultGSON.UserItem userItem: userList) {
            userEntityList.add(new UserEntity(userItem));
        }
        userDao.insertAll(userEntityList);
    }
}
