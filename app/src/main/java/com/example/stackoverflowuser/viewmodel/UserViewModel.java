package com.example.stackoverflowuser.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.common.NetworkStateValue;
import com.example.stackoverflowuser.model.UserPagedListResult;
import com.example.stackoverflowuser.repository.UserRepository;
import com.example.stackoverflowuser.repository.UserRepositoryImpl;
import com.example.stackoverflowuser.repository.local.AppDatabase;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;

public class UserViewModel extends AndroidViewModel {
    private Context context;
    private UserPagedListResult userPagedListResult;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        userRepository = new UserRepositoryImpl(AppDatabase.getInstance(context).userDao());
        userPagedListResult = userRepository.loadUsers();
    }

    public LiveData<PagedList<UserEntity>> getUserPagedListLiveData() {
        return userPagedListResult.getUserPagedListLiveData();
    }

    public LiveData<String> getNetworkStateLiveData () {
        return userPagedListResult.getNetworkStateLiveData();
    }

    /**
     * Action: update user Entity to Room db
     * @param userEntity
     */
    public void updateUser(UserEntity userEntity) {
        userRepository.updateUser(userEntity);
    }
}
