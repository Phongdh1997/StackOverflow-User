package com.example.stackoverflowuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Switch;
import android.widget.Toast;

import com.example.stackoverflowuser.adapter.UserPagedListAdapter;
import com.example.stackoverflowuser.annotation.UserLoadType;
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
        rcUserList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcUserList.setAdapter(adapter);

        // switch button
        swShowBookmarkedUser = findViewById(R.id.swShowBookmarkedUser);
        boolean isBookmarked = userViewModel.onLoadBookmarkedOption();
        swShowBookmarkedUser.setChecked(isBookmarked);
        setPagedListObserver(isBookmarked);
    }

    private void addEvent() {
        adapter.getItemClickedLiveData().observe(this, userEntity -> Toast.makeText(MainActivity.this, userEntity.getDisplayName(), Toast.LENGTH_SHORT).show());
        adapter.getBookmarkedClickedLiveData().observe(this, userEntity -> {
            userViewModel.onUpdateUser(userEntity);
        });
        swShowBookmarkedUser.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userViewModel.onBookmarkedOptionChange(isChecked);
        });
    }

    private void addViewModelObsever() {
        userViewModel.getBookmarkedOptionLiveData().observe(this, bookmarkedOption -> {
            setPagedListObserver(bookmarkedOption.isBookmarked());
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
