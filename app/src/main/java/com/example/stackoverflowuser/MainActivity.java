package com.example.stackoverflowuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.stackoverflowuser.adapter.UserRecyclerViewAdapter;
import com.example.stackoverflowuser.model.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcUserList;
    private UserRecyclerViewAdapter userRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        addEvent();
        addViewModelObsever();
    }

    private void initView() {
        rcUserList = findViewById(R.id.rcUserList);
        rcUserList.setHasFixedSize(true);
        userRvAdapter = new UserRecyclerViewAdapter(new ArrayList<User>());
        rcUserList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcUserList.setAdapter(userRvAdapter);
    }

    private void addEvent() {

    }

    private void addViewModelObsever() {

    }
}
