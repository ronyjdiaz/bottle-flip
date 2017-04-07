package com.slashmobility.bottleflip_android.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Edgar-W10 on 5/4/2017.
 */

public class PreferenceManager {
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "help_welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch";

    public PreferenceManager(Context context) {
        this.mContext = context;
        mPref = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mPref.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        mEditor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        mEditor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return mPref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
