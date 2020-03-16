package com.example.stackoverflowuser.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.stackoverflowuser.model.DetailUserInfoListResult;
import com.example.stackoverflowuser.repository.DetailUserInfoRepository;
import com.example.stackoverflowuser.repository.DetailUserInfoRepositoryImpl;

public class DetailUserInfoViewModel extends AndroidViewModel {

    private DetailUserInfoListResult detailUserInfoListResult;
    private DetailUserInfoRepository detailUserInfoRepository;

    public DetailUserInfoViewModel(@NonNull Application application) {
        super(application);

        detailUserInfoRepository = new DetailUserInfoRepositoryImpl();
        detailUserInfoListResult = detailUserInfoRepository.loadUsers();
    }
}
