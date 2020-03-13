package com.example.stackoverflowuser.model.business;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.common.NetworkStateValue;
import com.example.stackoverflowuser.common.UserPagedListConfig;
import com.example.stackoverflowuser.repository.local.dao.UserDao;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;
import com.example.stackoverflowuser.repository.remote.model.UserResultGSON;
import com.example.stackoverflowuser.repository.remote.service.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
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
        requestAndSaveData();
        Log.e("On Zero loaded", "test");
    }

    @Override
    public void onItemAtEndLoaded(@NonNull UserEntity itemAtEnd) {
        requestAndSaveData();
        Log.e("On end loaded", "test");
    }

    private void requestAndSaveData() {
        if (!isRequestInProgress && isHasMore) {
            networkState.postValue(NetworkStateValue.LOADING);
            isRequestInProgress = true;
            userService.getStackOverflowUsers(lastRequestedPage, UserPagedListConfig.NETWORK_PAGE_SIZE)
                    .enqueue(new Callback<UserResultGSON>() {
                        @Override
                        public void onResponse(Call<UserResultGSON> call, Response<UserResultGSON> response) {
                            if (response.code() == 200) {
                                UserResultGSON userResultGSON = response.body();
                                if (userResultGSON != null) {
                                    List<UserResultGSON.UserItem> userList = userResultGSON.getItems();
                                    Log.e("resuilt", userResultGSON.toString());
                                    saveDataToDB(userList);
                                    isHasMore = userResultGSON.getHasMore();
                                    lastRequestedPage++;
                                }
                                networkState.postValue(NetworkStateValue.SUCCESS);
                            } else {
                                Log.e("request message", response.code() + response.message());
                                networkState.postValue(NetworkStateValue.ERROR);
                            }
                            isRequestInProgress = false;
                        }

                        @Override
                        public void onFailure(Call<UserResultGSON> call, Throwable t) {
                            networkState.postValue(NetworkStateValue.ERROR);
                            isRequestInProgress = false;
                        }
                    });
        }
    }

    private void saveDataToDB(List<UserResultGSON.UserItem> userList) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (userList == null) return;
                List<UserEntity> userEntityList = new ArrayList<>();
                for (UserResultGSON.UserItem userItem: userList) {
                    userEntityList.add(new UserEntity(userItem));
                }
                userDao.insertAll(userEntityList);
            }
        }).start();
    }
}
