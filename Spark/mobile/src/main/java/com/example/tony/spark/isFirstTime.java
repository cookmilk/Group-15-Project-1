package com.example.yshen.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yshen on 4/29/2016.
 */
public class isFirstTime {
    public static final String PREFS_NAME = "SparkPrefsFile";

    public isFirstTime() {
    }

    public boolean checkIt(Context context) {
        SharedPreferences firstTime = context.getSharedPreferences(PREFS_NAME, 0);
        boolean res = firstTime.getBoolean("first_time", false);

        if (!res) {
            SharedPreferences.Editor editor = firstTime.edit();
            editor.putBoolean("first_time", true);
            editor.commit();
        }

        return !res;
    }
}
