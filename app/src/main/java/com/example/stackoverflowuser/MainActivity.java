package com.example.stackoverflowuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import com.example.stackoverflowuser.adapter.UserPagedListAdapter;
import com.example.stackoverflowuser.data.local.entity.UserEntity;
import com.example.stackoverflowuser.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcUserList;
    private UserPagedListAdapter adapter;
    private UserViewModel userViewModel;
    private Switch swShowBookmarkedUser;

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
        LiveData<PagedList<UserEntity>> userPagedListLiveData = userViewModel.getUserPagedListLiveData();
        userPagedListLiveData.observe(this, adapter::submitList);
        rcUserList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcUserList.setAdapter(adapter);

        // switch button
        swShowBookmarkedUser = findViewById(R.id.swShowBookmarkedUser);
        swShowBookmarkedUser.setChecked(userViewModel.onLoadBookmarkedOption());
    }

    private void addEvent() {
        adapter.getItemClickedLiveData().observe(this, userEntity -> Toast.makeText(MainActivity.this, userEntity.getDisplayName(), Toast.LENGTH_SHORT).show());
        adapter.getBookmarkedClickedLiveData().observe(this, userEntity -> {
            userEntity.setBookmarked(true);
            userViewModel.onUpdateUser(userEntity);
        });
        swShowBookmarkedUser.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userViewModel.onBookmarkedOptionChange(isChecked);
        });
    }

    private void addViewModelObsever() {
        userViewModel.getBookmarkedOptionLiveData().observe(this, bookmarkedOption -> {
            Toast.makeText(MainActivity.this, "bookmarked " + bookmarkedOption.isBookmarked(), Toast.LENGTH_SHORT).show();
        });
    }
}
