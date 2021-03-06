package com.example.stackoverflowuser.repository.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.stackoverflowuser.common.NetworkStateValue;
import com.example.stackoverflowuser.common.UserPagedListConfig;
import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.data.remote.model.ReputationDetailItem;
import com.example.stackoverflowuser.data.remote.model.ReputationDetailResult;
import com.example.stackoverflowuser.data.remote.service.DetailUserInfoService;

import java.io.IOException;
import java.util.List;

import retrofit2.Response;

public class DetailUserInfoDataSource extends PageKeyedDataSource<Integer, ReputationDetailItem> {
    private UserEntity user;
    private DetailUserInfoService detailUserInfoService;
    private MutableLiveData<String> networkState;
    private boolean isRequestInProgress = false;    // avoid triggering multiple requests in the same time
    private boolean isHasMore = true;   // show if has more page for next loading

    public LiveData<String> getNetworkStateLiveData () {
        return networkState;
    }
    public void setNetworkStateLiveDate(MutableLiveData<String> networkStateLiveDate) {
        this.networkState = networkStateLiveDate;
    }

    public DetailUserInfoDataSource(DetailUserInfoService detailUserInfoService, UserEntity userEntity) {
        this.detailUserInfoService = detailUserInfoService;
        this.user = userEntity;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, ReputationDetailItem> callback) {
        List<ReputationDetailItem> detailInfoList = loadDataFromServer(1,UserPagedListConfig.NETWORK_PAGE_SIZE);
        if (detailInfoList != null) {
            // Call onResult to update loaded page
            // Loaded page: page 1
            // Prev page: null
            // Next page: 2
            callback.onResult(detailInfoList, null, 2);
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ReputationDetailItem> callback) {
        List<ReputationDetailItem> detailInfoList = loadDataFromServer(params.key,UserPagedListConfig.NETWORK_PAGE_SIZE);
        if (detailInfoList != null) {
            callback.onResult(detailInfoList, params.key - 1);
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, ReputationDetailItem> callback) {
        List<ReputationDetailItem> detailInfoList = loadDataFromServer(1,UserPagedListConfig.NETWORK_PAGE_SIZE);
        if (detailInfoList != null) {
            callback.onResult(detailInfoList, params.key + 1);
        }
    }

    private List<ReputationDetailItem> loadDataFromServer(int page, int pageSize) {
        if (!isHasMore) {
            networkState.postValue(NetworkStateValue.NOT_HAS_MORE);
            return null;
        }
        if (!isRequestInProgress) {
            networkState.postValue(NetworkStateValue.LOADING);
            isRequestInProgress = true;
            try {
                Response<ReputationDetailResult> response = detailUserInfoService
                        .getDetailUserInfo(user.getUserId(), page, pageSize)
                        .execute();
                ReputationDetailResult resultData = response.body();
                if (response.code() == 200 && resultData != null) {
                    isHasMore = resultData.getHasMore();
                    isRequestInProgress = false;
                    networkState.postValue(NetworkStateValue.SUCCESS);
                    return resultData.getItems();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            isRequestInProgress = false;
            networkState.postValue(NetworkStateValue.ERROR);
        }
        return null;
    }

    public static class Factory extends DataSource.Factory<java.lang.Integer, ReputationDetailItem> {
        private DetailUserInfoService detailUserInfoService;
        private UserEntity user;
        private MutableLiveData<String> networkState = new MutableLiveData<>();

        public LiveData<String> getNetworkStateLiveData () {
            return networkState;
        }

        private Factory (DetailUserInfoService detailUserInfoService, UserEntity user) {
            this.detailUserInfoService = detailUserInfoService;
            this.user = user;
        }

        @NonNull
        @Override
        public DataSource<Integer, ReputationDetailItem> create() {
            DetailUserInfoDataSource dataSource = new DetailUserInfoDataSource(
                    detailUserInfoService, user);
            dataSource.setNetworkStateLiveDate(networkState);
            return dataSource;
        }
    }

    public static Factory getDataSourceFactory (DetailUserInfoService detailUserInfoService, UserEntity user) {
        return new Factory(detailUserInfoService, user);
    }
}
