package com.example.stackoverflowuser.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.stackoverflowuser.common.UserPagedListConfig;
import com.example.stackoverflowuser.data.local.entity.DetailUserInfoEntity;
import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.data.remote.RetrofitClient;
import com.example.stackoverflowuser.data.remote.model.ReputationDetailItem;
import com.example.stackoverflowuser.data.remote.service.DetailUserInfoService;
import com.example.stackoverflowuser.repository.datasource.DetailUserInfoDataSource;
import com.example.stackoverflowuser.model.PagedListResult;

public class DetailUserInfoRepositoryImpl implements DetailUserInfoRepository {
    private DetailUserInfoService detailUserInfoService;

    public DetailUserInfoRepositoryImpl() {
        detailUserInfoService = RetrofitClient.getDetailUserInfoService();
    }

    @Override
    public PagedListResult<ReputationDetailItem> loadDetailUserInfos(UserEntity user) {
        // Config paged list load data
        PagedList.Config userPagedListConfig = new PagedList.Config.Builder()
                .setPageSize(UserPagedListConfig.DATABASE_PAGE_SIZE)
                .setPrefetchDistance(UserPagedListConfig.PREFETCH_DISTANCE)
                .setEnablePlaceholders(true)
                .build();

        DataSource.Factory<Integer, ReputationDetailItem> dataSource =
                DetailUserInfoDataSource.getDataSourceFactory(detailUserInfoService, user);

        LiveData<PagedList<ReputationDetailItem>> userPagedListLiveData = new LivePagedListBuilder<>(
                dataSource, userPagedListConfig).build();
        return new PagedListResult<>(userPagedListLiveData, new MutableLiveData<>());
    }

    @Override
    public void updateUser(DetailUserInfoEntity detailUserInfoEntity) {

    }
}
