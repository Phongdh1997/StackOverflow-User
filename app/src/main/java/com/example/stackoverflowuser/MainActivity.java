package com.example.stackoverflowuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.stackoverflowuser.adapter.UserPagedListAdapter;
import com.example.stackoverflowuser.repository.local.entity.UserEntity;
import com.example.stackoverflowuser.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcUserList;
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

        adapter.getItemClickedLiveData().observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                Toast.makeText(MainActivity.this, userEntity.getDisplayName(), Toast.LENGTH_SHORT).show();
            }
        });
        adapter.getBookmarkedClickedLiveData().observe(this, new Observer<UserEntity>() {
            @Override
            public void onChanged(UserEntity userEntity) {
                Toast.makeText(MainActivity.this, "Bookmarked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addEvent() {

    }

    private void addViewModelObsever() {

    }
}
