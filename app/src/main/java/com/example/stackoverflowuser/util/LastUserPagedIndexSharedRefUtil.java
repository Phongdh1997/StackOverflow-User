package com.example.stackoverflowuser.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class LastUserPagedIndexSharedRefUtil extends SharedReferencesUtil {
    private static final String PAGED_INDEX_FIELD = "paged_index";

    public static void savePagedIndex(Context context, int index) {
        Log.e("save", "" + index);
        SharedPreferences.Editor editor = getSharedReferenceEditor(context);
        editor.putInt(PAGED_INDEX_FIELD, index);
        editor.apply();
    }

    public static int loadPagedIndex(Context context) {
        Log.e("Load index", getSharedReference(context).getInt(PAGED_INDEX_FIELD, 1)+ "");
        return getSharedReference(context).getInt(PAGED_INDEX_FIELD, 1);
    }
}
