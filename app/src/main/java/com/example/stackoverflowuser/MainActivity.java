package com.example.stackoverflowuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.stackoverflowuser.adapter.UserPagedListAdapter;
import com.example.stackoverflowuser.adapter.UserRecyclerViewAdapter;
import com.example.stackoverflowuser.model.User;
import com.example.stackoverflowuser.model.UserPagedListResult;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;
import com.example.stackoverflowuser.viewmodel.UserViewModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcUserList;
    private UserRecyclerViewAdapter userRvAdapter;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViewModel();
        initView();
        addEvent();
        addViewModelObsever();
    }

    private void getViewModel() {
        userViewModel = ViewModelProvider.AndroidViewModelFactory
                .getInstance(getApplication())
                .create(UserViewModel.class);
    }

    private void initView() {
        rcUserList = findViewById(R.id.rcUserList);
        rcUserList.setHasFixedSize(true);
        UserPagedListAdapter adapter = new UserPagedListAdapter();
        LiveData<PagedList<UserEntity>> userPagedListLiveData = userViewModel.getUserPagedListLiveData();
        userPagedListLiveData.observe(this, adapter::submitList);
        rcUserList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcUserList.setAdapter(adapter);
    }

    private void addEvent() {

    }

    private void addViewModelObsever() {

    }
}
