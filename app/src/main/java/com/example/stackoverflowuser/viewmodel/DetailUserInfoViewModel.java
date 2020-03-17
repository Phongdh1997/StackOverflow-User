package com.example.stackoverflowuser.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.data.remote.model.ReputationDetailItem;
import com.example.stackoverflowuser.model.PagedListResult;
import com.example.stackoverflowuser.repository.DetailUserInfoRepository;
import com.example.stackoverflowuser.repository.DetailUserInfoRepositoryImpl;

public class DetailUserInfoViewModel extends AndroidViewModel {

    private DetailUserInfoRepository detailUserInfoRepository;
    private PagedListResult<ReputationDetailItem> detailItemPagedListResult;

    public DetailUserInfoViewModel(@NonNull Application application) {
        super(application);

        detailUserInfoRepository = new DetailUserInfoRepositoryImpl();
    }

    public LiveData<PagedList<ReputationDetailItem>> getDetailInfoPagedListLiveData(UserEntity user) {
        detailItemPagedListResult = detailUserInfoRepository.loadDetailUserInfos(user);
        return detailItemPagedListResult.getPagedListLiveData();
    }

    public LiveData<String> getDetailInfoNetworkStateLiveData() {
        return detailItemPagedListResult.getNetworkStateLiveData();
    }
}
