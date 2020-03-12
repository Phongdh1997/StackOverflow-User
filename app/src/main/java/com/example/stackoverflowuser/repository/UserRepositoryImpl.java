package com.example.stackoverflowuser.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.model.UserPagedListResult;
import com.example.stackoverflowuser.model.business.UserPagedListBoundaryCallback;
import com.example.stackoverflowuser.repository.local.dao.UserDao;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;
import com.example.stackoverflowuser.repository.remote.RetrofitClient;
import com.example.stackoverflowuser.repository.remote.service.UserService;

public class UserRepositoryImpl implements UserRepository {

    private static final int DATABASE_PAGE_SIZE = 20;
    private UserDao userDao;

    public UserRepositoryImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Build and configure a paged list
     * Use RoomDB DataSource
     * Custom BoundaryCallback to load data from internet
     *
     * @return: UserPagedListResult
     */
    @Override
    public UserPagedListResult loadUsers() {
        DataSource.Factory<Integer, UserEntity> dataSourceFactory = userDao.getUsers();

        // instance UserPagedListBoundaryCallback
        UserService userService = RetrofitClient.getUserService();
        UserPagedListBoundaryCallback userPagedListBoundaryCallback = new UserPagedListBoundaryCallback(userService, userDao);
        LiveData<Boolean> netWorkError = userPagedListBoundaryCallback.getNetworkError();

        // Get the paged list
        LiveData<PagedList<UserEntity>> userPagedListLiveData = new LivePagedListBuilder<> (
                dataSourceFactory, DATABASE_PAGE_SIZE)
                .setBoundaryCallback(userPagedListBoundaryCallback)
                .build();

        return new UserPagedListResult(userPagedListLiveData, netWorkError);
    }
}
