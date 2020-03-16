package com.example.stackoverflowuser.ui.detail_info;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.stackoverflowuser.R;
import com.example.stackoverflowuser.data.local.entity.UserEntity;

public class DetailUserInfoActivity extends AppCompatActivity {
    public static final String USER_EXTRA_INTENT_FIELD = "user";

    private UserEntity user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user_info);

        getViewModel();
        initView();
        addEvent();
        addViewModelObsever();
    }

    private void getViewModel() {

    }

    private void initView() {

    }

    private void addEvent() {

    }

    private void addViewModelObsever() {

    }
}
