package com.example.stackoverflowuser.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.common.UserPagedListConfig;
import com.example.stackoverflowuser.model.UserPagedListResult;
import com.example.stackoverflowuser.model.business.UserPagedListBoundaryCallback;
import com.example.stackoverflowuser.repository.local.dao.UserDao;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;
import com.example.stackoverflowuser.repository.remote.RetrofitClient;
import com.example.stackoverflowuser.repository.remote.service.UserService;

public class UserRepositoryImpl implements UserRepository {

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
        LiveData<String> networkStateLiveData = userPagedListBoundaryCallback.getNetworkStateLiveData();

        // Config paged list load data
        PagedList.Config userPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(UserPagedListConfig.DATABASE_PAGE_SIZE)
                .setPrefetchDistance(UserPagedListConfig.PREFETCH_DISTANCE)
                .setEnablePlaceholders(true)
                .build();

        // Get the paged list
        LiveData<PagedList<UserEntity>> userPagedListLiveData = new LivePagedListBuilder<> (
                dataSourceFactory, userPagedListConfig)
                .setBoundaryCallback(userPagedListBoundaryCallback)
                .build();

        return new UserPagedListResult(userPagedListLiveData, networkStateLiveData);
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                userDao.updateUser(userEntity);
            }
        }).start();
    }
}
