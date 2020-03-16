package com.example.stackoverflowuser.common;

import android.content.Context;
import android.widget.Toast;

public class NetworkState {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String LOADING = "loading";
    public static final String NOT_HAS_MORE = "not_has_more";

    public static void toastState(Context context, String state) {
        switch (state) {
            case SUCCESS:
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                break;
            case LOADING:
                Toast.makeText(context, "Loading ...", Toast.LENGTH_SHORT).show();
                break;
            case ERROR:
                Toast.makeText(context, "Error ...", Toast.LENGTH_SHORT).show();
                break;
            case NOT_HAS_MORE:
                Toast.makeText(context, "Out of data", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}