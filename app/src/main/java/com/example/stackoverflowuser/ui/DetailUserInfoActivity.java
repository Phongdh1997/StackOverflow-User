package com.example.stackoverflowuser.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.adapter.DetailUserInfoAdapter;
import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.listener.EndlessScrollListener;
import com.example.stackoverflowuser.viewmodel.DetailUserInfoViewModel;

public class DetailUserInfoActivity extends AppCompatActivity {
    public static final String USER_EXTRA_INTENT_FIELD = "user";

    private UserEntity user;
    private DetailUserInfoViewModel detailUserInfoViewModel;
    private RecyclerView rvDetailUserInfo;
    private DetailUserInfoAdapter detailUserInfoPagedListAdapter;
    private LoadingNetworkStateView networkStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_info);

        user = (UserEntity) getIntent().getSerializableExtra(USER_EXTRA_INTENT_FIELD);
        getViewModel();
        initView();
        addEvent();
        addViewModelObsever();
    }

    private void getViewModel() {
        detailUserInfoViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication()).create(DetailUserInfoViewModel.class);
    }

    private void initView() {
        rvDetailUserInfo = findViewById(R.id.rcUserDetailInfoList);
        rvDetailUserInfo.setHasFixedSize(true);
        detailUserInfoPagedListAdapter = new DetailUserInfoAdapter();
        rvDetailUserInfo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvDetailUserInfo.setAdapter(detailUserInfoPagedListAdapter);
        rvDetailUserInfo.addItemDecoration(
                new DividerItemDecoration(rvDetailUserInfo.getContext(), DividerItemDecoration.VERTICAL));

        networkStateView = new LoadingNetworkStateView(findViewById(R.id.pbDetailListLoading));
    }

    private void addEvent() {
        rvDetailUserInfo.addOnScrollListener(new EndlessScrollListener() {
            @Override
            public void loadMore() {
                detailUserInfoViewModel.onInvalidateDetailListDataSource();
            }
        });
    }

    private void addViewModelObsever() {
        if (user != null) {
            detailUserInfoViewModel.loadNewDetailInfoPagedListLiveData(user)
                    .observe(this, detailUserInfoPagedListAdapter::submitList);
            detailUserInfoViewModel.getDetailInfoNetworkStateLiveData()
                    .observe(this, s -> {
                        networkStateView.handleState(s);
                    });
        }
    }
}
