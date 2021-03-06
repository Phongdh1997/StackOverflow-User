package com.example.stackoverflowuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Switch;

import com.example.stackoverflowuser.adapter.UserPagedListAdapter;
import com.example.stackoverflowuser.annotation.UserLoadType;
import com.example.stackoverflowuser.listener.EndlessScrollListener;
import com.example.stackoverflowuser.ui.DetailUserInfoActivity;
import com.example.stackoverflowuser.ui.LoadingNetworkStateView;
import com.example.stackoverflowuser.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcUserList;
    private UserPagedListAdapter adapter;
    private UserViewModel userViewModel;
    private Switch swShowBookmarkedUser;
    private LoadingNetworkStateView networkStateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewModel();
        initView();
        addEvent();
        addViewModelObsever();
    }

    @Override
    protected void onStop() {
        super.onStop();
        userViewModel.onSaveBookmarkedOption();
        userViewModel.onSaveLastPageIndex();
    }

    private void getViewModel() {
        userViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(UserViewModel.class);
    }

    private void initView() {
        rcUserList = findViewById(R.id.rcUserList);
        rcUserList.setHasFixedSize(true);
        adapter = new UserPagedListAdapter();
        rcUserList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcUserList.addItemDecoration(
                new DividerItemDecoration(rcUserList.getContext(), DividerItemDecoration.VERTICAL));
        rcUserList.setAdapter(adapter);

        // switch button
        swShowBookmarkedUser = findViewById(R.id.swShowBookmarkedUser);
        boolean isBookmarked = userViewModel.onLoadBookmarkedOption();
        swShowBookmarkedUser.setChecked(isBookmarked);
        setPagedListObserver(isBookmarked);

        userViewModel.onLoadLastPageIndex();    // load last page index
        networkStateView = new LoadingNetworkStateView(findViewById(R.id.pbUserListLoading));
    }

    private void addEvent() {
        adapter.getItemClickedLiveData().observe(this, userEntity -> {
            Intent intent = new Intent(MainActivity.this, DetailUserInfoActivity.class);
            intent.putExtra(DetailUserInfoActivity.USER_EXTRA_INTENT_FIELD, userEntity);
            startActivity(intent);
        });
        adapter.getBookmarkedClickedLiveData().observe(this, userEntity -> {
            userViewModel.onUpdateUser(userEntity);
        });
        swShowBookmarkedUser.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userViewModel.onBookmarkedOptionChange(isChecked);
        });
        rcUserList.addOnScrollListener(new EndlessScrollListener() {
            @Override
            public void loadMore() {
                userViewModel.onInvalidateUserPagedDataSource();
            }
        });
    }

    private void addViewModelObsever() {
        userViewModel.getBookmarkedOptionLiveData().observe(this, bookmarkedOption -> {
            setPagedListObserver(bookmarkedOption.isBookmarked());
        });
        userViewModel.getUserPagedListNetworkStateLiveData().observe(this, s -> {
            networkStateView.handleState(s);
        });
    }

    private void setPagedListObserver(boolean isBookmarked) {
        if (isBookmarked) {
            userViewModel.getUserPagedListLiveData(UserLoadType.ALL_USER).removeObservers(this);
            userViewModel.getUserPagedListLiveData(UserLoadType.BOOKMARKED_USER).observe(this, adapter::submitList);
        } else {
            userViewModel.getUserPagedListLiveData(UserLoadType.BOOKMARKED_USER).removeObservers(this);
            userViewModel.getUserPagedListLiveData(UserLoadType.ALL_USER).observe(this, adapter::submitList);
        }

    }
}
