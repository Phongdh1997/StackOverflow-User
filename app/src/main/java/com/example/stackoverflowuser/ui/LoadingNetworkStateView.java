package com.example.stackoverflowuser.ui;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoadingNetworkStateView extends NetworkStateView {
    private ProgressBar pbUserListLoading;

    public LoadingNetworkStateView(ProgressBar pbUserListLoading) {
        this.pbUserListLoading = pbUserListLoading;
        this.pbUserListLoading.setVisibility(View.GONE);
    }

    @Override
    protected void onSuccess() {
        pbUserListLoading.setVisibility(View.GONE);
    }

    @Override
    protected void onLoading() {
        pbUserListLoading.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onError() {
        Toast.makeText(pbUserListLoading.getContext(),
                "Something wrong with your network",
                Toast.LENGTH_SHORT).show();
        pbUserListLoading.setVisibility(View.GONE);
    }

    @Override
    protected void onNotHasMore() {
        Toast.makeText(pbUserListLoading.getContext(),
                "Out of data from server",
                Toast.LENGTH_SHORT).show();
        this.pbUserListLoading.setVisibility(View.GONE);
    }
}
