package com.example.stackoverflowuser.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class BookmarkSharedReferenceUtil extends SharedReferencesUtil {
    private static final String BOOK_MARKED_OPTION_FIELD = "bookmarked_field";

    public static void saveBookmarkedOption(Context context, boolean value) {
        SharedPreferences.Editor editor = getSharedReferenceEditor(context);
        editor.putBoolean(BOOK_MARKED_OPTION_FIELD, value);
        editor.apply();
    }

    public static boolean loadBookmarkedOption(Context context) {
        return getSharedReference(context).getBoolean(BOOK_MARKED_OPTION_FIELD, false);
    }
}
