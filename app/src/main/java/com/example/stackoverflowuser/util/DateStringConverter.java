package com.example.stackoverflowuser.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateStringConverter {
    private static DateFormat formatter = new SimpleDateFormat("MM/dd");

    public static String timeToDateString(long time) {
        try {
            Log.e("time", "" + time);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(time);
            return formatter.format(calendar.getTime());
        } catch (Exception e) {}
        return "";
    }
}
