package com.example.stackoverflowuser.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedReferencesUtil {
    private static final String SHARED_REF_NAME = "app_shared_ref";

    private static final String BOOK_MARKED_OPTION_FIELD = "bookmarked_field";

    public static void saveBookmarkedOption(Context context, boolean value) {
        Log.e("save ", "book " + value);
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_REF_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(BOOK_MARKED_OPTION_FIELD, value);
        editor.apply();
    }

    public static boolean loadBookmarkedOption(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_REF_NAME, Context.MODE_PRIVATE);
        Log.e("load ", "book " + sharedPreferences.getBoolean(BOOK_MARKED_OPTION_FIELD, false));
        return sharedPreferences.getBoolean(BOOK_MARKED_OPTION_FIELD, false);
    }
}
