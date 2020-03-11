package com.example.stackoverflowuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcUserList;

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
    }

    private void addEvent() {

    }

    private void addViewModelObsever() {

    }
}
