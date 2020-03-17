package com.example.stackoverflowuser.ui;

import static com.example.stackoverflowuser.common.NetworkStateValue.ERROR;
import static com.example.stackoverflowuser.common.NetworkStateValue.LOADING;
import static com.example.stackoverflowuser.common.NetworkStateValue.NOT_HAS_MORE;
import static com.example.stackoverflowuser.common.NetworkStateValue.SUCCESS;

public abstract class NetworkStateView {
    public void handleState(String state) {
        switch (state) {
            case SUCCESS:
                onSuccess();
                break;
            case LOADING:
                onLoading();
                break;
            case ERROR:
                onError();
                break;
            case NOT_HAS_MORE:
                onNotHasMore();
                break;
        }
    }

    public abstract void onSuccess();
    public abstract void onLoading();
    public abstract void onError();
    public abstract void onNotHasMore();
}