package com.example.stackoverflowuser.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.annotation.UserLoadType;
import com.example.stackoverflowuser.common.UserPagedListConfig;
import com.example.stackoverflowuser.model.PagedListResult;
import com.example.stackoverflowuser.model.UserPagedListBoundaryCallback;
import com.example.stackoverflowuser.data.local.dao.UserDao;
import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.data.remote.RetrofitClient;

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
     * @return: PagedListResult
     */
    @Override
    public PagedListResult<UserEntity> loadUsers(@UserLoadType String loadType) {
        if (loadType.equals(UserLoadType.BOOKMARKED_USER)) {
            return loadBookmarkedUsers();
        } else {
            return loadAllOfUsers();
        }
    }

    private PagedListResult<UserEntity> loadBookmarkedUsers() {
        // Config paged list load data
        PagedList.Config userPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(UserPagedListConfig.DATABASE_PAGE_SIZE)
                .setPrefetchDistance(UserPagedListConfig.PREFETCH_DISTANCE)
                .setEnablePlaceholders(true)
                .build();

        DataSource.Factory<Integer, UserEntity> dataSource = userDao.getBookmarkedUsers();

        LiveData<PagedList<UserEntity>> userPagedListLiveData = new LivePagedListBuilder<>(
                dataSource, userPagedListConfig).build();
        return new PagedListResult<>(userPagedListLiveData, new MutableLiveData<>());
    }

    private PagedListResult<UserEntity> loadAllOfUsers() {
        // Config paged list load data
        PagedList.Config userPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(UserPagedListConfig.DATABASE_PAGE_SIZE)
                .setPrefetchDistance(UserPagedListConfig.PREFETCH_DISTANCE)
                .setEnablePlaceholders(true)
                .build();

        // get data source
        DataSource.Factory<Integer, UserEntity> dataSource = userDao.getUsers();

        // setup boundary callback
        UserPagedListBoundaryCallback userPagedListBoundaryCallback =
                new UserPagedListBoundaryCallback(RetrofitClient.getUserService(), userDao);
        LiveData<String> networkStateLiveData = userPagedListBoundaryCallback.getNetworkStateLiveData();

        LiveData<PagedList<UserEntity>> userPagedListLiveData = new LivePagedListBuilder<>(
                dataSource, userPagedListConfig)
                .setBoundaryCallback(userPagedListBoundaryCallback)
                .build();
        return new PagedListResult<>(userPagedListLiveData, networkStateLiveData);
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
