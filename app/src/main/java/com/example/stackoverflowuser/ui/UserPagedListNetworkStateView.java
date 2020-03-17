package com.example.stackoverflowuser.ui;

import com.example.stackoverflowuser.viewmodel.UserViewModel;

public class UserPagedListNetworkStateView extends NetworkStateView {
    private UserViewModel userViewModel;

    public UserPagedListNetworkStateView(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onNotHasMore() {

    }
}
