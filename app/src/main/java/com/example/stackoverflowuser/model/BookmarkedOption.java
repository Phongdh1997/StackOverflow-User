package com.example.stackoverflowuser.model;

import android.content.Context;

import com.example.stackoverflowuser.util.SharedReferencesUtil;

public class BookmarkedOption {
    private boolean isBookmarked;

    public BookmarkedOption(boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }

    public void save(Context context) {
        SharedReferencesUtil.saveBookmarkedOption(context, isBookmarked);
    }

    public void load(Context context) {
        isBookmarked = SharedReferencesUtil.loadBookmarkedOption(context);
    }

    public boolean isBookmarked() {
        return isBookmarked;
    }

    public void setBookmarked(boolean bookmarked) {
        isBookmarked = bookmarked;
    }
}
