package com.example.stackoverflowuser.listener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == RecyclerView.SCROLL_STATE_SETTLING &&
                recyclerView.getLayoutManager() != null &&
                recyclerView.getAdapter() != null) {
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            int lastVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            int totalItem = recyclerView.getAdapter().getItemCount();
            if (lastVisibleItemPosition == totalItem - 1) {
                loadMore();
            }
        }
    }

    public abstract void loadMore();
}
